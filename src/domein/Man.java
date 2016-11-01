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
public class Man
{ 
  private Veld veld;

    /**
     * Methode om het veld voor een man in te stellen
     * @param veld object van de klasse veld waarop de man moet staan
     */
    public void setVeld(Veld veld) {
        this.veld = veld;
    }

    /**
     * Methode om het veld waarop een man staat terug te geven
     * @return geeft het veld terug waarop een man staat
     */ 
    public Veld getVeld() {
        return veld;
    }

  
  
  
}
    

