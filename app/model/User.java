package model;

/**
 * Created by Adam Piech on 2016-10-12.
 */

public class User {

    private long id;
    private String userName;
    private String password;
    private String login;

    public User(long id, String login, String userName, String password) {
        this.id = id;
        this.login = login;
        this.userName = userName;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
