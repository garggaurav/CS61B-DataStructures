
public class pixel
{
    short red;
    short blue;
    short green;

    public pixel(int r, int b, int g)
    {
        red = (short) r;
        blue = (short) b;
        green = (short) g;
    }

    public pixel()
    {
        red=0;
        blue=0;
        green=0;
    }

    public pixel multiply(int x)
    {
        pixel res = new pixel();
        res.red = (short)(x*red);
        res.blue = (short)(x*blue);
        res.green = (short)(x*green);
        return res;

    }

    public int squareSum()
    {
        return red*red + blue*blue + green*green;
    }

    public String toString()
    {
        return red+", "+green+", "+blue;
    }

    public boolean equals(pixel x)
    {
        return red==x.red && blue==x.blue && green==x.green;
    }

}
