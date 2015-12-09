package main.sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Created by Илья on 10.12.2015.
 */
public class DeleteWindowController {
    private Stage stage;
    //private RecordListName recordListName;
    private String str;
    private boolean okClicked = false;

    @FXML
    private Label label;

    public DeleteWindowController() {}

    @FXML
    private void initialize() {}

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setStr(String str) {
        this.str = str;
        String stroka = "Вы действительно желаете удалить запись: <<" + str +">>?";
        label.setText(stroka);
    }

    //Returns true if the user clicked OK, false otherwise.
    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        okClicked = true;
        stage.close();
        return;
    }

    @FXML
    private void handleCancel() {
        stage.close();
        return;
    }

    public void resetPointers() {
        this.stage = null;
        this.str = null;
    }
}
