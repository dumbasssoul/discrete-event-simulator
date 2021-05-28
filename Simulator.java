/**
 * Simulator creates next event to add into a priority queue 
 * and updates shop and the statistics of events.
 */

package cs2030.simulator;

import java.util.Queue;
import java.util.PriorityQueue;

/**
* Simularor class represents a Simulator with a shop and statistics which
* will be updated after each event.
*/
public class Simulator {
    private final Shop shop;
    private final Statistics statistics;

    public Simulator(Shop shop, Statistics statistics) {
        this.shop = shop;
        this.statistics = statistics;
    }

    /**
     * This returns the next event after checking the conditions of servers and time.
     * @params the previous event
     * @return the next event
     */
    public Event nextEvent(Event event) {
        if (event.getState() == State.ARRIVE) {
            int serverId = shop.findAvailableServer();
            if (serverId != -1) {
                return new Event(event.getTime(), State.SERVE, event.getCustomer(), serverId);
            } else {
                boolean isGreedy = event.getCustomer().isGreedy();
                if (!isGreedy) {
                    serverId = this.shop.getServerWithWaitingSpots();
                } else {
                    serverId = this.shop.getServerWithLeastWaitingSpots();
                }

                if (serverId != -1) {
                    return new Event(event.getTime(), State.WAIT, event.getCustomer(), serverId);
                } else {
                    return new Event(event.getTime(), State.LEAVE, event.getCustomer(), -1);
                }
            }
        } else if (event.getState() == State.LEAVE) {
            this.statistics.increaseNumOfCustomersLeft();
            return null;
        } else if (event.getState() == State.DONE) {
            double restTime;
            if (!shop.hasRandomGenerator() || shop.getGenerator().genRandomRest() 
                    < shop.getRestProbability()) {
                restTime = shop.getNextRestTime();
            } else {
                restTime = 0;
            }

            if (restTime == 0) {
                shop.makeServerAvailable(event.getServerId());
                if (shop.getServers()[event.getServerId() - 1].hasWaitingCustomer()) {
                    Customer customer = shop.getServers()[event.getServerId() - 1]
                        .getFirstWaitingCustomer();
                    return new Event(event.getTime(), State.SERVE, customer, event.getServerId());
                } else {
                    return null;
                }
            } else {
                return new Event(event.getTime() + restTime, State.BACK, null, event.getServerId());
            }
        } else if (event.getState() == State.BACK) {
            shop.makeServerAvailable(event.getServerId());
            if (shop.getServers()[event.getServerId() - 1].hasWaitingCustomer()) {
                Customer customer = shop.getServers()[event.getServerId() - 1]
                    .getFirstWaitingCustomer();
                return new Event(event.getTime(), State.SERVE, customer, event.getServerId());
            } else {
                return null;
            }
        } else if (event.getState() == State.SERVE) {
            this.statistics.increaseTotalWaitingTime(event.getTime() - event.getCustomer()
                    .getArrivalTime());
            this.statistics.increaseNumOfCustomersServed();
            shop.makeServerBusy(event.getServerId());
            double doneTime;
            if (event.getCustomer().getServiceTime() != -1) {
                doneTime = event.getTime() + event.getCustomer().getServiceTime();
            } else {
                doneTime = event.getTime() + shop.getGenerator().genServiceTime();
            }
            return new Event(doneTime, State.DONE, event.getCustomer(), event.getServerId());
        } else { // for this.state == State.WAIT
            shop.addCustomerToServerQueue(event.getServerId(), event.getCustomer());
            return null;
        }
    }
}
