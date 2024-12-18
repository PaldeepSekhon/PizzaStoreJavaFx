package com.example.sophmeth_proj4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.Node;

import java.io.IOException;
/**
 * Represents the controller for the main view or main menu
 * @author paldeepsekhon, adityaponni
 */
public class MainViewController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void handleExit(ActionEvent actionEvent) {
    }

    @FXML
    public void showChicagoView(ActionEvent actionEvent) {
        try {
            // Load the new FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("chicagoview.fxml"));
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
    public void showNewYorkView(ActionEvent actionEvent) {
        try {
            // Load the new FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("nyview.fxml"));
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
    public void showCurrentOrderView(ActionEvent actionEvent) {
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

    @FXML
    public void showStoreOrdersView(ActionEvent actionEvent) {
        try {
            // Load the new FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("store_orderview.fxml"));
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