package model;

import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * Created by Adam Piech on 2016-10-12.
 */

@Entity
public class Board {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    @NotNull
    private java.util.List<List> lists;
    @NotNull
    private java.util.List<User> users;
    @NotNull
    private String tableName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Board board = (Board) o;

        if (id != board.id) return false;
        if (!lists.equals(board.lists)) return false;
        if (!users.equals(board.users)) return false;
        return tableName.equals(board.tableName);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + lists.hashCode();
        result = 31 * result + users.hashCode();
        result = 31 * result + tableName.hashCode();
        return result;
    }

}
