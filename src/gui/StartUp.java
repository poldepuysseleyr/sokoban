/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import cui.SokobanApplicatie;

/**
 *
 * @author Maarten
 */
public class StartUp extends Application {

    public static void main(String[] args) {
        {
            DomeinController dc = new DomeinController();
            SokobanApplicatie applicatie = new SokobanApplicatie(dc);
            //applicatie.toonHoofdpaneel(); // Start console applicatie

            launch(args);               // Start GUI
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        DomeinController controller = new DomeinController();
        Scene scene = new Scene(new Hoofdpaneel(controller), 1200, 675);
        scene.getStylesheets().add("/gui/style.css");
        stage.setScene(scene);
        stage.show();
    }
}
