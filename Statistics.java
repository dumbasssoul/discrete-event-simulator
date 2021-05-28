package cs2030.simulator;

/**
* Statistics class represents the Statistics of all the events: number of customers
* served, left and total waiting time of customers.
*/
public class Statistics {
    private static final int TOTAL_WAITING_TIME = 0;
    private static final int NUMBER_OF_CUSTOMER_SERVED = 1;
    private static final int NUMBER_OF_CUSTOMER_LEFT = 2;
    private static final int NUMBER_OF_VALUES = 3;

    private final double[] values;

    public Statistics() {
        this.values = new double[NUMBER_OF_VALUES];
    }

    
    // Increases the number of customers who are served.
    public void increaseNumOfCustomersServed() {
        this.values[NUMBER_OF_CUSTOMER_SERVED]++;
    }

    // Increases the number of customers who left without being served.
    public void increaseNumOfCustomersLeft() {
        this.values[NUMBER_OF_CUSTOMER_LEFT]++;
    }

    // Increase total waiting time of customers before being served.
    public void increaseTotalWaitingTime(double time) {
        this.values[TOTAL_WAITING_TIME] += time;
    }

    /**
     * This will get the total number of customers served.
     * @return return number of customers served
     */
    public double getNumberOfCustomerServed() {
        return this.values[NUMBER_OF_CUSTOMER_SERVED];
    }

    /**
     * This will get the total number of customers left.
     * @return return the total number of customers left
     */
    public double getNumberOfCustomerLeft() {
        return this.values[NUMBER_OF_CUSTOMER_LEFT];
    }

    /**
     * This will get the average waiting time of customers.
     * @return return the average waiting time of customers
     */
    public double getAverageWaitingTime() {
        return (getNumberOfCustomerServed() != 0) ? 
                this.values[TOTAL_WAITING_TIME] / this.values[NUMBER_OF_CUSTOMER_SERVED] :
                0.000;
    }

    @Override
    public String toString() {
        return String.format("[%.3f %.0f %.0f]",
            this.getAverageWaitingTime(),
            this.values[NUMBER_OF_CUSTOMER_SERVED],
            this.values[NUMBER_OF_CUSTOMER_LEFT]);
    }
}
