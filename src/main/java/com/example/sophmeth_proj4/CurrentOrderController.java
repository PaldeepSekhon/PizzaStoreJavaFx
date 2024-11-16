package com.example.sophmeth_proj4;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.IOException;

public class CurrentOrderController {
    @FXML
    private Label orderNumberLabel;

    @FXML
    private ListView<String> orderItemsListView;

    @FXML
    private Label subtotalLabel;

    @FXML
    private Label taxLabel;

    @FXML
    private Label totalLabel;

    private Order currentOrder;

    private ObservableList<String> orderItems = FXCollections.observableArrayList();


    @FXML
    public void initialize() {
        // Bind the ObservableList to the ListView
        orderItemsListView.setItems(orderItems);

        // Access the global current order
        currentOrder = GlobalOrder.getCurrentOrder();

        // Populate the view with order details
        orderNumberLabel.setText(String.valueOf(currentOrder.getNumber()));

        for (Pizza pizza : currentOrder.getPizzas()) {
            StringBuilder pizzaDetails = new StringBuilder();


            // Add pizza type (class name), size, and crust
            pizzaDetails.append(pizza.getClass().getSimpleName())
                    .append(" - Size: ").append(pizza.getSize())
                    .append(", Crust: ").append(pizza.getCrust());

            // Add toppings if available
            if (pizza.getToppings().isEmpty()) {
                pizzaDetails.append(" - Toppings: None");
            } else {
                pizzaDetails.append(" - Toppings: ").append(pizza.getToppings());
            }

            // Add the price
            pizzaDetails.append(" - Price: $").append(String.format("%.2f", pizza.price()));

            // Add the formatted string to the ObservableList
            orderItems.add(pizzaDetails.toString());
        }

        // Update totals
        updateTotals();
    }
    private void updateTotals() {
        double subtotal = this.currentOrder.calculateTotal();
        double tax = this.currentOrder.calculateSalesTax();
        double total = this.currentOrder.calculateOrderTotal();

        subtotalLabel.setText(String.format("%.2f", subtotal));
        taxLabel.setText(String.format("%.2f", tax));
        totalLabel.setText(String.format("%.2f", total));
    }
    public void handleRemoveSelected(ActionEvent actionEvent) {
    }

    public void handleClearOrder(ActionEvent actionEvent) {
    }

    public void handlePlaceOrder(ActionEvent actionEvent) {
        if (!GlobalOrder.getCurrentOrder().getPizzas().isEmpty()) {
            // Add the current order to the store orders
            GlobalStore.getStoreOrders().addOrder(GlobalOrder.getCurrentOrder());

            // Reset the current order
            GlobalOrder.resetCurrentOrder();

            // Show confirmation
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Order Placed");
            alert.setHeaderText("Your order has been placed!");
            alert.setContentText("Order Number: " + (GlobalStore.getStoreOrders().getAllOrders().size()));
            alert.showAndWait();

            // Clear UI elements
            orderItemsListView.getItems().clear();
            subtotalLabel.setText("0.00");
            taxLabel.setText("0.00");
            totalLabel.setText("0.00");
            orderNumberLabel.setText(String.valueOf(GlobalOrder.getCurrentOrder().getNumber()));
        } else {
            // Show an error if no pizzas are in the order
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No Pizzas in Order");
            alert.setContentText("Please add pizzas to the order before placing it.");
            alert.showAndWait();
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
}
