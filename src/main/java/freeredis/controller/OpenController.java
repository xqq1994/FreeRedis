package freeredis.controller;


import de.felixroske.jfxsupport.FXMLController;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@FXMLController
public class OpenController {
    @FXML
    public TextField text_1;

    @FXML
    public void showDateTime(Event event) {
        System.out.println("Button Clicked!");

        Date now = new Date();

        DateFormat df = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");
        String dateTimeString = df.format(now);
        // Show in VIEW
        text_1.setText(dateTimeString);

    }

}
