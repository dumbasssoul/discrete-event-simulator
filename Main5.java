import cs2030.simulator.Customer;
import cs2030.simulator.Event;
import cs2030.simulator.State;
import cs2030.simulator.Shop;
import cs2030.simulator.EventComparator;
import cs2030.simulator.Statistics;
import cs2030.simulator.Simulator;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Main5 {
    /**
     * Main method for working operations for level 5.
     * @param args for generating run
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PriorityQueue<Event> events = new PriorityQueue<>(new EventComparator());
        int numOfServers = sc.nextInt();
        int maxQueueLength = sc.nextInt();
        int numOfCustomers = sc.nextInt();
        int seed = sc.nextInt();
        double arrivalRate = sc.nextDouble();
        double serviceRate = sc.nextDouble();
        double restingRate = sc.nextDouble();
        double restingProbability = sc.nextDouble();
        double greedyProbability = sc.nextDouble();
        sc.close();

        Shop shop = new Shop(numOfServers, maxQueueLength, restingProbability, 
                seed, arrivalRate, serviceRate, restingRate);
        Statistics statistics = new Statistics();

        int customerId = 1;
        double arrivalTime = 0.0;
        for (int i = 0; i < numOfCustomers; i++) {
            boolean greedyCustomer = shop.genCustomerType() < greedyProbability;
            Customer customer = new Customer(customerId, arrivalTime, -1, greedyCustomer);
            events.add(new Event(arrivalTime, State.ARRIVE, customer, -1));
            customerId += 1;
            arrivalTime += shop.genInterArrivalTime();
        }

        for (int i = 0; i < numOfCustomers; i++) {
            double restTime = shop.genRestPeriod();
            shop.addRestTime(restTime);
        }

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
