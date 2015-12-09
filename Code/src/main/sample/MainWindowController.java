package main.sample;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import main.Main;
import main.model.RecordListName;

/**
 * Created by Илья on 04.12.2015.
 */
public class MainWindowController {
    private Main main;

    @FXML
    private ListView<RecordListName> listNameList;

    public MainWindowController() {

    }

    @FXML
    private void initialize() {
    }

    //добавить запись
    @FXML
    private void handleAddList() {
        RecordListName recordListName = new RecordListName();
        if(main.viewAddListNameWindow(recordListName)) {
            main.getController().addRecordListName(recordListName);
        }
    }

    //удалить запись
    @FXML
    private void handleDeleteList() {
        int selectedIndex = listNameList.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            boolean flag = main.viewDeleteWindow(listNameList.getItems().get(selectedIndex).getListName());
            if(flag) {
                main.getController().removeRecordListName(listNameList.getItems().get(selectedIndex));
            }
            else {
                return;
            }
        }
        return;
    }

    @FXML
    private void handleAddBook() {
        //main.viewAddBookNameWindow();
    }

    @FXML
    private void handleDeleteBook() {
        //main.viewDeleteWindow();
    }

    public void setMain(Main main) {
        this.main = main;
        listNameList.setItems(main.getController().getRecordListName());
    }
}
