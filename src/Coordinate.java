/***
 * a class to represent a pair
 */
public class Coordinate {

    private int first;
    private int second;

    /***
     * a constructor for the coordinate
     * @param first the first axis
     * @param second the second axis
     */
    public Coordinate(int first, int second) {
        this.first = first;
        this.second = second;
    }

    /***
     * gets the first value
     * @return the first value
     */
    public int getFirst() {
        return first;
    }

    /***
     * gets the second value
     * @return the second value
     */
    public int getSecond() {
        return second;
    }

    /**
     * gets the distance between me and another coordinate
     * @param other the other one we are going to get ditance from
     * @return the distance in double
     */
    public double getDistance(Coordinate other) {
        double distance = 0.0;

        // calculates the euclid distance between two 2-d points
        distance += Math.pow((this.getFirst() - other.getFirst()), 2);
        distance += Math.pow((this.getSecond() - other.getSecond()), 2);
        distance = Math.sqrt(distance);

        return distance;
    }

}
