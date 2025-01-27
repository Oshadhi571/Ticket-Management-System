package com.example.oop_gui.backend;

public class Customer extends Thread {
    private TicketPool ticketPool;
    private int ticketsToBuy;
    private int retrievalRate;
    private String customerName;

    public Customer(TicketPool ticketPool, int ticketsToBuy, int retrievalRate, String customerName) {
        this.ticketPool = ticketPool;
        this.ticketsToBuy = ticketsToBuy;
        this.retrievalRate = retrievalRate;
        this.customerName = customerName;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < ticketsToBuy; i++) {
                Thread.sleep(retrievalRate);
                ticketPool.buyTickets(1, customerName);
            }
        } catch (InterruptedException e) {
            System.out.println("Customer " + customerName + " interrupted.");
        }
    }
}
