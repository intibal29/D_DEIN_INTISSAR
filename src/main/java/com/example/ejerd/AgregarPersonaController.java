package com.example.ejerd;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AgregarPersonaController {

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellidos;
    @FXML
    private TextField txtEdad;

    private Persona persona;


    @FXML
    private void guardarPersona() {
        String nombre = txtNombre.getText();
        String apellidos = txtApellidos.getText();
        String edadTexto = txtEdad.getText();

        // Validar que los campos de nombre y apellidos no estén vacíos
        if (nombre.isEmpty() || apellidos.isEmpty()) {
            mostrarAlerta("Error", "Nombre y apellidos son obligatorios.");
            return;
        }

        // Validar que la edad sea un número entero
        int edad;
        try {
            edad = Integer.parseInt(edadTexto);
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "La edad debe ser un número entero.");
            return;
        }

        // Crear una nueva persona con los datos ingresados
        persona = new Persona(nombre, apellidos, edad);

        // Cerrar la ventana
        Stage stage = (Stage) txtNombre.getScene().getWindow();
        stage.close();
    }


    @FXML
    private void cancelar() {
        // Cerrar la ventana
        Stage stage = (Stage) txtNombre.getScene().getWindow();
        stage.close();
    }

    public Persona getPersona() {
        return persona;
    }



    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
