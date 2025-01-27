import java.util.LinkedList;
import java.util.Queue;

public class TicketPool {
    private Queue<Integer> tickets = new LinkedList<>();
    private int totalTickets = 0;
    private int maxTickets;

    // Constructor to set max ticket capacity
    public TicketPool(int maxTickets) {
        this.maxTickets = maxTickets;
    }

    // Method to release tickets into the pool
    public synchronized void releaseTickets(int ticketCount) {
        for (int i = 1; i <= ticketCount; i++) {
            if (tickets.size() < maxTickets) {
                tickets.add(++totalTickets);
                System.out.println("Thread-" + Thread.currentThread().getId() + ": Released Ticket " + totalTickets);
            }
        }
    }

    // Method to buy tickets from the pool
    public synchronized void buyTickets(int ticketCount) {
        for (int i = 0; i < ticketCount; i++) {
            if (!tickets.isEmpty()) {
                int ticket = tickets.poll();
                System.out.println("Bought Ticket{ticketId=" + ticket + "}");
                System.out.println("Tickets remaining: " + tickets.size());
            } else {
                System.out.println("Not enough tickets available.");
            }
        }
    }

    // Check available tickets
    public int getTotalTickets() {
        return tickets.size();
    }

    // Method to delete the configuration file

}
