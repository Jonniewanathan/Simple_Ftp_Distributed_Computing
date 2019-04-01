package org.jonniewanathan.server;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;

public class ServerLogin {
    private ArrayList<User> activeUsers = new ArrayList<>();

    public ServerLogin() {
    }

    public boolean checkLogin(DatagramMessage datagramMessage){
        //Do this without hardcoding
        RegisteredUsers users = new RegisteredUsers();
        HashMap<String, String> userList = users.returnUsers();

        String username = extractUsername(datagramMessage);
        String password = extractPassword(datagramMessage);
        try{
            String checkP = userList.get(username);
            if(checkP.equals(password)){//Checks if the password of the registered user is correct
                if(checkUserLoggedInUsernamePassword(username, password)){
                    return false;
                }
                else{
                    activeUsers.add(new User(username, password, datagramMessage.getAddress(), datagramMessage.getPort()));
                    return true;
                }
            }
            else
                return false;

        }catch (Exception e){
            return false;
        }
    }

    private boolean checkUserLoggedInUsernamePassword(String username, String password) {
        for (User user: activeUsers) {
            System.out.println(user.getUsername());
            System.out.println(user.getPassword());
            if (user.getUsername().equals(username) &&
                    user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkUserLoggedInAddressPort(InetAddress address, int port) {
        for (User user: activeUsers) {
            if (user.getAddress().equals(address) &&
                    user.getPort() == port) {
                return true;
            }
        }
        return false;
    }

    //find active users based their address and port
    public int findUserInActiveUsers(InetAddress address, int port){
        for (int i = 0; i< activeUsers.size(); i++) {
            if (activeUsers.get(i).getAddress().equals(address) && activeUsers.get(i).getPort() == port) {
                return i;
            }
        }
        return 1000;
    }

    public int findUserInActiveUsers(String username, String password){
        for (int i = 0; i< activeUsers.size(); i++) {
            if (activeUsers.get(i).getUsername().equals(username) && activeUsers.get(i).getPassword().equals(password)) {
                return i;
            }
        }
        return 1000;
    }

    public User getUser(InetAddress address, int port){
        return activeUsers.get(findUserInActiveUsers(address, port));
    }

    public User getUser(String username, String password){
        return activeUsers.get(findUserInActiveUsers(username, password));
    }

    public void login(MyServerDatagramSocket datagramSocket, DatagramMessage message){
        String sentMessage;
        boolean check = checkLogin(message);
        if(check)
        {
            sentMessage = "201";
        }
        else{
            sentMessage = "202";
        }
        try{
            datagramSocket.sendMessage(message.getAddress(),
                    message.getPort(), sentMessage);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void logout(MyServerDatagramSocket datagramSocket, DatagramMessage message){

        // Add check to see if user is logged in
        String sentMessage;
        if (checkUserLoggedInAddressPort(message.getAddress(), message.getPort())) {
            sentMessage = "301";
            int index = findUserInActiveUsers(message.getAddress(), message.getPort());
            activeUsers.remove(activeUsers.get(index));
        }
        else{
            sentMessage = "302";
        }
        try{
            datagramSocket.sendMessage(message.getAddress(),
                    message.getPort(), sentMessage);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public String extractPassword(DatagramMessage message){
        String password = message.getMessage().split(",")[2];
        return password;
    }

    public String extractUsername(DatagramMessage message){
        String username = message.getMessage().split(",")[1];
        return username;
    }
}
