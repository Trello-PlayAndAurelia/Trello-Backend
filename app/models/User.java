package models;

import com.avaje.ebean.*;
import com.avaje.ebean.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Adam Piech on 2016-10-12.
 */

@Entity
public class User extends Model {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public long id;

    @Column(length = 64, unique = true, nullable = false)
    public String name;

    @Column(length = 255, unique = true, nullable = false)
    public String email;

    @Column(length = 64, nullable = false)
    private byte[] password;

    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private java.util.List<Board> boards;

    public static final Finder<Long, User> find = new Finder<Long, User>(Long.class, User.class);

    public void setEmail(String email) {
        this.email = email.toLowerCase();
    }

    public void setPassword(String password) {
        this.password = getSha512(password);
    }

    public static byte[] getSha512(String value) {
        try {
            return MessageDigest.getInstance("SHA-512").digest(value.getBytes("UTF-8"));
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
