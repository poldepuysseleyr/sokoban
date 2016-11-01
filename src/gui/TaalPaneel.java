/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 *
 * @author Thomas
 */
public class TaalPaneel extends GridPane
{

    private final DomeinController dc;
    private final Hoofdpaneel hoofdPaneel;
    private Taal taal;

    public TaalPaneel(DomeinController dc, Hoofdpaneel hoofdpaneel)
    {
	this.dc = dc;
	this.hoofdPaneel = hoofdpaneel;
	voegComponentenToe();
    }

    private final Button btnNederlands = new Button("Nederlands");
    private final Button btnEngels = new Button("English");
    private final Button btnFrans = new Button("Français");

    private void voegComponentenToe()
    {

	this.setAlignment(Pos.BASELINE_CENTER);
	this.setPadding(new Insets(10));
	HBox controls = new HBox(btnNederlands, btnFrans, btnEngels);
        //Css ids
	btnNederlands.setId("btnNederlands");
	btnFrans.setId("btnFrans");
	btnEngels.setId("btnEngels");
	//Size buttons
	btnNederlands.setPrefWidth(300);
	btnEngels.setPrefWidth(300);
	btnFrans.setPrefWidth(300);
	btnNederlands.setPrefHeight(210);
	btnEngels.setPrefHeight(210);
	btnFrans.setPrefHeight(210);
	btnNederlands.setOnAction(this::kiesNl);
	btnEngels.setOnAction(this::kiesEn);
	btnFrans.setOnAction(this::kiesFr);
	controls.setSpacing(30);
	controls.setPadding(new Insets(100, 0, 100, 0));

	add(controls, 0, 0);
    }

    private void kiesNl(ActionEvent event)
    {
	taal = new Taal("nl");
	hoofdPaneel.taalIsGekozen(taal);
    }

    private void kiesEn(ActionEvent event)
    {
	taal = new Taal("en");
	hoofdPaneel.taalIsGekozen(taal);
    }

    private void kiesFr(ActionEvent event)
    {
	taal = new Taal("fr");
	hoofdPaneel.taalIsGekozen(taal);
    }

    public Taal getTaal()
    {
	return taal;
    }
}
