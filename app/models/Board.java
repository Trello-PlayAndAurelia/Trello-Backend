package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;

/**
 * Created by Adam Piech on 2016-10-12.
 */

@Entity
public class Board extends Model {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public long id;

    @Column(length = 128, nullable = false)
    public String name;

    @OneToMany(cascade = CascadeType.ALL)
    public java.util.List<List> lists = new ArrayList<>();

//    public java.util.List<Long> userIds = new ArrayList<>();

    public static final Finder<Long, Board> find = new Finder<Long, Board>(Long.class, Board.class);
}
