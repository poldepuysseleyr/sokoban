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
public class OngeldigWachtwoordException extends RuntimeException {

    /**
     * Methode om een exception op het wachtwoord te construeren wanneer deze ongeldig is
     */
    public OngeldigWachtwoordException()
    {
    }
    
    /**
     * Methode om een exception op het wachtwoord te construeren wanneer deze ongeldig is
     * @param message boodschap die weergeeft waar de fout zich bevindt en welke exception er optreedt
     */
    public OngeldigWachtwoordException(String message)
    {
        super(message);
    }

    

}
