/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistentie;

import domein.Kist;
import domein.Man;
import domein.Veld;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author Remko
 */
public class VeldMapper {

    /**
     * Methode om de velden die bij een spelbord horen uit de databank te kunnen halen
     * @param spelbordId unieke identiteit van het spelbord waartoe de velden
     * behoren
     * @return geeft de velden van het spelbord terug
     */
    public Veld[][] geefVelden(int spelbordId) {
        Veld[][] velden = new Veld[10][10];
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL)) {
            PreparedStatement query = conn.prepareStatement("SELECT * FROM veld WHERE Spelbord_spelbordId = ?");
            query.setInt(1, spelbordId);
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {
                    int i = rs.getInt("x");
                    int j = rs.getInt("y");
                    String soortVeld = rs.getString("soortVeld");
                    switch (soortVeld) {
                        case "LeegVeld":
                            velden[i][j] = new Veld(i, j, false);
                            break;
                        case "Muur":
                            velden[i][j] = null;
                            break;
                        case "DoelVeld":
                            velden[i][j] = new Veld(i, j, true);
                            break;
                        case "Man":
                            Man man = new Man();
                            velden[i][j] = new Veld(i, j, false, man, null);
                            man.setVeld(velden[i][j]);
                            break;
                        case "Kist":
                            Kist kist = new Kist();
                            velden[i][j] = new Veld(i, j, false, null, kist);
                            kist.setVeld(velden[i][j]);
                            break;
                    }
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return velden;
    }

    void deleteVelden(int spelbordId) {
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL)) {
            PreparedStatement query = conn.prepareStatement("DELETE FROM veld WHERE Spelbord_spelBordId = ?");
            query.setInt(1, spelbordId);
            query.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Methode om de velden van een bepaald spelbord dat bij een bepaald spel hoort up te daten nadat
     * er een wijziging is aangebracht
     * @param velden de velden die moeten upgedate worden
     * @param spelId unieke identiteit van het spel waartoe deze velden behoren
     * @param spelbordId unieke identiteit van het spelbord waartoe deze velden behoren
     */
    public void updateVelden(Veld[][] velden, int spelId, int spelbordId) {
        String[][] str = new String[10][10];

        for (int i = 0; i < velden.length; i++) {
            for (int j = 0; j < velden[i].length; j++) {
                if (velden[i][j] == null) {
                    str[i][j] = "Muur";
                } else if (velden[i][j].isDoel() == true) {
                    str[i][j] = "DoelVeld";
                } else if (velden[i][j] == null) {
                    str[i][j] = "Muur";
                } else if (velden[i][j].getKist() != null) {
                    str[i][j] = "Kist";
                } else if (velden[i][j].getMan() != null) {

                    str[i][j] = "Man";
                } else {
                    str[i][j] = "LeegVeld";
                }
            }
        }
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL)) {
            PreparedStatement query = conn.prepareStatement("UPDATE veld SET soortVeld = ? WHERE Spelbord_Spel_spelId = ? AND Spelbord_spelBordId = ? AND x = ? AND y = ?");

            for (int i = 0; i < str.length; i++) {
                for (int j = 0; j < str[i].length; j++) {
                    query.setInt(2, spelId);
                    query.setInt(3, spelbordId);
                    query.setInt(4, i);
                    query.setInt(5, j);
                    query.setString(1, str[i][j]);
                    query.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Methode om velden in de databank toe te voegen die horen bij een spelbord
     *
     * @param velden object van de klasse Veld
     * @param spelId unieke identiteit van het spel waarbij de velden worden
     * toegevoegd
     * @param spelbordId unieke identiteit van het spelbord waarbij de velden
     * worden toegevoegd
     */
    public void bewaarVelden(Veld[][] velden, int spelbordId, int spelId) {
        String[][] str = new String[10][10];

        for (int i = 0; i < velden.length; i++) {
            for (int j = 0; j < velden[i].length; j++) {
                if (velden[i][j] == null) {
                    str[i][j] = "Muur";
                } else if (velden[i][j].isDoel() == true) {
                    str[i][j] = "DoelVeld";
                } else if (velden[i][j] == null) {
                    str[i][j] = "Muur";
                } else if (velden[i][j].getKist() != null) {
                    str[i][j] = "Kist";
                } else if (velden[i][j].getMan() != null) {

                    str[i][j] = "Man";
                } else {
                    str[i][j] = "LeegVeld";
                }
            }
        }
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL)) {
            PreparedStatement query = conn.prepareStatement("INSERT INTO veld(Spelbord_Spel_spelId,Spelbord_spelBordId, x, y, soortVeld)"
                    + "VALUES (?, ?, ?, ? ,?)");

            for (int i = 0; i < str.length; i++) {
                for (int j = 0; j < str[i].length; j++) {
                    query.setInt(1, spelId);
                    query.setInt(2, spelbordId);
                    query.setInt(3, i);
                    query.setInt(4, j);
                    query.setString(5, str[i][j]);
                    query.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}
