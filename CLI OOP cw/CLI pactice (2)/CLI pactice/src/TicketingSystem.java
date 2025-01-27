import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class TicketingSystem {
    private Queue<Integer> tickets = new LinkedList<>();
    private int totalTickets = 0;

    // Add tickets to the pool
    public synchronized void releaseTickets(int ticketCount, String vendorName) {
        for (int i = 1; i <= ticketCount; i++) {
            tickets.add(++totalTickets); // Adding a new ticket to the pool
        }
        System.out.println(vendorName + " released " + ticketCount + " tickets.");
    }

    // Buy tickets from the pool
    public synchronized void buyTickets(int ticketCount) {
        for (int i = 0; i < ticketCount; i++) {
            if (!tickets.isEmpty()) {
                tickets.poll(); // Remove the first ticket from the pool (buying a ticket)
            }
        }
    }

    // Log actions to a file
    public void logToFile(String action) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("ticketing_log.txt", true))) {
            writer.println(action);
        } catch (IOException e) {
            System.out.println("Error logging action: " + e.getMessage());
        }
    }

    // Delete all tickets from the pool
    public synchronized void deleteAllTickets() {
        tickets.clear();
        System.out.println("All tickets have been deleted.");
    }
}
