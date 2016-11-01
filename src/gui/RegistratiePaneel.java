/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import exceptions.GebruikersnaamInGebruikException;
import exceptions.OngeldigGebruikersnaamException;
import exceptions.OngeldigWachtwoordException;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Maarten
 */
class RegistratiePaneel extends GridPane
{

    private final DomeinController controller;
    private final Hoofdpaneel hoofdPaneel;
    private AanmeldPaneel aanmeldPaneel;
    private final Taal taalObj;

    RegistratiePaneel(DomeinController dc, Hoofdpaneel hoofdPaneel, Taal taalObj)
    {
        this.controller = dc;
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

    private final TextField naam = new TextField();
    private final TextField voornaam = new TextField();
    private final TextField gebruikersnaam = new TextField();
    private final PasswordField wachtwoord = new PasswordField();
    private final Label foutbericht = new Label();
    private final VBox error = new VBox();

    private void voegComponentenToe()
    {
        VBox vboxInput = new VBox();
        VBox wrapper = new VBox();
        this.setAlignment(Pos.BASELINE_CENTER);
        vboxInput.setPadding(new Insets(20, 10, 20, 10));
        error.setVisible(false);

        Label lblNaam = new Label(taalObj.getText("geefNaam"));
        Label lblVoornaam = new Label(taalObj.getText("geefVoornaam"));
        Label lblGebruikersnaam = new Label(taalObj.getText("gebruikersnaam"));
        Label lblWachtwoord = new Label(taalObj.getText("wachtwoord"));
        Button btnRegistreren = new Button(taalObj.getText("registreer"));
        Button btnCancel = new Button(taalObj.getText("cancel"));
        naam.setPrefWidth(400);
        voornaam.setPrefWidth(400);
        gebruikersnaam.setPrefWidth(400);
        wachtwoord.setPrefWidth(400);
        vboxInput.getChildren().add(lblNaam);
        vboxInput.getChildren().add(naam);
        vboxInput.getChildren().add(lblVoornaam);
        vboxInput.getChildren().add(voornaam);
        vboxInput.getChildren().add(lblGebruikersnaam);
        vboxInput.getChildren().add(gebruikersnaam);
        vboxInput.getChildren().add(lblWachtwoord);
        vboxInput.getChildren().add(wachtwoord);
        error.getChildren().add(foutbericht);
        foutbericht.setPadding(new Insets(10, 10, 10, 10));
        wrapper.getChildren().add(vboxInput);
        wrapper.getChildren().add(error);
        wrapper.getChildren().add(btnRegistreren);
        wrapper.getChildren().add(btnCancel);

        btnRegistreren.setPrefWidth(420);
        btnRegistreren.setPrefHeight(40);
        btnCancel.setPrefWidth(420);
        btnCancel.setPrefHeight(40);
        error.setPrefSize(420, USE_COMPUTED_SIZE);
        foutbericht.setWrapText(true);
        foutbericht.setAlignment(Pos.CENTER);
        wrapper.setSpacing(10);

        lblGebruikersnaam.setId("invoerLabel");
        lblWachtwoord.setId("invoerLabel");
        lblNaam.setId("invoerLabel");
        lblVoornaam.setId("invoerLabel");
        btnRegistreren.setId("btnRegistreren");
        btnCancel.setId("btnTerug");
        vboxInput.setId("vboxRegistreren");
        error.setId("vboxError");

        foutbericht.setId("foutbericht");
        btnRegistreren.setOnAction(this::registreren);
        btnCancel.setOnAction(this::annuleren);
        add(wrapper, 0, 0);
    }

    private void registreren(ActionEvent event)
    {
        if (gebruikersnaam.getText().trim().isEmpty())
        {
            foutbericht.setText(taalObj.getText("gebruikersnaamNietIngevuld"));
            error.setVisible(true);
            return;
        }
        if (wachtwoord.getText().trim().isEmpty())
        {
            foutbericht.setText(taalObj.getText("wachtwoordNietIngevuld"));
            error.setVisible(true);
            return;
        }

        try
        {
            controller.registreer(this.gebruikersnaam.getText(), this.wachtwoord.getText(), false, this.naam.getText(), this.voornaam.getText());
            controller.geefGebruikersnaam();
            foutbericht.setText(null);
            hoofdPaneel.spelerIsAangemeld();
            if (controller.geefGebruikersnaam() == null)
            {
                foutbericht.setText(taalObj.getText("gebruikersnaam_wachtwoord_ongeldig"));
                error.setVisible(true);
            }
        } catch (OngeldigGebruikersnaamException g)
        {
            foutbericht.setText(taalObj.getText("gebruikersnaamOngeldig"));
            error.setVisible(true);
        } catch (OngeldigWachtwoordException w)
        {
            foutbericht.setText(taalObj.getText("wachtwoordOngeldig"));
            error.setVisible(true);
        } catch (IllegalArgumentException ie)
        {
            foutbericht.setText(ie.getMessage());
            error.setVisible(true);
        } catch (GebruikersnaamInGebruikException e)
        {
            foutbericht.setText(taalObj.getText("spelerBestaatAl"));
            error.setVisible(true);
        } catch (NullPointerException n)
        {
            foutbericht.setText(taalObj.getText("gebruikersnaam_wachtwoord_ongeldig"));
            error.setVisible(true);
        }

    }

    private void annuleren(ActionEvent event)
    {
        hoofdPaneel.setCenter(new AanmeldPaneel(controller, hoofdPaneel, taalObj));
    }

}
