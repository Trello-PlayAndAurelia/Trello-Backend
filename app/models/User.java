package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * Created by Adam Piech on 2016-10-12.
 */

@Entity
public class User extends Model {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    @NotNull
    private String name;
    @NotNull
    private String login;
    @NotNull
    private String password;

    public static Finder<Integer, User> find = new Finder<>( User.class);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (!name.equals(user.name)) return false;
        if (!password.equals(user.password)) return false;
        return login.equals(user.login);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + login.hashCode();
        return result;
    }

}
