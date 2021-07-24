package home;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class VisitorList implements Initializable {



    @FXML
    public void MainMenuButtonPushed(ActionEvent event) throws IOException {
        Parent mainMenu = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Scene mainMenuScene = new Scene(mainMenu);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(mainMenuScene);
        window.show();
    }

    @FXML
    private TableView<Visitor> visitorTable;
    @FXML
    private TableColumn<Visitor, LocalDate> date;
    @FXML
    private TableColumn<Visitor, LocalTime> intime;
    @FXML
    private TableColumn<Visitor, String> name;
    @FXML
    private TableColumn<Visitor, String> number;
    @FXML
    private TableColumn<Visitor, String> purpose;


//    public ObservableList<Visitor> listVisitor(){
//        ObservableList<Visitor> visitor = FXCollections.observableArrayList
//                (Datasource.getInstance().queryArtists()); ;
//        visitorTable.itemsProperty().bind();
//
//        new Thread(task).start();
//    }

    @Override
    public void initialize(URL url , ResourceBundle rb){

        name.setCellValueFactory(new PropertyValueFactory<Visitor,String>("name"));
        date.setCellValueFactory(new PropertyValueFactory<Visitor , LocalDate>("date"));
        intime.setCellValueFactory(new PropertyValueFactory<Visitor,LocalTime>("time"));
        number.setCellValueFactory(new PropertyValueFactory<Visitor,String>("number"));
        purpose.setCellValueFactory(new PropertyValueFactory<Visitor,String>("purpose"));

        visitorTable.setItems(Datasource.getInstance().queryVisitor());
    }

}

