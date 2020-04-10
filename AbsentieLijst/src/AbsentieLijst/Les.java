package AbsentieLijst;

import javax.xml.crypto.Data;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;


public class Les {
    private String naam;
    private String lescode;
    private String locatie;
    private Time begintijd;
    private Time eindtijd;
    private LocalDate datum;

    public Les(String nm, String lc, String lo, Time bt, Time et) {
        naam = nm;
        lescode = lc;
        locatie = lo;
        begintijd = bt;
        eindtijd = et;


    }
    public LocalDate getDatum() { return datum; }

    public void setDatum(LocalDate datum) { this.datum = datum; }

    public void setLescode(String lescode) {
        this.lescode = lescode;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getLescode() {
        return lescode;
    }

    public String getLocatie() {
        return locatie;
    }

    public String getNaam() {
        return naam;
    }

    public Time getBegintijd() { return begintijd; }

    public void setBegintijd(Time begintijd) { this.begintijd = begintijd; }

    public Time getEindtijd() { return eindtijd; }

    public void setEindtijd(Time eindtijd) { this.eindtijd = eindtijd; }

    @Override
    public String toString ( ) { return naam + "," + lescode + "," + locatie + "," + begintijd + "," + eindtijd; }
}

