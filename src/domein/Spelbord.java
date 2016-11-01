/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dirk
 */
public class Spelbord
{

    //private boolean voltooid; moet methode worden
    private int spelbordId;
    private int volgnummer;
    private Veld[][] velden;
    private int aantalVerplaatsingen = 0;
    private Man man;
    private List<Kist> kisten = new ArrayList<>();

    /**
     * Constructor om een spelbord aan te maken
     *
     * @param spelbordId unieke identiteit van het spelbord
     * @param volgnummer volgnummer van het spelbord in het huidige spel
     * @param velden velden meegeven die het spelbord bevat, al dan niet
     * ingevuld
     */
    public Spelbord(int spelbordId, int volgnummer, Veld[][] velden)
    {
	this.spelbordId = spelbordId;
	this.volgnummer = volgnummer;
	this.velden = velden;
	if (this.velden != null)
	{
	    this.stelManEnKistenIn();
	}
	//this.voltooid = false;
    }

    /**
     * Methode om de velden van het spelbord terug te geven
     *
     * @return geeft de velden van een spelbord terug
     */
    public Veld[][] getVelden()
    {
	return this.velden;
    }

    /**
     * Methode om de unieke identiteit van het spelbord terug te geven
     *
     * @return geeft het spelbordId van het spelbord terug
     */
    public int getSpelbordId()
    {
	return spelbordId;
    }

    /**
     * Methode om het aantal verplaatsingen terug te geven
     *
     * @return geeft het aantal verplaatsingen terug
     */
    public int getAantalVerplaatsingen()
    {
	return aantalVerplaatsingen;
    }

    /**
     * Methode om een object van man terug te geven
     *
     * @return geeft een object van man terug
     */
//    public Man getMan() {
//        return man;
//    }
    /**
     * Methode om een lijst met objecten van klasse kist terug te geven
     *
     * @return geeft een lijst met kisten terug
     */
//    public List<Kist> getKisten() {
//        return kisten;
//    }
    /**
     * Methode om het volgnummer van het spelbord terug te geven
     *
     * @return geeft het volgnummer van het spelbord terug
     */
    public int getVolgnummer()
    {
	return volgnummer;
    }

    /**
     * Methode om na te gaan of een veld een muur is of niet
     * @param x(rij) x-coördinaat van het veld
     * @param y(kolom) y-coördinaat van het veld
     * @return geef terug of het veld wel/geen muur is
     */
    public boolean isMuur(int x, int y)
    {
	return velden[x][y] == null;
    }

    /**
     * Methode om de man te verplaatsen in een richting
     *
     * @param richting geeft de richting aan waarnaar de man zich verplaatst
     */
    public void verplaatsMan(String richting)
    {
        try{
	int i = this.man.getVeld().getX();
	int j = this.man.getVeld().getY();
	switch (richting)
	{
	    case "boven":
		verplaats(-1, 0, i, j);
		break;
	    case "onder":
		verplaats(1, 0, i, j);
		break;
	    case "links":
		verplaats(0, -1, i, j);
		break;
	    case "rechts":
		verplaats(0, 1, i, j);
		break;
	}
	controleerVoltooid();
        }catch(NullPointerException ie){
        }
    }

    private void verplaats(int xOffset, int yOffset, int i, int j)
    {   try{
	if (velden[i + xOffset][j + yOffset] == null)
	{
	} else if (velden[i + xOffset][j + yOffset].getKist() == null)
	{
	    velden[i + xOffset][j + yOffset].setMan(velden[i][j].getMan());
	    velden[i + xOffset][j + yOffset].getMan().setVeld(velden[i + xOffset][j + yOffset]);
	    velden[i][j].setMan(null);
	    aantalVerplaatsingen++;
	} else if (velden[i + (2 * xOffset)][j + (2 * yOffset)] != null)
	{
	    if (velden[i + (2 * xOffset)][j + (2 * yOffset)].getKist() == null)
            {
		velden[i + (2 * xOffset)][j + (2 * yOffset)].setKist(velden[(i + xOffset)][j + yOffset].getKist());
		velden[i + (2 * xOffset)][j + (2 * yOffset)].getKist().setVeld(velden[i + (2 * xOffset)][j + (2 * yOffset)]);
		velden[i + xOffset][j + yOffset].setKist(null);
		velden[i + xOffset][j + yOffset].setMan(velden[i][j].getMan());
		velden[i + xOffset][j + yOffset].getMan().setVeld(velden[i + xOffset][j + yOffset]);
		velden[i][j].setMan(null);
		aantalVerplaatsingen++;
	    }
	}
    }catch(IndexOutOfBoundsException ie){
        
    }
        
    }

    /**
     * Methode om te controleren of het spelbord voltooid is
     * @return geeft aan of het spelbord wel/niet voltooid is
     */
    public boolean controleerVoltooid()
    {
	for (Kist k : this.kisten)
	{
	    if (k.getVeld() == null || !k.getVeld().isDoel())
	    {

		return false;
	    }
	}
	return true;
    }

    private void stelManEnKistenIn()
    {
	for (int i = 0; i < this.velden.length; i++)
	{
	    for (int j = 0; j < this.velden[i].length; j++)
	    {
		if ((!isMuur(i, j)) && velden[i][j].bevatMan())
		{
		    this.man = velden[i][j].getMan();
		} else if ((!isMuur(i, j)) && velden[i][j].bevatKist())
		{
		    this.kisten.add(velden[i][j].getKist());
		}
	    }
	}
    }

    void maakVelden()
    {
	Veld[][] veldenNieuw = new Veld[10][10];
	for (int i = 0; i < veldenNieuw.length; i++)
	{
	    for (int j = 0; j < veldenNieuw[i].length; j++)
	    {
		veldenNieuw[i][j] = new Veld(i, j, false);
	    }
	}
	this.velden = veldenNieuw;
    }

    /**
     * Methode om een item op een veld te plaatsen 
     * @param soort geeft aan welk soort item er geplaatst moet worden
     * @param x(rij) x-coördinaat van het veld
     * @param y(kolom) y-coördinaat van het veld
     */
    public void plaatsItem(int soort, int x, int y)
    {
        
	switch (soort)
	{
	    case 1:
		this.velden[x][y] = null;
		break;
	    case 2:
		this.velden[x][y] = new Veld(x, y, false);
		break;
	    case 3:
		this.velden[x][y] = new Veld(x, y, true);
		break;
	    case 4:
		Kist kistNieuw = new Kist();
		velden[x][y] = new Veld(x, y, false, null, kistNieuw);
		kistNieuw.setVeld(velden[x][y]);
		break;
	    case 5:
		Man manNieuw = new Man();
		velden[x][y] = new Veld(x, y, false, manNieuw, null);
		manNieuw.setVeld(velden[x][y]);
	}
        
                
                
    }

    /**
     * Methode om het volgnummer te zetten van het spelbord dat gespeeld moet worden
     * @param volgnummer geeft aan welk spelbord er gespeeld zal worden
     */
    public void setVolgnummer(int volgnummer)
    {
	this.volgnummer = volgnummer;
    }

}
