/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.List;
import persistentie.SpelMapper;

/**
 *
 * @author Dirk
 */
public class SpelRepository
{

    private final SpelMapper spelmapper;
    private List<Spel> spellen;

    /**
     * Constructor om een SpelRepository aan te maken
     */
    public SpelRepository()
    {
	spelmapper = new SpelMapper();
	spellen = spelmapper.geefSpellen();
    }

    /**
     * Methode om een lijst met spellen terug te geven
     *
     * @return geeft een lijst met alle spellen terug
     */
    public List<Spel> getSpellen()
    {
	return spellen;
    }

    /**
     * Methode om een spel met bepaalde speldId terug te geven
     *
     * @param spelId unieke identiteit van het spel
     * @return geeft het spel met bepaalde spelId terug
     */
    public Spel getSpel(int spelId)
    {
	return spellen.get(spelId - 1);
    }



    /**
     * Methode om het aantal spelborden in het huidige spel terug te geven
     *
     * @return geeft het aantal spelborden in het huidige spel terug
     */
    public int geefTotaalAantalSpelborden()
    {
	int aantal = 0;
	for (Spel spellen1 : spellen)
	{
	    if (spellen1.getSpelborden() != null)
	    {
		aantal += spellen1.getAantalSpelborden();
	    }
	}
	return aantal;
    }

    /**
     * Methode om een bepaald spelbord uit de databank te verwijderen
     * @param spelbordId unieke identiteit van het spelbord dat moet verwijderd worden
     */
    public void verwijderSpelbord(int spelbordId)
    {
	spelmapper.verwijderSpelbord(spelbordId);
    }

    /**
     * Methode om een bepaald spelbord terug te geven
     * @param spelId unieke identiteit van het spel waartoe het spelbord behoort
     * @param spelbordId
     * @return
     */
    public Spelbord geefSpelbord(int spelId, int spelbordId)
    {
	return spelmapper.geefSpelbord(spelId, spelbordId);
    }

    /**
     * Methode om een spel op te slaan in de databank
     * @param spel object van de klasse Spel dat zal opgeslaan worden
     */
    public void bewaarSpel(Spel spel) {
      spelmapper.bewaarSpel(spel);
    }

    /**
     * Methode om een bepaald spelbord up te daten
     * @param spel object van de klasse Spel dat zal upgedate worden
     */
    public void updateSpelbord(Spel spel) {
        spelmapper.updateSpelbord(spel);
    }

    /**
     * Methode om de spelborden die bij een bepaald spel horen terug te geven 
     * @param spelId unieke identiteit van het spel waarvan de spelborden moeten teruggegeven worden
     * @return geeft een lijst van spelborden terug 
     */
    public List<Spelbord> geefSpelborden(int spelId){
        return spelmapper.geefSpelborden(spelId);
    }
    
    /**
     * Methode het aantal spellen terug te geven
     * @return geeft het aantal spellen terug
     */
    public int geefAantalSpellen(){
        return this.spellen.size();
    }
    
    /**
     * Methode om een spel toe te voegen aan het attribuut spellen
     * @param spel is een spel dat aangemaakt wordt in de domeincontroller
     */
    public void voegSpelToe(Spel spel){
        this.spellen.add(spel);
    }
    
}
