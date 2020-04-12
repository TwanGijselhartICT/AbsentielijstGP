package AbsentieLijst.userInterfaceLaag;

import AbsentieLijst.Docent;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class HoofdmenuDocentController {

    private static LocalDate clicked;
    public Button verwerken;

    @FXML
    private AnchorPane agendaPane;
    @FXML
    Label docentNaam = new Label();

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
                    MouseEvent.MOUSE_PRESSED, (e) ->{
                        this.clicked = cell.getItem();
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AbsentieLijst/userInterfaceLaag/DagInzienDocent.fxml"));
                            setCellData(clicked);
                            Parent dagInzien = loader.load();
                            Stage stage = new Stage();
                            stage.setTitle("Dag inzien");
                            stage.setScene(new Scene(dagInzien));
                            stage.getIcons().add(new Image("AbsentieLijst/Footage/calendar.png"));
                            stage.setResizable(false);
                            stage.show();
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

    public void zetNaam() {
        for (Docent docent : HU.getDocenten()) {
            if (docent.getIsIngelogd()) {
                docentNaam.setText(docent.getNaam());
                docentNaam.setTextFill(Color.web("#00a1e1"));
            }

        }
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

    @FXML
    public void uitloggen(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("InlogScreen.fxml"));
        Parent uitlogRoot = loader.load();
        Scene inlogScreen = new Scene(uitlogRoot);

        for (Docent docent : HU.getDocenten()) {
            if (docent.getIsIngelogd()) {
                docent.setIngelogd(false);
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
}
