public class ConnectionFactory {

    // creates an instance of the factory
    private static ConnectionFactory connectionFactory = new ConnectionFactory();

    /***
     * making it a singleton
     */
    private ConnectionFactory() {
    }


    /***
     * adds the option of getting the only existing instance of the factory
     * @return the instance of the single object
     */
    public static ConnectionFactory getInstance() {
        return connectionFactory;
    }

    /***
     * get's the needed connection from the user choice
     * @param choice what connection we want
     * @return which of the connection to use
     */
    public static Connection getConnection(int choice) {

        // based on the user choice we decide what connection we want
        switch (choice) {
            case 4:
                return new FourConnection();
            case 8:
                return new EightConnection();
            default:
                return null;
        }
    }
}
