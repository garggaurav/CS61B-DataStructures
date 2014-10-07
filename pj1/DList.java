public class DList
{
    Node head;
    Node tail;
    int size;

    public DList()
    {
        head = null;
        tail = null;
        size = 0;
    }

    public void insert(Node x)
    {
        if(size==0)
        {
            head = x;
            tail = x;
            size++;
        }
        else
        {
            tail.next = x;
            tail = x;
            size++;
        }
    }
    
    public String toString()
    {
        Node x = new Node();
        x = head;
        String repr = "";
        while(x!=null)
        {
            repr = repr + x.num+": "+x.item+" | ";
            x = x.next;
        }
        return repr;
    }
}
