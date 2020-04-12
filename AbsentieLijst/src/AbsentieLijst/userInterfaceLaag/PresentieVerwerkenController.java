package AbsentieLijst.userInterfaceLaag;

import AbsentieLijst.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
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

    ArrayList<String> absenten = new ArrayList<>();
    ArrayList<Student> presenten = new ArrayList<>();
    ArrayList<Student> Absenten = new ArrayList<>();
    School HU = School.getSchool();


    public void initialize ( ) throws Exception {
//        ObservableList<String> option1 = FXCollections.observableArrayList();
        ObservableList<String> option = FXCollections.observableArrayList();
        for (Klas klas : HU.getKlassen()) {
            System.out.println(klas.getKlasCode());
            option.add(klas.getKlasCode());
        }
        ComboboxKlassen.setItems(option);
        DagInzienDocentController data = new DagInzienDocentController();
        String leslabel = data.getLesData();
        String[] les = leslabel.split("     ");
        String vak = les[0];
        String klas = les[1];
        System.out.println("Hier print ik vak " + vak);
        System.out.println("Hier print ik vak " + klas);
        for (Klas klas1 : HU.getKlassen()) {
            if (klas1.getKlasCode().equals(klas)) {
                HashMap<String, Les> alleLessen = klas1.getLessen();
                for (String lesNaam : alleLessen.keySet()) {
                    if (lesNaam.equals("les" + vak + HoofdmenuDocentController.getCellData() + klas)) {
                        presenten = alleLessen.get(lesNaam).getPresent();
                        System.out.println(presenten);
//                        if(student.getZiek){
//                                Les.setAbsent(student, ziek);
                        absenten = alleLessen.get(lesNaam).getAbsent();
                        System.out.println(absenten);
                        // if (alleLessen.get(lesNaam).getNaam().equals(vak) + alleLessen.get(lesNaam).getDatum().equals(HoofdmenuDocentController.getCellData())

                    }
                }


//                    }
//        for (Klas klas : HU.getKlassen()) {
//            for (Student student : klas.getStudenten()) {
//                if (student.getisIngelogd()) {
//                    HashMap<String, Les> alleLessen = klas.getLessen();
//                    for (String lesNaam : alleLessen.keySet()) {
//                        if (alleLessen.get(lesNaam).getDatum().equals(HoofdmenuDocentController.getCellData()) && klas.getKlasCode().equals(ComboboxKlassen.getValue())){
//                            option1.add(alleLessen.get(lesNaam).getNaam());
//                            ComboboxAfspraak.setItems(option1);
//                        };
//
//                    }
//                }
//            }
            }
        }
    }


    public void aanroepOpslaan (ActionEvent actionEvent) {
        Button source = (Button) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void AanroepCancel (ActionEvent actionEvent) {
        Button source = (Button) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void klas (ActionEvent actionEvent) {
        ObservableList<String> option1 = FXCollections.observableArrayList();

        for (Klas klas : HU.getKlassen()) {
            HashMap<String, Les> alleLessen = klas.getLessen();
            for (String lesNaam : alleLessen.keySet()) {
                if (alleLessen.get(lesNaam).getDatum().equals(HoofdmenuDocentController.getCellData()) && klas.getKlasCode().equals(ComboboxKlassen.getValue())) {
                    option1.add(alleLessen.get(lesNaam).getNaam());
                    ComboboxAfspraak.setItems(option1);
                }
                ;

            }
        }

        ArrayList<Student> studenten = new ArrayList<>();
        for (Klas k : HU.klassen) {
            if (k.getKlasCode().equals(ComboboxKlassen.getValue())) {

                System.out.print(ComboboxKlassen.getValue());
                for (Student s : k.getStudenten()) {
                    studenten.add(s);

                    ArrayList<String> labels = new ArrayList<>();
                    for (Student n : studenten) {
                        JLabel label = new JLabel(n.getStudentNaam());
                        label.setText(n.getStudentNaam());
                        labels.add(label.getText());
                    }
                    System.out.println(labels);
                    Collections.reverse(labels);
                    LeerlingenBlock.setItems(FXCollections.observableArrayList(labels));
                    ArrayList<Label> labels1 = new ArrayList<>();
                }
                for (Student a : studenten) {
                    presenten.add(a);
                    System.out.println(a);
                }
            }
        }

//        for (Klas k : HU.klassen) {
//            if (k.getKlasCode().equals(ComboboxKlassen.getValue())) {
//                System.out.print(ComboboxKlassen.getValue());
////                for (Student s : k.getStudenten()) {
////                    System.out.print(s.getStudentNaam());
//
//                    ArrayList<Label> labels1 = new ArrayList<>();
//                    for (Object n : absenten) {
//                        Label label1 = new Label(n.toString());
//                        label1.setFont(new Font("Calibri", 18));
//                        label1.setPadding(new Insets(10, 10, 10, 10));
//                        labels1.add(label1);
//                    }
//                    Collections.reverse(labels1);
//                    AbsentBlock.setItems(FXCollections.observableArrayList(labels1));
//
//                    ArrayList<Label> labels2 = new ArrayList<>();
//                    ArrayList<CheckBox> checkBoxes = new ArrayList<>();
//                    ;

    }


    @FXML
    public void handleMouseClickLeerling (MouseEvent arg0) {
        String absent = (String) LeerlingenBlock.getSelectionModel().getSelectedItem();
        if (!absenten.contains(absent))
            absenten.add(absent);
        System.out.println(absenten);

        ArrayList<Label> labels1 = new ArrayList<>();
        for (String absent1 : absenten){
            Label label1 = new Label(absent1);
            label1.setFont(new Font("Calibri", 18));
            label1.setPadding(new Insets(10, 10, 10, 10));
            labels1.add(label1);
        }

        Collections.reverse(labels1);
        AbsentBlock.setItems(FXCollections.observableArrayList(labels1));

        ArrayList<Label> labels2 = new ArrayList<>();
        ArrayList<CheckBox> checkBoxes = new ArrayList<>();
    }

    public void handleMouseClickAbsent (MouseEvent arg0) {
        String absent = (String) LeerlingenBlock.getSelectionModel().getSelectedItem();
        absenten.remove(absent);
        System.out.println(absenten);

        ArrayList<Label> labels1 = new ArrayList<>();
        for (Object n : absenten) {
            Label label1 = new Label(n.toString());
            label1.setFont(new Font("Calibri", 18));
            label1.setPadding(new Insets(10, 10, 10, 10));
            labels1.add(label1);
        }

        Collections.reverse(labels1);
        AbsentBlock.setItems(FXCollections.observableArrayList(labels1));

        ArrayList<Label> labels2 = new ArrayList<>();
        ArrayList<CheckBox> checkBoxes = new ArrayList<>();
    }

    public void KiesLes (ActionEvent actionEvent) {
    }
}




