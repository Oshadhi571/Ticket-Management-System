package com.example.oop_gui.backend;

public class Vendor extends Thread {
    private TicketPool ticketPool;
    private int ticketsToRelease;
    private int releaseRate;
    private String vendorId;

    public Vendor(TicketPool ticketPool, int ticketsToRelease, int releaseRate, String vendorId) {
        this.ticketPool = ticketPool;
        this.ticketsToRelease = ticketsToRelease;
        this.releaseRate = releaseRate;
        this.vendorId = vendorId;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < ticketsToRelease; i++) {
                Thread.sleep(releaseRate);
                ticketPool.releaseTickets(1, vendorId);
            }
        } catch (InterruptedException e) {
            System.out.println("Vendor ID " + vendorId + " interrupted.");
        }
    }
}
