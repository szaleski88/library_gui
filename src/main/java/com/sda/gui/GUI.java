package com.sda.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GUI extends Application {

    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) {
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("BibliotekaGUI.fxml"));
//            root = FXMLLoader.load(getClass().getResource("BibliotekaGUI.fxml"));
            root = loader.load();
            KontrolerGUI kontroler = loader.getController();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setOnHidden(e -> kontroler.shutdown());
            primaryStage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void stop() {
        // executed when the application shuts down
        System.out.println("ZAPISANO STAN BIBLIOTEKI");
    }
}
