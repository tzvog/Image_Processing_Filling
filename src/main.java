import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.Scanner;

import static org.opencv.core.Core.NATIVE_LIBRARY_NAME;

/***
 * main class to run the program
 */
public class main {

    private final static double HOLE_INDICATOR = -1;

    /**
     * loads the image and finds the Base and points
     *
     * @param imagePath the path of the file we are going to load
     */
    private static double[][] loadImage(String imagePath, String maskPath) {

        // loads the library
        System.loadLibrary(NATIVE_LIBRARY_NAME);

        Mat imageRgb = new Imgcodecs().imread(imagePath);
        Mat image = new Mat();

        Mat maskRgb = new Imgcodecs().imread(maskPath);
        Mat mask = new Mat();

        double grayscale = 0.0;

        Imgproc.cvtColor(imageRgb, image, Imgproc.COLOR_RGB2GRAY);
        Imgproc.cvtColor(maskRgb, mask, Imgproc.COLOR_RGB2GRAY);

        // inits the 2-d array of the image to hold scaled values
        double [][] imageWithHoleGreyScale  = new double[image.width()][image.height()];

        // puts the values into our new matrix
        for (int x = 0; x < image.width(); x++) {
            for (int y = 0; y < image.height(); y++) {

                // gets the greyscale value and puts it into our matrix
                grayscale = image.get(x, y)[0] / 255.0;

                if (mask.get(x, y)[0] < 0.5)
                {
                    grayscale = HOLE_INDICATOR;
                }


                imageWithHoleGreyScale[x][y] = grayscale;
            }
        }

        return imageWithHoleGreyScale;
    }


    /***
     * an console application for the user to use our app
     */
    private static void consoleApplication() {

        // asking the user for the command line utility
        Scanner myObj = new Scanner(System.in);

        System.out.println("Enter image file name");
        String imageFileName = myObj.nextLine();

        System.out.println("Enter mask file name");
        String maskFileName = myObj.nextLine();

        System.out.println("Enter z number we want to work with");
        double z = myObj.nextDouble();

        System.out.println("Enter epsilon we want for our weight function");
        double epsilon = myObj.nextDouble();

        System.out.println("Enter Connection Type as number (currently only support 4,8)");
        int connectivityType = myObj.nextInt();


        double [][] matrix = loadImage(imageFileName,
                maskFileName);

        WeightFunction f = new DefaultWeightFunction(z, epsilon);

        // corrects the image
        ImageCorrection imc = new ImageCorrection(matrix,  connectivityType, f);
        imc.CorrectImage();
        imc.WriteToFile("lenna_fixed_part_2.png");
    }

    /***
     * main function
     * @param args the arguments accepted by the main function
     */
    public static void main(String[] args) {
        consoleApplication();
    }
}
