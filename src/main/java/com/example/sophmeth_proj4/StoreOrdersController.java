package com.example.sophmeth_proj4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import java.io.File;
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
                        .append(" (Size: ").append(pizza.getSize())
                        .append(", Crust: ").append(pizza.getCrust())
                        .append("): $").append(String.format("%.2f", pizza.price()))
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

    @FXML
    public void handleExportOrders(ActionEvent actionEvent) {
        // Create a FileChooser for selecting the file location and name
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Orders");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        fileChooser.setInitialFileName("store_orders.txt");

        // Show the save file dialog
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        File selectedFile = fileChooser.showSaveDialog(stage);

        // Check if the user canceled the file dialog
        if (selectedFile == null) {
            return;
        }

        try (FileWriter writer = new FileWriter(selectedFile)) {
            // Check if an order is selected in the ListView
            String selectedOrder = storeOrdersListView.getSelectionModel().getSelectedItem();

            if (selectedOrder != null) {
                // Extract the order number from the selected item
                int orderNumber = Integer.parseInt(selectedOrder.split("#")[1]);
                Order order = storeOrders.getOrder(orderNumber);

                // Export only the selected order
                exportOrder(writer, orderNumber, order);
            } else {
                // If no order is selected, export all orders
                Map<Integer, Order> allOrders = storeOrders.getAllOrders();

                if (allOrders.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("No Orders to Export");
                    alert.setHeaderText("There are no orders to export.");
                    alert.showAndWait();
                    return;
                }

                // Export all orders
                for (Map.Entry<Integer, Order> entry : allOrders.entrySet()) {
                    exportOrder(writer, entry.getKey(), entry.getValue());
                }
            }

            // Show success alert
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Export Successful");
            successAlert.setHeaderText("Orders have been exported successfully.");
            successAlert.setContentText("File saved to: " + selectedFile.getAbsolutePath());
            successAlert.showAndWait();

        } catch (IOException e) {
            // Handle any exceptions during file writing
            e.printStackTrace();
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Export Failed");
            errorAlert.setHeaderText("An error occurred while exporting the orders.");
            errorAlert.setContentText(e.getMessage());
            errorAlert.showAndWait();
        }
    }

    private void exportOrder(FileWriter writer, int orderNumber, Order order) throws IOException {
        if (order != null) {
            writer.write("Order #" + orderNumber + "\n");
            for (Pizza pizza : order.getPizzas()) {
                writer.write("  - " + pizza.getClass().getSimpleName() + " (Size: " + pizza.getSize() + ", Crust: " + pizza.getCrust() + "): $" + String.format("%.2f", pizza.price()) + "\n");
                writer.write("    Toppings: " + (pizza.getToppings().isEmpty() ? "None" : pizza.getToppings()) + "\n");
            }
            writer.write("Subtotal: $" + String.format("%.2f", order.calculateTotal()) + "\n");
            writer.write("Tax: $" + String.format("%.2f", order.calculateSalesTax()) + "\n");
            writer.write("Total: $" + String.format("%.2f", order.calculateOrderTotal()) + "\n");
            writer.write("=====================================\n");
        }
    }

    @FXML
    public void handleCancelOrder(ActionEvent actionEvent) {
        // Get the selected item from the ListView
        String selectedOrder = storeOrdersListView.getSelectionModel().getSelectedItem();

        if (selectedOrder != null) {
            // Extract the order number from the selected item
            int orderNumber = Integer.parseInt(selectedOrder.split("#")[1]);

            // Confirm cancellation with the user
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Cancel Order");
            confirmationAlert.setHeaderText("Are you sure you want to cancel Order #" + orderNumber + "?");
            confirmationAlert.setContentText("This action cannot be undone.");
            confirmationAlert.showAndWait();

            // Remove the order from the StoreOrders
            storeOrders.removeOrder(orderNumber);

            // Remove the selected item from the ListView
            storeOrdersListView.getItems().remove(selectedOrder);

            // Clear the order details area
            orderDetailsTextArea.clear();

            // Show confirmation alert
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Order Cancelled");
            alert.setHeaderText("Order #" + orderNumber + " has been successfully cancelled.");
            alert.showAndWait();
        } else {
            // Show an alert if no order is selected
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Order Selected");
            alert.setContentText("Please select an order to cancel.");
            alert.showAndWait();
        }
    }

}
