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

    public ChicagoViewController(){
        this.pizzaFactory = new ChicagoPizza(); // Chicago-style pizza factory
        this.availableToppings = FXCollections.observableArrayList(EnumSet.allOf(Topping.class));
        this.includedToppings = FXCollections.observableArrayList();
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

        // Default crust label
        crustLabel.setText("");
    }

    public void handleAddToOrder(ActionEvent actionEvent) {
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

}
