/* WUGraph.java */

package graph;
import dataStructures.*;
/**
 * The WUGraph class represents a weighted, undirected graph.  Self-edges are
 * permitted.
 */

public class WUGraph {

    private HashTable vertexTable;
    private HashTable edgeTable;
    private DList vertexList;
    private DList edgeList;
    /**
     * WUGraph() constructs a graph having no vertices or edges.
     *
     * Running time:  O(1).
     */
    public WUGraph()
    {
        vertexList = new DList();
        vertexTable = new HashTable();
        edgeList = new DList();
        edgeTable = new HashTable();
    }

    /**
     * vertexCount() returns the number of vertices in the graph.
     *
     * Running time:  O(1).
     */
    public int vertexCount()
    {
        return vertexList.size();
    }

    /**
     * edgeCount() returns the total number of edges in the graph.
     *
     * Running time:  O(1).
     */
    public int edgeCount()
    {
        return edgeList.size();
    }

    /**
     * getVertices() returns an array containing all the objects that serve
     * as vertices of the graph.  The array's length is exactly equal to the
     * number of vertices.  If the graph has no vertices, the array has length
     * zero.
     *
     * (NOTE:  Do not return any internal data structure you use to represent
     * vertices!  Return only the same objects that were provided by the
     * calling application in calls to addVertex().)
     *
     * Running time:  O(|V|).
     */
    public Object[] getVertices()
    {
        Object[] vertexArr = vertexList.toArr();
        Object[] objectArr = new Object[vertexArr.length];

        for(int i=0; i<vertexArr.length; i++)
        {
            objectArr[i] = ((Vertex)vertexArr[i]).item;
        }
        return objectArr;
    }

    /**
     * addVertex() adds a vertex (with no incident edges) to the graph.
     * The vertex's "name" is the object provided as the parameter "vertex".
     * If this object is already a vertex of the graph, the graph is unchanged.
     *
     * Running time:  O(1).
     */
    public void addVertex(Object vertex)
    {
        if(!isVertex(vertex))
        {
            Vertex v = new Vertex(vertex);
            DListNode node = vertexList.add(v);
            v.node = node;
            vertexTable.insert(vertex, v);
        }
    }

    /**
     * removeVertex() removes a vertex from the graph.  All edges incident on the
     * deleted vertex are removed as well.  If the parameter "vertex" does not
     * represent a vertex of the graph, the graph is unchanged.
     *
     * Running time:  O(d), where d is the degree of "vertex".
     */
    public void removeVertex(Object vertex)
    {
        if(isVertex(vertex))
        {
            Vertex toRemove = findVertex(vertex);

            Object[] edgesArr = toRemove.edges.toArr();
            for(int i=0; i<edgesArr.length; i++)
            {
                //System.out.println(edgesArr[i]);
                removeEdge(((Edge)edgesArr[i]).object1(), ((Edge)edgesArr[i]).object2());   
            }
            vertexTable.remove(vertex);
            vertexList.delete(toRemove.node);
            //System.out.println(vertexList);
        }

    }

    /**
     * isVertex() returns true if the parameter "vertex" represents a vertex of
     * the graph.
     *
     * Running time:  O(1).
     */
    public boolean isVertex(Object vertex)
    {
        return vertexTable.isPresent(vertex);
    }

    private Vertex findVertex(Object u)
    {
        if(!isVertex(u))
            return null;
        else
            return (Vertex)vertexTable.find(u);
    }

    /**
     * degree() returns the degree of a vertex.  Self-edges add only one to the
     * degree of a vertex.  If the parameter "vertex" doesn't represent a vertex
     * of the graph, zero is returned.
     *
     * Running time:  O(1).
     */
    public int degree(Object vertex)
    {
        if(isVertex(vertex))
        {
            Vertex v = (Vertex)vertexTable.find(vertex);
            return v.degree();
        }
        return 0;
    }

