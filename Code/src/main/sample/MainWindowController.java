package main.sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import main.Main;
import main.model.Book;
import main.model.RecordListName;

/**
 * Created by Илья on 04.12.2015.
 */
public class MainWindowController {
    private Main main;

    @FXML
    private ListView<RecordListName> listNameList;

    @FXML
    private TableView<Book> bookTableView;

    @FXML
    private TableColumn<Book, String> firstNameColumn;

    @FXML
    private TableColumn<Book, String> lastNameColumn;

    public MainWindowController() {

    }

    @FXML
    private void initialize() {
        listNameList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getClickCount() == 2) {
                    int selectedIndex = listNameList.getSelectionModel().getSelectedIndex();
                    if(selectedIndex >= 0) {
                        RecordListName recordListName = listNameList.getItems().get(selectedIndex);
                        if(main.viewAddListNameWindow(recordListName)) {
                            main.getController().updateRecordListName(selectedIndex, recordListName);
                            recordListName = null;
                        }
                    }
                }
            }
        });

        //listNameList.getSelectionModel().selectedItemProperty()

        // Initialize the person table with the two columns.
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameBookProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().getAuthorProperty());
    }

    //отображение списка книг в таблице
    private void showBookDetails(RecordListName recordListName) {
        if(recordListName != null) {
            this.main.getController().getBooksListDB(String.valueOf(recordListName.getId()));
            bookTableView.setItems(main.getController().getBooksList());
        }
        else {
            firstNameColumn.setText("");
            lastNameColumn.setText("");
        }
    }

    //добавить запись
    @FXML
    private void handleAddList() {
        RecordListName recordListName = new RecordListName();
        if(main.viewAddListNameWindow(recordListName)) {
            main.getController().addRecordListName(recordListName);
            main.getController().createTableBook(String.valueOf(recordListName.getId()));
        }
    }

    //удалить запись
    @FXML
    private void handleDeleteList() {
        int selectedIndex = listNameList.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            boolean flag = main.viewDeleteWindow(listNameList.getItems().get(selectedIndex).getListName());
            if(flag) {
                main.getController().removeTableBook(String.valueOf(listNameList.getItems().get(selectedIndex).getId()));
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
        int selectedIndex = listNameList.getSelectionModel().getSelectedIndex();
        if(selectedIndex >= 0) {
            Book book = new Book();
            if (main.viewAddBookNameWindow(book)) {
                System.out.println(listNameList.getSelectionModel().getSelectedItem().getId());
            }
        }
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
