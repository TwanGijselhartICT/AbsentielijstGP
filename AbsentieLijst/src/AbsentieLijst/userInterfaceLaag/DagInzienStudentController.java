
package AbsentieLijst.userInterfaceLaag;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import AbsentieLijst.Afspraak;
import AbsentieLijst.Klas;
import AbsentieLijst.School;
import AbsentieLijst.Student;
import AbsentieLijst.Les;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class DagInzienStudentController {
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Button buttonTerug;
    @FXML
    private Label date;
    @FXML
    private DatePicker dp;
    @FXML
    private ListView timeBlock;
    @FXML
    private ListView activiteitBlock;
    @FXML
    private Button buttonAfspraakToevoegen;
    @FXML
    private LocalDate cellData;


    @FXML
    void initialize() {

        handleButtonAction();
        handleStart();
    }

    School HU = School.getSchool();



    @FXML
    public void handleStart() {

        cellData = HoofdmenuStudentController.getCellData();
//        System.out.println(cellData+ "celldata in DI");

        DatePicker d = dp;
//        d.setValue(cellData);
        LocalDate i = cellData;
        if (i == null) {
            i = LocalDate.now();
        }
//        System.out.println(i);

        date.setText("Datum :" + i); // get the selected date
        ArrayList<String> tijden = new ArrayList<>();
        ArrayList<String> omschrijvingen = new ArrayList<>();

        for (Klas klas : HU.getKlassen()) { //Vind ingelogde leerling
            //System.out.println(klas);
            for (Student student : klas.getStudenten()) {
                //System.out.println(student);
                if (student.getisIngelogd()) {
//                    System.out.println(student);
                    ArrayList<Afspraak> afspraken = student.getAfspraken();
                    for (Afspraak afspraak : afspraken) {
                        if (afspraak.getDatum().isEqual(i)) {
//                            System.out.println(afspraak.getDatum());
                            String textAfspraak = afspraak.toString();
                            String[] gesplit = textAfspraak.split(",");
                            tijden.add(gesplit[1] + " - " + gesplit[2]);
                            omschrijvingen.add(gesplit[0]);
//                            System.out.println(omschrijvingen);
                        }
                    }
                    ArrayList<Les> alleLessen = klas.getLessen();
//                    System.out.println(klas);
                    for (Les les : alleLessen) {
//                        System.out.println(les.getDatum());
                        if (les.getDatum().isEqual(i)) {
                            String textLes = les.toString();
                            String[] gesplit = textLes.split(",");
                            tijden.add(gesplit[3] + " - "+ gesplit[4]);
                            omschrijvingen.add(gesplit[0] + "       "+ gesplit[2] );
                        }
                    }
                }
            }
            ArrayList<Label> labels = new ArrayList<>();
            for (String tijd : tijden) {
                Label label = new Label(tijd);
                label.setFont(new Font("Calibri", 12));
                label.setPadding(new Insets(10, 10, 10, 10));
                labels.add(label);
            }
            Collections.reverse(labels);
            timeBlock.setItems(FXCollections.observableArrayList(labels));

            ArrayList<Label> labels2 = new ArrayList<>();
            for (String omschrijving : omschrijvingen) {
                Label label2 = new Label(omschrijving);
                label2.setFont(new Font("Calibri", 12));
                label2.setPadding(new Insets(10, 10, 10, 10));
                labels2.add(label2);
            }
            Collections.reverse(labels2);
            activiteitBlock.setItems(FXCollections.observableArrayList(labels2));
        }


    }
    @FXML
    public void handleButtonAction() {
//        // label to show the date
//        date.setText("no date selected");
//        LocalDate today = LocalDate.now();
//
//        date.setText(today.toString());
        HoofdmenuStudentController data = new HoofdmenuStudentController();
        LocalDate cellData = data.getCellData();
        DatePicker d = dp;
//        d.setValue(cellData);
        LocalDate i = cellData;
        date.setText("Datum: " + i);

        // action event
        LocalDate finalCellData = cellData;
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {

                LocalDate i = finalCellData; // get the date picker value
                date.setText("Datum :" + i); // get the selected date
                ArrayList<String> tijden = new ArrayList<>();
                ArrayList<String> omschrijvingen = new ArrayList<>();

                for (Klas klas : HU.getKlassen()) { //Vind ingelogde leerling
                    //System.out.println(klas);
                    for (Student student : klas.getStudenten()) {
                        //System.out.println(student);
                        if (student.getisIngelogd()) {
//                            System.out.println(student);
                            ArrayList<Afspraak> afspraken = student.getAfspraken();
                            for (Afspraak afspraak : afspraken) {
                                if (afspraak.getDatum().isEqual(i)) {
                                    String textAfspraak = afspraak.toString();
                                    String[] gesplit = textAfspraak.split(",");
                                    tijden.add(gesplit[1]);
                                    omschrijvingen.add(gesplit[0]);
//                                    System.out.println(omschrijvingen);
                                }
                            }
                        }
                    }
                }
                ArrayList<Label> labels = new ArrayList<>();
                for (String tijd : tijden) {
                    Label label = new Label(tijd);
                    label.setFont(new Font("Calibri", 12));
                    label.setPadding(new Insets(10, 10, 10, 10));
                    labels.add(label);
                }
                Collections.reverse(labels);
                timeBlock.setItems(FXCollections.observableArrayList(labels));

                ArrayList<Label> labels2 = new ArrayList<>();
                for (String omschrijving : omschrijvingen) {
                    Label label2 = new Label(omschrijving);
                    label2.setFont(new Font("Calibri", 12));
                    label2.setPadding(new Insets(10, 10, 10, 10));
                    labels2.add(label2);
                }
                Collections.reverse(labels2);
                activiteitBlock.setItems(FXCollections.observableArrayList(labels2));
            }
        };
//        d.setOnAction(event);
    }

    public void buttonAfspraakToevoegen(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfspraakToevoegen.fxml"));
        Parent root = loader.load();
        Scene dagIS = new Scene(root);
        Stage dagIST = (Stage) ((Node)event.getSource()).getScene().getWindow();
        dagIST.setTitle("Nieuwe afspraak");
        dagIST.setScene(dagIS);
        dagIST.centerOnScreen();
        dagIST.setResizable(false);
        dagIST.getIcons().add(new Image("AbsentieLijst/Footage/calendar.png"));
        dagIST.show();
    }

    public void Terug() {
        Stage stage = (Stage) buttonTerug.getScene().getWindow();
        stage.close();
    }
}

