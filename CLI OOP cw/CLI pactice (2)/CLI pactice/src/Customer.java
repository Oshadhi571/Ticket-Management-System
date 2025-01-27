public class Customer extends Thread {
    private TicketPool ticketPool;
    private int ticketsToBuy;

    // Constructor accepts TicketPool and number of tickets to buy
    public Customer(TicketPool ticketPool, int ticketsToBuy) {
        this.ticketPool = ticketPool;
        this.ticketsToBuy = ticketsToBuy;
    }

    @Override
    public void run() {
        ticketPool.buyTickets(ticketsToBuy);
        System.out.println("Customer bought " + ticketsToBuy + " tickets. Remaining tickets: " + ticketPool.getTotalTickets());
    }
}
