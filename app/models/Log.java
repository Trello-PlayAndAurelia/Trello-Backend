package models;

import com.avaje.ebean.Model;

import javax.persistence.*;

/**
 * Created by Adam Piech on 2016-11-02.
 */

@Entity
public class Log extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;

    @Column(length = 350, nullable = false)
    public String log;

    @Column(nullable = false)
    public long boardId;

    public static final Model.Finder<Long, Log> find = new Model.Finder<Long, Log>(Long.class, Log.class);

}