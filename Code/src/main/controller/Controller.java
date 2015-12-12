package main.controller;

import com.mysql.fabric.jdbc.FabricMySQLDriver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.model.Book;
import main.model.BookDAO;
import main.model.RecordListName;
import main.model.RecordListNameDAO;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Controller {
    private static final String URL = "jdbc:mysql://localhost:3306/listbook";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private Connection connection;

    private ObservableList<RecordListName> recordListName = FXCollections.observableArrayList();
    private RecordListNameDAO recordListNameDAO;

    private ObservableList<Book> booksList = FXCollections.observableArrayList();
    private BookDAO bookDAO;

    public Controller() {
        this.initConnection();
        recordListNameDAO = new RecordListNameDAO(connection);
        bookDAO = new BookDAO(connection);
        this.getListNameDB();
    }

    private void initConnection() {
        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    //получить список вкладок из базы данных
    public void getListNameDB() {
        recordListName = FXCollections.observableArrayList(this.recordListNameDAO.getRecordListNameList());
    }

    public void getBooksListDB(String name){
        if(this.booksList.size() > 0)
            this.booksList.clear();
        booksList = FXCollections.observableArrayList(this.bookDAO.getBookList(name));
    }

    //получить колекцию
    public ObservableList<RecordListName> getRecordListName() {
        return recordListName;
    }

    public ObservableList<Book> getBooksList() {
        return this.booksList;
    }

    //добавить в коллекцию и в базу данных
    public void addRecordListName(RecordListName recordListName1) {
        this.recordListName.add(recordListName1);
        this.recordListNameDAO.updateRecordListName(recordListName1);
    }

    //удалить из коллекции и базы данных
    public void removeRecordListName(RecordListName recordListName) {
        this.recordListName.remove(recordListName);
        this.recordListNameDAO.deleteRecordListName(recordListName.getId());
    }

    //обновить RecordListName в базе данных
    public void updateRecordListName(int selectedIndex, RecordListName recordListName) {
        this.recordListName.set(selectedIndex, recordListName);
        this.recordListNameDAO.updateRecordListName(recordListName);
    }

    //создать таблицу для книг в базе данных
    public void createTableBook(String name) {
        this.bookDAO.createTable(name);
    }

    //удалить таблицу книг в базе данных
    public void removeTableBook(String name) {
        this.bookDAO.removeTable(name);
    }
}
