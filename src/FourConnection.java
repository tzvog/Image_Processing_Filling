/**
 * a small class to implement the four connection type of the points
 */
public class FourConnection extends Connection {

    /***
     * constructor for the four connection and add value to the directions
     */
    public FourConnection() {
        super();
        this.directions.add(new Coordinate(1,0));
        this.directions.add(new Coordinate(-1,0));
        this.directions.add(new Coordinate(0,1));
        this.directions.add(new Coordinate(0,-1));
    }
}
