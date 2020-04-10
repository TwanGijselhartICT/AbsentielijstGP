package AbsentieLijst;

import java.sql.Time;
import java.time.LocalDate;

public class Afspraak {
    private String omschrijving;
    private Time beginTijd;
    private Time eindeTijd;
    private LocalDate datum;


    public Afspraak(String om, Time be, Time ei, LocalDate da) {
        omschrijving = om;
        beginTijd = be;
        eindeTijd = ei;
        datum = da;
    }

    public void setBeginTijd (Time beginTijd) {
        this.beginTijd = beginTijd;
    }

    public void setDatum (LocalDate datum) {
        this.datum = datum;
    }

    public void setEindeTijd (Time eindeTijd) {
        this.eindeTijd = eindeTijd;
    }

    public void setOmschrijving (String omschrijving) {
        this.omschrijving = omschrijving;
    }

    public String getOmschrijving ( ) {
        return omschrijving;
    }

    public Time getBeginTijd ( ) {
        return beginTijd;
    }

    public LocalDate getDatum ( ) {
        return datum;
    }

    public Time getEindeTijd ( ) {
        return eindeTijd;
    }

    @Override
    public String toString ( ) {
        return omschrijving + "," + beginTijd + "," + eindeTijd + "," + datum;
    }
}

