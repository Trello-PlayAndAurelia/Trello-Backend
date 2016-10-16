package model;

import javax.persistence.*;

/**
 * Created by Adam Piech on 2016-10-12.
 */

@Entity
public class Table {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String tableName;
    private java.util.List<List> lists;
    private java.util.List<User> users;

//    public Table(long id, String tableName, java.util.List<List> lists, java.util.List<User> users) {
//        this.id = id;
//        this.tableName = tableName;
//        this.lists = lists;
//        this.users = users;
//    }
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public String getTableName() {
//        return tableName;
//    }
//
//    public void setTableName(String tableName) {
//        this.tableName = tableName;
//    }
//
//    public java.util.List<List> getLists() {
//        return lists;
//    }
//
//    public void setLists(java.util.List<List> lists) {
//        this.lists = lists;
//    }
//
//    public java.util.List<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(java.util.List<User> users) {
//        this.users = users;
//    }
}
