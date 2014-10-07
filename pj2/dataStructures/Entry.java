package dataStructures;

public class Entry
{
    Object key;
    Object value;
    
    Entry()
    {
        key = null;
        value = null;
    }
    
    Entry(Object k, Object v)
    {
        key = k;
        value = v;
    }
    
    Object value()
    {
        return value;
    }
    
    Object key()
    {
        return key;
    }
    
    public boolean equals(Object k)
    {
        if(!(k instanceof Entry))
            return false;
            
        if(key.equals(((Entry)k).key))
            return true;
        
        return false;
    }
}
