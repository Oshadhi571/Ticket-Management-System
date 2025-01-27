public class Configuration {
    private String eventName;
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;
    private int numVendors;
    private int numCustomers;

    public String getEventName() {
        return eventName;
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    public int getNumVendors() {
        return numVendors;
    }

    public int getNumCustomers() {
        return numCustomers;
    }

    // Constructor
    public Configuration(String eventName, int totalTickets, int ticketReleaseRate,
                         int customerRetrievalRate, int maxTicketCapacity,
                         int numVendors, int numCustomers) {
        this.eventName = eventName;
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
        this.maxTicketCapacity = maxTicketCapacity;
        this.numVendors = numVendors;
        this.numCustomers = numCustomers;
    }

    // Converts the configuration object to a string for file storage
    @Override
    public String toString() {
        return eventName + "," +
                totalTickets + "," +
                ticketReleaseRate + "," +
                customerRetrievalRate + "," +
                maxTicketCapacity + "," +
                numVendors + "," +
                numCustomers;
    }

    // Creates a Configuration object from a string
    public static Configuration fromString(String configData) {
        String[] lines = configData.split("\n");
        if (lines.length != 7) {  // Assuming there are exactly 7 lines of configuration data
            throw new IllegalArgumentException("Invalid configuration data: " + configData);
        }

        String eventName = lines[0].split(":")[1].trim();
        int totalTickets = Integer.parseInt(lines[1].split(":")[1].trim());
        int ticketReleaseRate = Integer.parseInt(lines[2].split(":")[1].trim());
        int customerRetrievalRate = Integer.parseInt(lines[3].split(":")[1].trim());
        int maxTicketCapacity = Integer.parseInt(lines[4].split(":")[1].trim());
        int numVendors = Integer.parseInt(lines[5].split(":")[1].trim());
        int numCustomers = Integer.parseInt(lines[6].split(":")[1].trim());

        return new Configuration(eventName, totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity, numVendors, numCustomers);
    }
}
