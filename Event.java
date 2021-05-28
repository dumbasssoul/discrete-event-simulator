package cs2030.simulator;

/**
 * The event Class is a combination of a time, a customer, server id and state
 * that shows an event at a specific point in time.
 */
public class Event {
    private final double time;
    private final State state;
    private final Customer customer;
    private final int serverId;

    public Event(double time, State state, Customer customer, int serverId) {
        this.time = time;
        this.state = state;
        this.customer = customer;
        this.serverId = serverId;
    }

    /**
     * This will get the time of the event.
     * @return return the time of the event
     */
    public double getTime() {
        return this.time;
    }

    /**
     * This will get the state of the event.
     * @return return the state of the event
     */
    public State getState() {
        return this.state;
    }

    /**
     * This will get the customer in the event.
     * @return return the customer in the event
     */
    public Customer getCustomer() {
        return this.customer;
    }

    /**
     * This will get the id of server in the event.
     * @return return the id of server in the event
     */
    public int getServerId() {
        return this.serverId;
    }

    @Override
    public String toString() {
        if (this.state == State.ARRIVE) {
            return String.format("%.3f %s arrives", this.time, this.customer);
        } else if (this.state == State.LEAVE) {
            return String.format("%.3f %s leaves", this.time, this.customer);
        } else if (this.state == State.SERVE) {
            return String.format("%.3f %s serves by server %d", 
                    this.time, this.customer, this.serverId);
        } else if (this.state == State.DONE) {
            return String.format("%.3f %s done serving by server %d", 
                    this.time, this.customer, this.serverId);
        } else if (this.state == State.WAIT) { 
            return String.format("%.3f %s waits at server %d", 
                    this.time, this.customer, this.serverId);
        } else { // for this.state == State.BACK
            return "";
        }
    }
}
