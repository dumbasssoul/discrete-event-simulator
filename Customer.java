package cs2030.simulator;

/**
* Customer class represents a Customer who has an id, arrival time, 
* service time and a boolean indicator of whether customer is greedy.
*/
public class Customer {
    private final int id;
    private final double arrivalTime;
    private final double serviceTime;
    private final boolean greedyCustomer;

    /**
     * This will initialise a new customer object.
     * @param id customer id
     * @param arrivalTime customer arrival time
     * @param serviceTime customer service time
     * @param boolean greedy customer
     */
    public Customer(int id, double arrivalTime, double serviceTime, boolean greedyCustomer) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
        this.greedyCustomer = greedyCustomer;
    }

    /**
     * This will get the id of customer.
     * @return return the id of customer
     */
    public int getID() {
        return this.id;
    }

    /**
     * This will get the arrival time of customer.
     * @return return the arrival time of customer
     */
    public double getArrivalTime() {
        return this.arrivalTime;
    }

    /**
     * This will get the service time of customer.
     * @return return the service time of customer
     */
    public double getServiceTime() {
        return this.serviceTime;
    }

    /**
     * This will get the boolean of whether customer is a greedy customer.
     * @return return true if customer is greedy
     */
    public boolean isGreedy() {
        return this.greedyCustomer;
    }

    @Override
    public String toString() {
        if (isGreedy()) {
            return String.format("%d(greedy)", this.id);
        }
        return String.format("%d", this.id);
    }
}
