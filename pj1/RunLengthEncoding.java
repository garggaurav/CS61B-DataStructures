/* RunLengthEncoding.java */

/**
 *  The RunLengthEncoding class defines an object that run-length encodes
 *  a PixImage object.  Descriptions of the methods you must implement appear
 *  below.  They include constructors of the form
 *
 *      public RunLengthEncoding(int width, int height);
 *      public RunLengthEncoding(int width, int height, int[] red, int[] green,
 *                               int[] blue, int[] runLengths) {
 *      public RunLengthEncoding(PixImage image) {
 *
 *  that create a run-length encoding of a PixImage having the specified width
 *  and height.
 *
 *  The first constructor creates a run-length encoding of a PixImage in which
 *  every pixel is black.  The second constructor creates a run-length encoding
 *  for which the runs are provided as parameters.  The third constructor
 *  converts a PixImage object into a run-length encoding of that image.
 *
 *  See the README file accompanying this project for additional details.
 */

import java.util.Iterator;

public class RunLengthEncoding implements Iterable {

    /**
     *  Define any variables associated with a RunLengthEncoding object here.
     *  These variables MUST be private.
     */

    /**
     *  The following methods are required for Part II.
     */

    /**
     *  RunLengthEncoding() (with two parameters) constructs a run-length
     *  encoding of a black PixImage of the specified width and height, in which
     *  every pixel has red, green, and blue intensities of zero.
     *
     *  @param width the width of the image.
     *  @param height the height of the image.
     */
    private int w;
    private int h;
    private DList result;

    public RunLengthEncoding(int width, int height) {
        this.w = width;
        this.h = height;
        result = new DList();
        pixel black = new pixel();
        Node x = new Node(black, (width*height));
        result.insert(x);

    }

    /**
     *  RunLengthEncoding() (with six parameters) constructs a run-length
     *  encoding of a PixImage of the specified width and height.  The runs of
     *  the run-length encoding are taken from four input arrays of equal length.
     *  Run i has length runLengths[i] and RGB intensities red[i], green[i], and
     *  blue[i].
     *
     *  @param width the width of the image.
     *  @param height the height of the image.
     *  @param red is an array that specifies the red intensity of each run.
     *  @param green is an array that specifies the green intensity of each run.
     *  @param blue is an array that specifies the blue intensity of each run.
     *  @param runLengths is an array that specifies the length of each run.
     *
     *  NOTE:  All four input arrays should have the same length (not zero).
     *  All pixel intensities in the first three arrays should be in the range
     *  0...255.  The sum of all the elements of the runLengths array should be
     *  width * height.  (Feel free to quit with an error message if any of these
     *  conditions are not met--though we won't be testing that.)
     */

    public RunLengthEncoding(int width, int height, int[] red, int[] green,
    int[] blue, int[] runLengths) {

        this.w = width;
        this.h = height;
        int len = red.length;
        result = new DList();
        for(int i=0; i<len; i++)
        {
            pixel x = new pixel((short)red[i], (short)blue[i], (short)green[i]);
            Node elem = new Node(x, runLengths[i]);
            result.insert(elem);
        }
    }

    /**
     *  getWidth() returns the width of the image that this run-length encoding
     *  represents.
     *
     *  @return the width of the image that this run-length encoding represents.
     */

    public int getWidth() {
        // Replace the following line with your solution.
        return w;
    }

    /**
     *  getHeight() returns the height of the image that this run-length encoding
     *  represents.
     *
     *  @return the height of the image that this run-length encoding represents.
     */
    public int getHeight() {
        // Replace the following line with your solution.
        return h;
    }

    public DList getResult() {
        return result;
    }

    /**
     *  iterator() returns a newly created RunIterator that can iterate through
     *  the runs of this RunLengthEncoding.
     *
     *  @return a newly created RunIterator object set to the first run of this
     *  RunLengthEncoding.
     */
    public RunIterator iterator() {
        RunIterator run = new RunIterator(result);
        return run;
    }

