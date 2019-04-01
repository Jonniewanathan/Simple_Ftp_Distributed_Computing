package org.jonniewanathan.client;

import java.io.*;
import java.net.InetAddress;

/**
 * This module contains the presentaton logic of an Echo Client.
 *
 * @author M. L. Liu
 */
public class EchoClient1 {
    static final String endMessage = ".";

    public static void main(String[] args) {
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);
        try {
//            System.out.println("Welcome to the Echo client.\n" +
//                    "What is the name of the server host?");
//            String hostName = br.readLine();
//            if (hostName.length() == 0) // if user did not enter a name
//                hostName = "localhost";  //   use the default host name
//            System.out.println("What is the port number of the server host?");
//            String portNum = br.readLine();
//            if (portNum.length() == 0)
//                portNum = "7";          // default port number
//            EchoClientHelper1 helper =
//                    new EchoClientHelper1(hostName, portNum);
//            boolean done = false;
//            String message;
//            String protocol;
//            while (!done) {
//                System.out.print("Type 'ping' or 'login': ");
//                message = br.readLine();
//
//                switch (message) {
//                    case "ping":
//                        message = "100";
//                        message = helper.getEcho(message);
//                        protocol = ClientMessage.extractProtocol(message);
//                        System.out.println(protocol);
//                        if (protocol.equals("101")) {
//                            System.out.println("Pong");
//                        } else if (protocol.equals("102")) {
//                            System.out.println("Error");
//                        } else {
//                            System.out.println("Generic Error");
//                        }
//                        System.out.println(message);
//                        break;
//                    case "login":
//                        message = "200";
//                        System.out.println("Please enter your username: ");
//                        message += "," + br.readLine();
//                        System.out.println("Please enter your password: ");
//                        message += "," + br.readLine();
//                        message = helper.getEcho(message);
//                        protocol = ClientMessage.extractProtocol(message);
//                        if (protocol.equals("201")) {
//                            System.out.println("Logging Successful");
//                        } else if (protocol.equals("202")) {
//                            System.out.println("Login not successful");
//                        } else {
//                            System.out.println("Generic Error");
//                        }
//                        System.out.println(message);
//                        break;
//                    case "logout":
//                        message = "300";
//                        message = helper.getEcho(message);
//                        System.out.println(message);
//                        protocol = ClientMessage.extractProtocol(message);
//                        if (protocol.equals("301")) {
//                            System.out.println("You have logged out of the system");
//                        } else if (protocol.equals("302")) {
//                            System.out.println("Error Logging Out!!");
//                        } else {
//                            System.out.println("Generic Error");
//                        }
//                        break;
//                    case "upload":
//                        message = "400";
//                        File file = ClientFile.getFile(
//                                "C:\\Users\\Jonathan\\Desktop\\Projects\\Simple_Ftp_Distributed_Computing\\src\\org\\jonniewanathan\\client\\data\\test2_electric_boogaloo.txt");
//                        System.out.println(file.getFileData().toString());
//                        System.out.println(new String(file.getFileData(), 0, file.getFileData().length));
//                        System.out.println(file.getFileName());
//                        file = Protocol.addProtocol(message, file);
//                        MyClientDatagramSocket datagramSocket = helper.getMySocket();
//                        datagramSocket.sendMessageFromBytes(InetAddress.getByName(hostName), Integer.parseInt(portNum), file.getFileData());
//                        message = datagramSocket.receiveMessage();
//                        System.out.println(message);
//                        protocol = ClientMessage.extractProtocol(message);
//                        if (protocol.equals("401")) {
//                            System.out.println("File Upload Successful");
//                        } else if (protocol.equals("402")) {
//                            System.out.println("Error Uploading!!");
//                        } else {
//                            System.out.println("Generic Error");
//                        }
//                        break;
//                    default:
//                        System.out.println("Wrong entry try again!!!");
                //}

            //} // end while
        } // end try
        catch (Exception ex) {
            ex.printStackTrace();
        } // end catch
    } //end main
} // end class      
