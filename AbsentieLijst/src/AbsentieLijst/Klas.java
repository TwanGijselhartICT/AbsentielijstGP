package AbsentieLijst;

import java.util.ArrayList;
import java.sql.Time;
import java.time.LocalDate;

public class Klas {
    private int aantalLeerlingen;
    private String klasCode;
    private ArrayList<Student> studenten = new ArrayList<>();
    private ArrayList<Les> lessen = new ArrayList<>();

    public Klas(int al, String kC) {
        aantalLeerlingen = al;
        klasCode = kC;
    }

    public int getAantalLeerlingen() {
        return aantalLeerlingen;
    }


    public void setKlasCode(String klasCode)  { this.klasCode = klasCode; }

    public String getKlasCode() {
        return klasCode;
    }

    public void setAantalLeerlingen(int aantalLeerlingen) {
        this.aantalLeerlingen = aantalLeerlingen;
    }

    public void setStudenten(Student student) {
        this.studenten.add(student);
    }

    public ArrayList<Student> getStudenten() {
        return studenten;
    }

    public void setLessen(Les les){
        this.lessen.add(les);
    }

    //public ArrayList<Les> getLessen(Time time, Time valueOf, LocalDate of){ return lessen; }
    public ArrayList<Les> getLessen(){ return lessen; }

    @Override
    public String toString() {
        return "Klas{" +
                "aantalLeerlingen=" + aantalLeerlingen +
                ", klasCode='" + klasCode + '\'' +
                '}';
    }
}

