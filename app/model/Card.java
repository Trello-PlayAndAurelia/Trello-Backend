package model;

/**
 * Created by Adam Piech on 2016-10-12.
 */

public class Card {

    private long id;
    private String listName;
    private String description;

    public Card(long id, String listName, String description) {
        this.id = id;
        this.listName = listName;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }
}
