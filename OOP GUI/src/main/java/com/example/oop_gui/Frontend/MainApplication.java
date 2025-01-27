package com.example.oop_gui.Frontend;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import com.example.oop_gui.backend.TicketPool;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.example.oop_gui.backend.Vendor;
import com.example.oop_gui.backend.Customer;

public class MainApplication extends Application {

    private TicketPool ticketPool;

    private TextField eventNameField = new TextField();
    private TextField totalTicketsField = new TextField();
    private TextField releaseRateField = new TextField();
    private TextField customerRateField = new TextField();
    private TextField maxCapacityField = new TextField();
    private TextField vendorIdField = new TextField();
    private TextField customerNameField = new TextField();
    private TextArea logArea = new TextArea();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Real-Time Event Ticketing System");

        // Input Fields
        VBox configBox = new VBox(10);
        configBox.getChildren().addAll(
                new Label("Event Name:"), eventNameField,
                new Label("Total Tickets:"), totalTicketsField,
                new Label("Release Rate (ms):"), releaseRateField,
                new Label("Customer Rate (ms):"), customerRateField,
                new Label("Max Capacity:"), maxCapacityField,
                new Label("Vendor ID:"), vendorIdField,
                new Label("Customer Name:"), customerNameField
        );

        // Buttons
        Button startButton = new Button("Start");
        startButton.setOnAction(e -> startTicketingSystem());

        Button stopButton = new Button("Stop");
        stopButton.setOnAction(e -> stopTicketingSystem());

        HBox buttonBox = new HBox(10, startButton, stopButton);

        // Log Area
        logArea.setEditable(false);

        // Layout
        VBox mainLayout = new VBox(20, configBox, buttonBox, new Label("Logs:"), logArea);
        mainLayout.setPadding(new javafx.geometry.Insets(10));

        Scene scene = new Scene(mainLayout, 400, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void startTicketingSystem() {
        // Parse Configuration
        String eventName = eventNameField.getText();
        int totalTickets = Integer.parseInt(totalTicketsField.getText());
        int releaseRate = Integer.parseInt(releaseRateField.getText());
        int customerRate = Integer.parseInt(customerRateField.getText());
        int maxCapacity = Integer.parseInt(maxCapacityField.getText());
        String vendorId = vendorIdField.getText();
        String customerName = customerNameField.getText();

        ticketPool = new TicketPool(maxCapacity);

        // Start Vendor and Customer Threads
        Vendor vendor = new Vendor(ticketPool, totalTickets, releaseRate, vendorId);
        Customer customer = new Customer(ticketPool, totalTickets, customerRate, customerName);

        vendor.start();
        customer.start();

        log("System started with " + totalTickets + " tickets. Vendor ID: " + vendorId + ", Customer Name: " + customerName);
    }

    private void stopTicketingSystem() {
        log("System stopped.");
        // Logic to gracefully stop threads could be added here.
    }

    private void log(String message) {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        Platform.runLater(() -> logArea.appendText("[" + timeStamp + "] " + message + "\n"));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
