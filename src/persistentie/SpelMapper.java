/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistentie;

import domein.Spel;
import domein.Spelbord;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dirk
 */
public class SpelMapper
{
    SpelbordMapper sbm = new SpelbordMapper();

    /**
     * Methode om een spel met een bepaald spelId uit de databank te halen
     * @param spelId unieke identiteit van het spel dat uit de databank wordt gehaald
     * @return geeft een spel terug
     */
    public Spel geefSpel(int spelId)
    {
	Spel spel = null;

	try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL))
	{
	    PreparedStatement query = conn.prepareStatement("SELECT * FROM spel WHERE spelId = ?");
	    query.setInt(1, spelId);
	    try (ResultSet rs = query.executeQuery())
	    {
		if (rs.next())
		{
		    String naam = rs.getString("naam");
                    List<Spelbord> spelborden = sbm.geefSpelborden(spelId);
		    spel = new Spel(spelId,naam,spelborden);
		}
	    }
	} catch (SQLException ex)
	{
	    throw new RuntimeException(ex);
	}

	return spel;
    }

    /**
     * Methode om een lijst van spellen uit de databank te halen
     * @return geeft een lijst van spellen terug
     */
    public List<Spel> geefSpellen()
    {
	List<Spel> spel = new ArrayList<>();

	try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL))
	{
	    PreparedStatement query = conn.prepareStatement("SELECT * FROM spel");
	    try (ResultSet rs = query.executeQuery())
	    {
		while (rs.next())
		{
                    int spelId = rs.getInt("spelId");
		    String naam = rs.getString("naam");
                    List<Spelbord> spelborden = sbm.geefSpelborden(spelId);
		    spel.add(new Spel(spelId,naam,spelborden));
		}
	    }
	} catch (SQLException ex)
	{
	    throw new RuntimeException(ex);
	}

	return spel;

    }

    /**
     * Methode om een bepaald spelbord te verwijderen uit de databank
     * @param spelbordId unieke identiteit van het spelbord dat aantoont welk spelbord verwijderd zal worden
     */
    public void verwijderSpelbord(int spelbordId) {
        sbm.verwijderSpelbord(spelbordId);
    }

    /**
     * Methode om een spelbord uit de databank te halen 
     * @param spelId unieke identiteit van het spel waartoe het spelbord behoort
     * @param spelbordId unieke identiteit van het spelbord dat uit de databank gehaald zal worden
     * @return geeft een spelbord terug
     */
    public Spelbord geefSpelbord(int spelId, int spelbordId) {
        return sbm.geefSpelbord(spelId, spelbordId);
    }

    /**
     * Methode om het spel op te slaan in de databank
     * @param spel object van de klasse Spel dat zal bewaard worden in de databank
     */
    public void bewaarSpel(Spel spel) {
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL))
        {
            PreparedStatement query = conn.prepareStatement("INSERT INTO spel(spelId, naam)"
                    + "VALUES (?, ?)");
            query.setInt(1, spel.getSpelId());
            query.setString(2, spel.getNaam());
            query.executeUpdate();
        } catch (SQLException ex)
        {
            throw new RuntimeException(ex);
        }
        List<Spelbord> spelborden = spel.getSpelborden();
        for (Spelbord spelborden1 : spelborden) {
            sbm.bewaarSpelbord(spelborden1,spel.getSpelId());
        }
    }

    /**
     * Methode om het spelbord up te daten wanneer er een wijziging aan toegebracht is
     * @param spel object van de klasse Spel dat zal upgedate worden
     */
    public void updateSpelbord(Spel spel) {
    sbm.updateSpelbord(spel.getSpelId(),spel.getSpelbord());
    }
    
    /**
     * Methode om de spelborden die bij een bepaald spel horen terug te geven 
     * @param spelId unieke identiteit van het spel waarvan de spelborden moeten teruggegeven worden
     * @return geeft een lijst van spelborden terug 
     */
    public List<Spelbord> geefSpelborden(int spelId){
        return sbm.geefSpelborden(spelId);
    }
}
