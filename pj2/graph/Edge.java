package graph;
import dataStructures.*;

public class Edge extends VertexPair
{
    int weight;
    DListNode node;
    
    protected Edge(Object a, Object b, int w)
    {
        super(a, b);
        weight = w;
        node = null;
    }
    
    Object object1()
    {
        return object1;
    }
    
    Object object2()
    {
        return object2;
    }
    
    int weight()
    {
        return weight;
    }
    
    public String toString()
    {
        return "( "+object1.toString()+", "+object2.toString()+", w:"+weight+" )";
    }

}
