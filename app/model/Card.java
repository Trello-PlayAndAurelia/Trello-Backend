package model;

import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * Created by Adam Piech on 2016-10-12.
 */

@Entity
public class Card {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    @NotNull
    private String cardName;
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (id != card.id) return false;
        if (!cardName.equals(card.cardName)) return false;
        return description != null ? description.equals(card.description) : card.description == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + cardName.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

}
