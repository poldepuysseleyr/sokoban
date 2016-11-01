/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import java.io.IOException;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Maarten
 */
public class SpelSpelenPaneelController extends BorderPane {

    @FXML
    private Button btnStoppen;
    @FXML
    private Button btnResetten;
    @FXML
    private GridPane gridSpelbord;
    @FXML
    private Button btnBoven;
    @FXML
    private Button btnOnder;
    @FXML
    private Button btnLinks;
    @FXML
    private Button btnRechts;

    private DomeinController dc;
    private Hoofdpaneel hoofdPaneel;
    private Taal taalobj;
    private int volgnummer = 1;
    private SpelNamenPaneel spelNamenPaneel;

    public SpelSpelenPaneelController(DomeinController domc, Hoofdpaneel hp, Taal taalobj) {
        this.dc = domc;
        this.hoofdPaneel = hp;
        this.taalobj = taalobj;
        spelNamenPaneel = new SpelNamenPaneel(dc, hoofdPaneel, taalobj, false);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SpelSpelenPaneel.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
            dc.resetAlleSpelborden();
            dc.kiesSpelbord(volgnummer);
            btnStoppen.setText(taalobj.getText("stoppen"));
            btnResetten.setText(taalobj.getText("resetten"));

            toonSpelBord();

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        catch(NullPointerException ie){
        }
    }

    public void toonSpelBord() {
        try {
            String[][] spelbord = dc.toonSpelbord();
            String url = "";
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    url = omzettenNaarImages(spelbord[i][j]);
                    gridSpelbord.add(new ImageView(url), j, i);

                }
            }
        } catch (NullPointerException ie) {

        }
        hoofdPaneel.setStatus(taalobj.getText("aantalVerplaatsingen"));

    }

    public void isVoltooid() {
        if (dc.isSpelbordVoltooid() && dc.geefAantalVoltooideSpelborden() != dc.geefAantalSpelborden()) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle(taalobj.getText("voltooid"));
            alert.setHeaderText(String.format("%s%n%s : %d%n%s : %d", taalobj.getText("verderSpelenofStoppen"), taalobj.getText("aantalVoltooideSpelborden"), dc.geefAantalVoltooideSpelborden(), taalobj.getText("aantalSpelborden"), dc.geefAantalSpelborden()));
            alert.setGraphic(new ImageView("images/complete-icon.png"));

            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("images/complete-icon.png"));

            ButtonType buttonTypeVerderSpelen = new ButtonType(taalobj.getText("verderSpelen"));
            ButtonType buttonTypeStoppen = new ButtonType(taalobj.getText("stoppen"));

            alert.getButtonTypes().setAll(buttonTypeVerderSpelen, buttonTypeStoppen);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeVerderSpelen) {

                dc.kiesSpelbord(++volgnummer);
                toonSpelBord();
                hoofdPaneel.setStatus(taalobj.getText("aantalVerplaatsingen") + dc.geefAantalVerplaatsingen());

            } else if (result.get() == buttonTypeStoppen) {
                hoofdPaneel.setCenter(new SpelNamenPaneel(dc, hoofdPaneel, taalobj, false));
                hoofdPaneel.vernieuwStatus();
            }

        }
        if (dc.isSpelbordVoltooid() && dc.geefAantalVoltooideSpelborden() == dc.geefAantalSpelborden()) {
            Alert klaar = new Alert(AlertType.CONFIRMATION);
            klaar.setTitle(taalobj.getText("voltooid"));
            klaar.setHeaderText(String.format("%s %s%n%s : %d%n%s : %d",dc.toonSpelNaam(),taalobj.getText("voltooid"), taalobj.getText("aantalVoltooideSpelborden"), dc.geefAantalVoltooideSpelborden(), taalobj.getText("aantalSpelborden"), dc.geefAantalSpelborden()));
            klaar.setGraphic(new ImageView("images/complete-icon.png"));
            Stage stage2 = (Stage) klaar.getDialogPane().getScene().getWindow();
            stage2.getIcons().add(new Image("images/complete-icon.png"));

            ButtonType buttonTypeSpelScherm = new ButtonType(taalobj.getText("terugNaarHetHoofdscherm"));

            klaar.getButtonTypes().setAll(buttonTypeSpelScherm);

            Optional<ButtonType> result2 = klaar.showAndWait();
            if (result2.get() == buttonTypeSpelScherm) {
                hoofdPaneel.setCenter(spelNamenPaneel);
                hoofdPaneel.setStatus(taalobj.getText("speelSpel"));
            }
            dc.resetAlleSpelborden();
        }
    }

    public void maakSpelbordLeeg() {

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                gridSpelbord.add(new ImageView("images/textures/empty.png"), j, i);
            }
        }

    }

    public String omzettenNaarImages(String soort) {
        String url = "";
        switch (soort) {
            case "@":
                url = "images/textures/steve.png";
                break;
            case "*":
                url = "images/textures/chestNietOk.jpg";
                break;
            case "X":
                url = "images/textures/cobble.jpg";
                break;
            case ".":
                url = "images/textures/grassDoel.jpg";
                break;
            case "#":
                url = "images/textures/steve.png";
                break;
            case " ":
                url = "images/textures/grass.jpg";
                break;
            case "V":
                url = "images/textures/chestOk.jpg";
                break;

        }
        return url;
    }

    @FXML
    private void btnStoppenOnAction(ActionEvent event) {
        hoofdPaneel.setCenter(new SpelNamenPaneel(dc, hoofdPaneel, taalobj, false));
        hoofdPaneel.vernieuwStatus();
    }

    @FXML
    private void btnResettenOnAction(ActionEvent event) {
        dc.resetSpelbord();
        this.toonSpelBord();

    }

    @FXML
    private void btnBovenOnAction(ActionEvent event) {
        dc.verplaatsMan("boven");
        this.toonSpelBord();
        hoofdPaneel.setStatus(taalobj.getText("aantalVerplaatsingen") + dc.geefAantalVerplaatsingen());
        isVoltooid();
    }

    @FXML
    private void btnOnderOnAction(ActionEvent event) {
        dc.verplaatsMan("onder");
        this.toonSpelBord();
        hoofdPaneel.setStatus(taalobj.getText("aantalVerplaatsingen") + dc.geefAantalVerplaatsingen());
        isVoltooid();

    }

    @FXML
    private void btnLinksOnAction(ActionEvent event) {
        dc.verplaatsMan("links");
        this.toonSpelBord();
        hoofdPaneel.setStatus(taalobj.getText("aantalVerplaatsingen") + dc.geefAantalVerplaatsingen());
        isVoltooid();
    }

    @FXML
    private void btnRechtsOnAction(ActionEvent event) {
        dc.verplaatsMan("rechts");
        this.toonSpelBord();
        hoofdPaneel.setStatus(taalobj.getText("aantalVerplaatsingen") + dc.geefAantalVerplaatsingen());
        isVoltooid();
    }

}
