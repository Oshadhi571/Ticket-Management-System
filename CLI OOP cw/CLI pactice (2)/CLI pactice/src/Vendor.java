public class Vendor extends Thread {
    private TicketingSystem ticketingSystem;
    private int ticketsToRelease;

    // Constructor accepts TicketingSystem and number of tickets to release
    public Vendor(TicketingSystem ticketingSystem, int ticketsToRelease) {
        this.ticketingSystem = ticketingSystem;
        this.ticketsToRelease = ticketsToRelease;
    }

    @Override
    public void run() {
        while (true) {
            // Simulate the release of tickets at intervals
            ticketingSystem.releaseTickets(ticketsToRelease, Thread.currentThread().getName());
            try {
                // Sleep for a fixed period (e.g., 2 seconds) before releasing more tickets
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("Vendor thread interrupted.");
            }
        }
    }
}
