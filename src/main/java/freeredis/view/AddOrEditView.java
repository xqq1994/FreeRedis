package freeredis.view;

import de.felixroske.jfxsupport.AbstractFxmlView;
import de.felixroske.jfxsupport.FXMLView;
import freeredis.entity.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@FXMLView
public class AddOrEditView extends AbstractFxmlView {

    private Person person;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}