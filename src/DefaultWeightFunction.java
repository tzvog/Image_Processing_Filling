public class DefaultWeightFunction extends WeightFunction {

    /***
     * a argument to represent the the z factor
     */
    private double z;

    /***
     * hols the epsilon we are going to work with
     */
    private double epsilon;

    /***
     * constructor for this function
     * @param z the z part of the function
     * @param epsilon the epsilon we are going to use
     */
    public DefaultWeightFunction(double z, double epsilon) {
        this.z = z;
        this.epsilon = epsilon;
    }

    /***
     * this function is used for specific weight funtions assuming we are only dealing with 2d images
     * a default implementation for the weight
     * @param u the first coordinate
     * @param v the second coordinate
     * @return the weight at a certain point
     */
    public double getWeight(Coordinate u, Coordinate v) {

        // gets the distance between both functions
        double distance = u.getDistance(v);
        return 1.0 / (Math.pow(distance, this.z) + epsilon);
    }

}
