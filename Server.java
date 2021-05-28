package cs2030.simulator;

import java.util.LinkedList;

/**
* Server class represents a Server who has an id, boolean indicating availaability,
* queue of customer, and maximum queue length.
*/
public class Server {
    private final int id;
    private final boolean availability;
    private final LinkedList<Customer> queue;
    private final int maxQueueLength;

    /**
     * This will initialise a new server object with no customer 
     * it is currently serving and no queue.
     * @param id id no. of the server
     * @param maxQueueLength maximum number of customers in the queue
     */
    public Server(int id, int maxQueueLength) {
        this.id = id;
        this.queue = new LinkedList<>();
        this.maxQueueLength = maxQueueLength;
        this.availability = true;
    }

    private Server(int id, int maxQueueLength, LinkedList<Customer> queue, boolean availability) {
        this.id = id;
        this.queue = queue;
        this.maxQueueLength = maxQueueLength;
        this.availability = availability;
    }

    /**
     * This will return a boolean determining if server is available to serve.
     * @return return true if server is available to serve
     */
    public boolean isAvailable() {
        return this.availability;
    }


    public boolean hasWaitingCustomer() {
        return !this.queue.isEmpty();
    }

    /**
     * This will return a boolean determining if queue is full.
     * @return return true if curent queue size is same as max queue length
     */
    public boolean isQueueFull() {
        return this.queue.size() == this.maxQueueLength;
    }

    /**
     * This will create a new server who is currently available.
     * @return return a new server who is available
     */
    public Server makeAvailable() {
        return new Server(this.id, this.maxQueueLength, this.queue, true);
    }

    /**
     * This will create a new server who is currently busy.
     * @return return a new server who is busy
     */
    public Server makeBusy() {
        return new Server(this.id, this.maxQueueLength, this.queue, false);
    }

    /**
     * This will create a new server with a new customer added to queue.
     * @param customer new customer which server will be serving
     * @return return new server with customer added to queue
     */
    public Server addToQueue(Customer customer) {
        LinkedList<Customer> updated = new LinkedList<>(queue);
        updated.add(customer);
        return new Server(this.id, this.maxQueueLength, updated, this.availability);
    }

    /**
     * This will get the id of server.
     * @return return the id of server
     */
    public int getId() {
        return this.id;
    }

    /**
     * This will get the queue length of server.
     * @return return the queue length of server
     */
    public int getQueueLength() {
        return this.queue.size();
    }

    /**
     * This will get the first waiting customer out of the queue of server.
     * @return return the first waiting customer
     */
    public Customer getFirstWaitingCustomer() {
        return this.queue.poll();
    }

    @Override
    public String toString() {
        return String.format("server %d", this.id);
    }
}
