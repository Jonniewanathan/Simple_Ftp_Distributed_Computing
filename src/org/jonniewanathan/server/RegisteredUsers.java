package org.jonniewanathan.server;

import java.util.HashMap;

public class RegisteredUsers {

    private HashMap<String, String> users = new HashMap<>();
    public RegisteredUsers(){
        users.put("Jonathan", "Quirke");
    }

    public HashMap<String, String> returnUsers(){
        return users;
    }
}
