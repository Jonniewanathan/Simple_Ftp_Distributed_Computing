package org.jonniewanathan.client;

public class ClientMessage {

    public static String extractProtocol(String message){
        String protocol = message.split(",")[0];
        return protocol;
    }

}
