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

public class EmployeeVisitorList implements Initializable {
    @FXML
    private TableView<Employee> employeeVisitorTable;
    @FXML
    private TableColumn<Employee, Integer> id;
    @FXML
    private TableColumn<Employee, LocalDate> date;
    @FXML
    private TableColumn<Employee, LocalTime> time;
    @FXML
    private TableColumn<Employee, String> name;
    @FXML
    private TableColumn<Employee, String> number;
    @FXML
    private TableColumn<Employee, String> department;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        id.setCellValueFactory(new PropertyValueFactory<Employee,Integer>("id"));
        date.setCellValueFactory(new PropertyValueFactory<Employee,LocalDate>("date"));
        time.setCellValueFactory(new PropertyValueFactory<Employee,LocalTime>("time"));
        name.setCellValueFactory(new PropertyValueFactory<Employee,String>("name"));
        number.setCellValueFactory(new PropertyValueFactory<Employee, String>("number"));
        department.setCellValueFactory(new PropertyValueFactory<Employee, String>("department"));

        employeeVisitorTable.setItems(Datasource.getInstance().queryEmployeeVisitorList());


    }

    @FXML
    public void MainMenuButtonPushed(ActionEvent event) throws IOException {
        Parent mainMenu = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Scene mainMenuScene = new Scene(mainMenu);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(mainMenuScene);
        window.show();

    }
}
