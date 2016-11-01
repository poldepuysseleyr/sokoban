package domein;


import exceptions.GebruikersnaamInGebruikException;
import java.util.ArrayList;
import java.util.List;
import persistentie.*;

/**
 * 
 * @author Dirk
 */
public class SpelerRepository
{

    private final SpelerMapper spelerMapper;
    private final List<Speler> spelers = new ArrayList<>();

    /**
     * Constructor om een SpelerRepository aan te maken
     */
    public SpelerRepository()
    {
	spelerMapper = new SpelerMapper();
       
    }

    /**
     * Methode om de huidige speler terug te geven
     * @param gebruikersnaam gebruikersnaam van persoon die zich wil aanmelden
     * @param wachtwoord wachtwoord van de persoon die zich wil aanmelden
     * @return geeft een speler object terug indien één gevonden wordt dat voldoet aan de voorwaarden (parameters)
     */
    public Speler geefSpeler(String gebruikersnaam, String wachtwoord)
    {
	Speler s = spelerMapper.geefSpeler(gebruikersnaam);

	if (s != null)
	{
	    if (s.getWachtwoord().equals(wachtwoord))
	    {
		return s;
	    }
	}
	return null;
    }

    /**
     * Methode om na te kijken of de speler niet al reeds bestaat in de databank
     * @param gebruikersnaam gebruikersnaam waarvan we willen weten of de speler al bestaat
     * @return geeft een boolean terug om aan te duiden of de speler al dan niet bestaat
     */
    private boolean bestaatSpeler(String gebruikersnaam)
    {
	return spelerMapper.geefSpeler(gebruikersnaam) != null;
    }

    /**
     * Methode om een speler toe te voegen in de databank
     * @param speler spelerobject dat aangemaakt moet worden in de databank
     */
    public void voegToe(Speler speler)
    {
	if (bestaatSpeler(speler.getGebruikersnaam()))
	{
	    throw new GebruikersnaamInGebruikException("Speler bestaat al!");
	}

	spelerMapper.voegToe(speler);
    }
}
