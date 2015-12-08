package main.sample;

import javafx.fxml.FXML;
import main.Main;

import java.awt.*;

/**
 * Created by Илья on 04.12.2015.
 */
public class AddListNameWindowController {
    @FXML
    private TextField listNameField;

    public AddListNameWindowController() {}

    @FXML
    private void initialize() {}

    @FXML
    private void handleOk() {
        if(isInputValid()) {

        }
        Main.hideAddListNameWindow();
        return;
    }

    @FXML
    private void handleCancel() {
        Main.hideAddListNameWindow();
        return;
    }

    private boolean isInputValid() {
        if(listNameField.getText() == null || listNameField.getText().length() ==0) {
            return false;
        }
        return true;
    }
}
