package models;

import com.avaje.ebean.Model;

import javax.persistence.*;

/**
 * Created by Adam Piech on 2016-10-12.
 */

@Entity
public class Card extends Model {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public long id;

    @Column(length = 128, nullable = false)
    public String name;

    @Column(length = 1000, nullable = true)
    public String description;

    public static final Finder<Long, Card> find = new Finder<Long, Card>(Long.class, Card.class);
}
