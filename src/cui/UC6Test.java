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
 * @author Remko
 */
public class UC6Test {

    public static void wijzigSpel(DomeinController dc, Taal taalObj) {
        int spelNr, soort = 0, x, y, spelbordId;
        String val1 = "", val2 = "", bewaard;
        Scanner input = new Scanner(System.in);
        String[] spelNamen = dc.geefSpelNamen();
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
        System.out.printf("-----%s-----%n", taalObj.getText("spelLijst"));
        for (String spelNamen1 : spelNamen) {
            System.out.printf("%s%n", spelNamen1);
        }
        do {
            try {
                System.out.printf("%s: ", taalObj.getText("kiesSpel"));
                spelNr = input.nextInt();
                if (spelNr < 1 || spelNr > dc.geefAantalSpellen()) {
                    throw new IllegalArgumentException();
                }
                dc.kiesSpel(spelNr);
                dc.resetAlleSpelborden();
                validatie = true;
            } catch (InputMismatchException ie) {
                System.out.println(taalObj.getText("geenGetal"));
                input.next();
            } catch (IllegalArgumentException ie) {
                System.out.println(taalObj.getText("buitenBereik"));
            }
        } while (!validatie);
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
        validatie = false;
        do {
            try {
                System.out.printf("***%s***", taalObj.getText("spelLijst"));
                System.out.println(dc.geefSpelbordenLijst());
                System.out.printf("%s: ", taalObj.getText("kiesSpelbord"));
                spelbordId = input.nextInt();
                if (spelbordId < 1 || spelbordId > dc.geefAantalSpelborden()) {
                    throw new IllegalArgumentException();
                }
                dc.kiesSpelbord(spelbordId);
                validatie = true;
            } catch (InputMismatchException ie) {
                System.out.println(taalObj.getText("geenGetal"));
                input.next();
            } catch (IllegalArgumentException ie) {
                System.out.println(taalObj.getText("buitenBereik"));
            }
        } while (!validatie);
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
        validatie = false;
        System.out.println(toonSpelbord(dc));

        do {
            do {
                try {
                    System.out.printf("%s: ", taalObj.getText("pasSpelbordAan"));
                    val1 = input.next();
                    if (!val1.equals("Y") && !val1.equals("N")) {
                        throw new IllegalArgumentException();
                    }
                    validatie = true;
                } catch (IllegalArgumentException ie) {
                    System.out.println(taalObj.getText("geldigeNaam"));
                }
            } while (!validatie);
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
            validatie = false;
            if (val1.equals("Y")) {
                do {
                    try {
                        System.out.println(toonSpelbord(dc));
                        System.out.printf("%s", taalObj.getText("lijstItems2"));
                        System.out.print(taalObj.getText("maakKeuze") + " :");
                        soort = input.nextInt();
                        if (soort < 1 || soort > 7) {
                            throw new IllegalArgumentException();
                        }
                        if (soort == 6) {
                            validatie = true;
                            dc.verwijderSpelbord();
                        }
                        else if(soort == 7){
                            validatie = true;
                        } else {
                            do {
                                try {
                                    System.out.printf("%s: ", taalObj.getText("xCoord"));
                                    x = input.nextInt();
                                    System.out.printf("%s: ", taalObj.getText("yCoord"));
                                    y = input.nextInt();
                                    do {
                                        try {
                                            System.out.printf("%s: ", taalObj.getText("bevestigWijziging"));
                                            val2 = input.next();
                                            if (!val2.equals("Y") && !val2.equals("N")) {
                                                throw new IllegalArgumentException();
                                            }
                                            if (val2.equals("Y")) {
                                                dc.plaatsItem(soort, x, y);
                                            }
                                            validatie = true;
                                        } catch (IllegalArgumentException ie) {
                                            System.out.println(taalObj.getText("geldigeNaam"));
                                        }
                                    } while (!(validatie));
                                   

                                } catch (InputMismatchException ie) {
                                    System.out.println(taalObj.getText("geenGetal"));
                                    input.next();

                                } catch (IndexOutOfBoundsException ie) {
                                    System.out.println(taalObj.getText("buitenBereik"));
                                }

                            } while (!(validatie || val2.equals("N")));
                        }
                    } catch (InputMismatchException ie) {
                        System.out.println(taalObj.getText("geenGetal"));
                        input.next();
                    } catch (IllegalArgumentException ie) {
                        System.out.println(taalObj.getText("buitenBereik"));
                    }
                    dc.resetAlleSpelborden();
                } while (!(soort == 7 && validatie));
                validatie = false;
                do {
                    try {
                        System.out.printf("%s: ", taalObj.getText("bewaarSpelbord"));

                        bewaard = input.next();
                        if (!bewaard.equals("Y") && !bewaard.equals("N")) {
                            throw new IllegalArgumentException();
                        }
                        if (bewaard.equals("Y")) {
                            dc.updateSpelbord();
                        }else{
                            dc.resetSpelbord();
                        }
                        validatie = true;

                    } catch (IllegalArgumentException ie) {
                        System.out.println(taalObj.getText("geldigeNaam"));
                    }
                } while (!validatie);
                validatie = false;
            }
        } while (!val1.equals("N"));
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
