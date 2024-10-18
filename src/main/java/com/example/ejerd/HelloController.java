package com.example.ejerd;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

    // Campos de texto de la interfaz para capturar nombre, apellidos y edad
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellidos;
    @FXML
    private TextField txtEdad;

    // Tabla y columnas donde se muestran las personas
    @FXML
    private TableView<Persona> tablaPersonas;
    @FXML
    private TableColumn<Persona, String> colNombre;
    @FXML
    private TableColumn<Persona, String> colApellidos;
    @FXML
    private TableColumn<Persona, Integer> colEdad;

    // Lista observable que contiene las personas para mostrarlas en la tabla
    private final ObservableList<Persona> listaPersonas = FXCollections.observableArrayList();


    @FXML
    public void initialize() {
        // Enlazar los datos de las columnas con las propiedades de la clase Persona
        colNombre.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getNombre()));
        colApellidos.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getApellidos()));
        colEdad.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getEdad()));

        // Asignar la lista observable a la tabla para que se muestren las personas
        tablaPersonas.setItems(listaPersonas);

        // Listener para cargar los datos de la persona seleccionada en los campos de texto
        tablaPersonas.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> seleccionarPersona());
    }


    @FXML
    private void agregarPersona() {
        try {
            // Cargar el archivo FXML de la ventana modal
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ejerd/AgregarPersona.fxml"));
            Parent root = loader.load();

            // Crear una nueva escena para la ventana modal
            Stage stage = new Stage();
            stage.setTitle("Nueva Persona");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL); // Modal para bloquear la ventana principal
            stage.setResizable(false); // No permitir cambiar el tamaño de la ventana

            // Obtener el controlador de la ventana modal
            AgregarPersonaController agregarPersonaController = loader.getController();

            // Mostrar la ventana modal y esperar a que se cierre
            stage.showAndWait();

            // Si los datos son válidos, agregar la persona a la tabla
            Persona nuevaPersona = agregarPersonaController.getPersona();
            if (nuevaPersona != null) {
                listaPersonas.add(nuevaPersona);
                mostrarAlerta("Éxito", "Persona agregada correctamente.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @FXML
    private void seleccionarPersona() {
        // Obtener la persona seleccionada de la tabla
        Persona personaSeleccionada = tablaPersonas.getSelectionModel().getSelectedItem();
        if (personaSeleccionada != null) {
            // Cargar los datos en los campos de texto
            txtNombre.setText(personaSeleccionada.getNombre());
            txtApellidos.setText(personaSeleccionada.getApellidos());
            txtEdad.setText(String.valueOf(personaSeleccionada.getEdad()));
        }
    }


    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
