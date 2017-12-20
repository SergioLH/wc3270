package main.java.vista;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import main.java.controlador.AppLegada;
import main.java.controlador.Comunicacion3270WS;
import main.java.controlador.ComunicacionMusicSP;

import java.io.IOException;

/**
 * @author Seryak
 */

public class PortadaController {
    Comunicacion3270WS comunicacionWS = Comunicacion3270WS.getInstancia();
    ComunicacionMusicSP comunicacionSP = ComunicacionMusicSP.getInstancia(comunicacionWS);
    AppLegada appLegada = AppLegada.getInstancia(comunicacionWS);

    @FXML
    private Button botonConectar;

    @FXML
    private void initialize() {
    }

    @FXML
    private void botonConectar() {
        conectar();
        Stage ventana = MainApp.primaryStage;
        Scene escena = ventana.getScene();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("Login.fxml"));

        try {
            escena.setRoot(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void conectar() {
        comunicacionSP.conectar();
    }
        /*
        Task<Void> tarea = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                comunicacionSP.conectar();
                System.out.println(comunicacionSP.verPantalla());
                return null;
            }
        };
        tarea.setOnSucceeded((event -> {
            // Código en caso de qu e vaya todo bien
        }));
        tarea.setOnFailed((event -> {
            // Códig en caso de que se vaya a la mierda
        }));
        new Thread(tarea).start();
    }*/
}