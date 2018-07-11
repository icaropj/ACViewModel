package br.com.icaropinho.acviewmodel.model;

/**
 * Created by icaro on 03/07/2018.
 */

public class User {

    private String login;

    public User(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
