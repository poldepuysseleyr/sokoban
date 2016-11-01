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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Maarten
 */
public class WijzigSpelPaneelController extends BorderPane {

    @FXML
    private Button btnOpslaan;
    @FXML
    private Button btnStoppen;
    @FXML
    private Button btnVerwijderen;
    @FXML
    private GridPane gridSpelbord;
    @FXML
    private ImageView imgMan;
    @FXML
    private ImageView imgLeegVeld;
    @FXML
    private ImageView imgKist;
    @FXML
    private ImageView imgDoel;
    @FXML
    private ImageView imgMuur;

    private DomeinController dc;
    ImageView image;
    Hoofdpaneel hoofdPaneel;
    Taal taalobj;
    private int soort;
    private int volgnummer;

    public WijzigSpelPaneelController(DomeinController domc, Hoofdpaneel hp, Taal taalobj, int volgnr) {
        this.dc = domc;
        this.hoofdPaneel = hp;
        this.taalobj = taalobj;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("WijzigSpelPaneel.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {

            loader.load();
            imgMan = new ImageView("images/textures/steve.png");
            imgDoel = new ImageView("images/textures/grassDoel.jpg");
            imgKist = new ImageView("images/textures/chestNietOk.jpg");
            imgLeegVeld = new ImageView("images/textures/grass.jpg");
            imgMuur = new ImageView("images/textures/cobble.jpg");

            btnStoppen.setText(taalobj.getText("stoppen"));
            btnOpslaan.setText(taalobj.getText("opslaan"));
            btnVerwijderen.setText(taalobj.getText("verwijder"));
            volgnummer = volgnr;
            dc.kiesSpelbord(volgnummer);
            dc.resetSpelbord();
            toonSpelBord();

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        catch(NullPointerException ie){
            
        }

    }

    public void toonSpelBord() {
        try{
        String[][] spelbord = dc.toonSpelbord();
        String url = "";
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                url = omzettenNaarImages(spelbord[i][j]);
                gridSpelbord.add(new ImageView(url), j, i);

            }
        }
        }catch(NullPointerException ie){
            
        }
        hoofdPaneel.setStatus(taalobj.getText("wijzigSpel"));

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
    private void btnOpslaanOnAction(ActionEvent event) {
        dc.updateSpelbord();
        hoofdPaneel.setStatus(taalobj.getText("spelbordOpslaan"));
    }

    @FXML
    private void btnStoppenOnAction(ActionEvent event) {
        dc.resetSpelbord();
        hoofdPaneel.setCenter(new SpelNamenPaneel(dc, hoofdPaneel, taalobj, true));
        hoofdPaneel.setStatus(taalobj.getText("wijzigSpel"));
    }

    @FXML
    private void btnVerwijderenOnAction(ActionEvent event) {
        dc.verwijderSpelbord();
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setHeaderText(taalobj.getText("alertVerwijder"));
        alert.setContentText(taalobj.getText("alertVerwijder"));
        alert.show();
        hoofdPaneel.setCenter(new SpelKeuzePaneel(dc, hoofdPaneel, taalobj));
        hoofdPaneel.vernieuwStatus();
    }

    @FXML
    private void gridSpelbordOnAction(MouseEvent event) {
        try{
        int kolom = (int) event.getX() / 50;
        int rij = (int) event.getY() / 50;
        Alert item = new Alert(AlertType.CONFIRMATION);
        item.setHeaderText(taalobj.getText("bevestigWijziging"));
        item.setContentText(taalobj.getText("bevestigWijziging"));
        ButtonType buttonTypeBevestigen = new ButtonType(taalobj.getText("bevestigen"));
        ButtonType buttonTypeCancel = new ButtonType(taalobj.getText("cancel"));
        item.getButtonTypes().setAll(buttonTypeBevestigen, buttonTypeCancel);
        Optional<ButtonType> result = item.showAndWait();
        if (result.get() == buttonTypeBevestigen) {
            System.out.println(kolom + "/n" + rij);
            gridSpelbord.getChildren().remove(this);
            gridSpelbord.add(image, kolom, rij);
            dc.plaatsItem(soort, rij, kolom);
            toonSpelBord();
        } else if (result.get() == buttonTypeCancel) {
            hoofdPaneel.setStatus(taalobj.getText("geannuleerd"));
        }
        }catch(IllegalArgumentException ie){
            
        }
    }

    @FXML
    private void imgManOnAction(MouseEvent event) {
        image = new ImageView("images/textures/steve.png");
        soort = 5;
        hoofdPaneel.setStatus(taalobj.getText("klikOpGrid") + " " + taalobj.getText("gekozenVoor") + " " +soort);
    }

    @FXML
    private void imgLeegVeldOnAction(MouseEvent event) {
        image = new ImageView("images/textures/grass.jpg");
        soort = 2;
        hoofdPaneel.setStatus(taalobj.getText("klikOpGrid") + " " + taalobj.getText("gekozenVoor") + " " +soort);
    }

    @FXML
    private void imgKistOnAction(MouseEvent event) {

        image = new ImageView("images/textures/chestNietOk.jpg");
        soort = 4;
        hoofdPaneel.setStatus(taalobj.getText("klikOpGrid") + " " + taalobj.getText("gekozenVoor") + " " +soort);
    }

    @FXML
    private void imgDoelOnAction(MouseEvent event) {
        image = new ImageView("images/textures/grassDoel.jpg");
        soort = 3;
        hoofdPaneel.setStatus(taalobj.getText("klikOpGrid") + " " + taalobj.getText("gekozenVoor") + " " +soort);
    }

    @FXML
    private void imgMuurOnAction(MouseEvent event) {
        image = new ImageView("images/textures/cobble.jpg");
        soort = 1;
        hoofdPaneel.setStatus(taalobj.getText("klikOpGrid") + " " + taalobj.getText("gekozenVoor") + " " +soort);
    }

}
