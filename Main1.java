import cs2030.simulator.Customer;
import cs2030.simulator.Event;
import cs2030.simulator.State;
import cs2030.simulator.Shop;
import cs2030.simulator.EventComparator;
import cs2030.simulator.Statistics;
import cs2030.simulator.Simulator;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Main1 {
    /**
     * Main method for working operations for level 1.
     * @param args for generating run
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numOfServers = sc.nextInt();
        final int maxQueueLength = 1;
        PriorityQueue<Event> events = new PriorityQueue<>(new EventComparator());

        Shop shop = new Shop(numOfServers, maxQueueLength);
        Statistics statistics = new Statistics();

        int customerId = 1;
        while (sc.hasNext()) {
            double arrivalTime = sc.nextDouble();
            double serviceDuration = 1.0;
            boolean greedyCustomer = false;
            Customer customer = new Customer(customerId, arrivalTime, 
                    serviceDuration, greedyCustomer);
            events.add(new Event(arrivalTime, State.ARRIVE, customer, -1));
            customerId += 1;
            shop.addRestTime(0.0);
        }
        sc.close();

        Simulator simulator = new Simulator(shop, statistics);

        while (!events.isEmpty()) {
            Event event = events.poll();
            if (!event.toString().equals("")) {
                System.out.println(event);
            }
            Event nextEvent = simulator.nextEvent(event);
            if (nextEvent != null) {
                events.add(nextEvent);
            }
        }

        System.out.println(statistics);
    }
}
