package org.jonniewanathan.server;

public class Login {

    static public boolean checkLogin(String username, String password ){
        //Do this without hardcoding

        String checkU = "Jonathan";
        String checkP = "Quirke";

        if(checkU.equals(username)){
            if(checkP.equals(password)){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }

        //Load Json file for the username
        //return user.isPassword(password);
    }

    static public String login(String username, String password){
        return "help";
    }


}
