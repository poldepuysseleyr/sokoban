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
public class OngeldigGebruikersnaamException extends RuntimeException {

    /**
     * Methode om een exception op de gebruikersnaam te construeren wanneer deze ongeldig is 
     */
    public OngeldigGebruikersnaamException()
    {
    }
    
    /**
     * Methode om een exception op de gebruikersnaam te construeren wanneer deze ongeldig is 
     * @param message boodschap die weergeeft waar de fout zich bevindt en welke exception er optreedt
     */
    public OngeldigGebruikersnaamException(String message)
    {
        super(message);
    }

    
}
