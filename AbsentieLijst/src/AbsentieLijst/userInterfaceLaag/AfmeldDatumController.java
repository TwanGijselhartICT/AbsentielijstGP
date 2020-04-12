package AbsentieLijst.userInterfaceLaag;

import AbsentieLijst.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class AfmeldDatumController {
    public ListView<Label> Afgemeld;
    public Button close;
    public ArrayList<String> afspraken = new ArrayList<>();
    School HU = School.getSchool();

    public void initialize() {
        ArrayList<Label> labels = new ArrayList<>();
        for (Klas klas : HU.getKlassen()) {
            for (Student student : klas.getStudenten()) {
                if (student.getisIngelogd()) {
                    for (String a : student.afgemeld) {
                        afspraken.add(a);
                    }

                    for (String afspraak : afspraken) {
                        Label label = new Label(afspraak);
                        label.setFont(new Font("Calibri", 18));
                        label.setPadding(new Insets(10, 10, 10, 10));
                        labels.add(label);
                    }
                    Collections.reverse(labels);
                    Afgemeld.setItems(FXCollections.observableArrayList(labels));
                }
            }
        }
    }

    public void terug(ActionEvent actionEvent) {
        Button source = (Button) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
