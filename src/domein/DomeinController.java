package domein;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dirk
 */
public class DomeinController {

    private final SpelerRepository spelerRepo;
    private final SpelRepository spelRepo;
    private Speler speler;
    private Spel spel;

    /**
     * Constructor om een DomeinController aan te maken
     */
    public DomeinController() {
        spelerRepo = new SpelerRepository();
        spelRepo = new SpelRepository();
    }

    
    /**
     * Methode om de gebruikersnaam van een speler terug te geven
     *
     * @return geeft de gebruikersnaam van een speler terug
     */
    public String geefGebruikersnaam() {
        return this.speler.getGebruikersnaam();
    }

    /**
     * Methode om de speler in te stellen die het spel zal spelen
     *
     * @param speler object van de klasse Speler die het spel zal spelen
     */
    public void setSpeler(Speler speler) {
        this.speler = speler;
    }

    /**
     * Methode om te kunnen aanmelden
     *
     * @param gebruikersnaam gebruikersnaam van de persson die zich wil
     * aanmelden
     * @param wachtwoord wachtwoord van de persoon die zich wil aanmelden
     */
    public void meldAan(String gebruikersnaam, String wachtwoord) {
        Speler gevondenSpeler = spelerRepo.geefSpeler(gebruikersnaam, wachtwoord);

        if (gevondenSpeler != null) {
            setSpeler(gevondenSpeler);

        } else {
            throw new IllegalArgumentException("Verkeerde login!");
        }
    }

    /**
     * Methode om te kijken of de speler een admin is
     *
     * @return geeft terug of de speler wel/geen admin is
     */
    public boolean isAdmin() {
        return this.speler.isAdminrechten();
    }

