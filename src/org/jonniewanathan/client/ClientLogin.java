package org.jonniewanathan.client;

public class ClientLogin {

    private String username;
    private String password;

    public ClientLogin(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String createLoginString(){

        String loginString = "200," + this.username + "," + this.password;
        return loginString;
    }

}
