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
public class SokobanApplicatie
{
//Git re-clone test door problemen.

    Scanner input = new Scanner(System.in);
    private DomeinController dc;
    private Taal taalObj;

    public SokobanApplicatie(DomeinController dc)
    {
	this.dc = dc;
    }

    public void toonHoofdpaneel()
    {
	boolean validatie = false;
	int keuze = 0;
	kiesTaal();

	System.out.printf("-----%s-----%n", taalObj.getText("welkom_hoofdpaneel"));
	do
	{
	    System.out.printf("1.%s%n", taalObj.getText("aanmelden"));
	    System.out.printf("2.%s%n", taalObj.getText("registreer"));
	    System.out.printf("%s: ", taalObj.getText("maakKeuze"));

	    try
	    {
		keuze = input.nextInt();
		if (keuze > 2 || keuze < 1)
		{
		    throw new IllegalArgumentException(taalObj.getText("buitenBereik"));
		}
		validatie = true;
	    } catch (InputMismatchException ie)
	    {
		System.out.println(taalObj.getText("geenGetal"));
		input.nextLine();
	    } catch (IllegalArgumentException ie)
	    {
		System.out.println(ie.getMessage());
	    }
	} while (validatie == false);
	input.nextLine(); //Zorgt ervoor dat input niet wordt overgeslaan in volgende methodes.
	if (keuze == 1)
	{   
            
	    UC1Test.meldAan(dc, taalObj);
	} else
	{
	    if (keuze == 2)
	    {
		UC2Test.registreer(dc, taalObj);
	    }
	}
    }

    //Taalkiezen
    public void kiesTaal()
    {
	String taal = "";
	do
	{
	    System.out.print("Kies een taal (nl)/ choose a language (en)/ Choisissez une langue(fr): ");
	    try
	    {
		taal = input.nextLine();
		if (!(("nl".equals(taal)) || ("en".equals(taal)) || ("fr".equals(taal))))
		{
		    throw new IllegalArgumentException("Verkeerde input/ Wrong input/ Entrée incorrecte");
		}
	    } catch (IllegalArgumentException ie)
	    {
		System.out.println(ie.getMessage());
	    }
	} while (!(taal.equals("nl") || taal.equals("fr") || taal.equals("en")));
	taalObj = new Taal(taal);
    }
}
