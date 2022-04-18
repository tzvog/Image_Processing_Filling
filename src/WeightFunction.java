/***
 * this class represents the weight function
 */
public abstract class WeightFunction {

    /***
     * this function is used for specific weight funtions assuming we are only dealing with 2d images
     * as written in t
     * @param u the first coordinate
     * @param v the second coordinate
     * @return the weight at a certain point
     */
    public abstract double getWeight(Coordinate u, Coordinate v);
}
