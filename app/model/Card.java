package model;

/**
 * Created by Adam Piech on 2016-10-12.
 */

public class Card {

    private long id;
    private String cardName;
    private String description;

    public Card(long id, String cardName, String description) {
        this.id = id;
        this.cardName = cardName;
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

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }
}
