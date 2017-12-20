package main.java.vista;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.java.controlador.AppLegada;
import main.java.controlador.Comunicacion3270WS;
import main.java.controlador.ComunicacionMusicSP;

import java.io.IOException;

public class LoginController {
    Comunicacion3270WS comunicacionWS = Comunicacion3270WS.getInstancia();
    ComunicacionMusicSP comunicacionSP = ComunicacionMusicSP.getInstancia(comunicacionWS);
    AppLegada appLegada = AppLegada.getInstancia(comunicacionWS);

    @FXML
    private Button botonValidar;

    @FXML
    private Button botonSalir;

    @FXML
    private TextField campoUser;

    @FXML
    PasswordField campoContaseña;

    @FXML
    private void initialize() {
    }

    @FXML
    private void botonValidar() {
        try {
            logearse();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void botonSalir() {
        salir();
        Stage ventana = MainApp.primaryStage;
        Scene escena = ventana.getScene();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("Portada.fxml"));
        try {
            escena.setRoot(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void logearse() throws Exception {
        final String usuario = campoUser.getText();
        final String contraseña = campoContaseña.getText();
        Boolean exito = comunicacionSP.login(usuario, contraseña);
        System.out.println(usuario);
        System.out.println(contraseña);
        if (exito) {
            System.out.println("Exito");
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
        } else {
            System.out.println("Fracaso");
        }
    }

    private void salir() {
        Task<Void> tarea = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                comunicacionSP.logout();
                return null;
            }
        };
        tarea.setOnSucceeded((event -> {
            // Código en caso de que vaya todo bien
        }));
        tarea.setOnFailed((event -> {
            // Código en caso de que se vaya a la mierda
        }));
        new Thread(tarea).start();
    }
}