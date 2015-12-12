package main.model;

import javafx.beans.property.StringProperty;

/**
 * Created by Илья on 11.12.2015.
 */
public class Book {
    private int id = 0;
    private StringProperty nameBook = null;
    private StringProperty author = null;
    private StringProperty comments = null;

    public Book() {}

    public Book(int id, String name, String author, String comments) {
        this.id = id;
        this.nameBook.set(name);
        this.author.set(author);
        this.comments.set(comments);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameBook() {
        return nameBook.get();
    }

    public void setNameBook(String nameBook) {
        this.nameBook.set(nameBook);
    }

    public String getAuthor() {
        return author.get();
    }

    public void setAuthor(String author) {
        this.author.set(author);
    }

    public String getComments() {
        return comments.get();
    }

    public void setComments(String comments) {
        this.comments.set(comments);
    }

    public StringProperty getNameBookProperty() {
        return this.nameBook;
    }

    public StringProperty getAuthorProperty() {
        return this.author;
    }
}