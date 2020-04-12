package AbsentieLijst.userInterfaceLaag;

import AbsentieLijst.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class ToekomstigAfmeldenController {
    public Button ButtonOpslaan;
    public Button ButtonAnnuleren;
    public DatePicker DatePickerDate;
    public ComboBox ComboBoxReden;
    public static ArrayList<Afspraak> afGmeld = new ArrayList<>();
    public Button overzicht;
    public Label label;
    public ComboBox tijd;

    School HU = School.getSchool();
    ObservableList<String> options =
            FXCollections.observableArrayList(
                    "Bruiloft",
                    "Tandarts afspraak",
                    "Begravenis", "Wegens corona.", "Overig"
            );


    public void initialize() {
        ComboBoxReden.setItems(options);

    }


    public void ActionOpslaan(ActionEvent actionEvent) {
        if (DatePickerDate.getValue() != null && ComboBoxReden != null) {
            LocalDate datum = DatePickerDate.getValue();
            Object time = tijd.getValue();
            try {
                for (Klas klas : HU.getKlassen()) {
                    for (Student student : klas.getStudenten()) {
                        if (student.getisIngelogd()) {
                            HashMap<String, Les> alleLessen = klas.getLessen();
                            for (String lesNaam : alleLessen.keySet()) {
                                if (alleLessen.get(lesNaam).getDatum().equals(datum)) {
                                    alleLessen.get(lesNaam).setAbsent(student, " met reden:"+" "+(String) ComboBoxReden.getValue()); //afgemeld_lijst in de les
                                    student.setAfgemeld("Vooraf afgemeld: " + alleLessen.get(lesNaam).getDatum() + " " + alleLessen.get(lesNaam).getLescode() + " met als reden: " + ComboBoxReden.getValue()); //afgemeld overzicht student.
                                    Button source = (Button) actionEvent.getSource();
                                    Stage stage = (Stage) source.getScene().getWindow();
                                    stage.close();
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                label.setText("ddddd");
            }
        } else label.setText("Je moet Datum en reden kiezen");
    }

    public void ActionAnnuleren(ActionEvent actionEvent) {
        Button source = (Button) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void DatapickerOnAction(ActionEvent actionEvent) {
        ObservableList<String> lessen = FXCollections.observableArrayList();
        for (Klas klas : HU.getKlassen()) {
            for (Student student : klas.getStudenten()) {
                if (student.getisIngelogd()) {
                    ArrayList<String> les = new ArrayList<>();

                    HashMap<String, Les> alleLessen = klas.getLessen();
                    for (Les lesNaam : alleLessen.values())
                        if (lesNaam.getDatum().equals(DatePickerDate.getValue())) {
//                            for (String les1 : alleLessen.keySet()) {
                            les.add(lesNaam.getNaam());
                            lessen.addAll(les);
                            tijd.setItems(lessen);
                        }

                }
            }
        }
    }
}
