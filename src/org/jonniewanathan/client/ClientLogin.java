package org.jonniewanathan.client;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ClientLogin {

    private String username;
    private String password;
    private EchoClientHelper1 helper;

    public ClientLogin(String username, String password){
        this.username = username;
        this.password = password;
    }
    public ClientLogin(){
        this.username = "";
        this.password = "";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String createLoginString(){

        String loginString = "200," + this.username + "," + this.password;
        return loginString;
    }

    public String createLogoutString(){

        String logoutString = "300";
        return logoutString;
    }

    public String loginUser(){
        try{
            helper = EchoClientHelper1.getHelper("LocalHost", "7");
            String echo = helper.getEcho(createLoginString());
            echo = ClientMessage.extractProtocol(echo);
            if(echo.equals("201")){
                return "Successful Login";
            }
            else if(echo.equals("202")){
                return "Login was not Successful at this time";
            }
            return echo;
        }
        catch (SocketException e){
            String issue = "Socket Exception";
            System.out.println(issue);
            return issue;
        }
        catch (UnknownHostException f){
            String issue = "UnknownHost";
            System.out.println(issue);
            return issue;
        }
        catch(IOException g){
            String issue = "IOException";
            System.out.println(issue);
            return issue;
        }

    }

    public String logoutUser(){
        try{
            String echo = helper.getEcho(createLogoutString());
            echo = ClientMessage.extractProtocol(echo);
            if(echo.equals("301")){
                return "301,Successful Logout";
            }
            else if(echo.equals("302")){
                return "302,Logout was not Successful at this time";
            }
            return echo;
        }
        catch (SocketException e){
            String issue = "Socket Exception";
            System.out.println(issue);
            return issue;
        }
        catch (UnknownHostException f){
            String issue = "UnknownHost";
            System.out.println(issue);
            return issue;
        }
        catch(IOException g){
            String issue = "IOException";
            System.out.println(issue);
            return issue;
        }

    }

}
