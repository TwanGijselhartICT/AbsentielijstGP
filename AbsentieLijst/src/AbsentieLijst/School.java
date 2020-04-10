package AbsentieLijst;

import java.util.ArrayList;

public class School {
    public String schoolNaam;
    public ArrayList<Docent> docenten = new ArrayList<>();
    public ArrayList<Klas> klassen = new ArrayList<>();
    private static School deSchool;

    public static void setSchool(School school) {
        deSchool = school;
    }

    public static School getSchool() {
        return deSchool;
    }

    public School(String nm) {
        this.schoolNaam = nm;
    }

    public void setDocenten(Docent docent) {
        docenten.add(docent);
    }

    public ArrayList<Docent> getDocenten() {
        return docenten;
    }

    ;

    public void setKlas(Klas klas) {
        klassen.add(klas);
    }

    public ArrayList<Klas> getKlassen() {
        return klassen;
    }
}
