package persistentie;

import domein.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Dirk
 */
public class SpelerMapper
{

    /**
     * Methode om een speler uit de databank te halen
     * @param gebruikersnaam gebruikersnaam van de speler waarvan het object
     * gewenst is
     * @return geeft het object terug van de gevraagde speler
     */
    public Speler geefSpeler(String gebruikersnaam)
    {
	Speler speler = null;

	try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL))
	{
	    PreparedStatement query = conn.prepareStatement("SELECT * FROM speler WHERE gebruikersnaam = ?");
	    query.setString(1, gebruikersnaam);
	    try (ResultSet rs = query.executeQuery())
	    {
		if (rs.next())
		{
		    String wachtwoord = rs.getString("wachtwoord");
		    boolean adminrechten = rs.getBoolean("adminrechten");
                    String naam = rs.getString("naam");
                    String voornaam = rs.getString("voornaam");
		    speler = new Speler(gebruikersnaam, wachtwoord, adminrechten,naam,voornaam);
		}
	    }
	} catch (SQLException ex)
	{
	    throw new RuntimeException(ex);
	}

	return speler;
    }

    /** 
     * @return geeft een lijst van alle speler objecten in de databank terug
     */
//    public List<Speler> geefSpelers()
//    {
//	List<Speler> spelers = new ArrayList<>();
//
//	try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL))
//	{
//	    PreparedStatement query = conn.prepareStatement("SELECT * FROM speler");
//	    try (ResultSet rs = query.executeQuery())
//	    {
//		while (rs.next())
//		{
//		    String gebruikersnaam = rs.getString("gebruikersnaam");
//		    String wachtwoord = rs.getString("wachtwoord");
//		    boolean adminrechten = rs.getBoolean("adminrechten");
//                    String naam = rs.getString("naam");
//                    String voornaam = rs.getString("voornaam");
//
//		    spelers.add(new Speler(gebruikersnaam, wachtwoord, adminrechten,naam,voornaam));
//		}
//	    }
//	} catch (SQLException ex)
//	{
//	    throw new RuntimeException(ex);
//	}
//
//	return spelers;
//    }

    /**
     * Methode om een speler toe te voegen aan de databank
     * @param speler voegt een speler object toe aan de database
     */
    public void voegToe(Speler speler)
    {

	try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL))
	{
	    PreparedStatement query = conn.prepareStatement("INSERT INTO speler (gebruikersnaam,wachtwoord,adminrechten,naam,voornaam)"
		    + "VALUES (?, ?, ?, ?, ?)");
            query.setString(1, speler.getGebruikersnaam());
            query.setString(2, speler.getWachtwoord());
            query.setBoolean(3, speler.isAdminrechten());
	    query.setString(4, speler.getNaam());
	    query.setString(5, speler.getVoornaam());    

	    query.executeUpdate();

	} catch (SQLException ex)
	{
	    throw new RuntimeException(ex);
	}
    }
    

}
