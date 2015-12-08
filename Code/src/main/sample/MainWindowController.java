package main.sample;

import main.Main;

import javafx.fxml.FXML;
/**
 * Created by Илья on 04.12.2015.
 */
public class MainWindowController {


    public MainWindowController() {

    }

    @FXML
    private void initialize() {

    }

    @FXML
    private void handleAddList() {
        Main.viewAddListNameWindow();
    }

    @FXML
    private void handleDeleteList() {
        Main.viewDeleteWindow();
    }

    @FXML
    private void handleAddBook() {
        Main.viewAddBookNameWindow();
    }

    @FXML
    private void handleDeleteBook() {
        Main.viewDeleteWindow();
    }
}
