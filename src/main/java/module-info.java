module com.example.ejerd {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ejerd to javafx.fxml;
    exports com.example.ejerd;
}