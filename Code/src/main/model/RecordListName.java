package main.model;

/**
 * Created by Илья on 04.12.2015.
 */
public class RecordListName {
    private int id = 0;
    private String listName = null;

    public RecordListName() {}

    public RecordListName(int id, String name) {
        this.id = id;
        this.listName = name;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
