public class EightConnection extends Connection {

    /***
     * a class to implement the eight connection type
     */
    public EightConnection() {
        super();
        this.directions.add(new Coordinate(1,0));
        this.directions.add(new Coordinate(-1,0));
        this.directions.add(new Coordinate(0,1));
        this.directions.add(new Coordinate(0,-1));
        this.directions.add(new Coordinate(1,1));
        this.directions.add(new Coordinate(-1,1));
        this.directions.add(new Coordinate(1,-1));
        this.directions.add(new Coordinate(-1,-1));
    }
}
