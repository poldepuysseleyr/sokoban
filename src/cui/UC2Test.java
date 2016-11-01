/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cui;

import domein.DomeinController;
import exceptions.GebruikersnaamInGebruikException;
import exceptions.OngeldigGebruikersnaamException;
import exceptions.OngeldigWachtwoordException;
import java.util.Scanner;
import gui.Taal;
import java.util.InputMismatchException;

/**
 *
 * @author Dirk
 */
public class UC2Test {

//    private static Taal taalObj;
    Scanner input = new Scanner(System.in);

    public static void registreer(DomeinController dc, Taal taal) {
        Scanner input = new Scanner(System.in);
        String gebruikersnaam;
        String wachtwoord;
        String naam;
        String voornaam;
        boolean validatie2 = false;
        int keuze = 0;
        boolean validatie = false;
        do {
            try {
                System.out.printf("%s: ", taal.getText("geefNaam"));
                naam = input.nextLine();
                System.out.printf("%s: ", taal.getText("geefVoornaam"));
                voornaam = input.nextLine();
                System.out.printf("%s: ", taal.getText("geef_gebruikersnaam"));
                gebruikersnaam = input.next();
                System.out.printf("%s: ", taal.getText("geef_wachtwoord"));
                wachtwoord = input.next();
                dc.registreer(gebruikersnaam, wachtwoord, false, naam, voornaam);
                dc.meldAan(gebruikersnaam, wachtwoord);
                System.out.printf("%s %s!%n", taal.getText("aanmelden_welkom"), dc.geefGebruikersnaam());

                validatie = true;
            } catch (GebruikersnaamInGebruikException ie) {
                System.out.println(ie.getMessage());
                System.out.println(taal.getText("probeerOpnieuw"));
                input.nextLine();
            } catch (OngeldigGebruikersnaamException ie) {
                System.out.println(taal.getText("gebruikersnaamOngeldig"));
                System.out.println(taal.getText("probeerOpnieuw"));
                input.nextLine();
            } catch (OngeldigWachtwoordException ie) {
                System.out.println(taal.getText("wachtwoordOngeldig"));
                System.out.println(taal.getText("probeerOpnieuw"));
                input.nextLine();
            }

        } while (validatie == false);
        do {
            System.out.printf("%s: %n1.%s%n", taal.getText("maakKeuze"), taal.getText("speelSpel"));
            try {
                System.out.printf("%s : ", taal.getText("maakKeuze"));

                keuze = input.nextInt();
                if (keuze != 1) {
                    throw new IllegalArgumentException(taal.getText("buitenBereik"));
                }
                validatie2 = true;
            } catch (InputMismatchException ie) {
                System.out.println(taal.getText("geenGetal"));
                input.nextLine();
            } catch (IllegalArgumentException ie) {
                System.out.println(ie.getMessage());
            }
        } while (validatie2 == false);
        if (keuze == 1) {
            UC3Test.geefSpelNamen(dc, taal);
            UC3Test.speelSpel(dc, taal);
        }

    }

    //Taalkiezen
    public void kiesTaal() {
        String taal = "";
        System.out.println("Kies uw taal/ choose your language/ Choisissez votre langue");
        taal = input.nextLine();

    }

}
