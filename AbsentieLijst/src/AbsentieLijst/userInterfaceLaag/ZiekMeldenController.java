package AbsentieLijst.userInterfaceLaag;
import AbsentieLijst.Klas;
import AbsentieLijst.School;
import AbsentieLijst.Student;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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

    public void ziekAnnuleren() {
        Stage stage = (Stage) ziekMeldenAnnuleren.getScene().getWindow();
        stage.close();
    }

    public void ziekBevestigen() {
        for (Klas klas : HU.getKlassen()) { //Vind ingelogde leerling
            //System.out.println(klas);
            for (Student student : klas.getStudenten()) {
                //System.out.println(student);
                if (student.getisIngelogd()) {
                    System.out.println(student);
                    student.getStudentZiek();
                    System.out.println(student.getStudentZiek());
                    student.setStudentZiek(true);
                    System.out.println(student.getStudentZiek());
                    Stage stage = (Stage) ziekMeldenAnnuleren.getScene().getWindow();
                    stage.close();
                }
            }
        }
    }
}