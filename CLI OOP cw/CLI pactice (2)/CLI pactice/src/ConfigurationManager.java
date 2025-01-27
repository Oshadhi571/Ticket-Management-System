import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ConfigurationManager {

    private static final String CONFIG_FILE_NAME = "configurations.txt";

    // Method to save a configuration to the file
    public static void saveConfigToFile(Configuration config) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(CONFIG_FILE_NAME, true))) {
            writer.println("Event Name: " + config.getEventName());
            writer.println("Total Tickets: " + config.getTotalTickets());
            writer.println("Ticket Release Rate: " + config.getTicketReleaseRate());
            writer.println("Customer Retrieval Rate: " + config.getCustomerRetrievalRate());
            writer.println("Max Ticket Capacity: " + config.getMaxTicketCapacity());
            writer.println("Number of Vendors: " + config.getNumVendors());
            writer.println("Number of Customers: " + config.getNumCustomers());
            writer.println(); // Add a blank line between configurations for readability
        } catch (IOException e) {
            System.out.println("Error saving configuration: " + e.getMessage());
        }
    }

    // Method to load configurations from the file
    public static List<Configuration> loadConfigFromFile() {
        List<Configuration> configs = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(CONFIG_FILE_NAME))) {
            StringBuilder configData = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    if (configData.length() > 0) {
                        configs.add(Configuration.fromString(configData.toString()));
                        configData.setLength(0);  // Reset for the next configuration
                    }
                } else {
                    configData.append(line).append("\n");
                }
            }
            // Add the last configuration if the file doesn't end with a blank line
            if (configData.length() > 0) {
                configs.add(Configuration.fromString(configData.toString()));
            }
        } catch (IOException e) {
            System.out.println("Error reading configuration file: " + e.getMessage());
        }
        return configs;
    }


}
