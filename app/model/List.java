package model;

/**
 * Created by Adam Piech on 2016-10-12.
 */

public class List {

    private long id;
    private String listName;
    private java.util.List<Card> cardss;

    public List(long id, String listName, java.util.List<Card> cardss) {
        this.id = id;
        this.listName = listName;
        this.cardss = cardss;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public java.util.List<Card> getCardss() {
        return cardss;
    }

    public void setCardss(java.util.List<Card> cardss) {
        this.cardss = cardss;
    }
}
