package AbsentieLijst.userInterfaceLaag;

import AbsentieLijst.Klas;
import AbsentieLijst.School;
import AbsentieLijst.Student;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ZiekMeldenController {


    @FXML
    void initialize() {
    }

    School HU = School.getSchool();
    @FXML
    private Button ziekMeldenAnnuleren;
    @FXML
    private Button ziekMeldenBevestigen;
    @FXML
    Label Ziek = new Label();

    public void ziekAnnuleren() {
        Stage stage = (Stage) ziekMeldenAnnuleren.getScene().getWindow();
        stage.close();
    }

    public void ziekBevestigen() {
        Ziek.setText("Je bent ziek");

        System.out.println(Ziek);

        for (Klas klas : HU.getKlassen()) { //Vind ingelogde leerling
            for (Student student : klas.getStudenten()) {
                if (student.getisIngelogd()) {
                    student.getStudentZiek();
                    student.setStudentZiek(true);
                    Stage stage = (Stage) ziekMeldenAnnuleren.getScene().getWindow();
                    stage.close();
                }
            }
        }
    }
}