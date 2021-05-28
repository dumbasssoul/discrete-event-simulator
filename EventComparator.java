package cs2030.simulator;

import cs2030.simulator.Event;
import java.util.Comparator;

/**
 * EventComparator compares eevnts in a priority queue
 * and decides which events to create for the next instance.
 */
public class EventComparator implements Comparator<Event> {
    /**
     * This returns an integer that indicates which event comes first.
     * @params e1 event 1
     * @params e2 event 2
     * @return return an integer -1, 1 or 0
     */
    @Override
    public int compare(Event e1, Event e2) {
        if (e1.getTime() < e2.getTime()) {
            return -1;
        } else if (e1.getTime() > e2.getTime()) {
            return 1;
        } else if (e1.getCustomer().getID() < e2.getCustomer().getID()) {
            return -1;
        } else if (e1.getCustomer().getID() > e2.getCustomer().getID()) {
            return 1;
        } else {
            return 0;
        }
    }
}