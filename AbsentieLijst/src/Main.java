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
import java.time.temporal.IsoFields;
import java.util.ArrayList;
import java.util.HashMap;

public class
Main extends Application {

    public static void main (String[] args) throws Exception {
//        Les lesFEP = new Les("FEP", "TCIF-V1FEP1-19", "HL15-2.031", null, null, null);
//        Les lesBEP = new Les("BEP", "TCIF-V1BEP1-19", "HL15-2.031", null, null, null);
//        Les lesIPASS = new Les("IPASS", "TCIF-V1IPASS-19", "HL15-2.031", null, null, null);
//
        School HU = new School("HU");

        Docent Jos = new Docent("jvreenen", "Jos van Reenen", 1, "1234");
        Docent Martijn = new Docent("mjansen", "Martijn Jansen", 2, "1234");
        Docent Andre = new Docent("adonk", "André Donk", 3, "1234");
        Docent Peter = new Docent("pvrooijen", "Peter van Rooijen", 4, "1234");
        Docent Leeg = new Docent("", "Peter van Rooijen", 4, "");

        HU.setDocenten(Jos);
        HU.setDocenten(Martijn);
        HU.setDocenten(Andre);
        HU.setDocenten(Peter);
        HU.setDocenten(Leeg);

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

        HashMap<String, Les> lessenV1A = new HashMap<String, Les>();
        HashMap<String, Les> lessenV1E = new HashMap<String, Les>();
        for (Klas klas : HU.getKlassen()) {
            for (LocalDate date = ( LocalDate.of(2020, 4, 6) ); date.isBefore(LocalDate.of(2020, 6, 6)); date = date.plusDays(7)){
                if (klas.getKlasCode().equals("V1A")){
                    String lesnaamOOP = "lesOOP" + date.plusDays(1) + "V1A";
                    String lesnaamOOAD = "lesOOAD" + date.plusDays(4) + "V1A";
                    String lesnaamGP = "lesGP" + date.plusDays(2) + "V1A";

                    lessenV1A.put(lesnaamOOP,new Les("OOP", "TCIF-V1OOP-19", "PL101-3.311", Time.valueOf("08:30:00"), Time.valueOf("11:30:00"), date.plusDays(1)));
                    lessenV1A.put(lesnaamOOAD,new Les("OOAD", "TCIF-V1OOAD1-19", "HL15-2.031", Time.valueOf("08:30:00"), Time.valueOf("11:30:00"), date.plusDays(4)));
                    lessenV1A.put(lesnaamGP,new Les("GP", "TCIF-V1GP-19", "HL15-4.031", Time.valueOf("13:00:00"), Time.valueOf("14:30:00"), date.plusDays(2)));
                }
                if (klas.getKlasCode().equals("V1E")) {
                    String lesnaamOOP = "lesOOP" + date + "V1E";
                    String lesnaamOOAD = "lesOOAD" + date.plusDays(2) + "V1E";
                    String lesnaamGP = "lesGP" + date.plusDays(1) + "V1E";

                    lessenV1E.put(lesnaamOOP,new Les("OOP", "TCIF-V1OOP-19", "PL101-3.311", Time.valueOf("08:30:00"), Time.valueOf("11:30:00"), date));
                    lessenV1E.put(lesnaamOOAD,new Les("OOAD", "TCIF-V1OOAD1-19", "HL15-2.031", Time.valueOf("08:30:00"), Time.valueOf("11:30:00"), date.plusDays(2)));
                    lessenV1E.put(lesnaamGP,new Les("GP", "TCIF-V1GP-19", "HL15-4.031", Time.valueOf("13:00:00"), Time.valueOf("14:30:00"), date.plusDays(1)));
                }
            }
        }

        V1E.setLessen(lessenV1E);
        for(Les les : V1E.getLessen().values()){
            les.setPresent(V1E);
        }

        V1A.setLessen(lessenV1A);
        for(Les les : V1A.getLessen().values()){
            les.setPresent(V1A);
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
            public void start( Stage primaryStage) throws Exception {
                Parent root = FXMLLoader.load(getClass().getResource("/AbsentieLijst/userInterfaceLaag/InlogScreen.fxml"));
                primaryStage.setTitle("Inlogscherm");
                primaryStage.setScene(new Scene(root));
                primaryStage.setResizable(false);
                primaryStage.getIcons().add(new Image("AbsentieLijst/Footage/calendar.png"));
                primaryStage.show();

            }

        }