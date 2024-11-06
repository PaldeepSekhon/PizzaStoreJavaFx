module com.example.sophmeth_proj4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.sophmeth_proj4 to javafx.fxml;
    exports com.example.sophmeth_proj4;
}