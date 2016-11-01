/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Thomas
 */
public class SpelWijzigenPaneel extends GridPane
{

    private DomeinController dc;
    private Taal taalObj;
    private Hoofdpaneel hoofdPaneel;
    String[] spelnamen;
    Button[] spellen;

    public SpelWijzigenPaneel(DomeinController dc, Hoofdpaneel hp, Taal taalObj)
    {
	this.dc = dc;
	this.hoofdPaneel = hp;
	this.taalObj = taalObj;
	toonSpelNamen();
	hoofdPaneel.setStatus("Kies een spel om te wijzigen");
    }

    private void toonSpelNamen()
    {
	VBox controls = new VBox();
	this.setAlignment(Pos.BASELINE_CENTER);
	spelnamen = dc.geefSpelNamen();
	spellen = new Button[spelnamen.length];
	Button btnCancel = new Button(taalObj.getText("terug"));
	for (int i = 0; i < spelnamen.length; i++)
	{
	    spellen[i] = new Button(spelnamen[i]);
	    spellen[i].setId("btnAanmelden");
	    spellen[i].setPrefWidth(420);
	    controls.getChildren().add(spellen[i]);
	    spellen[i].setOnAction(new EventHandler<ActionEvent>()
	    {

		@Override
		public void handle(ActionEvent event)
		{
		    Object source = event.getSource();
		    if (source instanceof Button)
		    {
			Button click = (Button) source;
			//dc.resetSpelbord();

			dc.kiesSpel(Integer.parseInt(click.getText().substring(0, 1)));

			hoofdPaneel.toonSpelbord();

		    }
		}

	    });

	}
	btnCancel.setId("btnTerug");
	btnCancel.setPrefWidth(420);
	btnCancel.setOnAction(this::terugkeren);
	controls.getChildren().add(btnCancel);
	controls.setPadding(new Insets(10, 0, 0, 0));
	controls.setSpacing(5);
	add(controls, 0, 0);
    }

    private void terugkeren(ActionEvent event)
    {
	hoofdPaneel.toonKeuzePaneel();
    }

}
