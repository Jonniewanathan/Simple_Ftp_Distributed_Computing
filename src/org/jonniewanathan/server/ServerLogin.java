package org.jonniewanathan.server;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;

public class ServerLogin {
    private ArrayList<User> activeUsers = new ArrayList<>();

    public ServerLogin() {
    }

    public boolean checkLogin(MyServerDatagramSocket socket, DatagramMessage datagramMessage){
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
                else {
                    activeUsers.add(new User(username, password, socket.getInetAddress(), socket.getPort()));
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
            if (user.getUsername().equals(username) &&
                    user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkUserLoggedInAddressPort(InetAddress address, int port) {
        for (User user: activeUsers) {
            if (user.getAddress() == address &&
                    user.getPort() == port) {
                return true;
            }
        }
        return false;
    }

    //find active users based their address and port
    private int findUserInActiveUsers(InetAddress address, int port){
        for (int i = 0; i< activeUsers.size(); i++) {
            if (activeUsers.get(i).getAddress() == address && activeUsers.get(i).getPort() == port) {
                return i;
            }
        }
        return 1000;
    }

    public void login(MyServerDatagramSocket datagramSocket, DatagramMessage message){
        String sentMessage;
        boolean check = checkLogin(datagramSocket, message);
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
        if (checkUserLoggedInAddressPort(datagramSocket.getInetAddress(), datagramSocket.getPort())) {
            sentMessage = "301";
            int index = findUserInActiveUsers(datagramSocket.getInetAddress(), datagramSocket.getPort());
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
