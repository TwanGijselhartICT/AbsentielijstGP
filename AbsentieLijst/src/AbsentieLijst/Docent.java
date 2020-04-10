package AbsentieLijst;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;

public class Docent {
    private String inlogcode;
    private String naam;
    private int docentNummer;
    private String wachtwoord;
    private boolean isIngelogd;
    public ArrayList<Afspraak> afspraken = new ArrayList<>();



    public Docent(String inlogcode, String nm, int dN, String wachtwoord) {
        this.inlogcode = inlogcode;
        this.naam = nm;
        this.docentNummer = dN;
        this.wachtwoord = wachtwoord;
        this.isIngelogd = false;
    }

    public String getNaam() {
        return naam;
    }

    public int getDocentNummer() {
        return docentNummer;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    public void voegAfspraakToe(String omschrijving, Time begin, Time einde, LocalDate datum) throws Exception {
        Afspraak afspraak = new Afspraak(omschrijving, begin, einde, datum);
        afspraken.add(afspraak);
    }

    public String getInlogcode() {
        return inlogcode;
    }

    public void setIngelogd(boolean ingelogd) {
        isIngelogd = ingelogd;
    }

    public boolean getIsIngelogd() {
        return this.isIngelogd;
    }

    @Override
    public String toString() {
        return "Docent{" +
                "inlogcode='" + inlogcode + '\'' +
                ", naam='" + naam + '\'' +
                ", docentNummer=" + docentNummer +
                ", wachtwoord='" + wachtwoord + '\'' +
                '}';
    }

    public ArrayList<Afspraak> getAfspraken() {
        return afspraken;
    }
}
