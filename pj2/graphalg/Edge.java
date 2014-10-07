package graphalg;
import dataStructures.*;

public class Edge implements Comparable
{
    Object object1;
    Object object2;
    int weight;

    Edge(Object a, Object b, int w)
    {
        object1 = a;
        object2 = b;
        weight = w;
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

    public boolean equals(Object o)
    {
        if (o instanceof Edge) {
            return ((object1.equals(((Edge) o).object1)) &&
                (object2.equals(((Edge) o).object2))) ||
            ((object1.equals(((Edge) o).object2)) &&
                (object2.equals(((Edge) o).object1)));
        } else {
            return false;
        }
    }
    
    public int compareTo(Object o)
    {
        if(!(o instanceof Edge))
            return Integer.MIN_VALUE;
        
        return weight - ((Edge) o).weight;
    }
    
    public String toString()
    {
        return "( "+object1.toString()+", "+object2.toString()+", w:"+weight+" )";
    }

}
