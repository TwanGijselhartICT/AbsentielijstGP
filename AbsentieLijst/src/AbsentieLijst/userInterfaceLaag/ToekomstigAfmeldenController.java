package AbsentieLijst.userInterfaceLaag;

import AbsentieLijst.Afspraak;
import AbsentieLijst.Klas;
import AbsentieLijst.School;
import AbsentieLijst.Student;
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

public class ToekomstigAfmeldenController {
    public Button ButtonOpslaan;
    public Button ButtonAnnuleren;
    public DatePicker DatePickerDate;
    public ComboBox ComboBoxReden;
    public static ArrayList<Afspraak> afGmeld = new ArrayList<>();
    public ArrayList<Afspraak> sAfspraken = new ArrayList<>();
    public Button overzicht;
    public Label label;
    public ComboBox tijd;

    School HU = School.getSchool();
    ObservableList<String> options =
            FXCollections.observableArrayList(
                    "Ziek Melden",
                    "Tandarts afspraak",
                    " Overige"
            );


    public void initialize() {
        ComboBoxReden.setItems(options);
        ObservableList<Time> option =
                FXCollections.observableArrayList();
        for (Klas klas : HU.getKlassen()) {
            for (Student student : klas.getStudenten()) {
                if (student.getisIngelogd()) {
                    sAfspraken = student.afspraken;
                }
                for (Afspraak a : sAfspraken) {
                    option.add(a.getBeginTijd());
                }
                tijd.setItems(option);
            }
        }
    }


    public void ActionOpslaan(ActionEvent actionEvent) {
        if (DatePickerDate.getValue() != null && ComboBoxReden != null) {
            LocalDate datum = DatePickerDate.getValue();
            Object time = tijd.getValue();
            try {
                for (Afspraak afs : sAfspraken) {
                    if (afs.getDatum().equals(datum) && afs.getBeginTijd().equals(time)) {
                        afGmeld.add(afs);
                        sAfspraken.remove(afs);
                        Button source = (Button) actionEvent.getSource();
                        Stage stage = (Stage) source.getScene().getWindow();
                        stage.close();
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

    public void overzicht(ActionEvent actionEvent) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfmeldDatum.fxml"));
        Parent root = loader.load();
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));

        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.setTitle("Afgemelde afspraken");
        newStage.getIcons().add(new Image("AbsentieLijst/Footage/calendar.png"));
        newStage.showAndWait();
        newStage.setResizable(false);

        initialize();
    }
}
