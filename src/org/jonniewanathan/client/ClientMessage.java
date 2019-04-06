package org.jonniewanathan.client;

public class ClientMessage {

    public static String extractProtocol(String message){
        String protocol = message.split(",")[0];
        return protocol;
    }

    public static String addProtocol(String protocol, String message){
        String messageWithProtocol = protocol + "," + message;
        return messageWithProtocol;
    }


    public static String extractFileName(String message){
        String fileName = message.split(",")[1];
        return fileName;
    }

    public static String extractFileData(String message){
        String data = message.split(",")[2];
        return data;
    }
}
