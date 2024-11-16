package com.example.sophmeth_proj4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.EnumSet;

public class ChicagoViewController {

    @FXML
    private ComboBox<String> pizzaTypeComboBox;

    @FXML
    private ComboBox<String> sizeComboBox;

    @FXML
    private Label crustLabel;

    @FXML
    private ImageView pizzaImageView;

    @FXML
    private ListView<Topping> includedToppingsListView;

    @FXML
    private ListView<Topping> availableToppingsListView;

    @FXML
    private Label priceLabel;

    private ObservableList<Topping> availableToppings;
    private ObservableList<Topping> includedToppings;

    private double basePrice = 0;
    private double toppingPrice = 1.69; // Price per topping

    private PizzaFactory pizzaFactory;
    private Pizza currentPizza;
    private Order currentOrder; // Tracks the current order
    private OrderManager orderManager; // Tracks all orders

    public ChicagoViewController(){
        this.pizzaFactory = new ChicagoPizza(); // Chicago-style pizza factory
        this.availableToppings = FXCollections.observableArrayList(EnumSet.allOf(Topping.class));
        this.includedToppings = FXCollections.observableArrayList();

        this.currentOrder = new Order(); // Start a new order
        this.orderManager = new OrderManager(); // Initialize the order manager
    }
    @FXML
    public void initialize() {
        // Populate pizza types
        pizzaTypeComboBox.setItems(FXCollections.observableArrayList("Deluxe", "BBQ Chicken", "Meatzza", "Build Your Own"));

        // Populate sizes
        sizeComboBox.setItems(FXCollections.observableArrayList("Small", "Medium", "Large"));

        // Set available toppings
        availableToppingsListView.setItems(availableToppings);

        // Set included toppings
        includedToppingsListView.setItems(includedToppings);

        sizeComboBox.setOnAction(event -> updateBasePrice());

        // Default crust label
        crustLabel.setText("");
    }

    public void handleAddToOrder(ActionEvent actionEvent) {
        String pizzaType = pizzaTypeComboBox.getSelectionModel().getSelectedItem();
        String size = sizeComboBox.getSelectionModel().getSelectedItem();

        if (pizzaType != null && size != null) {
            Size pizzaSize = Size.valueOf(size.toUpperCase());
            switch (pizzaType) {
                case "Deluxe":
                    currentPizza = pizzaFactory.createDeluxe(pizzaSize);
                    break;
                case "BBQ Chicken":
                    currentPizza = pizzaFactory.createBBQChicken(pizzaSize);
                    break;
                case "Meatzza":
                    currentPizza = pizzaFactory.createMeatzza(pizzaSize);
                    break;
                case "Build Your Own":
                    currentPizza = pizzaFactory.createBuildYourOwn(pizzaSize, new ArrayList<>(includedToppings));
                    break;
                default:
                    currentPizza = null;
            }

            if (currentPizza != null) {
                // Add pizza to the current order
                currentOrder.addPizza(currentPizza);

                // Print a confirmation


                // Update order price
                updatePrice();
            }
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

    public void handleRemoveTopping(ActionEvent actionEvent) {
        Topping selectedTopping = includedToppingsListView.getSelectionModel().getSelectedItem();
        if (selectedTopping != null) {
            includedToppings.remove(selectedTopping);
            availableToppings.add(selectedTopping);
            updatePrice();
        }
    }

    @FXML
    public void handleAddTopping(ActionEvent actionEvent) {
        Topping selectedTopping = availableToppingsListView.getSelectionModel().getSelectedItem();
        if (selectedTopping != null && includedToppings.size() < 7) {
            includedToppings.add(selectedTopping);
            availableToppings.remove(selectedTopping);
            updatePrice();

        }
    }

    private void updatePrice() {
        double price = basePrice + (includedToppings.size() * toppingPrice);
        priceLabel.setText(String.format("%.2f", price));
    }

    private void updateBasePrice() {
        String size = sizeComboBox.getSelectionModel().getSelectedItem();
        String pizzaType = pizzaTypeComboBox.getSelectionModel().getSelectedItem();

        if (size != null && pizzaType != null) {
            Size pizzaSize = Size.valueOf(size.toUpperCase());
            switch (pizzaType) {
                case "Deluxe":
                    basePrice = getDeluxeBasePrice(pizzaSize);
                    break;
                case "BBQ Chicken":
                    basePrice = getBBQChickenBasePrice(pizzaSize);
                    break;
                case "Meatzza":
                    basePrice = getMeatzzaBasePrice(pizzaSize);
                    break;
                case "Build Your Own":
                    basePrice = getBuildYourOwnBasePrice(pizzaSize);
                    break;
            }
        } else {
            basePrice = 0;
        }
        updatePrice(); // Refresh the price display
    }

    private double getDeluxeBasePrice(Size size) {
        switch (size) {
            case SMALL: return 16.99;
            case MEDIUM: return 18.99;
            case LARGE: return 20.99;
            default: return 0;
        }
    }

    private double getBBQChickenBasePrice(Size size) {
        switch (size) {
            case SMALL: return 14.99;
            case MEDIUM: return 16.99;
            case LARGE: return 19.99;
            default: return 0;
        }
    }

    private double getMeatzzaBasePrice(Size size) {
        switch (size) {
            case SMALL: return 17.99;
            case MEDIUM: return 19.99;
            case LARGE: return 21.99;
            default: return 0;
        }
    }

    private double getBuildYourOwnBasePrice(Size size) {
        switch (size) {
            case SMALL: return 8.99;
            case MEDIUM: return 10.99;
            case LARGE: return 12.99;
            default: return 0;
        }
    }

}
