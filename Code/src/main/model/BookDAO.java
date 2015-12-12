package main.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Илья on 11.12.2015.
 */
public class BookDAO {
    private final Connection connection;

    public BookDAO(Connection connection) {
        this.connection = connection;
    }

    //получение Book из строки ResultSet
    private Book getBookFromRs(ResultSet resultSet) {
        Book book = new Book();
        try {
            book.setId(resultSet.getInt("id"));
            book.setNameBook(resultSet.getString("namebook"));
            book.setAuthor(resultSet.getString("author"));
            book.setComments(resultSet.getString("comments"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    //создать таблицу для книг в базе данных
    public void createTable(String nameTable) {
        try {
            String SQL_CREATE = "CREATE TABLE `tablebook" + nameTable + "` (\n"
                    + " `id` INT NOT NULL AUTO_INCREMENT,\n"
                    + " `namebook` VARCHAR(80) NULL,\n"
                    + " `author` VARCHAR(80) NULL,\n"
                    + " `comments` VARCHAR(256) NULL,\n"
                    + " PRIMARY KEY (`id`),\n"
                    + " UNIQUE INDEX `id_UNIQUE` (`id` ASC))\n"
                    + "ENGINE = InnoDB\n"
                    + "DEFAULT CHARACTER SET = utf8";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //удалить таблицу книг из базы данных
    public void removeTable(String nameTable) {
        try {
            String SQL_DROP = "DROP TABLE `tablebook" + nameTable + "`";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DROP);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //получение Book  по id
    public Book getBookId(int id, String name) {
        Book book = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM tablebook"
                    + name + " WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                book = this.getBookFromRs(resultSet);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    //получение всего списка RecordListName
    public List<Book> getBookList(String name) {
        List<Book> bookList = new ArrayList<Book>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM tablebook" + name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                bookList.add(this.getBookFromRs(resultSet));
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList;
    }

    //создание/обновление Book
    public void updateBook(Book book, String name) {
        try {
            PreparedStatement preparedStatement = null;
            if(book.getId() > 0) {
                preparedStatement = connection.prepareStatement("UPDATE tablebook" +
                        name + " SET namebook=?, author=?, comments=? WHERE id=?");
                preparedStatement.setString(1, book.getNameBook());
                preparedStatement.setString(2, book.getAuthor());
                preparedStatement.setString(3, book.getComments());
                preparedStatement.setInt(4, book.getId());
            }
            else {
                preparedStatement = connection.prepareStatement("INSERT INTO tablebook" +
                        name + " VALUES(?,?,?,?)");
                preparedStatement.setInt(1, book.getId());
                preparedStatement.setString(2, book.getNameBook());
                preparedStatement.setString(3, book.getAuthor());
                preparedStatement.setString(4, book.getComments());
            }
            preparedStatement.executeUpdate();
            preparedStatement.close();

            book.setId(this.getBookName(book.getNameBook(), book.getAuthor(), name).getId());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //получение Book  по имени и автору
    public Book getBookName(String nameBook, String author, String nameDB) {
        Book book = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM tablebook" +
                    nameDB + " WHERE namebook=? AND author=?");
            preparedStatement.setString(1, nameBook);
            preparedStatement.setString(2, author);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                book = this.getBookFromRs(resultSet);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    //удаление
    public void deleteBook(int id, String name) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM tablebook" +
                    name + " WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
