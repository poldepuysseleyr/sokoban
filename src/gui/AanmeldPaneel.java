package gui;

import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class AanmeldPaneel extends GridPane
{

    private final DomeinController controller;
    private final Hoofdpaneel hoofdPaneel;
    private final Taal taalObj;

    public AanmeldPaneel(DomeinController controller, Hoofdpaneel hoofdPaneel, Taal taalObj)
    {
	this.controller = controller;
	this.hoofdPaneel = hoofdPaneel;
	this.taalObj = taalObj;
	configureerGrid();
	voegComponentenToe();
    }

    private void configureerGrid()
    {
	setPadding(new Insets(10));
	setHgap(10);
	setVgap(10);

    }

    private final TextField gebruikersnaam = new TextField();
    private final PasswordField wachtwoord = new PasswordField();
    private final Label foutbericht = new Label();

    private void voegComponentenToe()
    {

	VBox vboxInput = new VBox();
	VBox wrapper = new VBox();
	this.setAlignment(Pos.BASELINE_CENTER);
	vboxInput.setPadding(new Insets(20, 10, 20, 10));

	Label lblGebruikersnaam = new Label(taalObj.getText("gebruikersnaam"));
	Button btnAanmelden = new Button(taalObj.getText("aanmelden"));
	Button btnRegistreren = new Button(taalObj.getText("geenAccount"));
        Button btnTerug = new Button(taalObj.getText("terug"));
	gebruikersnaam.setPrefWidth(400);
	Label lblWachtwoord = new Label(taalObj.getText("wachtwoord"));
	vboxInput.getChildren().add(lblGebruikersnaam);
	vboxInput.getChildren().add(gebruikersnaam);
	vboxInput.getChildren().add(lblWachtwoord);
	vboxInput.getChildren().add(wachtwoord);
	vboxInput.getChildren().add(foutbericht);
	wrapper.getChildren().add(vboxInput);
	wrapper.getChildren().add(btnAanmelden);
	wrapper.getChildren().add(btnRegistreren);
        wrapper.getChildren().add(btnTerug);

	btnAanmelden.setPrefWidth(420);
	btnAanmelden.setPrefHeight(80);
	btnRegistreren.setPrefWidth(420);
	btnRegistreren.setPrefHeight(80);
        btnTerug.setPrefWidth(420);
	btnTerug.setPrefHeight(80);
	wrapper.setSpacing(10);
	lblGebruikersnaam.setId("invoerLabel");
	lblWachtwoord.setId("invoerLabel");
	btnAanmelden.setId("btnAanmelden");
	btnRegistreren.setId("btnRegistreren");
        btnTerug.setId("btnTerug");
	vboxInput.setId("vboxAanmelden");
	foutbericht.setId("foutbericht");
        foutbericht.wrapTextProperty();
	btnAanmelden.setOnAction(this::aanmelden);
	btnRegistreren.setOnAction(this::registreren);
        btnTerug.setOnAction(this::terugkeren);
	add(wrapper, 0, 0);

    }

    private void aanmelden(ActionEvent event)
    {
	if (gebruikersnaam.getText().trim().isEmpty())
	{
	    foutbericht.setText(taalObj.getText("gebruikersnaamNietIngevuld"));
	    return;
	}
	if (wachtwoord.getText().trim().isEmpty())
	{
	    foutbericht.setText(taalObj.getText("wachtwoordNietIngevuld"));
	    return;
	}
	try
	{
	    controller.meldAan(gebruikersnaam.getText().trim(), wachtwoord.getText().trim());

	} catch (IllegalArgumentException ie)
	{
	    foutbericht.setText(ie.getMessage());
	} catch (NullPointerException ne)
	{
	    foutbericht.setText(taalObj.getText("gebruikersnaam_wachtwoord_ongeldig"));
	}
	try
	{
	    controller.geefGebruikersnaam();
	    foutbericht.setText(null);
	    hoofdPaneel.spelerIsAangemeld();
	} catch (NullPointerException ne)
	{
	    foutbericht.setText(taalObj.getText("gebruikersnaam_wachtwoord_ongeldig"));
	}

    }

    private void registreren(ActionEvent event)
    {
	hoofdPaneel.toonRegistratie();
    }
    private void terugkeren(ActionEvent event)
    {
        hoofdPaneel.setCenter(new TaalPaneel(controller, hoofdPaneel));
        hoofdPaneel.setStatus("Kies een taal/Choose a language/ Choisissez une langue.");
    }
}
