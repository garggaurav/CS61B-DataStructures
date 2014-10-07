/* Kruskal.java */

package graphalg;

import dataStructures.*;
import graph.*;
import set.*;

/**
 * The Kruskal class contains the method minSpanTree(), which implements
 * Kruskal's algorithm for computing a minimum spanning tree of a graph.
 */

public class Kruskal {

    /**
     * minSpanTree() returns a WUGraph that represents the minimum spanning tree
     * of the WUGraph g.  The original WUGraph g is NOT changed.
     *
     * @param g The weighted, undirected graph whose MST we want to compute.
     * @return A newly constructed WUGraph representing the MST of g.
     */
    static WUGraph T;
    public static WUGraph minSpanTree(WUGraph g)
    {

        T = new WUGraph();
        Object[] vertices = g.getVertices();
        DList edges = new DList();

        for(int i=0; i<vertices.length; i++)
        {
            T.addVertex(vertices[i]);
        }

        for(int i=0; i<vertices.length; i++)
        {
            Neighbors n = g.getNeighbors(vertices[i]);
            //System.out.println(n==null);
            for(int j=0; j<n.neighborList.length; j++)
            {
                Edge e = new Edge(vertices[i], vertices[j], n.weightList[j]);
                edges.add(e);
            }
        }

        edges = Sort(edges);
        DListNode current = edges.head();
        DisjointSets ds = new DisjointSets(vertices.length);
        for(int i=0; i<edges.size(); i++)
        {
            Object u = ((Edge)current.item()).object1();
            Object v = ((Edge)current.item()).object2();
            int w = ((Edge)current.item()).weight();
            if(!ds.find((int)u).equals(ds.find((in)v)))
            {
                ds = ds.union();
            }
        }

        return T;
    }

    private static DList Sort(DList dl)
    {
        Object[] arr = sortArr(dl.toArr());
        DList result = new DList();
        for(int i=0; i<arr.length; i++)
        {
            result.add(arr[i]);
        }
        return result;
    }

    public static Object[] sortArr(Object[] arr)
    {
        int S=0;
        for(int m=0; m<arr.length; m++)
        {
            Object small = arr[S];
            int index = S;
            for(int k=S; k<arr.length; k++)
            {
                if(((Comparable)arr[k]).compareTo(small)<0)
                {
                    small = arr[k];
                    index = k;
                }
            }

            Object temp = small;
            arr[index] = arr[S];
            arr[S] = temp;
            S++;
        }

        for(int i=0; i<arr.length; i++)
            System.out.println(arr[i]);
        return arr;
    }

}