    /**
     *  toPixImage() converts a run-length encoding of an image into a PixImage
     *  object.
     *
     *  @return the PixImage that this RunLengthEncoding encodes.
     */
    public PixImage toPixImage() {
        PixImage img = new PixImage(w, h);
        int width=0;
        int height=0;

        Node x = result.head;
        while(x!=null)
        {
            for(int i=0; i<x.num; i++)
            {
                img.setPixel(width, height, x.item);
                width++;
                if(width==w)
                {
                    width=0;
                    height++;
                }
            }
            x=x.next;
        }
        return img;
    }

    /**
     *  toString() returns a String representation of this RunLengthEncoding.
     *
     *  This method isn't required, but it should be very useful to you when
     *  you're debugging your code.  It's up to you how you represent
     *  a RunLengthEncoding as a String.
     *
     *  @return a String representation of this RunLengthEncoding.
     */
    public String toString() {
        String repr = "";
        Node x = result.head;
        while(x!=null)
        {
            repr = repr + x;
            x = x.next;
        }
        return repr;
    }

    /**
     *  The following methods are required for Part III.
     */

    /**
     *  RunLengthEncoding() (with one parameter) is a constructor that creates
     *  a run-length encoding of a specified PixImage.
     * 
     *  Note that you must encode the image in row-major format, i.e., the second
     *  pixel should be (1, 0) and not (0, 1).
     *
     *  @param image is the PixImage to run-length encode.
     */
    public RunLengthEncoding(PixImage image) {     
        this.w = image.getWidth();
        this.h = image.getHeight();
        result = new DList();
        int j=0;

        for(int i=0; i<w; )
        {
            if(j>=h)
                break;
            pixel x = image.getPic()[i][j];
            int size = 0;
            while(j<h && x.equals(image.getPic()[i][j]))
            {   
                size++;
                i++;
                if(i==w)
                {
                    i=0;
                    j++;
                }                           
            }

            Node elem = new Node(x, size);
            result.insert(elem);
        }

        check();
    }

    /**
     *  check() walks through the run-length encoding and prints an error message
     *  if two consecutive runs have the same RGB intensities, or if the sum of
     *  all run lengths does not equal the number of pixels in the image.
     */
    public void check() {
        int sum=0;
        RunIterator x = iterator();

        while(x.hasNext())
        {
            int[] arr = x.next();
            pixel pix = new pixel(arr[1], arr[3], arr[2]);
            sum = sum + arr[0];            
            if(x.hasNext())
            {
                int[] arr2 = x.next();
                pixel nextpix = new pixel(arr2[1], arr2[3], arr2[2]);
                sum = sum + arr2[0];
                if(pix.equals(nextpix))
                    System.out.println("Two Nodes are equal");
            }

        }

        if(sum!=w*h)
            System.out.println("Number of pixels don't match");

        if(sum<=0)
            System.out.println("Less than 1 run");

    }

    /**
     *  The following method is required for Part IV.
     */

