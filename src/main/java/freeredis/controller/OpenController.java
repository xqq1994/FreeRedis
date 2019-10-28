package freeredis.controller;

import de.felixroske.jfxsupport.FXMLController;
import freeredis.Main;
import freeredis.entity.Person;
import freeredis.view.AddOrEditView;
import freeredis.view.OpenView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

@FXMLController
public class OpenController {
    @FXML
    public TableView<Person> personTable;
    @FXML
    public TableColumn<Person, String> firstNameColumn;
    @FXML
    public TableColumn<Person, String> lastNameColumn;
    @FXML
    public Label firstNameLabel;
    @FXML
    public Label lastNameLabel;
    @FXML
    public Label addressLabel;
    @FXML
    public Button newButton;
    @FXML
    public Button editButton;
    @FXML
    public Button deleteButton;
    @Autowired
    private OpenView openView;
    @Autowired
    private AddOrEditView addOrEditView;

    @FXML
    private void initialize() {
        personTable.setItems(openView.getPersonData());
        // Initialize the person table with the two columns.
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        // Clear person details.
        showPersonDetails(null);

        // Listen for selection changes and show the person details when changed.
        personTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));
    }

    private void showPersonDetails(Person person) {
        if (person != null) {
            // Fill the labels with info from the person object.
            firstNameLabel.setText(person.getFirstName());
            lastNameLabel.setText(person.getLastName());
            addressLabel.setText(person.getAddress());
        } else {
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            addressLabel.setText("");
        }
    }

    @FXML
    private void handleDeletePerson() {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if(selectedIndex >= 0){
            personTable.getItems().remove(selectedIndex);
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("不能删除");
            alert.setHeaderText("没有选中删除项");
            alert.setContentText("请选中后继续");
            alert.showAndWait();
        }
    }

    public boolean showPersonEditDialog(Person person) {
        try {
            Main.showView(AddOrEditView.class, Modality.NONE);
            addOrEditView.setPerson(person);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @FXML
    private void handleNewPerson() {
        Person tempPerson = new Person();
        boolean okClicked = showPersonEditDialog(tempPerson);
        if (okClicked) {
            openView.getPersonData().add(tempPerson);
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleEditPerson() {
        Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = showPersonEditDialog(selectedPerson);
            if (okClicked) {
                showPersonDetails(selectedPerson);
            }
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("没有选中");
            alert.setHeaderText("没有选中项");
            alert.setContentText("请选中后继续");
            alert.showAndWait();
        }
    }

}
