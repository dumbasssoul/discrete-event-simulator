package cs2030.simulator;

import java.util.LinkedList;

/**
* Shop class represents a Shop with an array of servers, rest time of servers,
* random generator and rest probability of servers.
*/
public class Shop {
    private final Server[] servers;
    private final LinkedList<Double> restTimes;
    private final RandomGenerator generator;
    private final double restProbability;

    public Shop(int numOfServers, int maxQueueLength) {
        servers = new Server[numOfServers];
        for (int i = 1; i <= numOfServers; i++) {
            servers[i - 1] = new Server(i, maxQueueLength);
        }
        this.restTimes = new LinkedList<>();
        this.restProbability = 0.0;
        this.generator = null;
    }

    public Shop(int numOfServers, int maxQueueLength, double restProbability, 
            int seed, double arrivalRate, double serviceRate, double restingRate) {
        this.servers = new Server[numOfServers];
        for (int i = 1; i <= numOfServers; i++) {
            this.servers[i - 1] = new Server(i, maxQueueLength);
        }
        this.restTimes = new LinkedList<>();
        this.restProbability = restProbability;
        this.generator = new RandomGenerator(seed, arrivalRate, serviceRate, restingRate);

    }

    public Server[] getServers() {
        return this.servers;
    }

    public int findAvailableServer() {
        for (Server server : this.servers) {
            if (server.isAvailable()) {
                return server.getId();
            }
        }
        return -1;
    }

    public int getServerWithWaitingSpots() {
        for (Server server : this.servers) {
            if (!server.isQueueFull()) {
                return server.getId();
            }
        }
        return -1;
    }

    public int getServerWithLeastWaitingSpots() {
        int serverWithLeastWaitingSpots = -1;
        int shortestQueueLength = -1;
        for (Server server : servers) {
            if (server.isQueueFull()) {
                continue;
            }
            if (shortestQueueLength == -1) {
                serverWithLeastWaitingSpots = server.getId();
                shortestQueueLength = server.getQueueLength();
                continue;
            }
            if (server.getQueueLength() < shortestQueueLength) {
                serverWithLeastWaitingSpots = server.getId();
                shortestQueueLength = server.getQueueLength();
            }
        }
        return serverWithLeastWaitingSpots;
    }

    public void addRestTime(double time) {
        this.restTimes.add(time);
    }

    public double getNextRestTime() {
        return this.restTimes.poll();
    }

    public RandomGenerator getGenerator() {
        return this.generator;
    }

    public boolean hasRandomGenerator() {
        return this.generator != null;
    }

    public double getRestProbability() {
        return this.restProbability;
    }

    public void addCustomerToServerQueue(int serverId, Customer customer) {
        servers[serverId - 1] = servers[serverId - 1].addToQueue(customer);
    }

    public void makeServerAvailable(int serverId) {
        servers[serverId - 1] = servers[serverId - 1].makeAvailable();
    }

    public void makeServerBusy(int serverId) {
        servers[serverId - 1] = servers[serverId - 1].makeBusy();
    }

    public double genCustomerType() {
        return this.generator.genCustomerType();
    }

    public double genInterArrivalTime() {
        return this.generator.genInterArrivalTime();
    }

    public double genRestPeriod() {
        return this.generator.genRestPeriod();
    }
}