    /**
     *  setPixel() modifies this run-length encoding so that the specified color
     *  is stored at the given (x, y) coordinates.  The old pixel value at that
     *  coordinate should be overwritten and all others should remain the same.
     *  The updated run-length encoding should be compressed as much as possible;
     *  there should not be two consecutive runs with exactly the same RGB color.
     *
     *  @param x the x-coordinate of the pixel to modify.
     *  @param y the y-coordinate of the pixel to modify.
     *  @param red the new red intensity to store at coordinate (x, y).
     *  @param green the new green intensity to store at coordinate (x, y).
     *  @param blue the new blue intensity to store at coordinate (x, y).
     */
    public void setPixel(int x, int y, short red, short green, short blue) {
        /* Basic Algorithm - First add the new Node to the DList and adjust the 
         * size of the nodes so the total number of pixels remain constant. This 
         * may cause some node sizes to become 0. This may also cause repeated Nodes.
         * Iterate through the List and remove all nodes with size 0.
         * Iterate through the List and merge repetetive nodes.
         * Runtime = O(nlog(n))
         */
        int pos = w*y + x;
        pixel toInsert = new pixel(red, blue, green);
        Node curr = null;
        int prevs = 0;
        int sum=0;
        while(sum<pos)
        {
            if(curr==null)
                curr=result.head;
            else
                curr = curr.next;
            prevs = sum;
            sum = sum+curr.num;
        }

        if(sum==pos)
        {
            Node nw = new Node(toInsert, 1);
            if(curr==null)
            {
                nw.next = result.head;
                result.head.num--;
                result.head = nw;
            }
            else
            {
                if(curr.next==null)
                {
                    curr.num--;
                    curr.next = nw;
                    nw.next = null;
                }
                else
                {
                    curr.next.num--;
                    nw.next = curr.next;                    
                    curr.next = nw;
                }
            }
        }

        else
        {
            Node N = new Node(toInsert, 1);
            int prevnum = pos-prevs;
            int nextnum = curr.num-prevnum-1;

            curr.num = prevnum;
            Node afterN = new Node(curr.item, nextnum);
            afterN.next = curr.next;
            N.next = afterN;
            curr.next = N;
        }

        Node flatten = result.head;
        Node beforeFlatten = null;
        while(flatten.next!=null)
        {  
            if(flatten.num==0)
            {
                if(flatten==result.head)      
                    result.head = flatten.next;

                else
                    beforeFlatten.next = flatten.next;
            }
            beforeFlatten = flatten;
            flatten = flatten.next;
        }
        Node flat = result.head;
        Node flatNext = flat.next;

        while(flatNext!=null)
        {
            while(flatNext!=null && flat.equals(flatNext))
            {
                flat.num+=flatNext.num;
                flat.next = flatNext.next;
                flatNext = flat.next;
            }

            if(flat!=null && flatNext!=null)
            {
                flat = flat.next;
                flatNext = flatNext.next;
            }
        }
        check();
    }

    /**
     * TEST CODE:  YOU DO NOT NEED TO FILL IN ANY METHODS BELOW THIS POINT.
     * You are welcome to add tests, though.  Methods below this point will not
     * be tested.  This is not the autograder, which will be provided separately.
     */

    /**
     * doTest() checks whether the condition is true and prints the given error
     * message if it is not.
     *
     * @param b the condition to check.
     * @param msg the error message to print if the condition is false.
     */
    private static void doTest(boolean b, String msg) {
        if (b) {
            System.out.println("Good.");
        } else {
            System.err.println(msg);
        }
    }

    /**
     * array2PixImage() converts a 2D array of grayscale intensities to
     * a grayscale PixImage.
     *
     * @param pixels a 2D array of grayscale intensities in the range 0...255.
     * @return a new PixImage whose red, green, and blue values are equal to
     * the input grayscale intensities.
     */
    private static PixImage array2PixImage(int[][] pixels) {
        int width = pixels.length;
        int height = pixels[0].length;
        PixImage img = new PixImage(width, height);

        for(int i=0; i<width; i++)
        {
            for(int j=0; j<height; j++)
            {
                short pix = (short)(pixels[i][j]);
                img.setPixel(i, j, pix, pix, pix);
            }
        }

        return img;
    }

    /**
     * setAndCheckRLE() sets the given coordinate in the given run-length
     * encoding to the given value and then checks whether the resulting
     * run-length encoding is correct.
     *
     * @param rle the run-length encoding to modify.
     * @param x the x-coordinate to set.
     * @param y the y-coordinate to set.
     * @param intensity the grayscale intensity to assign to pixel (x, y).
     */
    private static void setAndCheckRLE(RunLengthEncoding rle,
    int x, int y, int intensity) {
        rle.setPixel(x, y,
            (short) intensity, (short) intensity, (short) intensity);
        rle.check();
    }

