package main.java.vista;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Seryak
 */

public class MainApp extends Application {

    public static Stage primaryStage;
    private static String Titulo = "Wrapper 3270";
    private BorderPane rootLayout;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle(Titulo);

        showPortada();
        this.primaryStage.show();
    }

    public void showPortada() {
        Stage ventana = MainApp.primaryStage;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("Portada.fxml"));
        try {
            MainApp.primaryStage.setScene(new Scene(loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
}