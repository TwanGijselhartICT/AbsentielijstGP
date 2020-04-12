package AbsentieLijst.userInterfaceLaag;

import AbsentieLijst.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.*;

import javax.swing.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class PresentieVerwerkenController {

    public ComboBox<String> ComboboxKlassen;
    public ComboBox ComboboxAfspraak;
    public CheckBox chek;
    @FXML
    private Label PresentieLabeL;

    @FXML
    private ListView LeerlingenBlock;

    @FXML
    private ListView AbsentBlock;

    @FXML
    private Button Opslaan;

    @FXML
    private Button Cancel;
    private String lesCode;

    ArrayList<String> absenten = new ArrayList<>();
    ArrayList<Student> presenten = new ArrayList<>();
    School HU = School.getSchool();


    public void initialize() throws Exception {
        DagInzienDocentController data = new DagInzienDocentController();
        String leslabel = data.getLesData();
        String[] les = leslabel.split("     ");
        String vak = les[0];
        String klas = les[1];
        this.lesCode = "les" + vak + HoofdmenuDocentController.getCellData() + klas;
        for (Klas klas1 : HU.getKlassen()) {
            if (klas1.getKlasCode().equals(klas)) {
                HashMap<String, Les> alleLessen = klas1.getLessen();
                for (String lesNaam : alleLessen.keySet()) {
                    if (lesNaam.equals("les" + vak + HoofdmenuDocentController.getCellData() + klas)) {
                        presenten = alleLessen.get(lesNaam).getPresent();
                        absenten = alleLessen.get(lesNaam).getAbsent();
                        checkOpZieken();
                    }
                }
            }
        }
        maakLijstenEersteKeer();
    }

    public void checkOpZieken() {
        ArrayList<Student> ziekeStudenten = new ArrayList<>();
        for (Student student : presenten) {
            if (student.getStudentZiek()) {
                absenten.add(student.getStudentNaam() + " is ziek!"); //String toevoegen aan absentlijst
                ziekeStudenten.add(student);
            }
        }
        presenten.removeAll(ziekeStudenten);
    }

    public void maakLijstenEersteKeer() {
        ArrayList<String> labels1 = new ArrayList<>();
        for (String absentie : absenten) {
            JLabel label1 = new JLabel(absentie);
            label1.setText(absentie);
            labels1.add(label1.getText());
        }


        AbsentBlock.setItems(FXCollections.observableArrayList(labels1));

        AbsentBlock.getSelectionModel().clearSelection();
        LeerlingenBlock.getSelectionModel().clearSelection();
        // --------------------------------------------------------- ^Boven absentlijst onder presentlijst
        ArrayList<String> labels2 = new ArrayList<>();
        for (Student presentt : presenten) {
            JLabel label2 = new JLabel(presentt.getStudentNaam());
            label2.setText(presentt.getStudentNaam());
            labels2.add(label2.getText());
        }


        LeerlingenBlock.setItems(FXCollections.observableArrayList(labels2));

        AbsentBlock.getSelectionModel().clearSelection();
        LeerlingenBlock.getSelectionModel().clearSelection();
    }


    public void aanroepOpslaan(ActionEvent actionEvent) {
        for (Klas klas1 : HU.getKlassen())
            for (Student student : klas1.getStudenten()) {
                for (String studentString : absenten) {
                    String[] splitted = studentString.split(" ");
                    if (student.getStudentNaam().equals(studentString)) {
                        student.setAfgemeld(this.lesCode + " Afgemeld door docent.");
                    }
                    if (splitted.length > 2) {
                        if (student.getStudentNaam().equals(splitted[0]) && splitted[2].equals("ziek!")) {
                            student.setAfgemeld(this.lesCode + " Afgemeld ivm ziekmelding.");
                        }
                    }
                }
            }
                Button source = (Button) actionEvent.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                stage.close();
            }

        public void AanroepCancel (ActionEvent actionEvent){
            Button source = (Button) actionEvent.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        }


        @FXML
        public void handleMouseClickLeerling (MouseEvent arg0){
            String present = (String) LeerlingenBlock.getSelectionModel().getSelectedItem(); //String met studentnaam
            if (!(present == null)) {
                absenten.add(present); //String toevoegen aan absentlijst
                presenten.removeIf(student -> student.getStudentNaam().equals(present));

                ArrayList<String> labels1 = new ArrayList<>();
                for (String absentie : absenten) {
                    JLabel label1 = new JLabel(absentie);
                    label1.setText(absentie);
                    labels1.add(label1.getText());
                }

                ArrayList<String> labels2 = new ArrayList<>();
                for (Student presentt : presenten) {
                    JLabel label2 = new JLabel(presentt.getStudentNaam());
                    label2.setText(presentt.getStudentNaam());
                    labels2.add(label2.getText());
                }

                AbsentBlock.setItems(FXCollections.observableArrayList(labels1));
                LeerlingenBlock.setItems(FXCollections.observableArrayList(labels2));
                AbsentBlock.getSelectionModel().clearSelection();
                LeerlingenBlock.getSelectionModel().clearSelection();
            }
        }

        public void handleMouseClickAbsent (MouseEvent arg0){
            String absent = (String) AbsentBlock.getSelectionModel().getSelectedItem();
            if (!(absent == null)) {
                absenten.remove(absent);
                for (Klas klas1 : HU.getKlassen())
                    for (Student student : klas1.getStudenten()) {
                        if (student.getStudentNaam().equals(absent)) {
                            presenten.add(student);
                        }
                    }
            }

            ArrayList<String> labels1 = new ArrayList<>();
            for (String absentie : absenten) {
                JLabel label1 = new JLabel(absentie);
                label1.setText(absentie);
                labels1.add(label1.getText());
            }

            ArrayList<String> labels2 = new ArrayList<>();
            for (Student present : presenten) {
                JLabel label2 = new JLabel(present.getStudentNaam());
                label2.setText(present.getStudentNaam());
                labels2.add(label2.getText());
            }


            AbsentBlock.setItems(FXCollections.observableArrayList(labels1));
            LeerlingenBlock.setItems(FXCollections.observableArrayList(labels2));
            AbsentBlock.getSelectionModel().clearSelection();
            LeerlingenBlock.getSelectionModel().clearSelection();
        }

    }