    /**
     * main() runs a series of tests of the run-length encoding code.
     */
    public static void main(String[] args) {
        // Be forwarned that when you write arrays directly in Java as below,
        // each "row" of text is a column of your image--the numbers get
        // transposed.
        PixImage image1 = array2PixImage(new int[][] { { 0, 3, 6 },
                    { 1, 4, 7 },
                    { 2, 5, 8 } });

        System.out.println("Testing one-parameter RunLengthEncoding constuctor " +
            "on a 3x3 image.  Input image:");
        System.out.print(image1);
        RunLengthEncoding rle1 = new RunLengthEncoding(image1);
        rle1.check();
        System.out.println("Testing getWidth/getHeight on a 3x3 encoding.");
        doTest(rle1.getWidth() == 3 && rle1.getHeight() == 3,
            "RLE1 has wrong dimensions");

        System.out.println("Testing toPixImage() on a 3x3 encoding.");
        doTest(image1.equals(rle1.toPixImage()),
            "image1 -> RLE1 -> image does not reconstruct the original image");

        System.out.println("Testing setPixel() on a 3x3 encoding.");
        setAndCheckRLE(rle1, 0, 0, 42);
        image1.setPixel(0, 0, (short) 42, (short) 42, (short) 42);
        doTest(rle1.toPixImage().equals(image1),
            /*
            array2PixImage(new int[][] { { 42, 3, 6 },
            { 1, 4, 7 },
            { 2, 5, 8 } })),
             */
            "Setting RLE1[0][0] = 42 fails.");

        System.out.println("Testing setPixel() on a 3x3 encoding.");
        setAndCheckRLE(rle1, 1, 0, 42);
        image1.setPixel(1, 0, (short) 42, (short) 42, (short) 42);
        doTest(rle1.toPixImage().equals(image1),
            "Setting RLE1[1][0] = 42 fails.");

        System.out.println("Testing setPixel() on a 3x3 encoding.");
        setAndCheckRLE(rle1, 0, 1, 2);
        image1.setPixel(0, 1, (short) 2, (short) 2, (short) 2);
        doTest(rle1.toPixImage().equals(image1),
            "Setting RLE1[0][1] = 2 fails.");
        System.out.println("Testing setPixel() on a 3x3 encoding.");
        setAndCheckRLE(rle1, 0, 0, 0);
        image1.setPixel(0, 0, (short) 0, (short) 0, (short) 0);
        doTest(rle1.toPixImage().equals(image1),
            "Setting RLE1[0][0] = 0 fails.");

        System.out.println("Testing setPixel() on a 3x3 encoding.");
        setAndCheckRLE(rle1, 2, 2, 7);
        image1.setPixel(2, 2, (short) 7, (short) 7, (short) 7);
        doTest(rle1.toPixImage().equals(image1),
            "Setting RLE1[2][2] = 7 fails.");

        System.out.println("Testing setPixel() on a 3x3 encoding.");
        setAndCheckRLE(rle1, 2, 2, 42);
        image1.setPixel(2, 2, (short) 42, (short) 42, (short) 42);
        doTest(rle1.toPixImage().equals(image1),
            "Setting RLE1[2][2] = 42 fails.");

        System.out.println("Testing setPixel() on a 3x3 encoding.");
        setAndCheckRLE(rle1, 1, 2, 42);
        image1.setPixel(1, 2, (short) 42, (short) 42, (short) 42);
        doTest(rle1.toPixImage().equals(image1),
            "Setting RLE1[1][2] = 42 fails.");

        PixImage image2 = array2PixImage(new int[][] { { 2, 3, 5 },
                    { 2, 4, 5 },
                    { 3, 4, 6 } });

        System.out.println("Testing one-parameter RunLengthEncoding constuctor " +
            "on another 3x3 image.  Input image:");
        System.out.print(image2);
        RunLengthEncoding rle2 = new RunLengthEncoding(image2);
        rle2.check();
        System.out.println("Testing getWidth/getHeight on a 3x3 encoding.");
        doTest(rle2.getWidth() == 3 && rle2.getHeight() == 3,
            "RLE2 has wrong dimensions");

        System.out.println("Testing toPixImage() on a 3x3 encoding.");
        doTest(rle2.toPixImage().equals(image2),
            "image2 -> RLE2 -> image does not reconstruct the original image");

        System.out.println("Testing setPixel() on a 3x3 encoding.");
        setAndCheckRLE(rle2, 0, 1, 2);
        image2.setPixel(0, 1, (short) 2, (short) 2, (short) 2);
        doTest(rle2.toPixImage().equals(image2),
            "Setting RLE2[0][1] = 2 fails.");

        System.out.println("Testing setPixel() on a 3x3 encoding.");
        setAndCheckRLE(rle2, 2, 0, 2);
        image2.setPixel(2, 0, (short) 2, (short) 2, (short) 2);
        doTest(rle2.toPixImage().equals(image2),
            "Setting RLE2[2][0] = 2 fails.");
        System.out.println("image2\n"+image2);
        PixImage image3 = array2PixImage(new int[][] { { 0, 5 },
                    { 1, 6 },
                    { 2, 7 },
                    { 3, 8 },
                    { 4, 9 } });

        System.out.println("Testing one-parameter RunLengthEncoding constuctor " +
            "on a 5x2 image.  Input image:");
        System.out.print(image3);
        RunLengthEncoding rle3 = new RunLengthEncoding(image3);
        rle3.check();

        System.out.println("Testing getWidth/getHeight on a 5x2 encoding.");
        doTest(rle3.getWidth() == 5 && rle3.getHeight() == 2,
            "RLE3 has wrong dimensions");

        System.out.println("Testing toPixImage() on a 5x2 encoding.");
        doTest(rle3.toPixImage().equals(image3),
            "image3 -> RLE3 -> image does not reconstruct the original image");

        System.out.println("Testing setPixel() on a 5x2 encoding.");
        setAndCheckRLE(rle3, 4, 0, 6);
        image3.setPixel(4, 0, (short) 6, (short) 6, (short) 6);
        doTest(rle3.toPixImage().equals(image3),
            "Setting RLE3[4][0] = 6 fails.");

        System.out.println("Testing setPixel() on a 5x2 encoding.");
        setAndCheckRLE(rle3, 0, 1, 6);
        image3.setPixel(0, 1, (short) 6, (short) 6, (short) 6);
        doTest(rle3.toPixImage().equals(image3),
            "Setting RLE3[0][1] = 6 fails.");

        System.out.println("Testing setPixel() on a 5x2 encoding.");
        setAndCheckRLE(rle3, 0, 0, 1);
        image3.setPixel(0, 0, (short) 1, (short) 1, (short) 1);
        doTest(rle3.toPixImage().equals(image3),
            "Setting RLE3[0][0] = 1 fails.");

        PixImage image4 = array2PixImage(new int[][] { { 0, 3 },
                    { 1, 4 },
                    { 2, 5 } });

        System.out.println("Testing one-parameter RunLengthEncoding constuctor " +
            "on a 3x2 image.  Input image:");
        System.out.print(image4);
        RunLengthEncoding rle4 = new RunLengthEncoding(image4);
        rle4.check();
        System.out.println("Testing getWidth/getHeight on a 3x2 encoding.");
        doTest(rle4.getWidth() == 3 && rle4.getHeight() == 2,
            "RLE4 has wrong dimensions");

        System.out.println("Testing toPixImage() on a 3x2 encoding.");
        doTest(rle4.toPixImage().equals(image4),
            "image4 -> RLE4 -> image does not reconstruct the original image");

        System.out.println("Testing setPixel() on a 3x2 encoding.");
        setAndCheckRLE(rle4, 2, 0, 0);
        image4.setPixel(2, 0, (short) 0, (short) 0, (short) 0);
        doTest(rle4.toPixImage().equals(image4),
            "Setting RLE4[2][0] = 0 fails.");

        System.out.println("Testing setPixel() on a 3x2 encoding.");
        setAndCheckRLE(rle4, 1, 0, 0);
        image4.setPixel(1, 0, (short) 0, (short) 0, (short) 0);
        doTest(rle4.toPixImage().equals(image4),
            "Setting RLE4[1][0] = 0 fails.");

        System.out.println("Testing setPixel() on a 3x2 encoding.");
        setAndCheckRLE(rle4, 1, 0, 1);
        image4.setPixel(1, 0, (short) 1, (short) 1, (short) 1);
        doTest(rle4.toPixImage().equals(image4),
            "Setting RLE4[1][0] = 1 fails.");

    }
}
