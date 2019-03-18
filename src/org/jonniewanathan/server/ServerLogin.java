package org.jonniewanathan.server;

public class ServerLogin {

    public ServerLogin() {
    }

    public boolean checkLogin(MyServerDatagramSocket datagramSocket, DatagramMessage datagramMessage){
        //Do this without hardcoding

        String checkU = "Jonathan";
        String checkP = "Quirke";

        String username = extractUsername(datagramMessage);
        String password = extractPassword(datagramMessage);

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

    public void login(MyServerDatagramSocket datagramSocket, DatagramMessage message){
        String sentMessage;
        if(checkLogin(datagramSocket, message))
        {
            sentMessage = "201";
        }
        else{
            sentMessage = "201";
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
        String sentMessage = "301";

        try{
            datagramSocket.sendMessage(message.getAddress(),
                    message.getPort(), sentMessage);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    private String extractPassword(DatagramMessage message){
        String password = message.getMessage().split(",")[2];
        return password;
    }

    private String extractUsername(DatagramMessage message){
        String username = message.getMessage().split(",")[1];
        return username;
    }


}
