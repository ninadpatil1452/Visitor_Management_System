package home;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    @FXML
    public void NewVisitorButtonPushed(ActionEvent event) throws IOException {
        Parent newVisitor = FXMLLoader.load(getClass().getResource("/home/NewVisitor.fxml"));
        Scene newVisitorScene = new Scene(newVisitor);

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        stage.setScene(newVisitorScene);
        stage.show();

    }

    @FXML
    public void VisitorListButtonPushed(ActionEvent event1) throws IOException {


            Parent VisitorList = FXMLLoader.load(getClass().getResource("/home/VisitorList.fxml"));
            Scene VisitorListScene = new Scene(VisitorList);
            Stage stage = (Stage)((Node)event1.getSource()).getScene().getWindow();

            stage.setScene(VisitorListScene);
            stage.show();
    }

    @FXML
    public void EmployeeEntryButtonPushed(ActionEvent event) throws IOException{

        Parent EmployeeEntry = FXMLLoader.load(getClass().getResource("/home/Employee Register.fxml"));
        Scene EmployeeScene = new Scene(EmployeeEntry);

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        stage.setScene(EmployeeScene);
        stage.show();
    }

    @FXML
    public void EmployeeListButtonPushed(ActionEvent event)throws IOException{
        Parent EmployeeList = FXMLLoader.load(getClass().getResource("/home/EmployeeList.fxml"));
        Scene EmployeeListScene = new Scene(EmployeeList);

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        stage.setScene(EmployeeListScene);
        stage.show();
    }

    @FXML
    public void EmployeeVisitorButtonPushed(ActionEvent event)throws IOException{
        Parent EmployeeVisitor = FXMLLoader.load(getClass().getResource("/home/EmployeeVisitor.fxml"));
        Scene EmployeeVisitorScene = new Scene(EmployeeVisitor);

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        stage.setScene(EmployeeVisitorScene);
        stage.show();
    }

    @FXML
    public void EmployeeVisitorListButtonPushed(ActionEvent event)throws IOException{
        Parent EmployeeVisitor = FXMLLoader.load(getClass().getResource("/home/EmployeeVisitorList.fxml"));
        Scene EmployeeVisitorScene = new Scene(EmployeeVisitor);

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        stage.setScene(EmployeeVisitorScene);
        stage.show();
    }


}
