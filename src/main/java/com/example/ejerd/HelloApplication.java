package com.example.ejerd;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.util.Objects;

/**
 * La clase {@code HelloApplication} es la clase principal que inicia la aplicación JavaFX.
 * Extiende la clase {@code Application}, que es el punto de entrada para aplicaciones JavaFX.
 *
 * El objetivo de esta aplicación es cargar una interfaz gráfica de usuario desde un archivo FXML
 * y mostrarla en una ventana de gestión de personas.
 */
public class HelloApplication extends Application {

    /**
     * El método {@code start} es el punto de entrada para la aplicación JavaFX. Este método se
     * ejecuta cuando la aplicación se inicia y se encarga de configurar y mostrar la ventana principal.
     *
     * @param primaryStage El escenario principal (ventana) de la aplicación donde se muestra la interfaz.
     * @throws Exception Si ocurre un error al cargar el archivo FXML.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Carga el archivo FXML que contiene la estructura de la interfaz de usuario
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/C/hello-view.fxml"));
        Parent root = loader.load();  // Carga el contenido del archivo FXML en un objeto Parent

        // Establece el título de la ventana
        primaryStage.setTitle("Gestión de Personas");

        // Crea una nueva escena con el contenido del archivo FXML y establece las dimensiones de la ventana
        primaryStage.setScene(new Scene(root, 600, 400));

        // Muestra la ventana principal
        primaryStage.show();
    }

    /**
     * El método principal {@code main} es el punto de entrada estándar para cualquier aplicación Java.
     * En el caso de aplicaciones JavaFX, este método llama a {@code launch}, que inicia el ciclo de vida
     * de la aplicación JavaFX.
     *
     * @param args Los argumentos de la línea de comandos (si hay).
     */
    public static void main(String[] args) {
        // Llama al método launch para iniciar la aplicación JavaFX
        launch(args);
    }
}
