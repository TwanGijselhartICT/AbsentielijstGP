package AbsentieLijst;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;

public class Student {
    private String studentNaam;
    private int studentNummer;
    private String wachtwoord;
    public ArrayList<Afspraak> afspraken = new ArrayList<>();
    public ArrayList<String> afgemeld = new ArrayList<>();
    private boolean studentZiek = false;
    private boolean isIngelogd;

    public Student(String sNa, int sNu, String wachtwoord, boolean sZ) {
        studentNaam = sNa;
        studentNummer = sNu;
        this.wachtwoord = wachtwoord;
        this.isIngelogd = false;
        this.studentZiek = sZ;

    }
    public ArrayList getAfgemeld(){return afgemeld;}
    public void setAfgemeld(String afmelding){afgemeld.add(afmelding);}
    public void setStudentNaam(String studentNaam) {
        this.studentNaam = studentNaam;
    }

    public void setStudentNummer(int studentNummer) {
        this.studentNummer = studentNummer;
    }

    public String getStudentNaam() {
        return studentNaam;
    }

    public int getStudentNummer() {
        return studentNummer;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }

    public void setStudentZiek(boolean studentZiek) {
        this.studentZiek = studentZiek;
    }

    public boolean getStudentZiek() {
        return studentZiek;
    }

    public void voegAfspraakToe(String omschrijving, Time begin, Time einde, LocalDate datum) throws Exception {
        Afspraak afspraak = new Afspraak(omschrijving, begin, einde, datum);
        afspraken.add(afspraak);
    }

    public ArrayList<Afspraak> getAfspraken() {
        return afspraken;
    }

    public void setIngelogd(boolean ingelogd) {
        isIngelogd = ingelogd;
    }

    public boolean getisIngelogd() {
        return this.isIngelogd;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentNaam='" + studentNaam + '\'' +
                ", studentNummer=" + studentNummer +
                '}';
    }
}