/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.List;

/**
 *
 * @author Dirk
 */
public class Spel
{

    private String naam;
    private int spelId;
    private List<Spelbord> spelborden;
    private Spelbord spelbord;

    /**
     * Constructor om een spel aan te maken
     *
     * @param spelId unieke identiteit van het spel
     * @param naam de naam van het spel
     * @param spelborden alle spelborden die het spel bevat meegeven
     */
    public Spel(int spelId, String naam,List<Spelbord> spelborden)
    {
	this.spelId = spelId;
	this.naam = naam;
	
	this.spelborden = spelborden;
    }



    /**
     * Methode om de unieke identiteit van het spel terug te geven
     *
     * @return geeft de unieke identiteit van het spel terug
     */
    public int getSpelId()
    {
	return spelId;
    }

    /**
     * Methode om de naam van het spel terug te geven
     *
     * @return geeft de naam van het spel terug
     */
    public String getNaam()
    {
	return naam;
    }

    /**
     * Methode om het aantal spelborden van het spel terug te geven
     *
     * @return geeft het aantal spelborden van het spel terug
     */
    public int getAantalSpelborden()
    {
	return spelborden.size();
    }

    /**
     * Methode om het aantal voltooide spelborden terug te geven
     *
     * @return geeft het aantal voltooide spelborden terug
     */
    public int getAantalVoltooideSpelborden()
    {
	int aantal = 0;
	for (Spelbord k : this.spelborden)
	{
	    if (k.controleerVoltooid())
	    {
		aantal++;
	    }
	}
	return aantal;
    }

    /**
     * Methode om een spelbord van het spel terug te geven
     *
     * @return geeft een spelbord van het spel terug
     */
    public Spelbord getSpelbord()
    {
	return spelbord;
    }

    /**
     * Methode om een lijst van spelborden van het spel terug te geven
     *
     * @return geeft een lijst van spelborden van het spel terug
     */
    public List<Spelbord> getSpelborden()
    {
	return spelborden;
    }

    /**
     * Methode om het huidig spelbord te zetten dat je kan spelen
     *
     * @param spelbord object van de klasse Spelbord
     */
    public void setSpelbord(Spelbord spelbord)
    {
	this.spelbord = spelbord;
    }

    /**
     * Methode om het mannetje te verplaatsen in gegeven richting
     *
     * @param richting richting waarin het mannetje moet bewegen
     */
    public void verplaatsMan(String richting)
    {
	this.spelbord.verplaatsMan(richting);
    }

    /**
     * Methode om het aantal verplaatsingen terug te geven
     *
     * @return geeft het aantal verplaatsingen van het mannetje terug
     */
    public int geefAantalVerplaatsingen()
    {
	return spelbord.getAantalVerplaatsingen();
    }


 

    void voegSpelbordToe(Spelbord spelbord)
    {
	this.spelborden.add(spelbord);
    }

    /**
     * Methode om een spelbord te kiezen met bepaald spelbordnummer
     *
     * @param volgnummer bepaalt welk spelbord gekozen wordt
     */
    public void kiesSpelbord(int volgnummer)
    {
	for (Spelbord spelborden1 : spelborden)
	{
	    if (spelborden1.getVolgnummer() == volgnummer)
	    {
		this.spelbord = spelborden1;
	    }
	}
        
    }

    /**
     * Methode om een item te plaatsen op een bepaald veld
     * @param soort geeft aan welk item er geplaatst moet worden
     * @param x(rij) x-coördinaat van het veld
     * @param y(kolom) y-coördinaat van het veld
     */
    public void plaatsItem(int soort, int x, int y)
    {
	this.spelbord.plaatsItem(soort, x, y);
    }

    /**
     * Methode om een spelbord te verwijderen uit de databank
     */
    public void verwijderSpelbord() {
        this.spelborden.remove(this.spelbord);

    
    }

    /**
     * Methode om het spelbord te resetten
     * @param nieuwSpelbord spelbord dat het gewijzigde spelbord zal overschrijven naar zijn originele staat
     */
    public void resetSpelbord(Spelbord nieuwSpelbord) {
       
        this.spelbord = nieuwSpelbord;
            
    }

    /**
     * Methode om alle spelborden te resetten
     * @param nieuweSpelborden de lijst met spelborden dat zal gereset worden
     */
    public void resetAlleSpelborden(List<Spelbord> nieuweSpelborden) {
        this.spelborden = nieuweSpelborden;
    }

    /**
     * Methode om te controleren opdat het huidig spelbord voltooid is
     * @return geeft waarde true terug als het spelbord voltooid is anders false
     */
    public boolean controleerVoltooid() {
        return this.spelbord.controleerVoltooid();
    }

    /**
     * Methode om velden aan te maken in het huidige spelbord
     */
    public void maakVelden() {
        this.spelbord.maakVelden();
    }
}
