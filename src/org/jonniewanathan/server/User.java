package org.jonniewanathan.server;
import org.json.simple.JSONObject;

public class User {
    private String username;
    private String password;
    private JSONObject jsonObject;
    public User(String username, String password){
        this.password = password;
        this.username = username;
    }

    public void saveToJson() {
        jsonObject = new JSONObject();
        jsonObject.put("UserName", this.username);
        jsonObject.put("Password", this.password);
    }

    public boolean isPassword(String password){
        return this.password.equals(password);
    }

}
