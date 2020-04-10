//import java.time.LocalDate;
//public class Gebruiker {
//    private String naam;
//    private String inlogNaam;
//    private LocalDate geboorteDatum;
//    private String woonplaats;
//
//    public Gebruiker(String nm, String in, LocalDate ge, String wo) {
//        nm = naam;
//        in = inlogNaam;
//        ge = geboorteDatum;
//        wo = woonplaats;
//    }
//
//
//    public void setNaam(String naam) { this.naam = naam; }
//
//    public void setInlogNaam(String inlogNaam) { this.inlogNaam = inlogNaam; }
//
//    public void setGeboorteDatum(LocalDate geboorteDatum) { this.geboorteDatum = geboorteDatum; }
//
//    public void setWoonplaats(String woonplaats) { this.woonplaats = woonplaats; }
//
//    public String getNaam() { return naam; }
//
//    public String getInlogNaam() { return inlogNaam; }
//
//    public LocalDate getGeboorteDatum() { return geboorteDatum; }
//
//    public String getWoonplaats() { return woonplaats; }
//
//    @Override
//    public String toString() {
//        return "Gebruiker{" +
//                "naam='" + naam + '\'' +
//                ", inlogNaam='" + inlogNaam + '\'' +
//                ", geboorteDatum=" + geboorteDatum +
//                ", woonplaats='" + woonplaats + '\'' +
//                '}';
//    }
//}
//
//class Docent extends Gebruiker {
//    private Boolean docentenBevoegdheid;
//    private int docentCode;
//    private String docentNaam;
//
//
//    public Docent(Boolean dB, int dC, String dN){
//        dB = docentenBevoegdheid;
//        dC = docentCode;
//        dN = docentNaam;
//    }
//
//    public void setDocentenBevoegdheid(Boolean docentenBevoegdheid) {
//        this.docentenBevoegdheid = docentenBevoegdheid;
//    }
//
//    public void setDocentCode(int docentCode) {
//        this.docentCode = docentCode;
//    }
//
//    public void setDocentNaam(String docentNaam) {
//        this.docentNaam = docentNaam;
//    }
//
//    public Boolean getDocentenBevoegdheid() {
//        return docentenBevoegdheid;
//    }
//
//    public int getDocentCode() {
//        return docentCode;
//    }
//
//    public String getDocentNaam() {
//        return docentNaam;
//    }
//
//    @Override
//    public String toString() {
//        return "Docent{" +
//                "docentenBevoegdheid=" + docentenBevoegdheid +
//                ", docentCode=" + docentCode +
//                ", docentNaam='" + docentNaam + '\'' +
//                '}';
//    }
//}
//
//}
//
//class Student extends Gebruiker {
//    private String studentNaam;
//    private int studentNummer;
//
//    public Student(String sNa, int sNu){
//        sNa = studentNaam;
//        sNu = studentNummer;
//
//    }
//
//    public void setStudentNaam(String studentNaam) { this.studentNaam = studentNaam; }
//
//    public void setStudentNummer(int studentNummer) { this.studentNummer = studentNummer; }
//
//    public String getStudentNaam() { return studentNaam; }
//
//    public int getStudentNummer() { return studentNummer; }
//
//    @Override
//    public String toString() {
//        return "Student{" +
//                "studentNaam='" + studentNaam + '\'' +
//                ", studentNummer=" + studentNummer +
//                '}';
//    }
//}
//}