    /**
     * getNeighbors() returns a new Neighbors object referencing two arrays.  The
     * Neighbors.neighborList array contains each object that is connected to the
     * input object by an edge.  The Neighbors.weightList array contains the
     * weights of the corresponding edges.  The length of both arrays is equal to
     * the number of edges incident on the input vertex.  If the vertex has
     * degree zero, or if the parameter "vertex" does not represent a vertex of
     * the graph, null is returned (instead of a Neighbors object).
     *
     * The returned Neighbors object, and the two arrays, are both newly created.
     * No previously existing Neighbors object or array is changed.
     *
     * (NOTE:  In the neighborList array, do not return any internal data
     * structure you use to represent vertices!  Return only the same objects
     * that were provided by the calling application in calls to addVertex().)
     *
     * Running time:  O(d), where d is the degree of "vertex".
     */
    public Neighbors getNeighbors(Object vertex)
    {
        Vertex v = findVertex(vertex);
        int d = v.edges.size();
        if(d==0)
            return null;
        Neighbors neighbor = new Neighbors();
        Object[] edgesArr = v.edges.toArr();
        neighbor.neighborList = new Object[d];
        neighbor.weightList = new int[d];

        for(int i=0; i<d; i++)
        {
            neighbor.weightList[i] = ((Edge)edgesArr[i]).weight();

            if(((Edge)edgesArr[i]).object1.equals(vertex))
                neighbor.neighborList[i] = ((Edge)edgesArr[i]).object2;

            if(((Edge)edgesArr[i]).object2.equals(vertex))
                neighbor.neighborList[i] = ((Edge)edgesArr[i]).object1;

        }
        return neighbor;
    }

    /**
     * addEdge() adds an edge (u, v) to the graph.  If either of the parameters
     * u and v does not represent a vertex of the graph, the graph is unchanged.
     * The edge is assigned a weight of "weight".  If the graph already contains
     * edge (u, v), the weight is updated to reflect the new value.  Self-edges
     * (where u == v) are allowed.
     *
     * Running time:  O(1).
     */
    public void addEdge(Object u, Object v, int weight)
    {
        //System.out.println(isVertex(u));
        //System.out.println(isVertex(v));
        // System.out.println(isEdge(u,v));

        if(isVertex(u) && isVertex(v) && !isEdge(u, v))
        {
            Edge e = new Edge(u, v, weight);
            DListNode node = edgeList.add(e);
            e.node = node;
            edgeTable.insert(new VertexPair(u, v), e);
            findVertex(u).edges.add(e);
            if(!u.equals(v))
                findVertex(v).edges.add(e);
            //System.out.println("addEdge: "+e);
        }

        if(isEdge(u, v))
        {
            findEdge(u,v).weight = weight;    
        }
    }

    /**
     * removeEdge() removes an edge (u, v) from the graph.  If either of the
     * parameters u and v does not represent a vertex of the graph, the graph
     * is unchanged.  If (u, v) is not an edge of the graph, the graph is
     * unchanged.
     *
     * Running time:  O(1).
     */
    public void removeEdge(Object u, Object v)
    {
        if(isEdge(u, v))
        {
            Edge toRemove = findEdge(u,v);
            findVertex(toRemove.object1).edges.delete(toRemove);
            if(!u.equals(v))
                findVertex(toRemove.object2).edges.delete(toRemove);
            edgeTable.remove(new VertexPair(u,v));
            edgeList.delete(toRemove.node);
        }
    }

    /**
     * isEdge() returns true if (u, v) is an edge of the graph.  Returns false
     * if (u, v) is not an edge (including the case where either of the
     * parameters u and v does not represent a vertex of the graph).
     *
     * Running time:  O(1).
     */
    public boolean isEdge(Object u, Object v)
    {
        return edgeTable.isPresent(new VertexPair(u, v));
    }

    private Edge findEdge(Object u, Object v)
    {
        if(!isEdge(u, v))
            return null;
        else
            return (Edge)edgeTable.find(new VertexPair(u,v));
    }

    /**
     * weight() returns the weight of (u, v).  Returns zero if (u, v) is not
     * an edge (including the case where either of the parameters u and v does
     * not represent a vertex of the graph).
     *
     * (NOTE:  A well-behaved application should try to avoid calling this
     * method for an edge that is not in the graph, and should certainly not
     * treat the result as if it actually represents an edge with weight zero.
     * However, some sort of default response is necessary for missing edges,
     * so we return zero.  An exception would be more appropriate, but also more
     * annoying.)
     *
     * Running time:  O(1).
     */
    public int weight(Object u, Object v)
    {
        if(!isEdge(u,v))
            return 0;
        return ((Edge)edgeTable.find(new VertexPair(u, v))).weight();
    }

}
