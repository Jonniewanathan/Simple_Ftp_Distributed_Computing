package org.jonniewanathan.server;

public class ServerMessage {

    //Maybe change to return int instead of String
    public static String extractProtocol(DatagramMessage message){
        String protocol = message.getMessage().split(",")[0];
        return protocol;
    }

    public static String extractFileName(DatagramMessage message){
        String fileName = message.getMessage().split(",")[1];
        return fileName;
    }

    public static String extractFileData(DatagramMessage message){
        String data = message.getMessage().split(",")[2];
        return data;
    }
}
