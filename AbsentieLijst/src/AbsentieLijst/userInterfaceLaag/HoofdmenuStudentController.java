package AbsentieLijst.userInterfaceLaag;

import AbsentieLijst.Klas;
import AbsentieLijst.School;
import AbsentieLijst.Student;
import com.sun.javafx.scene.control.DatePickerContent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.skin.DatePickerSkin;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HoofdmenuStudentController {
    @FXML
    public AnchorPane agendaPane;
    private ResourceBundle resources;
    private URL location;
    private Button aanroepZiekMelden;
    private Button aanroepAbsentiePlannen;
    private static LocalDate clicked;
    private Student naamStudent;
    @FXML
    private Label studentNaam;

    School HU = School.getSchool();

    @FXML
    void initialize() {
        zetNaam();
        DatePickerSkin datePickerSkin = new DatePickerSkin(new DatePicker(LocalDate.now()));
        DatePickerContent pop = (DatePickerContent) datePickerSkin.getPopupContent();
        Node popupContent = datePickerSkin.getPopupContent();
        pop.setMinHeight(agendaPane.getMinHeight());
        pop.setPrefWidth(agendaPane.getPrefWidth());
        agendaPane.getChildren().add(popupContent);
        List<DateCell> dateCells = getAllDateCells(pop);

        for (DateCell cell : dateCells) {
            cell.addEventHandler(
                    MouseEvent.MOUSE_PRESSED, (e) ->
                    {
                        this.clicked = cell.getItem();
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AbsentieLijst/userInterfaceLaag/DagInzienStudent.fxml"));
                            setCellData(clicked);
                            Parent dagInzien = loader.load();
                            Stage stage = new Stage();
                            stage.setTitle("Dag inzien");
                            stage.setScene(new Scene(dagInzien));
                            stage.getIcons().add(new Image("AbsentieLijst/Footage/calendar.png"));
                            stage.setResizable(false);
                            stage.showAndWait();
                        } catch (IOException i) {
                            i.printStackTrace();
                        }
                    }
            );
        }
    }

    public static void setCellData(LocalDate input){
        clicked = input;
    }

    public static LocalDate getCellData(){
        return clicked;
    }

    private static List<DateCell> getAllDateCells(DatePickerContent content) {
        List<DateCell> result = new ArrayList<>();

        for (Node n : content.getChildren()) {
            if (n instanceof GridPane) {
                GridPane grid = (GridPane) n;
                for (Node gChild : grid.getChildren()) {
                    if (gChild instanceof DateCell) {
                        result.add((DateCell) gChild);
                    }
                }
            }
        }

        return result;
    }


    public void zetNaam(){
        for (Klas klas : HU.getKlassen()) {
            for (Student student : klas.getStudenten()) {
                if (student.getisIngelogd()) {
                    studentNaam.setText(student.getStudentNaam());
                    studentNaam.setTextFill(Color.web("#00a1e1"));
                }
            }
        }
    }
    public void aanroepZiekMelden(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AbsentieLijst/userInterfaceLaag/ZiekMelden.fxml"));
            Parent ziekmeldRoot = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Ziek melden");
            stage.setScene(new Scene(ziekmeldRoot));
            stage.setResizable(false);
            stage.getIcons().add(new Image("AbsentieLijst/Footage/calendar.png"));
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void aanroepAbsentiePlannen(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AbsentieLijst/userInterfaceLaag/ToekomstigAfmelden.fxml"));
        Parent absentieRoot = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Afmelden");
        stage.setScene(new Scene(absentieRoot));
        stage.setResizable(false);
        stage.getIcons().add(new Image("AbsentieLijst/Footage/calendar.png"));
        stage.showAndWait();
    }

    @FXML
    public void uitloggen(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("InlogScreen.fxml"));
        Parent uitlogRoot = loader.load();
        Scene inlogScreen = new Scene(uitlogRoot);

        for (Klas klas : HU.getKlassen()) {
            for (Student student : klas.getStudenten()) {
                if (student.getisIngelogd()) {
                    student.setIngelogd(false);
                }
            }
        }

        Stage hoofdmenu = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        hoofdmenu.setScene(inlogScreen);
        hoofdmenu.centerOnScreen();
        hoofdmenu.setTitle("Inlogscherm");
        hoofdmenu.getIcons().add(new Image("AbsentieLijst/Footage/calendar.png"));
        hoofdmenu.setResizable(false);
        hoofdmenu.show();
    }

    @FXML
    public void aanroepBeterMelden(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AbsentieLijst/userInterfaceLaag/BeterMelden.fxml"));
            Parent ziekmeldRoot = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Beter Melden");
            stage.setScene(new Scene(ziekmeldRoot));
            stage.setResizable(false);
            stage.getIcons().add(new Image("AbsentieLijst/Footage/calendar.png"));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void overzicht(ActionEvent actionEvent) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfmeldDatum.fxml"));
        Parent root = loader.load();
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));

        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.setTitle("Afgemelde Lessen en Afspraken");
        newStage.getIcons().add(new Image("AbsentieLijst/Footage/calendar.png"));
        newStage.showAndWait();
        newStage.setResizable(false);
    }
}

