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
public class ConfigureerNieuwSpelController extends BorderPane {

    @FXML
    private Button btnOpslaan;
    @FXML
    private Button btnStoppen;
    @FXML
    private GridPane gridSpelbord;
    @FXML
    private ImageView imgMan;
    @FXML
    private ImageView imgDoel;
    @FXML
    private ImageView imgKist;
    @FXML
    private ImageView imgLeegVeld;
    @FXML
    private ImageView imgMuur;

    private DomeinController dc;
    ImageView image;
    Hoofdpaneel hoofdPaneel;
    Taal taalobj;
    private int soort;
    private int volgnummer;
    private int totaalAantalSpelborden;

    public ConfigureerNieuwSpelController(DomeinController domc, Hoofdpaneel hp, Taal taalobj, int volgnummer)
    {
        this.dc = domc;
        this.totaalAantalSpelborden = dc.geefTotaalAantalSpelborden();
        this.hoofdPaneel = hp;
        this.taalobj = taalobj;
        this.volgnummer = volgnummer;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ConfigureerNieuwSpelPaneel.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {

            loader.load();
            spelbordAanmaken();
            imgMan = new ImageView("images/textures/steve.png");
            imgDoel = new ImageView("images/textures/grassDoel.jpg");
            imgKist = new ImageView("images/textures/chestNietOk.jpg");
            imgLeegVeld = new ImageView("images/textures/grass.jpg");
            imgMuur = new ImageView("images/textures/cobble.jpg");
            btnStoppen.setText(taalobj.getText("stoppen"));
            btnOpslaan.setText(taalobj.getText("opslaan"));

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void spelbordAanmaken()
    {
        hoofdPaneel.setStatus(taalobj.getText("klikOpItem"));
        dc.maakSpelbord(this.volgnummer++);
        dc.maakVelden();
    }

    @FXML
    private void btnOpslaanOnAction(ActionEvent event)
    {
        hoofdPaneel.setStatus(taalobj.getText("spelbordOpslaan"));
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(taalobj.getText("configureerSpel"));
        alert.setHeaderText(taalobj.getText("maakSpelbord"));
        alert.setContentText(taalobj.getText("maakSpelbord"));
        ButtonType btnOk = new ButtonType("OK");
        ButtonType btnCancel = new ButtonType(taalobj.getText("cancel"));
        ButtonType btnOpslaan2 = new ButtonType(taalobj.getText("opslaan"));

        alert.getButtonTypes().remove(0, 2);
        alert.getButtonTypes().addAll(btnOk, btnCancel, btnOpslaan2);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == btnOpslaan2)
        {
            dc.bewaarSpel();
            hoofdPaneel.setCenter(new SpelKeuzePaneel(dc, hoofdPaneel, taalobj));
            hoofdPaneel.vernieuwStatus();
        } else
        {
            if (result.get() == btnOk)
            {
                hoofdPaneel.setCenter(new ConfigureerNieuwSpelController(dc, hoofdPaneel, taalobj, volgnummer));
            } else
            {
                hoofdPaneel.setCenter(new SpelKeuzePaneel(dc, hoofdPaneel, taalobj));
                hoofdPaneel.vernieuwStatus();
            }
        }
    }

    @FXML
    private void btnStoppenOnAction(ActionEvent event) {
        hoofdPaneel.setCenter(new SpelKeuzePaneel(dc, hoofdPaneel, taalobj));
        hoofdPaneel.vernieuwStatus();
    }

    @FXML
    private void gridSpelbordOnAction(MouseEvent event)
    {
        try
        {
            int kolom = (int) event.getX() / 50;
            int rij = (int) event.getY() / 50;
            gridSpelbord.add(image, kolom, rij);
            dc.plaatsItem(soort, rij, kolom);
        } catch (IllegalArgumentException ie)
        {

        }
    }

    @FXML
    private void imgManOnAction(MouseEvent event) {
        image = new ImageView("images/textures/steve.png");
        soort = 5;
        hoofdPaneel.setStatus(taalobj.getText("klikOpGrid") + " " + taalobj.getText("gekozenVoor") + " " +soort);
    }

    @FXML
    private void imgDoelOnAction(MouseEvent event) {
        image = new ImageView("images/textures/grassDoel.jpg");
        soort = 3;
        hoofdPaneel.setStatus(taalobj.getText("klikOpGrid") + " " + taalobj.getText("gekozenVoor") + " " +soort);
    }

    @FXML
    private void imgKistOnAction(MouseEvent event) {
        image = new ImageView("images/textures/chestNietOk.jpg");
        soort = 4;
        hoofdPaneel.setStatus(taalobj.getText("klikOpGrid") + " " + taalobj.getText("gekozenVoor") + " " +soort);
    }

    @FXML
    private void imgLeegVeldOnAction(MouseEvent event) {
        image = new ImageView("images/textures/grass.jpg");
        soort = 2;
        hoofdPaneel.setStatus(taalobj.getText("klikOpGrid") + " " + taalobj.getText("gekozenVoor") + " " +soort);
    }

    @FXML
    private void imgMuurOnAction(MouseEvent event) {
        image = new ImageView("images/textures/cobble.jpg");
        soort = 1;
        hoofdPaneel.setStatus(taalobj.getText("klikOpGrid") + " " + taalobj.getText("gekozenVoor") + " " +soort);
    }

}
