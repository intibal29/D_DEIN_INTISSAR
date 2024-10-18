package com.example.ejerd;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;


public class HelloApplication extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        // Cargar el archivo FXML que contiene la estructura de la interfaz de usuario
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ejerd/hello-view.fxml"));
        Parent root = loader.load();  // Carga el contenido del archivo FXML en un objeto Parent

        // Establecer el título de la ventana
        primaryStage.setTitle("Personas");

        // Crear una nueva escena con el contenido del archivo FXML y establecer las dimensiones de la ventana
        primaryStage.setScene(new Scene(root, 600, 400));

        // Mostrar la ventana principal
        primaryStage.show();
    }


    public static void main(String[] args) {
        // Llama al método launch para iniciar la aplicación JavaFX
        launch(args);
    }
}
