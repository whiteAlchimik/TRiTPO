package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.view.View;

import java.io.IOException;

public class Main extends Application {
    private static Stage stageAddBookNameWindow = null;
    private static Stage stageAddListNameWindow = null;
    private static Stage stageDeleteWindow = null;

    private static final String URL = "jdbc:mysql://localhost:3306/listbook";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/main/sample/MainWindow.fxml"));
        primaryStage.setTitle("MainWindow");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        View view = new View();
       /* try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            RecordListNameDAO recordListNameDAO = new RecordListNameDAO(connection);
            RecordListName recordListName = new RecordListName(6, "Разноеееееее");
            recordListNameDAO.updateRecordListName(recordListName);
            //List<RecordListName> recordListName = recordListNameDAO.getRecordListNameList();
            if(!connection.isClosed())
            {
                System.out.println("Соединение с БД установлена");
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }*/

    }


    public static void main(String[] args) {
        launch(args);
    }

    //отобразить окно AddListNameWindow
    public static void viewAddListNameWindow() {
        if(stageAddListNameWindow == null) {
            try {
                stageAddListNameWindow = new Stage();
                Parent root = FXMLLoader.load(Main.class.getResource("/main/sample/AddListNameWindow.fxml"));
                stageAddListNameWindow.setScene(new Scene(root));
                stageAddListNameWindow.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //отобразить окно AddBookNameWindow
    public static void viewAddBookNameWindow() {
        if(stageAddBookNameWindow == null) {
            try {
                stageAddBookNameWindow = new Stage();
                Parent root = FXMLLoader.load(Main.class.getResource("/main/sample/AddBookNameWindow.fxml"));
                stageAddBookNameWindow.setScene(new Scene(root));
                stageAddBookNameWindow.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //отобразить окно DeleteWindow
    public static void viewDeleteWindow() {
        if(stageDeleteWindow == null) {
            try {
                stageDeleteWindow = new Stage();
                Parent root = FXMLLoader.load(Main.class.getResource("/main/sample/DeleteWindow.fxml"));
                stageDeleteWindow.setScene(new Scene(root));
                stageDeleteWindow.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //скрыть окно AddListNameWindow
    public static void hideAddListNameWindow() {
        if(stageAddListNameWindow != null) {
            stageAddListNameWindow.close();
            stageAddListNameWindow = null;
        }
        return;
    }

    //скрыть окно AddBookNameWindow
    public static void hideAddBookNameWindow() {
        if(stageAddBookNameWindow != null) {
            stageAddBookNameWindow.close();
            stageAddBookNameWindow = null;
        }
        return;
    }

    //скрыть окно DeleteWindow
    public static void hideDeleteWindow() {
        if(stageDeleteWindow != null) {
            stageDeleteWindow.close();
            stageDeleteWindow = null;
        }
        return;
    }
}
