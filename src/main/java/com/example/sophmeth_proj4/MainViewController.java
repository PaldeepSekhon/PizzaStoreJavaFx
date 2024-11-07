package com.example.sophmeth_proj4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainViewController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void handleExit(ActionEvent actionEvent) {
    }

    public void showChicagoView(ActionEvent actionEvent) {
    }

    public void showNewYorkView(ActionEvent actionEvent) {
    }

    public void showCurrentOrderView(ActionEvent actionEvent) {
    }

    public void showStoreOrdersView(ActionEvent actionEvent) {
    }
}