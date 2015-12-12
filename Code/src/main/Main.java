package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.controller.Controller;
import main.model.Book;
import main.model.RecordListName;
import main.sample.AddBookNameWindowController;
import main.sample.AddListNameWindowController;
import main.sample.DeleteWindowController;
import main.sample.MainWindowController;

import java.io.IOException;

public class Main extends Application {
    private Stage primaryStage;
    private Parent root;

    private Controller controller;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;

        this.controller = new Controller();

        this.initMainWindow();
    }

    public void initMainWindow() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/main/sample/MainWindow.fxml"));
            //this.root = FXMLLoader.load(getClass().getResource("/main/sample/MainWindow.fxml"));
            this.root = (Parent)loader.load();
            primaryStage.setTitle("MainWindow");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
            MainWindowController mainWindowController = loader.getController();
            mainWindowController.setMain(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }

    public Controller getController() {
        return this.controller;
    }

    //отобразить окно AddListNameWindow
    public boolean viewAddListNameWindow(RecordListName recordListName) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/main/sample/AddListNameWindow.fxml"));
            //Parent root = FXMLLoader.load(getClass().getResource("/main/sample/AddListNameWindow.fxml"));
            Parent root = (Parent)loader.load();

            Stage stageAddListNameWindow = new Stage();
            stageAddListNameWindow.setScene(new Scene(root));
            stageAddListNameWindow.initOwner(this.primaryStage);

            AddListNameWindowController addListNameWindowController = loader.getController();
            addListNameWindowController.setStage(stageAddListNameWindow);
            addListNameWindowController.setMain(this);
            addListNameWindowController.setRecordListName(recordListName);

            //stageAddListNameWindow.show();

            stageAddListNameWindow.showAndWait();

            boolean isOkClicked = addListNameWindowController.isOkClicked();
            addListNameWindowController.resetPointers();

            return isOkClicked;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    //отобразить окно AddBookNameWindow
    public boolean viewAddBookNameWindow(Book book) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/main/sample/AddBookNameWindow.fxml"));
            //Parent root = FXMLLoader.load(getClass().getResource("/main/sample/AddListNameWindow.fxml"));
            Parent root = (Parent)loader.load();

            Stage stageAddBookNameWindow = new Stage();
            stageAddBookNameWindow.setScene(new Scene(root));
            stageAddBookNameWindow.initOwner(this.primaryStage);

            AddBookNameWindowController addBookNameWindowController = loader.getController();
            addBookNameWindowController.setStage(stageAddBookNameWindow);
            addBookNameWindowController.setMain(this);
            addBookNameWindowController.setBook(book);

            //stageAddListNameWindow.show();

            stageAddBookNameWindow.showAndWait();

            boolean isOkClicked = addBookNameWindowController.isOkClicked();
            addBookNameWindowController.resetPointers();

            return isOkClicked;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    //отобразить окно DeleteWindow
    public boolean viewDeleteWindow(String str) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/main/sample/DeleteWindow.fxml"));
            //Parent root = FXMLLoader.load(Main.class.getResource("/main/sample/DeleteWindow.fxml"));
            Parent root = (Parent) loader.load();

            Stage stageDeleteWindow = new Stage();
            stageDeleteWindow.setScene(new Scene(root));
            stageDeleteWindow.initOwner(this.primaryStage);

            DeleteWindowController deleteWindowController = loader.getController();
            deleteWindowController.setStage(stageDeleteWindow);
            deleteWindowController.setStr(str);

            stageDeleteWindow.showAndWait();

            boolean isOkClicked = deleteWindowController.isOkClicked();
            deleteWindowController.resetPointers();

            return isOkClicked;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
