package AbsentieLijst.userInterfaceLaag;


import AbsentieLijst.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;


public class AfspraakToevoegenController {
    public DatePicker DataPickerDatum;
    public TextField TxtFieldOmschrijving;
    public TextField TxtFieldBeginTijd;
    public TextField TxtFieldEindeTijd;
    public Button ButtonOpslaan;
    public Button ButtonAnnuleren;
    public Label label;
    School HU = School.getSchool();

    public void opslaan(ActionEvent actionEvent) throws Exception {
        try {
            String omschrijving = TxtFieldOmschrijving.getText();
            String begin = TxtFieldBeginTijd.getText();
            String eind = TxtFieldEindeTijd.getText();
            LocalDate datum = DataPickerDatum.getValue();
            for (Klas klas : HU.getKlassen()) {
                for (Student student : klas.getStudenten()) {
                    if (student.getisIngelogd()) {
                        if (omschrijving != null && begin != null && eind != null && datum != null) {
                            student.voegAfspraakToe(omschrijving, Time.valueOf(begin + ":00"), Time.valueOf(eind + ":00"), datum);
                            Button source = (Button) actionEvent.getSource();
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("DagInzienStudent.fxml"));
                            Parent root = loader.load();
                            Scene dagIS = new Scene(root);
                            Stage dagIST = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
                            dagIST.setTitle("Dag inzien");
                            dagIST.setScene(dagIS);
                            dagIST.centerOnScreen();
                            dagIST.setResizable(false);
                            dagIST.getIcons().add(new Image("AbsentieLijst/Footage/calendar.png"));
                            dagIST.show();
                        } else
                            label.setText("Je moet alle velden invulen");
                    }
                }
            }
        } catch (Exception e) {
            label.setText("Voer een geldig tijd in.");
        }
    }


    public void annuleren(ActionEvent actionEvent) throws IOException {
        Button source = (Button) actionEvent.getSource();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DagInzienStudent.fxml"));
        Parent root = loader.load();

        Scene dagIS = new Scene(root);
        Stage dagIST = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        dagIST.setTitle("Dag inzien");
        dagIST.setScene(dagIS);
        dagIST.centerOnScreen();
        dagIST.setResizable(false);
        dagIST.getIcons().add(new Image("AbsentieLijst/Footage/calendar.png"));
        dagIST.show();
    }


}