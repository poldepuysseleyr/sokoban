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
public class Veld
{    
    private int x;
    private int y;
    private boolean doel;
    private Man man;
    private Kist kist;

    /**
     * Constructor om een veld aan te maken
     * @param x coördinaat van het veld op de x-as(rij)
     * @param y coördinaat van het veld op de y-as(kolom)
     * @param doel kijken of het veld een kist moet bevatten om het spel te voltooien
     */
    public Veld(int x, int y,boolean doel){
      this(x,y,doel,null,null);
  }

    /**
     * Constructor om een veld aan te maken met man of kist op
     * @param x coördinaat van het veld op de x-as(rij)
     * @param y coördinaat van het veld op de y-as(kolom)
     * @param doel kijken of het veld een kist moet bevatten om het spel te voltooien
     * @param man kijken of het veld een man moet bevatten
     * @param kist kijken of het veld een kist moet bevatten
     */
    public Veld(int x, int y,boolean doel,Man man,Kist kist)
    {
        this.x = x;
        this.y = y;
        this.doel = doel;
        this.man = man;
        this.kist = kist;
    }

    /**
     * Methode om de x-coördinaat van een veld terug te geven(rij)
     * @return geeft de x-coördinaat van een veld terug
     */
    public int getX() {
        return x;
    }

    /**
     * Methode om de y-coördinaat van een veld terug te geven(kolom)
     * @return geeft de y-coördinaat van een veld terug 
     */
    public int getY() {
        return y;
    }

    /**
     * Methode om te kijken of een veld een kist moet bevatten om het spel te voltooien
     * @return geeft aan of het veld wel/geen doel is
     */
    public boolean isDoel() {
        return doel;
    }

    /**
     * Methode om een object van man terug te geven 
     * @return geeft een object van man terug
     */
    public Man getMan() {
      return this.man;
    }

    /**
     * Methode om een object van kist terug te geven
     * @return geeft een object van kist terug
     */
    public Kist getKist() {
        return kist;
    }
    
    /**
     * Methode om te kijken of het veld een kist bevat
     * @return geeft aan of het veld wel/geen kist bevat
     */
    public boolean bevatKist(){
        return kist != null;
    }

    /**
     * Methode om te kijken of het veld een man bevat
     * @return geeft aan of het veld wel/geen man bevat
     */
    public boolean bevatMan(){
        return man != null;
    }

    /**
     * Methode om op het veld een object van man te plaatsen
     * @param man object van klasse man
     */
    public void setMan(Man man) {
        this.man = man;
    }
    
    /**
     * Methode om op het veld een object van kist te plaatsen 
     * @param kist object van klasse kist
     */
    public void setKist(Kist kist) {
        this.kist = kist;
    }

    
    
       
}
