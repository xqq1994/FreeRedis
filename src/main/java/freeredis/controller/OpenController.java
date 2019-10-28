package freeredis.controller;

import de.felixroske.jfxsupport.FXMLController;
import freeredis.entity.Person;
import freeredis.view.OpenView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.springframework.beans.factory.annotation.Autowired;

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
        personTable.getItems().remove(selectedIndex);
    }
}
