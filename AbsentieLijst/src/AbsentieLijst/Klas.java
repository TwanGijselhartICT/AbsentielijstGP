package AbsentieLijst;

import java.util.ArrayList;
import java.util.HashMap;

public class Klas {
    private int aantalLeerlingen;
    private String klasCode;
    private ArrayList<Student> studenten = new ArrayList<>();
    private HashMap<String, Les> lessen = new HashMap<String, Les>();

    public Klas(int al, String kC) {
        aantalLeerlingen = al;
        klasCode = kC;
    }

    public int getAantalLeerlingen() {
        return studenten.size();
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

    public void setLessen(HashMap<String, AbsentieLijst.Les> hashMap){
        this.lessen.putAll(hashMap);
    }

    //public ArrayList<Les> getLessen(Time time, Time valueOf, LocalDate of){ return lessen; }
    public HashMap<String, Les> getLessen(){ return lessen; }

    @Override
    public String toString() {
        return "Klas{" +
                "aantalLeerlingen=" + aantalLeerlingen +
                ", klasCode='" + klasCode + '\'' +
                '}';
    }
}

