package com.example.oop_gui.backend;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

public class TicketPool {
    private Queue<Integer> tickets = new LinkedList<>();
    private int totalTickets = 0;
    private int maxCapacity;

    public TicketPool(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public synchronized void releaseTickets(int count, String vendorId) {
        for (int i = 0; i < count; i++) {
            if (tickets.size() < maxCapacity) {
                tickets.add(++totalTickets);
                log("Vendor ID " + vendorId + " released Ticket " + totalTickets);
            } else {
                log("Vendor ID " + vendorId + " could not release Ticket. Max capacity reached.");
            }
        }
    }

    public synchronized void buyTickets(int count, String customerName) {
        for (int i = 0; i < count; i++) {
            if (!tickets.isEmpty()) {
                int ticket = tickets.poll();
                log("Customer " + customerName + " purchased Ticket " + ticket);
            } else {
                log("Customer " + customerName + " could not purchase Ticket. No tickets available.");
            }
        }
    }

    public synchronized int getAvailableTickets() {
        return tickets.size();
    }

    private void log(String message) {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        System.out.println("[" + timeStamp + "] " + message);
    }
}
