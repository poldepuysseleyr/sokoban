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
public class UC5Test {

    public static void configureerNieuwSpel(DomeinController dc, Taal taalObj) {
        Scanner input = new Scanner(System.in);
        String naam = "", valid = "", bewaard;
        int soort = 0, x = 0, y = 0;
        int volgnummer = 1;
        boolean validatie = false;
        String stoppen = "";
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
        do {
            try {
                System.out.printf("%s: ", taalObj.getText("uniekeNaam"));
                naam = input.next();
                validatie = true;
                if (dc.controleerUniekeNaam(naam)) {
                    System.out.println(taalObj.getText("spelBestaatAl"));
                }
            } catch (InputMismatchException ie) {
                System.out.println();
            }
        } while (!(!dc.controleerUniekeNaam(naam) && validatie));
        validatie = false;
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
        dc.maakSpel(naam);
        validatie = false;
        do {
            dc.maakSpelbord(volgnummer++);
            dc.maakVelden();
            System.out.println(toonSpelbord(dc));
            do {
                System.out.printf("%s", taalObj.getText("lijstItems"));
                try {

                    soort = input.nextInt();
                    if (soort < 1 || soort > 6) {
                        throw new IllegalArgumentException();
                    }
                    if (soort != 6) {
                        do {
                            try {
                                System.out.printf("%s: ", taalObj.getText("xCoord"));
                                x = input.nextInt();
                                System.out.printf("%s: ", taalObj.getText("yCoord"));
                                y = input.nextInt();

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
                                dc.plaatsItem(soort, x, y);
                                validatie = true;
                            } catch (IndexOutOfBoundsException ie) {
                                System.out.println(taalObj.getText("buitenBereik"));
                            } catch (InputMismatchException ie) {
                                System.out.println(taalObj.getText("geenGetal"));
                                input.next();
                            }
                        } while (!validatie);

                        System.out.println(toonSpelbord(dc));
                    }
                    validatie = true;
                } catch (InputMismatchException ie) {
                    System.out.println(taalObj.getText("geenGetal"));
                    input.next();
                } catch (IllegalArgumentException ix) {
                    System.out.println(taalObj.getText("buitenBereik"));
                }

            } while (!(soort == 6 && validatie));
            validatie = false;
            do {
                try {
                    System.out.printf("%s: ", taalObj.getText("maakSpelbord"));
                    valid = input.next();
                    if (!valid.equals("Y") && !valid.equals("N")) {
                        throw new IllegalArgumentException();
                    }
                    validatie = true;

                } catch (IllegalArgumentException ie) {
                    System.out.println(taalObj.getText("geldigeNaam"));

                }
            } while (!validatie);
        } while (valid.charAt(0) == 'Y');
        validatie = false;
        do {
            try {
                System.out.printf("%s: ", taalObj.getText("bewaarSpelbord"));
                bewaard = input.next();
                if (!bewaard.equals("Y") && !bewaard.equals("N")) {
                    throw new IllegalArgumentException();
                }
                if (bewaard.equals("Y")) {
                    dc.bewaarSpel();
                } else {
//                       
                }
                validatie = true;
            } catch (IllegalArgumentException ie) {
                System.out.println(taalObj.getText("geldigeNaam"));

            }

        } while (!validatie);
        System.out.println(taalObj.getText("gekozenVoor") + " " + dc.toonSpelNaam());
        System.out.printf("Aantal spelborden : %d%n", dc.geefAantalSpelborden());
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
