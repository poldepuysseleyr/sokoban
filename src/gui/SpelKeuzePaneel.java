/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Thomas
 */
public class SpelKeuzePaneel extends GridPane
{

    private final DomeinController controller;
    private final Hoofdpaneel hoofdPaneel;
    private final Taal taalObj;

    public SpelKeuzePaneel(DomeinController controller, Hoofdpaneel hoofdpaneel, Taal taalObj)
    {
	this.controller = controller;
	this.hoofdPaneel = hoofdpaneel;
	this.taalObj = taalObj;

	voegComponentenToe();
    }

    private void voegComponentenToe()
    {
	VBox buttons = new VBox();
	this.setAlignment(Pos.BASELINE_CENTER);

	Button btnSpeelSpel = new Button(taalObj.getText("speelSpel"));
	Button btnVoegSpelToe = new Button(taalObj.getText("configureerSpel"));
	Button btnWijzigSpel = new Button(taalObj.getText("wijzigSpel"));
	Button btnAfmelden = new Button(taalObj.getText("afmelden"));
	btnSpeelSpel.setPrefWidth(420);
	btnVoegSpelToe.setPrefWidth(420);
	btnWijzigSpel.setPrefWidth(420);
	btnAfmelden.setPrefWidth(420);
	btnSpeelSpel.setPrefHeight(80);
	btnVoegSpelToe.setPrefHeight(80);
	btnWijzigSpel.setPrefHeight(80);
	btnAfmelden.setPrefHeight(80);
	btnSpeelSpel.setId("btnAanmelden");
	btnVoegSpelToe.setId("btnAanmelden");
	btnWijzigSpel.setId("btnAanmelden");
	btnAfmelden.setId("btnTerug");
	buttons.setSpacing(50);
	buttons.getChildren().add(btnSpeelSpel);
	btnSpeelSpel.setOnAction(this::speelSpel);
	if (controller.isAdmin())
	{
            btnVoegSpelToe.setOnAction(this::spelmaken);
	    btnWijzigSpel.setOnAction(this::wijzigen);
	    buttons.setPadding(new Insets(20, 10, 20, 10));
	    buttons.getChildren().add(btnVoegSpelToe);
	    buttons.getChildren().add(btnWijzigSpel);

	} else
	{
	    buttons.setPadding(new Insets(150, 10, 50, 10));
	}
	buttons.getChildren().add(btnAfmelden);
        btnVoegSpelToe.setOnAction(this::spelmaken);
        btnWijzigSpel.setOnAction(this::wijzigen);
	btnAfmelden.setOnAction(this::terugkeren);
	add(buttons, 0, 0);
    }

    private void speelSpel(ActionEvent event)
    {
        hoofdPaneel.setCenter( new SpelNamenPaneel(controller,hoofdPaneel, taalObj, false));
	hoofdPaneel.setStatus(taalObj.getText("speelSpel"));
    }

    private void terugkeren(ActionEvent event)
    {
	hoofdPaneel.setCenter(new AanmeldPaneel(controller, hoofdPaneel, taalObj));
	hoofdPaneel.setStatus(taalObj.getText("welkom_hoofdpaneel"));
    }

    private void spelmaken(ActionEvent event)
    {
        String naam;
        TextInputDialog dialog = new TextInputDialog(taalObj.getText("uniekeNaam"));
        dialog.setTitle(taalObj.getText("configureerSpel"));
        dialog.setHeaderText(taalObj.getText("uniekeNaam"));
        
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent())
        {
            naam = result.get();
            if (!controller.controleerUniekeNaam(naam))
            {
                int volgnummer = 1;
                controller.maakSpel(naam);
                hoofdPaneel.setCenter(new ConfigureerNieuwSpelController(controller, hoofdPaneel, taalObj, volgnummer));
        	hoofdPaneel.setStatus(taalObj.getText("configureerSpel"));
            } else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("TODO");
                alert.setContentText("TODO: NAAM BESTAAT AL");
                alert.showAndWait();
                this.spelmaken(event);
                
            }
        }

    }
    
    private void wijzigen(ActionEvent event)
    {
      hoofdPaneel.setCenter(new SpelNamenPaneel(controller, hoofdPaneel, taalObj, true));
      hoofdPaneel.setStatus(taalObj.getText("wijzigSpel"));
    }
}
