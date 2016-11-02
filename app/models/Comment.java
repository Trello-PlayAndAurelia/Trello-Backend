package models;

import com.avaje.ebean.Model;

import javax.persistence.*;

/**
 * Created by Adam Piech on 2016-11-02.
 */

@Entity
public class Comment extends Model {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public long id;

    @Column(length = 1000, nullable = false)
    public String text;

    @Column(nullable = false)
    public long cardId;

    @Column(nullable = false)
    public long userId;

    public static final Model.Finder<Long, Comment> find = new Model.Finder<Long, Comment>(Long.class, Comment.class);
}
