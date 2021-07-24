package home;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.SQLException;

public class EmployeeRegister {
    @FXML
    private TextField employeeName;
    @FXML
    private TextField employeeNumber;
    @FXML
    private TextField employeeDepartment;
    @FXML
    private TextField employeePost;
    @FXML
    private AnchorPane anchorPane;

    @FXML
    public void MainMenuButtonPushed(ActionEvent event) throws IOException {
        Parent mainMenu = FXMLLoader.load(getClass().getResource("/home/Home.fxml"));
        Scene mainMenuScene = new Scene(mainMenu);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(mainMenuScene);
        window.show();
    }

    @FXML
    public void enterButtonPushed()  {
        String name = employeeName.getText();
        String number = employeeNumber.getText();
        String department = employeeDepartment.getText();
        String post = employeePost.getText();


            Task<Void> task = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    Datasource.getInstance().insertEmployee(name,number,department,post);
                    return null;
                }
            };
            task.setOnSucceeded(e -> {
                Stage dialog = new Stage();
                dialog.initStyle(StageStyle.UTILITY);
                dialog.setTitle("New Employee Registered!");
                Scene scene = null;
                try {
                    scene = new Scene(new Group(new Text(50,25, "Your Employee ID is : "+Datasource.getInstance().getEmployeeId())));
                } catch (SQLException throwables) {
                    System.out.println(throwables.getMessage());
                }
                dialog.setScene(scene);
                dialog.showAndWait();
            });
            new Thread(task).start();


    }
}
