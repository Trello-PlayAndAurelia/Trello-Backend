package models;

import com.avaje.ebean.Model;

import javax.persistence.*;

/**
 * Created by Adam Piech on 2016-10-12.
 */

@Entity
public class Board extends Model {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @Column(length = 128, nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    private java.util.List<List> lists;

    @ManyToMany(cascade = CascadeType.ALL)
    private java.util.List<User> users;

    public static final Finder<Long, Board> find = new Finder<Long, Board>(Long.class, Board.class);
}
