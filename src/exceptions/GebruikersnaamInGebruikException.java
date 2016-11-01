/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author Remko
 */
public class GebruikersnaamInGebruikException extends RuntimeException {

    /**
     * Methode om een exception op de gebruikersnaam te construeren wanneer deze alreeds bestaat 
     */
    public GebruikersnaamInGebruikException()
    {
    }
    
    /**
     * Methode om een exception op de gebruikersnaam te construeren wanneer deze alreeds bestaat 
     * @param message boodschap die weergeeft waar de fout zich bevindt en welke exception er optreedt 
     */
    public GebruikersnaamInGebruikException(String message)
    {
        super(message);
    }

   
}
