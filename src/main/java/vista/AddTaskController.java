package main.java.vista;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.java.controlador.AppLegada;
import main.java.controlador.Comunicacion3270WS;
import main.java.controlador.ComunicacionMusicSP;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddTaskController {
    Comunicacion3270WS comunicacionWS = Comunicacion3270WS.getInstancia();
    ComunicacionMusicSP comunicacionSP = ComunicacionMusicSP.getInstancia(comunicacionWS);
    AppLegada appLegada = AppLegada.getInstancia(comunicacionWS);

    @FXML
    private Button botonEnter;

    @FXML
    TextField campoNumero;

    @FXML
    TextField campoNombre;

    @FXML
    TextField campoDescripcion;

    @FXML
    DatePicker campoFecha;

    @FXML
    private void botonEnter() {
        IntroducirTarea();
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

    private void IntroducirTarea() {
        System.out.println("Exito introducir");
        final String numero = campoNumero.getText();
        final String nombre = campoNombre.getText();
        final String descripcion = campoDescripcion.getText();
        final LocalDate fecha = campoFecha.getValue();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd MM yyyy");
        String fechaAux = df.format(fecha);
        String [] ddmmyy = fechaAux.split(" ");

        try {
            appLegada.añadirTarea(numero, nombre, descripcion, ddmmyy [0], ddmmyy[1], ddmmyy[2]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
