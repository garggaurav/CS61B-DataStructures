public class Node
{
    pixel item;
    int num;
    Node next;
    
    public Node()
    {
        item = new pixel();
        num = 0;
        next = null;
    }
    
    public Node(pixel x, int n)
    {
        num = n;
        item = x;
        next = null;
    }
    
    public String toString()
    {
        return item+" times "+num+" | ";
    }
    
    public boolean equals(Node x)
    {
        return item.equals(x.item);
    }
    
}
