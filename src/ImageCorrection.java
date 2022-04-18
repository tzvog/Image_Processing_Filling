import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.w3c.dom.css.RGBColor;
import org.opencv.core.CvType;

import java.util.HashSet;
import java.util.Set;

import static org.opencv.core.Core.NATIVE_LIBRARY_NAME;

/***
 * a class to calculate the pixel needs
 */
public class ImageCorrection {

    private final static double HOLE_INDICATOR = -1;
    private double[][] imageValues;
    private WeightFunction weightFunction;
    private Set<Coordinate> holes;
    private Set<Coordinate> border;
    private Connection connection;


    /***
     * this class made to fix the image
     * @param img the image formated into a matrix
     * @param connectionType the connection type we want for each pieice
     * @param f the function for the weight of the object
     */
    public ImageCorrection(double [][] img, int connectionType, WeightFunction f) {

        this.imageValues = img;
        this.holes = new HashSet<Coordinate>();
        this.border = new HashSet<Coordinate>();
        this.connection = ConnectionFactory.getConnection(connectionType);
        this.findHoles();
        this.weightFunction = f;
    }

    /***
     * finds the hole in the image
     */
    private void findHoles() {

        // puts the values into our new matrix
        for (int x = 0; x < this.imageValues.length; x++) {
            for (int y = 0; y < this.imageValues[0].length; y++) {

                // if we have reached a hole add it to the set
                if (this.imageValues[x][y] == HOLE_INDICATOR) {
                    holes.add(new Coordinate(x, y));
                }
                // if we have reached a border add it to the set
                else if (this.connection.isBoundary(new Coordinate(x, y), this.imageValues)) {
                    border.add(new Coordinate(x, y));
                }
            }
        }

    }

    /***
     * calculates the color needed to fill the whole with
     * @param h the coordiante we want to fill
     * @return a value to put at that spot
     */
    private double holeColorCalculation(Coordinate h) {

        double numerator = 0.0;
        double denominator = 0.0;

        // calculates the numerator and denominator
        for (Coordinate b : this.border) {
            double weightAtPoint = this.weightFunction.getWeight(b, h);
            numerator += (weightAtPoint * this.imageValues[b.getFirst()][b.getSecond()]);
            denominator += weightAtPoint;
        }

        // returns the wanted value
        return numerator / denominator;
    }

    /***
     * this function corrects the holes in the image and puts them back into our current grey scale
     */
    public void CorrectImage() {
        // for each hole fixes the whole by calculating what should go in it
        for (Coordinate h : this.holes) {

            // gets the color for the hole
            double colorForHole = this.holeColorCalculation(h);

            // put the value in our local scaled matrix
            this.imageValues[h.getFirst()][h.getSecond()] = colorForHole;
        }
    }

    /***
     * writes the image to a file
     * @param fileName the name of the file we want to write to
     */
    public void WriteToFile(String fileName) {

        // creates an empty image
        Size s = new Size(this.imageValues.length, this.imageValues[0].length);
        Mat image = new Mat(s, CvType.CV_64FC1);


        // puts the values into our new matrix
        for (int x = 0; x < this.imageValues.length; x++) {
            for (int y = 0; y < this.imageValues[0].length; y++) {

                // adds the value to the image
                image.put(x, y,this.imageValues[x][y] * 255);
            }
        }

        // writes to a file
        new Imgcodecs().imwrite(fileName, image);
    }
}
