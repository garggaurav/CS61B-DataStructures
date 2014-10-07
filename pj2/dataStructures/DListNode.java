package dataStructures;

public class DListNode
{
    Object item;
    DListNode next;
    DListNode prev;
    
    DListNode()
    {
        this.item = null;
        this.next = null;
        this.prev = null;
    }

    DListNode(Object item)
    {
        this.item = item;
        this.next = null;
        this.prev = null;
    }

    DListNode(Object item, DListNode prev, DListNode next)
    {
        this.item = item;
        this.next = next;
        this.prev = prev;
    }
    
    public Object item()
    {
        return item;
    }
    
    public DListNode next()
    {
        return next;
    }
    
    public DListNode prev()
    {
        return prev;
    }
    
    public String toString()
    {
        return item.toString();
    }

}
