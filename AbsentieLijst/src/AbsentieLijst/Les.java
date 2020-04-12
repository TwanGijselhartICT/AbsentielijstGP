package AbsentieLijst;

import java.util.ArrayList;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Objects;


public class Les {
    private String naam;
    private String lescode;
    private String locatie;
    private Time begintijd;
    private Time eindtijd;
    private LocalDate datum;
    private ArrayList<String> absent = new ArrayList<>();
    private ArrayList<Student> present = new ArrayList<>();
    School HU = School.getSchool();

    public Les (String nm, String lc, String lo, Time bt, Time et, LocalDate da) {
        naam = nm;
        lescode = lc;
        locatie = lo;
        begintijd = bt;
        eindtijd = et;
        datum = da;

    }

    public LocalDate getDatum ( ) {
        return datum;
    }

    public ArrayList getAbsent ( ) {
        return absent;
    }

    public void setAbsent (Student student, String reden) {
        absent.add(student.getStudentNaam() + Objects.requireNonNullElse(reden, " Afwezig zonder reden"));
        present.remove(student);
    }

    public void setPresent (Klas klas) {
        for (Student student : klas.getStudenten()) {
             present.add(student);
        }
    }


    public ArrayList getPresent ( ) {
        return present;
    }

    public void setDatum (LocalDate datum) {
        this.datum = datum;
    }

    public void setLescode (String lescode) {
        this.lescode = lescode;
    }

    public void setLocatie (String locatie) {
        this.locatie = locatie;
    }

    public void setNaam (String naam) {
        this.naam = naam;
    }

    public String getLescode ( ) {
        return lescode;
    }

    public String getLocatie ( ) {
        return locatie;
    }

    public String getNaam ( ) {
        return naam;
    }

    public Time getBegintijd ( ) {
        return begintijd;
    }

    public void setBegintijd (Time begintijd) {
        this.begintijd = begintijd;
    }

    public Time getEindtijd ( ) {
        return eindtijd;
    }

    public void setEindtijd (Time eindtijd) {
        this.eindtijd = eindtijd;
    }

    @Override
    public String toString ( ) {
        return naam + "," + lescode + "," + locatie + "," + begintijd + "," + eindtijd;
    }
}

