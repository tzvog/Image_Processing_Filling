import java.util.Vector;

import org.opencv.core.Mat;

public abstract class Connection {

    protected Vector<Coordinate> directions;
    private final static double HOLE_INDICATOR = -1;

    /***
     * a constructor for the Connection Class
     */
    protected Connection() {
        directions = new Vector<Coordinate>();
    }

    /***
     * a function to check if this is a boundary point
     * @param u the given point
     * @param Img the image we are dealing with
     * @return is this a boundary point
     */
    public boolean isBoundary(Coordinate u, double[][] Img) {
        // for every directions checks if this is a boundary
        for (Coordinate v : this.directions) {

            // checks that we are actually within the border for checking reasons
            if ((v.getFirst() + u.getFirst() < 0) ||
                    (v.getFirst() + u.getFirst() >= Img[0].length) ||
                    (v.getSecond() + u.getSecond() < 0) ||
                    (v.getSecond() + u.getSecond() >= Img.length)) {
                continue;
            }

            // it is indeed a border space
            if (Img[v.getFirst() + u.getFirst()]
                    [v.getSecond() + u.getSecond()] == HOLE_INDICATOR) {
                return true;
            }
        }

        // we did not find that it touches a whole
        return false;
    }

}
