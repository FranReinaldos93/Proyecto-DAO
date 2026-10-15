package com.prog11.tarea.controlador;

import com.prog11.tarea.modelo.Concesionario;
import dao.ConcesionarioDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controlador implements Initializable {

    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfLocalización;
    @FXML
    private ListView lvConcesionarios;
    ObservableList<Concesionario> elementosConcesionario;
    @FXML
    private TextField tfId;
    @FXML
    private Pane paneConcesionario;
    @FXML
    private TextField tfCapacidad;

    Alert alerta = new Alert(Alert.AlertType.INFORMATION);

    /*
     *
     * 1. Declarar e instanciar un objeto de claseDAO, para acceso a las operaciones de bbdd sobre la tabla
    ConcesionarioDAO objetoConcesionario = new ConcesionarioDAO();*/

        ConcesionarioDAO objetoConcesionario = new ConcesionarioDAO();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tfId.setDisable(true);
        paneConcesionario.setDisable(true);
        elementosConcesionario = FXCollections.observableArrayList();
        lvConcesionarios.setItems(elementosConcesionario);



        /*
         * 2. Cargar en el ListView la consulta de todos los concesionarios de la tabla
         */

        try {
            ArrayList<Concesionario> lista = objetoConcesionario.consultarConcesionario();
            elementosConcesionario.setAll(lista);
        } catch (SQLException e) {
            alerta.setAlertType(Alert.AlertType.ERROR);
            alerta.setHeaderText("Error base de datos");
            alerta.setContentText(e.getMessage());
            alerta.showAndWait();
        }


    }

    public void limpiarCampos() {
        tfLocalización.clear();
        tfNombre.clear();
        tfCapacidad.clear();

    }

    @FXML
    public void añadirConcesionario(ActionEvent actionEvent) {
        int id;
        paneConcesionario.setDisable(false);
        //A tfNombre le pasamos el foco del cursor
        tfNombre.requestFocus();
        /*
         * 3. Recuperar el siguiente id de base de datos, select MAX(id)+1 from concesionarios
         * para añadirlo con tfId.setText()*/


        try {
            id = objetoConcesionario.obtenerIdSiguiente();
            tfId.setText(String.valueOf(id));

        } catch (SQLException e) {
            alerta.setAlertType(Alert.AlertType.ERROR);
            alerta.setHeaderText("Error base de datos");
            alerta.setContentText(e.getMessage());
            alerta.showAndWait();
        }

        /*
         * limpiar el resto de campos de concesionario
         */
        limpiarCampos();
    }

    @FXML
    public void eliminarConcesionario(ActionEvent actionEvent) {
        int id;
        int posicion = lvConcesionarios.getSelectionModel().getSelectedIndex();
        if (posicion == -1) {
            alerta.setAlertType(Alert.AlertType.ERROR);
            alerta.setHeaderText("Error eliminación");
            alerta.setContentText("Debe seleccionar el concesionario que desea eliminar");
            alerta.showAndWait();
        } else {

            Concesionario c = (Concesionario) lvConcesionarios.getSelectionModel().getSelectedItem();
            id = c.getId();
            /*
             * 4. Eliminar de base de datos el concesionario*/
             try {
                objetoConcesionario.eliminarConcesionario(id);
                elementosConcesionario.remove(posicion);
             } catch (SQLException e) {
                alerta.setAlertType(Alert.AlertType.ERROR);
                alerta.setHeaderText("Error al eliminar registro de base de datos");
                alerta.setContentText(e.getMessage());
                alerta.showAndWait();
         }


        }

    }

    @FXML
    public void guardarConcesionario(ActionEvent actionEvent) {
        Concesionario c = new Concesionario();
        try {
            c.setId(Integer.parseInt(tfId.getText()));
            c.setNombre(tfNombre.getText());
            c.setLocalizacion(tfLocalización.getText());
        } catch (NumberFormatException e) {
            alerta.setAlertType(Alert.AlertType.ERROR);
            alerta.setHeaderText("Error dato no esperado");
            alerta.setContentText(e.getMessage());
            alerta.showAndWait();
        } catch (Exception e) {
            alerta.setAlertType(Alert.AlertType.ERROR);
            alerta.setHeaderText("Error desconocido");
            alerta.setContentText(e.getMessage());
            alerta.showAndWait();
        }

        /*
         * 5. Insertar registro en base de datos*/
          try {
            objetoConcesionario.insertarConcesionario(c);
            elementosConcesionario.add(c);

            alerta.setAlertType(Alert.AlertType.INFORMATION);

            alerta.setHeaderText("Concesionario guardado correctamente");
            alerta.setContentText(c.toString());
            alerta.showAndWait();
            paneConcesionario.setDisable(true);
         } catch (SQLException e) {
             alerta.setAlertType(Alert.AlertType.ERROR);
             alerta.setHeaderText("Error al eliminar registro de base de datos");
             alerta.setContentText(e.getMessage());
             alerta.showAndWait();
         }

    }
}