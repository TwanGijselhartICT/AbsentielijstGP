package AbsentieLijst.userInterfaceLaag;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.ResourceBundle;

import AbsentieLijst.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.swing.*;

public class DagInzienDocentController {
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
    public  ListView activiteitBlock;
    @FXML
    private LocalDate cellData;

    private static String lesData;

    public DagInzienDocentController ( ) throws Exception {
    }



    @FXML
    void initialize ( ) {
        handleButtonAction();
        handleStart();
    }

    School HU = School.getSchool();


    public LocalDate getDate ( ) {
        return dp.getValue();
    }

    @FXML
    public void handleStart ( ) {
        cellData = HoofdmenuDocentController.getCellData();
        DatePicker d = dp;
        d.setValue(cellData);
        LocalDate i = cellData;
        if (i == null) {
            i = LocalDate.now();
        }
        date.setText("Datum :" + i); // get the selected date
        ArrayList<String> tijden = new ArrayList<>();
        ArrayList<String> omschrijvingen = new ArrayList<>();

        for (Docent docent : HU.getDocenten()) {
            if (docent.getIsIngelogd()) {
                ArrayList<Afspraak> afspraken = docent.getAfspraken();
                for (Afspraak afspraak : afspraken) {
                    if (afspraak.getDatum().isEqual(i)) {
                        String textAfspraak = afspraak.toString();
                        String[] gesplit = textAfspraak.split(",");
                        tijden.add(gesplit[1] + " - " + gesplit[2]);
                        omschrijvingen.add(gesplit[0]);
                    }
                }
                for (Klas klas : HU.getKlassen()) {
                    HashMap<String, Les> alleLessen = klas.getLessen();
                    for (String lesNaam : alleLessen.keySet()) {
                        if (( "lesOOP" + i + klas.getKlasCode() ).equals(lesNaam)) {
                            Les les = alleLessen.get(lesNaam);
                            String textLes = les.toString();
                            String[] gesplit = textLes.split(",");
                            tijden.add(gesplit[3] + " - " + gesplit[4]);
                            omschrijvingen.add(gesplit[0] + "       " + gesplit[2] + "        " + klas.getKlasCode());
                        }
                        if (( "lesOOAD" + i + klas.getKlasCode() ).equals(lesNaam)) {
                            Les les = alleLessen.get(lesNaam);
                            String textLes = les.toString();
                            String[] gesplit = textLes.split(",");
                            tijden.add(gesplit[3] + " - " + gesplit[4]);
                            omschrijvingen.add(gesplit[0] + "       " + gesplit[2] + "        " + klas.getKlasCode());
                        }
                        if (( "lesGP" + i + klas.getKlasCode() ).equals(lesNaam)) {
                            Les les = alleLessen.get(lesNaam);
                            String textLes = les.toString();
                            String[] gesplit = textLes.split(",");
                            tijden.add(gesplit[3] + " - " + gesplit[4]);
                            omschrijvingen.add(gesplit[0] + "       " + gesplit[2] + "        " + klas.getKlasCode());
                        }
                    }
                }
            }
            ArrayList<Label> labelsLijst = new ArrayList<>();
            for (String tijd : tijden) {
                Label label = new Label(tijd);
                label.setFont(new Font("Calibri", 12));
                label.setPadding(new Insets(10, 10, 10, 10));
                labelsLijst.add(label);
            }
            Collections.reverse(labelsLijst);
            timeBlock.setItems(FXCollections.observableArrayList(labelsLijst));

            ArrayList<String> labelsLijst2 = new ArrayList<>();
            for (Klas klas : HU.getKlassen()) {
                HashMap<String, Les> alleLessen = klas.getLessen();
                for (String lesNaam : alleLessen.keySet()) {
                    if (alleLessen.get(lesNaam).getDatum().equals(dp.getValue())) {
                        JLabel label = new JLabel(lesNaam);
                        label.setText(alleLessen.get(lesNaam).getNaam() + "     " + klas.getKlasCode() + "     " + alleLessen.get(lesNaam).getLocatie()); // LETOP! moeten exact 5 spaties zijn.
                        labelsLijst2.add(label.getText());
                    }
                }
                Collections.reverse(labelsLijst2);
                activiteitBlock.setItems(FXCollections.observableArrayList(labelsLijst2));
            }

        }
    }

    @FXML
    public void handleButtonAction ( ) {
        HoofdmenuDocentController data = new HoofdmenuDocentController();
        LocalDate cellData = data.getCellData();
        DatePicker d = dp;
        d.setValue(cellData);
        LocalDate i = cellData;
        date.setText("Datum: " + i);

        LocalDate finalCellData = cellData;
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle (ActionEvent e) {
                LocalDate i = finalCellData; // get the date picker value
                date.setText("Datum :" + i); // get the selected date
                ArrayList<String> tijden = new ArrayList<>();
                ArrayList<String> omschrijvingen = new ArrayList<>();

                for (Docent docent : HU.getDocenten()) {
                    ArrayList<Afspraak> afspraken = docent.getAfspraken();
                    for (Afspraak afspraak : afspraken) {
                        if (afspraak.getDatum().isEqual(i)) {
                            String textAfspraak = afspraak.toString();
                            String[] gesplit = textAfspraak.split(",");
                            tijden.add(gesplit[1]);
                            omschrijvingen.add(gesplit[0]);
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

                ArrayList<String> labels2 = new ArrayList<>(); // Dit zet het activiteiten block
                for (Klas klas : HU.getKlassen()) {
                    HashMap<String, Les> alleLessen = klas.getLessen();
                    for (String lesNaam : alleLessen.keySet()) {
                        JLabel label = new JLabel(lesNaam);
                        label.setText(lesNaam + klas.getKlasCode() + alleLessen.get(lesNaam).getLocatie());
                        labels2.add(label.getText());
                    }
                }
                Collections.reverse(labels);
                activiteitBlock.setItems(FXCollections.observableArrayList(labels2));
            }
        };
        d.setOnAction(event);
    }

    public void Terug ( ) {
        Stage stage = (Stage) buttonTerug.getScene().getWindow();
        stage.close();
    }

    public void MouseClickEvent (MouseEvent mouseEvent) throws Exception {
        String leslabel = (String) activiteitBlock.getSelectionModel().getSelectedItem();
        this.lesData = leslabel;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("PresentieVerwerken.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Presentie Verwerken");
        stage.setScene(new Scene(root));
        stage.show();


    }

    public static String getLesData (){
        return lesData;
    }

}

