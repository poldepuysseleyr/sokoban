/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Thomas
 */
public class SpelNamenPaneel extends GridPane
{

    private final DomeinController dc;
    private final Hoofdpaneel hoofdPaneel;
    private final Taal taalObj;
    private SpelSpelenPaneelController spelSpelenPaneel;
    String[] spelnamen;
    Button[] spellen;
    boolean wijzigen;

    SpelNamenPaneel(DomeinController dc, Hoofdpaneel hoofdPaneel, Taal taalObj, boolean wijzigen)
    {
        this.dc = dc;
        this.hoofdPaneel = hoofdPaneel;
        this.taalObj = taalObj;
        this.wijzigen = wijzigen;

        hoofdPaneel.setStatus(taalObj.getText("speelSpel"));
        voegComponentenToe();
    }

    private void voegComponentenToe()
    {
        ScrollPane sp = new ScrollPane();
        sp.setId("scrollSpelNamen");
        VBox controls = new VBox();
        controls.setId("scrollSpelNamen");
        setAlignment(Pos.BASELINE_CENTER);
        spelnamen = dc.geefSpelNamen();
        spellen = new Button[spelnamen.length];
        Button btnCancel = new Button(taalObj.getText("terug"));
        for (int i = 0; i < spelnamen.length; i++)
        {
            spellen[i] = new Button(spelnamen[i]);
            spellen[i].setId("btnAanmelden" + i);
            spellen[i].getStyleClass().add("btnsSpelNamen");
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
                        dc.kiesSpel(Integer.parseInt(click.getId().substring(12, click.getId().length())) + 1);
                        if(dc.geefAantalSpelborden()!=0){
                        if (!wijzigen)
                        {
                            SpelSpelenPaneelController spelSpelenPan = new SpelSpelenPaneelController(dc, hoofdPaneel, taalObj);
                            hoofdPaneel.setCenter(spelSpelenPan);
                        } else
                        {

                            List<String> keuzes = new ArrayList<>();
                            for (int i = 1; i <= dc.geefAantalSpelborden(); i++)
                            {
                                keuzes.add(String.format("%d", i));
                            }

                            ChoiceDialog<String> dialog = new ChoiceDialog<>("1", keuzes);
                            dialog.setTitle(taalObj.getText("spelLijst"));
                            dialog.setHeaderText(taalObj.getText("kiesSpelbord"));
                            dialog.setContentText(taalObj.getText("kiesSpelbord"));

                            Optional<String> result = dialog.showAndWait();
                            if (result.isPresent())
                            {
                                int volgnummer = Integer.parseInt(result.get());
                                hoofdPaneel.setStatus(taalObj.getText("wijzigSpel"));
                                WijzigSpelPaneelController wijzigSpelPan = new WijzigSpelPaneelController(dc, hoofdPaneel, taalObj, volgnummer);
                                hoofdPaneel.setCenter(wijzigSpelPan);
                            }

                        }
                        }
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
        sp.setContent(controls);
        sp.setPadding(new Insets(5,5,5,5));
        add(sp, 0, 0);

    }

    private void terugkeren(ActionEvent event)
    {
        hoofdPaneel.setCenter(new SpelKeuzePaneel(dc, hoofdPaneel, taalObj));
    }
}
