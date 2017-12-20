package main.java.vista;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import main.java.controlador.AppLegada;
import main.java.controlador.Comunicacion3270WS;
import main.java.controlador.ComunicacionMusicSP;
import main.java.controlador.Tarea;

import java.io.IOException;

@SuppressWarnings("ALL")
public class ListController {

    Comunicacion3270WS comunicacionWS = Comunicacion3270WS.getInstancia();
    ComunicacionMusicSP comunicacionSP = ComunicacionMusicSP.getInstancia(comunicacionWS);
    AppLegada appLegada = AppLegada.getInstancia(comunicacionWS);

    @FXML
    private Button botonMenu;

    @FXML
    private Button botonEliminar;

    @FXML
    TableView<Tarea> tablaListar;

    @FXML
    TableColumn<Tarea, String> columnaNumeroTareaLista;

    @FXML
    TableColumn<Tarea, String> columnaNombreLista;

    @FXML
    TableColumn<Tarea, String> columnaDescripcionLista;

    @FXML
    TableColumn<Tarea, String> columnaFechaLista;

    @FXML
    private void initialize() {
        columnaNumeroTareaLista.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getNumeroTarea()));
        columnaNombreLista.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getNombre()));
        columnaDescripcionLista.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getDescripcion()));
        columnaFechaLista.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getFecha()));
        actualizarLista();
    }

    @FXML
    private void botonMenu() {
        Stage ventana = MainApp.primaryStage;
        Scene escena = ventana.getScene();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("Menu.fxml"));

        try {
            escena.setRoot(loader.load());
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void botonEliminar() {
        try {
            Tarea t = this.tablaListar.getSelectionModel().getSelectedItem();
            appLegada.eliminarTarea(t.getNumeroTarea());
            actualizarLista();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void actualizarLista(){
        tablaListar.getItems().clear();
        tablaListar.setItems(FXCollections.observableArrayList(appLegada.getTareas()));
    }
}
