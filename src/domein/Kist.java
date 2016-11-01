/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

/**
 *
 * @author Dirk
 */
public class Kist
{ 
  private Veld veld;

    /**
     * Methode om het veld voor een kist in te stellen
     * @param veld object van de klasse veld waarop de kist moet staan
     */
    public void setVeld(Veld veld) {
        this.veld = veld;
    }

    /**
     * Methode om het veld waarop een kist staat terug te geven
     * @return geeft het veld terug waarop een kist staat
     */
    public Veld getVeld() {
        return veld;
    }

  
  
  
}
