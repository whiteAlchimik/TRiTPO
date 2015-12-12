package main.sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Main;
import main.model.Book;

/**
 * Created by Илья on 11.12.2015.
 */
public class AddBookNameWindowController {
    private Stage stage;
    private Main main;
    private Book book;
    private boolean okClicked = false;

    @FXML
    private TextField bookNameField;

    @FXML
    private TextField authorField;

    @FXML
    private TextArea commentsField;

    public AddBookNameWindowController() {}

    @FXML
    private void initialize() {}

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void setBook(Book book) {
        this.book = book;
        bookNameField.setText(book.getNameBook());
        authorField.setText(book.getAuthor());
        commentsField.setText(book.getComments());
    }

    //Returns true if the user clicked OK, false otherwise.
    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if(isInputValid()) {
            book.setNameBook(bookNameField.getText());
            book.setAuthor(authorField.getText());
            book.setComments(commentsField.getText());
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
        if(bookNameField.getText() == null || bookNameField.getText().length() == 0) {
            errorMessage += "No valid list name!\n";
        }
        if(authorField.getText() == null || authorField.getText().length() == 0) {
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
        this.book = null;
    }
}
