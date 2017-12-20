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

@SuppressWarnings("ALL")

public class MenuController {

    Comunicacion3270WS comunicacionWS = Comunicacion3270WS.getInstancia();
    ComunicacionMusicSP comunicacionSP = ComunicacionMusicSP.getInstancia(comunicacionWS);
    AppLegada appLegada = AppLegada.getInstancia(comunicacionWS);

    @FXML
    private Button botonNewTaskFile;

    @FXML
    private Button botonAddTaskMenu;

    @FXML
    private Button botonAddTask;

    /*@FXML
    private Button botonRemoveTask;
*/
    @FXML
    private Button botonSaveTask;

    @FXML
    private Button botonGetTareas;

    @FXML
    private Button botonSearchTask;

    @FXML
    private Button botonLogout;

    @FXML
    private void initialize() {
    }

    @FXML
    private void botonNewTaskFile() {
        Stage ventana = MainApp.primaryStage;
        Scene escena = ventana.getScene();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("NewTask.fxml"));
        try {
            escena.setRoot(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void botonAddTaskMenu() {
        appLegada.cambiarMenuNuevaTarea();
        Stage ventana = MainApp.primaryStage;
        Scene escena = ventana.getScene();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("AddTask.fxml"));
        try {
            escena.setRoot(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* @FXML
     private void botonRemoveTask() {
         Stage ventana = MainApp.primaryStage;
         Scene escena = ventana.getScene();
         FXMLLoader loader = new FXMLLoader();
         loader.setLocation(MainApp.class.getResource("RemoveTask.fxml"));
         try {
             escena.setRoot(loader.load());
         } catch (IOException e) {
             e.printStackTrace();
         }
     }
 */
    @FXML
    private void botonSaveTask() {
        guardarTarea();
        Stage ventana = MainApp.primaryStage;
        Scene escena = ventana.getScene();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("SaveTask.fxml"));
        try {
            escena.setRoot(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void guardarTarea() {
        appLegada.guardarTareas();
        System.out.println("Guardado correcto");
    }

    @FXML
    private void botonGetTareas() {
        Stage ventana = MainApp.primaryStage;
        Scene escena = ventana.getScene();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("ListedTasks.fxml"));
        try {
            escena.setRoot(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void botonSearchTask() {
        Stage ventana = MainApp.primaryStage;
        Scene escena = ventana.getScene();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("SearchTask.fxml"));
        try {
            escena.setRoot(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void botonLogout() {
        appLegada.logout();
        comunicacionSP.logout();
        Stage ventana = MainApp.primaryStage;
        Scene escena = ventana.getScene();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("Portada.fxml"));
        try {
            escena.setRoot(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Logout Correcto");
    }
}