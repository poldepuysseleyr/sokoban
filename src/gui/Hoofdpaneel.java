/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 *
 * @author Thomas
 */
public class Hoofdpaneel extends BorderPane
{

    private final DomeinController dc;
    private Taal taalObj;
    //gui componenten
    private final Label status = new Label();
    private final TaalPaneel taalPaneel;
    private AanmeldPaneel aanmeldPaneel;
    private SpelNamenPaneel spelNamenPaneel;
    private RegistratiePaneel registratiePaneel;
    private SpelKeuzePaneel spelKeuzePaneel;
    private SpelSpelenPaneelController spelbord;

    public Hoofdpaneel(DomeinController dc)
    {
	this.dc = dc;

	this.taalPaneel = new TaalPaneel(dc, this);

	voegComponentenToe();
    }

    private void voegComponentenToe()
    {

	Text titel = new Text("Sokoban");
	titel.setFill(Color.WHITE);

	titel.setId("titel");
	titel.setWrappingWidth(1200);

	status.setText("Kies een taal/Choose a language/ Choisissez une langue.");
	status.setId("status");
	status.setMaxWidth(Double.MAX_VALUE);
	VBox hoofding = new VBox(titel, status);
	hoofding.setSpacing(10);
	hoofding.setPadding(new Insets(10, 0, 0, 0));
	setTop(hoofding);
	setCenter(taalPaneel);

    }

    public void taalIsGekozen(Taal taal)
    {
	taalObj = taal;
	this.aanmeldPaneel = new AanmeldPaneel(dc, this, taalObj);
	this.setCenter(aanmeldPaneel);
	status.setText(taalObj.getText("welkom_hoofdpaneel"));

    }

    public void spelerIsAangemeld()
    {
	toonKeuzePaneel();
	vernieuwStatus();
    }

    public void toonRegistratie()
    {
	this.registratiePaneel = new RegistratiePaneel(dc, this, taalObj);
	this.setCenter(registratiePaneel);
	status.setText(taalObj.getText("welkom_hoofdpaneel"));
    }

    public void vernieuwStatus()
    {
	String gebruikersnaam = dc.geefGebruikersnaam();
	if (dc.isAdmin())
	{
	    status.setText(String.format("%s admin %s.",taalObj.getText("aanmelden_welkom"), gebruikersnaam));
	} else
	{
	    status.setText(String.format("%s %s.",taalObj.getText("aanmelden_welkom"), gebruikersnaam));
	}

    }

    public void toonKeuzePaneel()
    {
	this.spelKeuzePaneel = new SpelKeuzePaneel(dc, this, taalObj);
	this.setCenter(spelKeuzePaneel);

    }

    public void setStatus(String status)
    {
	this.status.setText(status);
    }

    public void toonSpelbord()
    {
	spelbord = new SpelSpelenPaneelController(dc, this, taalObj);
	this.setCenter(spelbord);
    }

}
