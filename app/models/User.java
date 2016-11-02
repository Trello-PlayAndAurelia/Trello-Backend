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
//    private String password;
    private byte[] password;

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JsonIgnore
//    private java.util.List<Board> boards;

    public static final Finder<Long, User> find = new Finder<Long, User>(Long.class, User.class);

    public void setEmail(String email) {
        this.email = email.toLowerCase();
    }

    public void setPassword(String password) {
        this.password = getSha512(password);
    }

//    public void setPassword(String password) {
//        this.password = password;
//    }

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

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email.toLowerCase();
        this.password = getSha512(password);
//        this.password = password;
    }

    public static User findByEmail(String email) {
        return find
                .where()
                .eq("email", email.toLowerCase())
                .findUnique();
    }

    public static User findByName(String name) {
        return find
                .where()
                .eq("name", name)
                .findUnique();
    }

    public static User findByEmailAndPassword(String email, String password) {
        return find
                .where()
                .eq("email", email.toLowerCase())
                .eq("password", getSha512(password))
//                .eq("password", password)
                .findUnique();
    }

    public static User findByNameAndPassword(String name, String password) {
        return find
                .where()
                .eq("name", name)
                .eq("password", getSha512(password))
//                .eq("password", password)
                .findUnique();
    }

}
