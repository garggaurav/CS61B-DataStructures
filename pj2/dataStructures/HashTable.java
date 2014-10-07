package dataStructures;
public class HashTable
{
    DList[] table;
    int size;
    int capacity;
    public HashTable()
    {
        table = new DList[103];
        capacity = 103;
        size = 0;
    }

    public HashTable(int n)
    {
        n = nextPrime(n);
        table = new DList[n];
        capacity = n;
        size = 0;
    }

    public void insert(Object key, Object value)
    {
        if(find(key)==null)
        {
            Entry insert = new Entry(key, value);
            if(table[compFunction(key.hashCode())]==null)
                table[compFunction(key.hashCode())] = new DList();
            table[compFunction(key.hashCode())].add(insert);
            size++;
        }
    }

    //Compression Function
    private int compFunction(int code)
    {
        return Math.abs(((127*code + 65993) % 1999993) % capacity);
    }

    public Object find(Object key)
    {
        if(key==null)
            return null;
        DList list = table[compFunction(key.hashCode())];
        if(list==null)
            return null;
        Object[] listArr = list.toArr();
        for(int i=0; i<listArr.length; i++)
        {
            if(((Entry)listArr[i]).key().equals(key))
            {
                return ((Entry)listArr[i]).value();
            }
        }
        return null;
    }

    public boolean isPresent(Object key)
    {
        if(table[compFunction(key.hashCode())]==null)
        {
            return false;
        }
        else
        {
            DList list = table[compFunction(key.hashCode())];
            if(list.find(new Entry(key, null))==null)
            {
                return false;
            }
            return true;
        }
    }

    public Object remove(Object key)
    {

        DList list = table[compFunction(key.hashCode())];
        size--;
        return ((Entry)list.delete(new Entry(key, null)).item).value();

    }

    public int size()
    {
        return size;
    }

    public boolean isEmpty()
    {
        return size==0;
    }

    private int nextPrime(int n)
    {
        while(!isPrime(n))
        {
            n+=1;
        }
        return n;
    }

    private boolean isPrime(int n)
    {
        for(int i=2; i<n; i++)
            if(n%i == 0)
                return false;
        return true;
    }
}
