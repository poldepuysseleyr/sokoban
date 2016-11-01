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
 * @author Thomas
 */
public class UC1Test {

    public static void meldAan(DomeinController dc, Taal taalObj) {
        Scanner input = new Scanner(System.in);
        String gebruikersnaam = "";
        String wachtwoord = "";
        String stoppen = "";
        SokobanApplicatie sb;
        boolean validatie = false;
        do {
            try {
                System.out.printf("%s: ", taalObj.getText("geef_gebruikersnaam"));
                gebruikersnaam = input.next();
                System.out.printf("%s: ", taalObj.getText("geef_wachtwoord"));
                wachtwoord = input.next();
                do {
                    try {
                        System.out.print(taalObj.getText("stoppenVraag") + " ");
                        stoppen = input.next();
                        if (!stoppen.equals("Y") && !stoppen.equals("N")) {
                            throw new IllegalArgumentException();
                        }
                        validatie = true;
                    } catch (IllegalArgumentException ie) {
                        System.out.println(taalObj.getText("buitenBereik"));
                    }
                } while (!validatie);
                validatie = false;
                if (stoppen.equals("N")) {
                    dc.meldAan(gebruikersnaam, wachtwoord);
                } else {
                    sb = new SokobanApplicatie(dc);
                    sb.toonHoofdpaneel();
                }
                validatie = true;
            } catch (IllegalArgumentException ie) {
                System.out.println(taalObj.getText("gebruikersnaam_wachtwoord_ongeldig"));
            } catch (InputMismatchException ie) {
                System.out.println(taalObj.getText("geenGetal"));
            }

        } while (!validatie);

        System.out.printf("%s %s!%n", taalObj.getText("aanmelden_welkom"), dc.geefGebruikersnaam());
        menu(dc, taalObj);
    }

    public static void menu(DomeinController dc, Taal taalObj) {
        boolean validatie2 = false;
        int keuze = 0;
        Scanner input = new Scanner(System.in);
        String terug = "";
        do {
            System.out.printf("Menu : %n1.%s%n",
                    taalObj.getText("speelSpel"));

            if (dc.isAdmin()) {
                System.out.printf("2.%s%n3.%s%n", taalObj.getText("configureerSpel"), taalObj.getText("wijzigSpel"));
            }

            System.out.printf("%s : ", taalObj.getText("maakKeuze"));
            try {
                keuze = input.nextInt();
                if (dc.isAdmin() == false & (keuze != 1 || keuze < 1)) {
                    throw new IllegalArgumentException(taalObj.getText("buitenBereik"));
                } else {
                    if (dc.isAdmin() & (keuze > 3 || keuze < 1)) {
                        throw new IllegalArgumentException(taalObj.getText("buitenBereik"));
                    }
                }
                validatie2 = true;

            } catch (InputMismatchException ie) {
                System.out.println(taalObj.getText("geenGetal"));
                input.nextLine();
            } catch (IllegalArgumentException ie) {
                System.out.println(ie.getMessage());
            }

        } while (validatie2 == false);
        validatie2 = false;
        if (keuze == 1) {
            do {
                UC3Test.geefSpelNamen(dc, taalObj);
                UC3Test.speelSpel(dc, taalObj);

                do {
                    try {
                        System.out.print(taalObj.getText("terugNaarHetHoofdscherm") + " (Y/N)?");
                        terug = input.next();
                        if (!terug.equals("Y") && !terug.equals("N")) {
                            throw new IllegalArgumentException();
                        }

                        validatie2 = true;
                    } catch (IllegalArgumentException ie) {
                        System.out.println(taalObj.getText("buitenBereik"));
                    }
                } while (!validatie2);

            } while (!terug.equals("Y"));
            menu(dc, taalObj);
        }
        validatie2 = false;
        if (dc.isAdmin() & (keuze == 2)) {
            do {
                UC5Test.configureerNieuwSpel(dc, taalObj);
                do {
                    try {
                        System.out.print(taalObj.getText("terugNaarHetHoofdscherm") + " (Y/N)?");
                        terug = input.next();
                        if (!terug.equals("Y") && !terug.equals("N")) {
                            throw new IllegalArgumentException();
                        }

                        validatie2 = true;
                    } catch (IllegalArgumentException ie) {
                        System.out.println(taalObj.getText("buitenBereik"));
                    }
                } while (!validatie2);

            } while (!terug.equals("Y"));
            menu(dc, taalObj);
        }
        validatie2 = false;
        if (dc.isAdmin() & (keuze == 3)) {
            do {
                UC6Test.wijzigSpel(dc, taalObj);
                do {
                    try {
                        System.out.print(taalObj.getText("terugNaarHetHoofdscherm") + " (Y/N)?");
                        terug = input.next();
                        if (!terug.equals("Y") && !terug.equals("N")) {
                            throw new IllegalArgumentException();
                        }

                        validatie2 = true;
                    } catch (IllegalArgumentException ie) {
                        System.out.println(taalObj.getText("buitenBereik"));
                    }
                } while (!validatie2);
            } while (!terug.equals("Y"));
            menu(dc, taalObj);
        }

    }
}
