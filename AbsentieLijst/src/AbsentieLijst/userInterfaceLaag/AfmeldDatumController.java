package AbsentieLijst.userInterfaceLaag;

import AbsentieLijst.Afspraak;
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

public class AfmeldDatumController {
    public ListView<Label> Afgemeld;
    public Button close;
    public ArrayList<Afspraak> afspraken = ToekomstigAfmeldenController.afGmeld;


    public void initialize() {
        ArrayList<Label> labels = new ArrayList<>();

        for (Afspraak n : afspraken) {
            Label label = new Label(n.toString());
            label.setFont(new Font("Calibri", 18));
            label.setPadding(new Insets(10, 10, 10, 10));
            labels.add(label);
        }
        Collections.reverse(labels);
        Afgemeld.setItems(FXCollections.observableArrayList(labels));
    }

    public void terug(ActionEvent actionEvent) {
        Button source = (Button) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
