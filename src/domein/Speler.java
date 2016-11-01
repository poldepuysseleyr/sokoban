package domein;

import exceptions.OngeldigGebruikersnaamException;
import exceptions.OngeldigWachtwoordException;

/**
 *
 * @author Dirk
 */
public class Speler
{

    private String gebruikersnaam;
    private String wachtwoord;
    private boolean adminrechten;
    private String naam;
    private String voornaam;

    //Constructors

    /**
     * Constructor om een speler object aan te maken met of zonder adminrechten
     * @param gebruikersnaam gebruikersnaam die de speler kiest
     * @param wachtwoord wachtwoord die de speler kiest
     * @param adminrechten kijken of de speler wel/geen adminrechten krijgt
     * @param naam familienaam van de speler 
     * @param voornaam voornaam van de speler
     */
        public Speler(String gebruikersnaam, String wachtwoord, boolean adminrechten, String naam, String voornaam)
    {
	this.setGebruikersnaam(gebruikersnaam);
	this.setWachtwoord(wachtwoord);
	this.adminrechten = adminrechten;
	this.naam = naam;
	this.voornaam = voornaam;
    }
//Getters

    /**
     * Methode om de gebruikersnaam van een speler terug te geven
     * @return geeft de gebruikersnaam van de speler terug
     */
    public String getGebruikersnaam()
    {
	return this.gebruikersnaam;
    }

    /**
     * Methode om het wachtwoord van een speler terug te geven
     * @return geeft het wachtwoord van een speler terug
     */
    public String getWachtwoord()
    {
	return this.wachtwoord;
    }

    /**
     * Methode om te kijken of een speler adminrechten heeft
     * @return geeft terug of de speler wel/geen adminrechten heeft
     */
    public boolean isAdminrechten()
    {
	return this.adminrechten;
    }

    /**
     * Methode om de familienaam van een speler terug te krijgen
     * @return geeft de familienaam van de speler terug
     */
    public String getNaam()
    {
	return this.naam;
    }

    /**
     * Methode om de voornaam van een speler terug te krijgen
     * @return geeft de voornaam van de speler terug
     */
    public String getVoornaam()
    {
	return this.voornaam;
    }

    //setters

    /**
     * Methode om de gebruikersnaam van een speler in te stellen
     * @param gebruikersnaam gebruikersnaam die de speler kiest
     */
        public void setGebruikersnaam(String gebruikersnaam)
    {
	if (gebruikersnaam == null || gebruikersnaam.length() == 0)
	{
	    throw new OngeldigGebruikersnaamException("Gebruikersnaam is verplicht in te vullen."); // Alles veranderen
	} else if (gebruikersnaam.length() < 8)
	{
	    throw new OngeldigGebruikersnaamException("Gebruikersnaam is minstens 8 tekens lang");
	}
	this.gebruikersnaam = gebruikersnaam;
    }

    /**
     * Methode om het wachtwoord van de speler in te stellen
     * @param wachtwoord wachtwoord die de speler kiest 
     */
    public void setWachtwoord(String wachtwoord)
    {
	if (wachtwoord == null || wachtwoord.length() == 0)
	{
	    throw new OngeldigWachtwoordException("Wachtwoord is verplicht in te vullen.");
	} else if (isCorrectWachtwoord(wachtwoord) == false)
	{
	    throw new OngeldigWachtwoordException("Wachtwoord is minstens 8 tekens lang,bevat minstens 1 hoofdletter, 1 kleine letter en 1 cijfer");
	}
	this.wachtwoord = wachtwoord;
    }

    /**
     * @param wachtwoord ingegeven wachtwoord dat moet gecontroleerd worden op geldigheid
     * @return geeft boolean terug of het wachtwoord geldig is voor de gewenste parameters.
     */
    private boolean isCorrectWachtwoord(String wachtwoord)
    {

	boolean isMinstens8TekensLang = wachtwoord.length() >= 8;
	boolean bevatKleineLetter = !wachtwoord.equals(wachtwoord.toLowerCase());
	boolean bevatGroteLetter = !wachtwoord.equals(wachtwoord.toUpperCase());
	boolean bevatCijfer = wachtwoord.matches(".*[0-9].*");

	return (isMinstens8TekensLang && bevatKleineLetter && bevatGroteLetter && bevatCijfer);

    }

}
