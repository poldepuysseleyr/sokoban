/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cui;

import domein.DomeinController;
import gui.Taal;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Maarten
 */
public class UC3Test {

    public static void geefSpelNamen(DomeinController dc, Taal taalObj) {
        Scanner input = new Scanner(System.in);
        String[] spelnamen = dc.geefSpelNamen();
        int keuze = 0;
        boolean validatie = false;
        String stoppen = "";
        System.out.println("---" + taalObj.getText("hoeSpelen")+"---");
        System.out.println(taalObj.getText("uitleg"));
        do {
            try {
                System.out.print(taalObj.getText("stoppenVraag") + " ");
                stoppen = input.next();
                if (!stoppen.equals("Y") && !stoppen.equals("N")) {
                    throw new IllegalArgumentException();
                }
                if (stoppen.equals("Y")) {
                    UC1Test.menu(dc, taalObj);
                }
                validatie = true;
            } catch (IllegalArgumentException ie) {
                System.out.println(taalObj.getText("buitenBereik"));
            }
        } while (!validatie);
        validatie = false;
        System.out.printf("-----%s-----%n", taalObj.getText("spelLijst"));
        for (String spelnamen1 : spelnamen) {
            System.out.printf("%s%n", spelnamen1);
        }
        do {
            System.out.printf("%s : ", taalObj.getText("maakKeuze"));
            try {
                keuze = input.nextInt();
                if (keuze > dc.geefAantalSpellen() || keuze < 1) {
                    throw new IllegalArgumentException(taalObj.getText("buitenBereik"));
                }
                validatie = true;
            } catch (InputMismatchException ie) {
                System.out.println(taalObj.getText("geenGetal"));
                input.next();
            } catch (IllegalArgumentException ie) {
                System.out.println(ie.getMessage());
            }
        } while (validatie == false);

        dc.kiesSpel(keuze);
        dc.resetAlleSpelborden();
        do {
            try {
                System.out.print(taalObj.getText("stoppenVraag") + " ");
                stoppen = input.next();
                if (!stoppen.equals("Y") && !stoppen.equals("N")) {
                    throw new IllegalArgumentException();
                }
                if (stoppen.equals("Y")) {
                    UC1Test.menu(dc, taalObj);
                }
                validatie = true;
            } catch (IllegalArgumentException ie) {
                System.out.println(taalObj.getText("buitenBereik"));
            }
        } while (!validatie);

        System.out.println(taalObj.getText("gekozenVoor") + " " + dc.toonSpelNaam());
    }

    public static void speelSpel(DomeinController dc, Taal taalObj) {
        Scanner input = new Scanner(System.in);
        boolean validatie = false;
        String richting;
        String stoppen = "";
        for (int i = 1; i < dc.geefAantalSpelborden() + 1; i++) {

            dc.kiesSpelbord(i);

            do {

                System.out.println(toonSpelbord(dc));
                System.out.print(taalObj.getText("richting") + ": ");
                try {
                    richting = input.next();
                    if (!richting.equals("links") && !richting.equals("rechts") && !richting.equals("boven") && !richting.equals("onder") && !richting.equals("reset") && !richting.equals("stop")) {
                        throw new InputMismatchException();
                    }
                    
                    if (richting.equals("reset")) {
                        dc.resetSpelbord();
                    }
                    else if(richting.equals("stop"))
                        UC1Test.menu(dc, taalObj);
                    else {
                        dc.verplaatsMan(richting);
                        toonAantalVerplaatsingen(dc, taalObj);
                    }
                    if (dc.isSpelbordVoltooid()) {
                        System.out.println(toonSpelbord(dc));
                        do {
                            try {
                                System.out.print(taalObj.getText("stoppenVraag") + " ");
                                stoppen = input.next();
                                if (!stoppen.equals("Y") && !stoppen.equals("N")) {
                                    throw new IllegalArgumentException();
                                }
                                if (stoppen.equals("Y")) {
                                    UC1Test.menu(dc, taalObj);
                                }
                                validatie = true;
                            } catch (IllegalArgumentException ie) {
                                System.out.println(taalObj.getText("buitenBereik"));
                            }
                        } while (!validatie);
                        
                        System.out.printf("Aantal voltooide spelborden : %d%n", dc.geefAantalVoltooideSpelborden());
                        System.out.printf("Aantal spelborden : %d%n", dc.geefAantalSpelborden());
                    }
                } catch (InputMismatchException ie) {
                    System.out.println(taalObj.getText("verkeerdeRichting"));
                }
            } while (!(dc.isSpelbordVoltooid() && validatie == true));
        }
        System.out.printf("%n%s %s%n",dc.toonSpelNaam(),taalObj.getText("voltooid"));
        dc.resetAlleSpelborden();
    }

    public static void toonAantalVerplaatsingen(DomeinController dc, Taal taalObj) {
        System.out.printf("%s: %d%n", taalObj.getText("aantalVerplaatsingen"), dc.geefAantalVerplaatsingen());
    }

    private static String toonSpelbord(DomeinController dc) {
        String[][] spelbord = dc.toonSpelbord();
        String output = "";
        for (int i = 0; i < spelbord.length; i++) {
            for (int j = 0; j < spelbord[i].length; j++) {
                output += String.format("%s  ", spelbord[i][j]);
                if (j == 9) {
                    output += String.format("%n");
                }
            }

        }
        return output;
    }
}
