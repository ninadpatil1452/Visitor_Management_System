package home;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.SQLException;

public class EmployeeVisitor {

    @FXML
    private CheckBox temperature;
    @FXML
    private RadioButton symptoms;
    @FXML
    private TextField text;


    @FXML
    public void enterButtonPushed() throws SQLException {
        String id = text.getText();
        try {
            if (!temperature.isDisable() && symptoms.isSelected()) {
                Task<Void> task = new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        Datasource.getInstance().insertEmployeeVisitor(id);
                        return null;
                    }
                };
                task.setOnSucceeded(e -> {
                    Stage dialog = new Stage();
                    dialog.initStyle(StageStyle.UTILITY);
                    Scene scene = new Scene(new Group(new Text(50, 25, "New Visitor Entered")));
                    dialog.setScene(scene);
                    dialog.showAndWait();
                });
                new Thread(task).start();
            } else {
                Stage dialog = new Stage();
                dialog.initStyle(StageStyle.UTILITY);
                Scene scene = new Scene(new Group(new Text(50, 25, "Sorry! You cannot enter the premisses.")));
                dialog.setScene(scene);
                dialog.showAndWait();
            }
        }catch (Exception e){
            System.out.println("The error is : "+e.getMessage());
        }
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
