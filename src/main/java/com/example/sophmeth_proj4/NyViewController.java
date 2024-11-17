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
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;

import java.io.IOException;

public class NyViewController {
    @FXML
    private ComboBox<String> pizzaTypeComboBox;

    @FXML
    private ComboBox<String> sizeComboBox;

    @FXML
    private Label crustLabel;

    @FXML
    private ListView<Topping> includedToppingsListView;

    @FXML
    private ListView<Topping> availableToppingsListView;

    @FXML
    private Label priceLabel;

    @FXML
    private Button addButton;

    @FXML
    private Button removeButton;

    private ObservableList<Topping> availableToppings;
    private ObservableList<Topping> includedToppings;

    private ObservableList<Topping> deluxeToppings;
    private ObservableList<Topping> bbqToppings;
    private ObservableList<Topping> meatzzaToppings;

    private ObservableList<Topping> originalAvailableToppings;

    private double basePrice = 0;
    private double toppingPrice = 1.69;

    private PizzaFactory pizzaFactory;
    private Pizza currentPizza;
    public NyViewController() {
        this.pizzaFactory = new NYPizza(); // New York-style pizza factory
        this.availableToppings = FXCollections.observableArrayList(EnumSet.allOf(Topping.class));
        this.originalAvailableToppings = FXCollections.observableArrayList(availableToppings);
        this.includedToppings = FXCollections.observableArrayList();
        this.deluxeToppings = FXCollections.observableArrayList(Topping.SAUSAGE, Topping.PEPPERONI, Topping.GREEN_PEPPER, Topping.ONION, Topping.MUSHROOM);
        this.bbqToppings = FXCollections.observableArrayList(Topping.BBQ_CHICKEN, Topping.GREEN_PEPPER, Topping.PROVOLONE, Topping.CHEDDAR);
        this.meatzzaToppings = FXCollections.observableArrayList(Topping.SAUSAGE, Topping.PEPPERONI, Topping.BEEF, Topping.HAM);
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
        pizzaTypeComboBox.setOnAction(event -> handlePizzaTypeSelection());


        // Default crust label
        crustLabel.setText("");
        if (pizzaTypeComboBox.getSelectionModel().getSelectedItem() != null) {
            handlePizzaTypeSelection();
        }
    }

    private void handlePizzaTypeSelection() {
        String selectedPizzaType = pizzaTypeComboBox.getSelectionModel().getSelectedItem();

        if ("Deluxe".equals(selectedPizzaType)) {
            includedToppings.clear();
            availableToppingsListView.setDisable(true);
            includedToppingsListView.setItems(FXCollections.observableArrayList(deluxeToppings));
            crustLabel.setText("Brooklyn");
            addButton.setDisable(true);
            removeButton.setDisable(true);
        } else if ("BBQ Chicken".equals(selectedPizzaType)) {
            includedToppings.clear();
            availableToppingsListView.setDisable(true);
            includedToppingsListView.setItems(FXCollections.observableArrayList(bbqToppings));
            crustLabel.setText("Thin");
            addButton.setDisable(true);
            removeButton.setDisable(true);
        } else if ("Meatzza".equals(selectedPizzaType)) {
            includedToppings.clear();
            availableToppingsListView.setDisable(true);
            includedToppingsListView.setItems(FXCollections.observableArrayList(meatzzaToppings));
            crustLabel.setText("Hand-tossed");
            addButton.setDisable(true);
            removeButton.setDisable(true);
        } else if ("Build Your Own".equals(selectedPizzaType)) {
            availableToppingsListView.setDisable(false);
            includedToppingsListView.setItems(includedToppings);

            crustLabel.setText("Hand-tossed");
            addButton.setDisable(false);
            removeButton.setDisable(false);

        }
    }
    public void handleAddTopping(ActionEvent actionEvent) {
        Topping selectedTopping = availableToppingsListView.getSelectionModel().getSelectedItem();
        if (selectedTopping != null && includedToppings.size() < 7) {
            includedToppings.add(selectedTopping);
            availableToppings.remove(selectedTopping);
            updatePrice();

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
                GlobalOrder.getCurrentOrder().addPizza(currentPizza);
                updatePrice();

                pizzaTypeComboBox.getSelectionModel().clearSelection();
                sizeComboBox.getSelectionModel().clearSelection();
                includedToppings.clear();
                includedToppingsListView.setItems(includedToppings);

                availableToppings.setAll(originalAvailableToppings);
                availableToppingsListView.setItems(availableToppings);
                updatePrice();

            }
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
    public void handleViewCurrentOrder(ActionEvent actionEvent) {
        try {
            // Load the new FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("current_orderview.fxml"));
            Parent currentOrderView = loader.load();

            // Get the current stage from the ActionEvent
            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            // Set the new scene on the current stage
            currentStage.setScene(new Scene(currentOrderView));
            currentStage.setTitle("Current Order");
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
