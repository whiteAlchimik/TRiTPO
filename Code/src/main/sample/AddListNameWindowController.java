package main.sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Main;
import main.model.RecordListName;

/**
 * Created by Илья on 04.12.2015.
 */
public class AddListNameWindowController {
    private Stage stage;
    private Main main;
    private RecordListName recordListName;
    private boolean okClicked = false;

    @FXML
    private TextField listNameField;

    public AddListNameWindowController() {}

    @FXML
    private void initialize() {}

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void setRecordListName(RecordListName recordListName) {
        this.recordListName = recordListName;
        listNameField.setText(recordListName.getListName());
    }

    //Returns true if the user clicked OK, false otherwise.
    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if(isInputValid()) {
            recordListName.setListName(listNameField.getText());
            okClicked = true;
            stage.close();
        }
        return;
    }

    @FXML
    private void handleCancel() {
        stage.close();
        return;
    }

    //return true if the input is valid
    private boolean isInputValid() {
        String errorMessage = "";
        if(listNameField.getText() == null || listNameField.getText().length() == 0) {
            errorMessage += "No valid list name!\n";
        }
        if(errorMessage.length() == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public void resetPointers() {
        this.stage = null;
        this.main = null;
        this.recordListName = null;
    }
}
