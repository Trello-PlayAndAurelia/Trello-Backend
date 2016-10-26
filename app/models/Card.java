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
    private long id;

    @Column(length = 128, nullable = false)
    private String name;

    @Column(length = 1000, nullable = true)
    private String description;

    public static final Finder<Long, Card> find = new Finder<Long, Card>(Long.class, Card.class);
}