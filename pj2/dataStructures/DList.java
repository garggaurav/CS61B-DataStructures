package dataStructures;

public class DList
{
    private DListNode sentinel;
    private DListNode tail;
    private int size;

    public DList()
    {
        sentinel = new DListNode();
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        tail = sentinel;
        size = 0;
    }

    public DListNode head()
    {
        if(size==0)
            return null;

        return sentinel.next;
    }

    public int size()
    {
        return size;
    }

    public DListNode add(Object item)
    {
        DListNode insert = new DListNode(item, tail, sentinel);
        tail.next = insert;
        sentinel.prev = insert;
        tail = insert;
        size++;
        return insert;
    }

    public DListNode delete(Object item)
    {
        DListNode current = sentinel.next;
        while(current!=sentinel)
        {
            if(current.item.equals(item))
            {
                current.prev.next = current.next;
                current.next.prev = current.prev;
                size--;
                return current;
            }
            current = current.next;
        }
        return null;
    }
    
    public Object find(Object item)
    {
        DListNode current = sentinel.next;
        while(current!=sentinel)
        {
            if(current.item.equals(item))
            {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public Object delete(DListNode toDelete)
    {
        toDelete.prev.next = toDelete.next;
        toDelete.next.prev = toDelete.prev;
        size--;
        return toDelete;
    }

    public String toString()
    {
        DListNode current = sentinel.next;
        String repr = "[";
        while(current!=sentinel)
        {
            repr = repr +" "+ current.item;
            current = current.next;
        }
        repr = repr+" ]";
        return repr;
    }

    public Object[] toArr()
    {
        Object[] arr = new Object[size];
        DListNode current = sentinel.next;
        for(int i=0; i<size; i++)
        {
            arr[i] = current.item;
            current = current.next;
        }
        return arr;
    }
    
    public static void main(String[] args)
    {
        DList trial = new DList();
        System.out.println(trial.delete("h"));
        trial.add("a");
        trial.add("b");
        trial.add("c");
        System.out.println(trial);
        System.out.println(trial.size());
        System.out.println(trial.delete("b"));
        System.out.println(trial.size());
        System.out.println(trial);
        System.out.println(trial.toArr()[2]);
    }
}
