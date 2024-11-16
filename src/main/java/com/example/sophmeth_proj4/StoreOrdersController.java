package com.example.sophmeth_proj4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.io.IOException;

public class StoreOrdersController {

    @FXML
    private ListView<String> storeOrdersListView;

    @FXML
    private TextArea orderDetailsTextArea;

    private StoreOrders storeOrders;

    @FXML
    public void initialize() {
         storeOrders = GlobalStore.getStoreOrders();

        // Populate the ListView with order numbers
        for (Map.Entry<Integer, Order> entry : storeOrders.getAllOrders().entrySet()) {
            storeOrdersListView.getItems().add("Order #" + entry.getKey());
        }

        // Display order details when an order is selected
        storeOrdersListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                int orderNumber = Integer.parseInt(newValue.split("#")[1]);
                displayOrderDetails(orderNumber);
            }
        });
    }

    private void displayOrderDetails(int orderNumber) {
        Order order = storeOrders.getOrder(orderNumber);
        if (order != null) {
            StringBuilder details = new StringBuilder("Order Number: ").append(orderNumber).append("\n");
            for (Pizza pizza : order.getPizzas()) {
                details.append("- ")
                        .append(pizza.getClass().getSimpleName())
                        .append(" (")
                        .append(pizza.getSize())
                        .append(", ")
                        .append(pizza.getCrust())
                        .append("): $")
                        .append(String.format("%.2f", pizza.price()))
                        .append("\nToppings: ")
                        .append(pizza.getToppings().isEmpty() ? "None" : pizza.getToppings())
                        .append("\n");
            }
            details.append("Subtotal: $").append(String.format("%.2f", order.calculateTotal())).append("\n");
            details.append("Tax: $").append(String.format("%.2f", order.calculateSalesTax())).append("\n");
            details.append("Total: $").append(String.format("%.2f", order.calculateOrderTotal())).append("\n");

            orderDetailsTextArea.setText(details.toString());
        }
    }
    @FXML
    public void handleBackToMain(ActionEvent actionEvent) {
        try {
            // Load the new FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("mainview.fxml"));
            Parent newView = loader.load();

            // Get the current stage
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            // Set the new scene on the current stage
            stage.setScene(new Scene(newView));



            // Show the stage with the new scene
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleExportOrders(ActionEvent actionEvent) {
    }

    public void handleCancelOrder(ActionEvent actionEvent) {

    }
}
