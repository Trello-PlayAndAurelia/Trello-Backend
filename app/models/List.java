package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;

/**
 * Created by Adam Piech on 2016-10-12.
 */

@Entity
public class List extends Model {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public long id;

    @Column(length = 128, nullable = false)
    public String name;

    @OneToMany(cascade = CascadeType.ALL)
    public java.util.List<Card> cards = new ArrayList<>();

    public boolean isArchived;

    public static final Model.Finder<Long, List> find = new Model.Finder<Long, List>(Long.class, List.class);

}
