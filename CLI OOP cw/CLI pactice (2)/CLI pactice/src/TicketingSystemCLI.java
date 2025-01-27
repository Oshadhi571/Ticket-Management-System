import java.io.*;
import java.util.Scanner;

public class TicketingSystemCLI {

    // Declare eventName at the class level
    private static String eventName;
    private static TicketPool ticketPool;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Display menu options
        System.out.println("=========================================");
        System.out.println("Real-Time Event Ticketing System - CLI");
        System.out.println("1. Enter System Configuration");
        System.out.println("2. Load Configuration from File");

        System.out.println("3. Exit");

        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character

        switch (choice) {
            case 1:
                // Prompt for event name
                System.out.print("Enter Event Name: ");
                eventName = scanner.nextLine();  // Store event name in class-level variable

                // Prompt for other configurations
                System.out.print("Enter Total Tickets: ");
                int totalTickets = scanner.nextInt();

                System.out.print("Enter Ticket Release Rate (milliseconds): ");
                int releaseRate = scanner.nextInt();

                System.out.print("Enter Customer Retrieval Rate (milliseconds): ");
                int customerRate = scanner.nextInt();

                System.out.print("Enter Max Ticket Capacity: ");
                int maxTickets = scanner.nextInt();

                // Create and configure the ticket pool
                ticketPool = new TicketPool(maxTickets);

                // Save configuration to file, including the event name
                saveConfigToFile(eventName, totalTickets, releaseRate, customerRate, maxTickets);

                // Start ticket release and purchase threads
                startTicketingProcess(totalTickets, releaseRate, customerRate);
                break;

            case 2:
                loadConfigFromFile();
                break;


            case 3:
                System.out.println("Exiting...");
                System.exit(0);
                break;

            default:
                System.out.println("Invalid option.");
                break;
        }
    }

    // Method to save configuration to a file, including event name
    private static void saveConfigToFile(String eventName, int totalTickets, int releaseRate, int customerRate, int maxTickets) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("ticket_config.txt"))) {
            writer.println("Event Name: " + eventName); // Save event name
            writer.println("Total Tickets: " + totalTickets);
            writer.println("Ticket Release Rate: " + releaseRate);
            writer.println("Customer Retrieval Rate: " + customerRate);
            writer.println("Max Ticket Capacity: " + maxTickets);
            System.out.println("Configuration saved to ticket_config.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to load configuration from the file
    private static void loadConfigFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("ticket_config.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to start the ticketing process using threads
    private static void startTicketingProcess(int totalTickets, int releaseRate, int customerRate) {
        // Thread to release tickets
        Thread releaseThread = new Thread(() -> {
            for (int i = 0; i < totalTickets; i++) {
                try {
                    Thread.sleep(releaseRate);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ticketPool.releaseTickets(1);
            }
        });

        // Thread to buy tickets
        Thread buyThread = new Thread(() -> {
            for (int i = 0; i < totalTickets; i++) {
                try {
                    Thread.sleep(customerRate);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ticketPool.buyTickets(1);
            }
        });

        releaseThread.start();
        buyThread.start();
    }
}