    /**
     * Methode om het spelbord te tonen en af te printen
     *
     * @return geeft het spelbord terug dat afgeprint wordt
     */
    public String[][] toonSpelbord() {
        String output[][] = new String[10][10];
        Veld[][] velden = this.spel.getSpelbord().getVelden();

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (this.spel.getSpelbord().isMuur(i, j)) {
                    output[i][j] = "X";
                } else if (velden[i][j].isDoel() && velden[i][j].bevatKist()) {
                    output[i][j] = "V";
                } else if (velden[i][j].isDoel() && !velden[i][j].bevatMan()) {
                    output[i][j] = ".";
                } else if (!velden[i][j].isDoel() && !velden[i][j].bevatKist() && !velden[i][j].bevatMan()) {
                    output[i][j] = " ";
                } else if (velden[i][j].bevatMan() && !velden[i][j].isDoel()) {
                    output[i][j] = "@";
                } else if (velden[i][j].bevatKist()) {
                    output[i][j] = "*";
                } else if (velden[i][j].isDoel() && velden[i][j].bevatMan()) {
                    output[i][j] = "#";
                }
            }
        }
        return output;

    }

    /**
     * Methode om te kunnen registreren
     *
     * @param gebruikersnaam gekozen gebruikersnaam door de gebruiker
     * @param wachtwoord gekozen wachtwoord door de gebruiker
     * @param adminrechten boolean om aan te duiden of de gebruiker adminrechten
     * heeft. Standaard op false
     * @param naam achternaam van de gebruiker, optioneel
     * @param voornaam voornaam van de gebruiker, optioneel
     */
    public void registreer(String gebruikersnaam, String wachtwoord, boolean adminrechten, String naam, String voornaam) {

        Speler nieuweSpeler = new Speler(gebruikersnaam, wachtwoord, adminrechten, naam, voornaam);
        setSpeler(nieuweSpeler);
        spelerRepo.voegToe(nieuweSpeler);

    }

    private void setSpel(Spel spel) {
        this.spel = spel;
    }

    /**
     * Methode om de naam van het spel te tonen
     *
     * @return geeft de naam van het spel terug
     */
    public String toonSpelNaam() {
        return this.spel.getNaam();
    }

    /**
     * Methode om een spel te kiezen met een bepaald spelnummer
     *
     * @param spelId spelnummer van het spel dat gekozen wordt
     */
    public void kiesSpel(int spelId) {
        setSpel(spelRepo.getSpel(spelId));
    }

    /**
     * Methode om de namen van de spellen terug te geven
     *
     * @return geeft de namen van de spellen terug
     */
    public String[] geefSpelNamen() {
        List<Spel> spellen = spelRepo.getSpellen();
        int n = spellen.size();
        String[] namen = new String[n];

        for (int i = 0; i < n; i++) {
            namen[i] = String.format("%d. %s", i + 1, spellen.get(i).getNaam());
        }

        return namen;
    }

    /**
     * Methode om de man te verplaatsen in een bepaalde richting
     *
     * @param richting geeft de richting waarnaar de man zich moet verplaatsen
     */
    public void verplaatsMan(String richting) {
        this.spel.verplaatsMan(richting);

    }

    /**
     * Methode om het aantal verplaatsingen terug te geven van het mannetje
     *
     * @return geeft het aantal verplaatsingen van het mannetje terug
     */
    public int geefAantalVerplaatsingen() {
        return spel.geefAantalVerplaatsingen();
    }

    /**
     * Methode om het aantal spellen terug te geven
     *
     * @return geeft het aantal spellen terug
     */
    public int geefAantalSpellen() {
        return spelRepo.geefAantalSpellen();
    }

    /**
     * Methode om te controleren op een unieke naam van het spel
     *
     * @param naam naam die het spel zal meekrijgen
     * @return geeft terug of het spel wel/geen unieke naam bevat
     */
    public boolean controleerUniekeNaam(String naam) {
        for (Spel sp : spelRepo.getSpellen()) {
            if (sp.getNaam().equals(naam)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Methode om een item te plaatsen
     *
     * @param soort het soort item dat geplaatst moet
     * worden(kist,man,doel,leegveld)
     * @param x x-coördinaat(rij) waarop het item moet geplaatst worden
     * @param y y-coördinaat(kolom) waarop het item moet geplaatst worden
     */
    public void plaatsItem(int soort, int x, int y) {
        this.spel.plaatsItem(soort, x, y);
    }

    /**
     * Methode om een nieuw spel aan te maken met een naam
     *
     * @param naam naam dat het spel zal krijgen
     */
    public void maakSpel(String naam) {
        Spel spelNieuw = new Spel(geefAantalSpellen() + 1, naam, new ArrayList<>());
        spelRepo.voegSpelToe(spelNieuw);
        setSpel(spelNieuw);

    }

    /**
     * Methode om een nieuw spelbord aan te maken met een volgnummer
     *
     * @param volgnummer volgnummer dat het spelbord zal toegewezen krijgen
     */
    public void maakSpelbord(int volgnummer) {
        Spelbord spelbordNieuw = new Spelbord(spelRepo.geefTotaalAantalSpelborden() + 1, volgnummer, null);
        this.spel.voegSpelbordToe(spelbordNieuw);
        this.spel.setSpelbord(spelbordNieuw);
    }

    /**
     * Methode om velden aan te maken voor het spelbord
     */
    public void maakVelden() {
        this.spel.maakVelden();
    }

    /**
     * Methode om een lijst van spelborden terug te krijgen va het spel
     *
     * @return geeft een lijst met spelborden terug
     */
    public String geefSpelbordenLijst() {
        int teller = 1;
        String[][] input;
        String output = "";
        List<Spelbord> spelborden = this.spel.getSpelborden();
        for (Spelbord spelborden1 : spelborden) {
            this.spel.setSpelbord(spelborden1);
            input = toonSpelbord();
            output += String.format("%n%d.%n", teller++);
            for (String[] input1 : input) {
                for (int j = 0; j < input1.length; j++) {
                    output += String.format("%s  ", input1[j]);
                    if (j == input1.length - 1) {
                        output += String.format("%n");
                    }
                }
            }
            output += String.format("%n");
        }
        return output;
    }

    /**
     * Methode om een spelbord te kiezen met bepaald spelbordnummer
     *
     * @param volgnummer spelbordnummer van het spelbord dat gekozen wordt
     */
    public void kiesSpelbord(int volgnummer) {
        this.spel.kiesSpelbord(volgnummer);

    }

    /**
     * Methode om een spelbord te verwijderen
     */
    public void verwijderSpelbord() {
        this.spel.verwijderSpelbord();
        spelRepo.verwijderSpelbord(this.spel.getSpelbord().getSpelbordId());
    }

    /**
     * Methode om het aantal spelborden terug te geven
     *
     * @return geeft het aantal spelborden terug
     */
    public int geefAantalSpelborden() {
        return this.spel.getAantalSpelborden();
    }

    /**
     * Methode om het spelbord te resetten indien je het niet kan oplossen
     */
    public void resetSpelbord() {
        Spelbord nieuwSpelbord = spelRepo.geefSpelbord(this.spel.getSpelId(), this.spel.getSpelbord().getSpelbordId());
        this.spel.resetSpelbord(nieuwSpelbord);
    }

    /**
     * Methode om na te gaan of het spelbord is voltooid
     *
     * @return geeft aan of het spelbord wel/niet voltooid is
     */
    public boolean isSpelbordVoltooid() {
        return this.spel.controleerVoltooid();
    }

    /**
     * Methode om het aantal voltooide spelborden weer te geven
     *
     * @return geeft het aantal voltooide spelborden terug
     */
    public int geefAantalVoltooideSpelborden() {
        return this.spel.getAantalVoltooideSpelborden();
    }

    /**
     * Methode om het spelbord op te slaan in een databank
     */
    public void bewaarSpel() {
        spelRepo.bewaarSpel(this.spel);
    }

    /**
     * Methode om het spelbord up te daten nadat een wijziging is toegebracht
     */
    public void updateSpelbord() {
        spelRepo.updateSpelbord(this.spel);
    }

    /**
     * Methode om het totaal aantal spelborden dat bestaat terug te geven
     *
     * @return geeft het totaal aantal spelborden terug
     */
    public int geefTotaalAantalSpelborden() {
        return spelRepo.geefTotaalAantalSpelborden();
    }

    /**
     * Methode om alle spelborden te resetten bij een bepaald spel
     */
    public void resetAlleSpelborden() {
        this.spel.resetAlleSpelborden(spelRepo.geefSpelborden(this.spel.getSpelId()));

    }
    
    

}
