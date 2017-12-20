package main.java.vista;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import main.java.controlador.AppLegada;
import main.java.controlador.Comunicacion3270WS;
import main.java.controlador.ComunicacionMusicSP;
import main.java.controlador.Tarea;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@SuppressWarnings("ALL")
public class SearchController {
    Comunicacion3270WS comunicacionWS = Comunicacion3270WS.getInstancia();
    ComunicacionMusicSP comunicacionSP = ComunicacionMusicSP.getInstancia(comunicacionWS);
    AppLegada appLegada = AppLegada.getInstancia(comunicacionWS);

    @FXML
    private Button botonBuscar;

    @FXML
    private Button botonMenu;

    @FXML
    private Button botonEliminar;

    @FXML
    TableView<Tarea> tablaBusqueda;

    @FXML
    TableColumn<Tarea, String> columnaNumeroTarea;

    @FXML
    TableColumn<Tarea, String> columnaNombre;

    @FXML
    TableColumn<Tarea, String> columnaDescripcion;

    @FXML
    TableColumn<Tarea, String> columnaFecha;

    @FXML
    DatePicker campoFecha;

    @FXML
    private void initialize() {
        columnaNumeroTarea.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getNumeroTarea()));
        columnaNombre.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getNombre()));
        columnaDescripcion.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getDescripcion()));
        columnaFecha.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getFecha()));

    }

    @FXML
    private void botonBuscar() {
        buscarTarea();
    }

    @FXML
    private void BotonMenu() {
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
            Tarea t = this.tablaBusqueda.getSelectionModel().getSelectedItem();
            appLegada.eliminarTarea(t.getNumeroTarea());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void buscarTarea() {

        final LocalDate fecha = campoFecha.getValue();
        String textoBuscado = "";
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd MM yyyy");
        try {
            String fechaAux = df.format(fecha);
            String[] ddmmyy = fechaAux.split(" ");

            tablaBusqueda.setItems(FXCollections.observableArrayList(appLegada.buscarTareas(ddmmyy[0], ddmmyy[1], ddmmyy[2])));
        } catch (
                Exception e)

        {
            e.printStackTrace();
        }
    }
}
