package models;

import javax.persistence.*;

/**
 * Created by Adam Piech on 2016-10-12.
 */

@Entity
public class Board {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @Column(length = 128, nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    private java.util.List<List> lists;

    @ManyToMany(cascade = CascadeType.ALL)
    private java.util.List<User> users;

}
