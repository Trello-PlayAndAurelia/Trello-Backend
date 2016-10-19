package models;

import javax.persistence.*;

/**
 * Created by Adam Piech on 2016-10-12.
 */

@Entity
public class Card {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @Column(length = 128, nullable = false)
    private String name;

    @Column(length = 1000, nullable = true)
    private String description;

}
