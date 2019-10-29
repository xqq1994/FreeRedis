package freeredis;

import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import freeredis.view.OverViewView;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Main extends AbstractJavaFxApplicationSupport {
    public static void main(String[] args) {
        launch(Main.class, OverViewView.class, args);
    }
}
