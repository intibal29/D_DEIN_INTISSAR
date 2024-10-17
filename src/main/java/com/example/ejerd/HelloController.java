package com.example.ejerd;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * La clase {@code HelloController} es el controlador de la interfaz gráfica de la aplicación.
 * Gestiona la interacción entre la vista (definida en un archivo FXML) y la lógica del programa.
 * Se encarga de manejar la lógica de la aplicación de gestión de personas, incluyendo agregar,
 * modificar, eliminar y seleccionar personas en una tabla.
 */
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

    /**
     * El método {@code initialize} se llama automáticamente después de que se carga la vista FXML.
     * Inicializa la tabla y asigna las propiedades de cada columna a los campos correspondientes
     * de los objetos {@code Persona}. También se configura un listener para detectar cuando se
     * selecciona una persona en la tabla.
     */
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

    /**
     * Método para agregar una nueva persona a la lista y mostrarla en la tabla.
     * Valida que los campos no estén vacíos y que la edad sea un número válido.
     * Muestra una alerta si la persona ya existe en la lista.
     */
    @FXML
    private void agregarPersona() {
        // Obtener los valores de los campos de texto
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

        // Crear un nuevo objeto Persona
        Persona nuevaPersona = new Persona(nombre, apellidos, edad);

        // Verificar si la persona ya existe en la lista
        if (listaPersonas.contains(nuevaPersona)) {
            mostrarAlerta("Error", "Esta persona ya existe.");
        } else {
            // Agregar la nueva persona a la lista
            listaPersonas.add(nuevaPersona);
            mostrarAlerta("Éxito", "Persona agregada correctamente.");
        }
    }

    /**
     * Método que se invoca al seleccionar una persona en la tabla.
     * Carga los datos de la persona seleccionada en los campos de texto.
     */
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

    /**
     * Método para modificar los datos de una persona seleccionada en la tabla.
     * Valida que se haya seleccionado una persona y que los campos no estén vacíos.
     * Actualiza los datos de la persona y refresca la tabla.
     */
    @FXML
    private void modificarPersona() {
        // Obtener la persona seleccionada
        Persona personaSeleccionada = tablaPersonas.getSelectionModel().getSelectedItem();

        // Validar que se haya seleccionado una persona
        if (personaSeleccionada == null) {
            mostrarAlerta("Error", "No hay ninguna persona seleccionada.");
            return;
        }

        // Obtener los nuevos valores de los campos de texto
        String nuevoNombre = txtNombre.getText();
        String nuevosApellidos = txtApellidos.getText();
        String nuevaEdadTexto = txtEdad.getText();

        // Validar que los campos no estén vacíos
        if (nuevoNombre.isEmpty() || nuevosApellidos.isEmpty()) {
            mostrarAlerta("Error", "Nombre y apellidos son obligatorios.");
            return;
        }

        // Validar que la edad sea un número entero
        int nuevaEdad;
        try {
            nuevaEdad = Integer.parseInt(nuevaEdadTexto);
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "La edad debe ser un número entero.");
            return;
        }

        // Actualizar los datos de la persona seleccionada
        personaSeleccionada.setNombre(nuevoNombre);
        personaSeleccionada.setApellidos(nuevosApellidos);
        personaSeleccionada.setEdad(nuevaEdad);

        // Refrescar la tabla para mostrar los nuevos datos
        tablaPersonas.refresh();
        mostrarAlerta("Éxito", "Persona modificada correctamente.");
    }

    /**
     * Método para eliminar la persona seleccionada en la tabla.
     * Borra los datos de la persona y limpia los campos de texto.
     */
    @FXML
    private void eliminarPersona() {
        // Obtener la persona seleccionada
        Persona personaSeleccionada = tablaPersonas.getSelectionModel().getSelectedItem();

        // Validar que se haya seleccionado una persona
        if (personaSeleccionada == null) {
            mostrarAlerta("Error", "No hay ninguna persona seleccionada.");
            return;
        }

        // Eliminar la persona de la lista
        listaPersonas.remove(personaSeleccionada);

        // Limpiar los campos de texto
        txtNombre.clear();
        txtApellidos.clear();
        txtEdad.clear();

        mostrarAlerta("Éxito", "Persona eliminada correctamente.");
    }

    /**
     * Método auxiliar para mostrar una alerta de información en la interfaz.
     *
     * @param titulo  El título de la alerta.
     * @param mensaje El mensaje que se mostrará en la alerta.
     */
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
