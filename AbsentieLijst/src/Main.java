import AbsentieLijst.*;
import AbsentieLijst.userInterfaceLaag.ToekomstigAfmeldenController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.time.LocalTime;
import static java.sql.Timestamp.valueOf;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;

public class
Main extends Application {

    public static void main(String[] args) throws Exception {
        Les lesOOP = new Les("OOP", "TCIF-V1OOP-19", "PL101-3.311", null, null);
        Les lesOOAD = new Les("OOAD", "TCIF-V1OOAD1-19", "HL15-2.031", null, null);
        Les lesGP = new Les("GP", "TCIF-V1GP-19", "HL15-4.031", null, null);
        Les lesFEP = new Les("FEP", "TCIF-V1FEP1-19", "HL15-2.031", null, null);
        Les lesBEP = new Les("BEP", "TCIF-V1BEP1-19", "HL15-2.031", null, null);
        Les lesIPASS = new Les("IPASS", "TCIF-V1IPASS-19", "HL15-2.031", null, null);

        ArrayList<Les> LP_V1SDC = new ArrayList<>();
        LP_V1SDC.add(lesOOP);
        LP_V1SDC.add(lesOOAD);
        LP_V1SDC.add(lesGP);
        ArrayList<Les> LP_V1SDD = new ArrayList<>();
        LP_V1SDD.add(lesFEP);
        LP_V1SDD.add(lesBEP);
        LP_V1SDD.add(lesIPASS);

        School HU = new School("HU");

        Docent Jos = new Docent("jvreenen", "Jos van Reenen", 1, "1234");
        Docent Martijn = new Docent("mjansen", "Martijn Jansen", 2, "1234");
        Docent Andre = new Docent("adonk", "André Donk", 3, "1234");
        Docent Peter = new Docent("pvrooijen", "Peter van Rooijen",4, "1234");

        HU.setDocenten(Jos);
        HU.setDocenten(Martijn);
        HU.setDocenten(Andre);
        HU.setDocenten(Peter);

        Student Nick = new Student("Nick", 1, "123456", false);
        Student Daniel = new Student("Daniel", 2, "abcdef", false);
        Student Gabriel = new Student("Gabriel", 3, "123456", false);
        Student Twan = new Student("Twan", 4, "123456", false);
        Student Gert = new Student("Gert", 1, "123456", false);
        Student Boris = new Student("Boris", 2, "abcdef", false);
        Student Hendrik = new Student("Hendrik", 3, "123456", false);
        Student Berend = new Student("", 4, "", false);

        Klas V1A = new Klas(24, "V1A");
        Klas V1B = new Klas(25, "V1B");
        Klas V1C = new Klas(30, "V1C");
        Klas V1D = new Klas(23, "V1D");
        Klas V1E = new Klas(22, "V1E");

        HU.setKlas(V1A);
        HU.setKlas(V1B);
        HU.setKlas(V1C);
        HU.setKlas(V1D);
        HU.setKlas(V1E);

        V1A.setStudenten(Gert);
        V1B.setStudenten(Boris);
        V1B.setStudenten(Hendrik);
        V1C.setStudenten(Berend);
        V1E.setStudenten(Nick);
        V1E.setStudenten(Daniel);
        V1E.setStudenten(Gabriel);
        V1E.setStudenten(Twan);

        V1A.setLessen(lesOOP);
        V1A.setLessen(lesOOAD);
        V1A.setLessen(lesGP);
        V1E.setLessen(lesOOP);
        V1E.setLessen(lesOOAD);
        V1E.setLessen(lesGP);

        for (Klas klas : HU.getKlassen()) {
            if (klas.getKlasCode().equals("V1E")) {
                lesOOP.setBegintijd(Time.valueOf("08:30:00"));
                lesOOP.setEindtijd(Time.valueOf("11:30:00"));
                lesOOAD.setBegintijd(Time.valueOf("08:30:00"));
                lesOOAD.setEindtijd(Time.valueOf("11:30:00"));
                lesGP.setBegintijd(Time.valueOf("13:00:00"));
                lesGP.setEindtijd(Time.valueOf("14:30:00"));
                lesOOP.setDatum(LocalDate.of(2020, 4, 6));
                lesOOAD.setDatum(LocalDate.of(2020, 4, 8));
                lesGP.setDatum(LocalDate.of(2020, 4, 10));
            }
        }
        for (Klas klas : HU.getKlassen()) {
            if (klas.getKlasCode().equals("V1A")) {
                lesOOP.setBegintijd(Time.valueOf("12:30:00"));
                lesOOP.setEindtijd(Time.valueOf("15:30:00"));
                lesOOAD.setBegintijd(Time.valueOf("12:30:00"));
                lesOOAD.setEindtijd(Time.valueOf("15:30:00"));
                lesGP.setBegintijd(Time.valueOf("11:30:00"));
                lesGP.setEindtijd(Time.valueOf("13:00:00"));
                lesOOP.setDatum(LocalDate.of(2020, 4, 6));
                lesOOAD.setDatum(LocalDate.of(2020, 4, 8));
                lesGP.setDatum(LocalDate.of(2020, 4, 10));
            }
        }



        Afspraak af = new Afspraak("sssss", Time.valueOf("10:10:22"), Time.valueOf("10:55:22"), LocalDate.of(2020, 4, 10));
        ToekomstigAfmeldenController.afGmeld.add(af);

        Daniel.voegAfspraakToe("Dit is de afspraak van daniel 1", Time.valueOf("12:00:00"), Time.valueOf("13:30:00"), LocalDate.of(2020, 4, 10));
        Daniel.voegAfspraakToe("Dit is de afspraak van daniel 2", Time.valueOf("12:00:00"), Time.valueOf("13:00:00"), LocalDate.of(2020, 4, 11));
        Daniel.voegAfspraakToe("Dit is de afspraak van daniel 3", Time.valueOf("08:30:00"), Time.valueOf("12:00:00"), LocalDate.of(2020, 4, 12));
        Daniel.voegAfspraakToe("Dit is de afspraak van daniel 4", Time.valueOf("08:30:00"), Time.valueOf("09:00:00"), LocalDate.of(2020, 4, 13));

        Twan.voegAfspraakToe("Dit is de afspraak van twan 1", Time.valueOf("12:00:00"), Time.valueOf("13:00:00"), LocalDate.of(2020, 4, 10));
        Twan.voegAfspraakToe("Dit is de afspraak van twan 2", Time.valueOf("11:00:00"), Time.valueOf("12:00:00"), LocalDate.of(2020, 4, 11));
        Twan.voegAfspraakToe("Dit is de afspraak van twan 3", Time.valueOf("10:00:00"), Time.valueOf("11:30:00"), LocalDate.of(2020, 4, 12));
        Twan.voegAfspraakToe("Dit is de afspraak van twan 4", Time.valueOf("09:00:00"), Time.valueOf("10:00:00"), LocalDate.of(2020, 4, 13));

        Nick.voegAfspraakToe("Dit is de afspraak van Nick 1", Time.valueOf("13:00:00"), Time.valueOf("14:00:00"), LocalDate.of(2020, 4, 10));
        Nick.voegAfspraakToe("Dit is de afspraak van Nick 2", Time.valueOf("14:00:00"), Time.valueOf("15:00:00"), LocalDate.of(2020, 4, 11));
        Nick.voegAfspraakToe("Dit is de afspraak van Nick 3", Time.valueOf("15:00:00"), Time.valueOf("16:00:00"), LocalDate.of(2020, 4, 12));
        Nick.voegAfspraakToe("Dit is de afspraak van Nick 4", Time.valueOf("16:00:00"), Time.valueOf("17:00:00"), LocalDate.of(2020, 4, 13));
        School.setSchool(HU);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/AbsentieLijst/userInterfaceLaag/InlogScreen.fxml"));
        primaryStage.setTitle("Inlogscherm");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("AbsentieLijst/Footage/calendar.png"));
        primaryStage.show();

    }

}