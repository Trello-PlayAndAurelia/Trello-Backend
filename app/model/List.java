package model;

import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * Created by Adam Piech on 2016-10-12.
 */

@Entity
public class List {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    @NotNull
    private String listName;
    @NotNull
    private java.util.List<Card> cards;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        List list = (List) o;

        if (id != list.id) return false;
        if (!listName.equals(list.listName)) return false;
        return cards.equals(list.cards);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + listName.hashCode();
        result = 31 * result + cards.hashCode();
        return result;
    }

}
