package freeredis.controller;

import de.felixroske.jfxsupport.FXMLController;
import freeredis.entity.Person;
import freeredis.view.AddOrEditView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;

@FXMLController
public class AddOrEditController {
    @FXML
    public TextField firstNameField;
    @FXML
    public TextField lastNameField;
    @FXML
    public TextField addressField;
    @FXML
    private AnchorPane anchorPane;

    private Person person;

    @Autowired
    private AddOrEditView addOrEditView;

    private boolean okClicked = false;
    private Stage stage;

    @FXML
    private void initialize() {
    }

    public void handleCancel(ActionEvent actionEvent) {
        getStage().close();
    }

    public Stage getStage() {
        return (Stage) anchorPane.getScene().getWindow();
    }

    public void handleOk(ActionEvent actionEvent) {
        if (isInputValid()) {
            this.person.setFirstName(firstNameField.getText());
            this.person.setLastName(lastNameField.getText());
            this.person.setAddress(addressField.getText());

            okClicked = true;
            getStage().close();
        }
    }
    //设置
    public void setPerson(Person person) {
        this.person = person;
        firstNameField.setText(person.getFirstName());
        lastNameField.setText(person.getLastName());
        addressField.setText(person.getAddress());
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
            errorMessage += "No valid first name!\n";
        }
        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
            errorMessage += "No valid last name!\n";
        }
        if (addressField.getText() == null || addressField.getText().length() == 0) {
            errorMessage += "No valid street!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("未知数据");
            alert.setHeaderText("请输入正确数据");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }

    public void setDialogStage(Stage dialogStage) {
        stage = dialogStage;
    }
}
