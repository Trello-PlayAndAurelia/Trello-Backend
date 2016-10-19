package models;

import com.avaje.ebean.Model;

import javax.persistence.*;

/**
 * Created by Adam Piech on 2016-10-12.
 */

@Entity
public class List extends Model {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @Column(length = 128, nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    private java.util.List<Card> cards;

    public static final Model.Finder<Long, List> find = new Model.Finder<Long, List>(Long.class, List.class);

}
