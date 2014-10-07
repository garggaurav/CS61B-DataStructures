public class Matrix
{
    pixel[][] arr;

    public Matrix(pixel[][] a)
    {
        arr = a;
    }

    public Matrix()
    {
        arr = new pixel[3][3];
    }
    
    //Scalar Multiplication of b with Matrix
    public Matrix product(int[][] b)
    {
        Matrix result = new Matrix();

        for(int i=0; i<3; i++)
        {
            for(int j=0; j<3; j++)
            {
                result.arr[i][j] = arr[i][j].multiply(b[i][j]);                
            }
        }
        return result;
    }
    
    public String toString()
    {
        String repr = "";
        for(int i=0; i<arr.length; i++)
        {
            for(int j=0; j<arr[0].length; j++)
            {
                repr = repr + " | " + arr[i][j];
            }
            repr = repr + "\n";
        }
        return repr;
    }
    
    //return sum of all the pixels in the Matrix
    public pixel sum()
    {
        pixel result = new pixel();

        for(int i=0; i<3; i++)
        {
            for(int j=0; j<3; j++)
            {
                result.red = (short)(result.red + arr[i][j].red);
                result.blue = (short)(result.blue + arr[i][j].blue);
                result.green = (short)(result.green + arr[i][j].green);
            }
        }
        return result;
    }
}