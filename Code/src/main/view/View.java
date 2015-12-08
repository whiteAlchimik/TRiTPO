package main.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Илья on 04.12.2015.
 */
public class View {
    private Stage stage;
    private Parent root;

    public View() {
        try {
            stage = new Stage();
            root = FXMLLoader.load(getClass().getResource("/main/sample/AddBookNameWindow.fxml"));
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
