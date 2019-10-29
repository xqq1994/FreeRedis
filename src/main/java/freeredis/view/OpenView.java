package freeredis.view;

import de.felixroske.jfxsupport.AbstractFxmlView;
import de.felixroske.jfxsupport.FXMLView;
import freeredis.entity.Person;
import freeredis.util.Persistant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.List;

@FXMLView
public class OpenView extends AbstractFxmlView {
    Logger logger = LoggerFactory.getLogger(OpenView.class);
    public ObservableList<Person> personData = FXCollections.observableArrayList();

    public OpenView() {
        try {
            personData = Persistant.readPersons();
        } catch (IOException e) {
            logger.error("获取不到配置文件，之前取默认数据");
        }
        if(CollectionUtils.isEmpty(personData)){
            personData.add(new Person("Hans", "Muster"));
        }
    }

    public ObservableList<Person> getPersonData() {
        return personData;
    }

}
