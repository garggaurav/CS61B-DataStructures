package graph;
import dataStructures.*;

/**
 * Write a description of class Vertex here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
class Vertex
{
    Object item;
    DList edges;
    DListNode node;
    
    protected Vertex(Object n)
    {
        item = n;
        edges = new DList();
        node = null;
    }
    
    void addEdge(Edge e)
    {
        edges.add(e);
    }
    
    void removeEdge(Edge e)
    {
        edges.delete(e);
    }
    
    int degree()
    {
        return edges.size();
    }
    
    public String toString()
    {
        String repr = "Item: "+item+" Edges: "+edges;
        return repr;
    }
   
}
