/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistentie;

//import domein.Vak;
import domein.Spelbord;
import domein.Veld;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Remko
 */
public class SpelbordMapper {

    VeldMapper vm = new VeldMapper();

    /**
     * Methode om een bepaald spelbord uit de databank te halen
     *
     * @param spelId unieke identeit van het spel waartoe het spelbord behoort
     * @param spelbordId unieke identiteit van het spelbord dat uit de databank
     * gehaald zal worden
     * @return geeft een spelbord terug
     */
    public Spelbord geefSpelbord(int spelId, int spelbordId) {
        Spelbord spelbord = null;
        Veld[][] velden;
        VeldMapper vm = new VeldMapper();
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL)) {
            PreparedStatement query = conn.prepareStatement("SELECT * FROM spelbord WHERE Spel_spelId = ?");
            query.setInt(1, spelId);
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {

                    int volgnummer = rs.getInt("volgnummer");
                    velden = vm.geefVelden(spelbordId);
                    spelbord = new Spelbord(spelbordId, volgnummer, velden);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return spelbord;
    }

    /**
     * Methode om een lijst van spelborden uit de databank te halen die horen
     * bij een bepaald spel
     *
     * @param spelId unieke identiteit van het spel waartoe de spelborden
     * behoren
     * @return geeft een lijst van spelborden terug
     */
    public List<Spelbord> geefSpelborden(int spelId) {
        List<Spelbord> spelborden = new ArrayList<>();
        Veld[][] velden;

        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL)) {
            PreparedStatement query = conn.prepareStatement("SELECT * FROM spelbord WHERE Spel_spelId = ?");
            query.setInt(1, spelId);
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {
                    int spelbordId = rs.getInt("SpelbordId");
                    int volgnummer = rs.getInt("volgnummer");
                    velden = vm.geefVelden(spelbordId);
                    spelborden.add(new Spelbord(spelbordId, volgnummer, velden));
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return spelborden;
    }

    /**
     * Methode om een bepaald spelbord uit de databank te verwijderen 
     * @param spelbordId unieke identiteit van het spelbord dat moet verwijderd worden
     */
    public void verwijderSpelbord(int spelbordId) {
        vm.deleteVelden(spelbordId);
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL)) {
            PreparedStatement query = conn.prepareStatement("DELETE FROM spelbord WHERE spelbordId = ?");
            query.setInt(1, spelbordId);
            query.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Methode om een spelbord toe te voegen aan een bepaald spel in een databank
     * @param spelbord unieke identiteit van het spelbord dat wordt toegevoegd
     * in de databank
     * @param spelId unieke identiteit van het spel waartoe het spelbord zal
     * behoren
     */
    public void bewaarSpelbord(Spelbord spelbord, int spelId) {

        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL)) {
            PreparedStatement query = conn.prepareStatement("INSERT INTO spelbord(spelBordId, Spel_spelId, volgnummer)"
                    + "VALUES (?, ?, ?)");
            query.setInt(1, spelbord.getSpelbordId());
            query.setInt(2, spelId);
            query.setInt(3, spelbord.getVolgnummer());
            query.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        vm.bewaarVelden(spelbord.getVelden(), spelbord.getSpelbordId(), spelId);

    }

    /**
     * Methode om een bepaald spelbord up te daten dat bij een bepaald spel hoort
     * @param spelId unieke identiteit van het spel waarbij het spelbord wordt upgedate
     * @param spelbord het spelbord dat zal upgedate worden
     */
    public void updateSpelbord(int spelId, Spelbord spelbord) {
        vm.updateVelden(spelbord.getVelden(), spelId, spelbord.getSpelbordId());
    }
}
