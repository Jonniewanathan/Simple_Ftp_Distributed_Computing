package org.jonniewanathan.client;

import java.io.*;

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
            System.out.println("Welcome to the Echo client.\n" +
                    "What is the name of the server host?");
            String hostName = br.readLine();
            if (hostName.length() == 0) // if user did not enter a name
                hostName = "localhost";  //   use the default host name
            System.out.println("What is the port number of the server host?");
            String portNum = br.readLine();
            if (portNum.length() == 0)
                portNum = "7";          // default port number
            EchoClientHelper1 helper =
                    new EchoClientHelper1(hostName, portNum);
            boolean done = false;
            String message, echo;
//            message = "100";
//
//            echo = helper.getEcho(message);
//            System.out.println(echo);
            while (!done) {
//                System.out.println("Enter a line to receive an echo back from the server, "
//                                + "or a single peroid to quit.");
                System.out.print("Type 'ping' or 'login': ");
                message = br.readLine();

                switch (message){
                    case "ping":
                        message = "100";
                        break;
                    case "login":
                        message = "200";
                        System.out.println("Please enter your username: ");
                        message += "," + br.readLine();
                        System.out.println("Please enter your password: ");
                        message += "," + br.readLine();
                        break;
                    case "logout":
                        message = "300";
                        System.out.println("You have logged out of the system");
                        break;
                    default:
                        System.out.println("Wrong entry try again!!!");
                }

                message = helper.getEcho(message);
                System.out.println(message);

                String protocol = ClientMessage.extractProtocol(message);
                System.out.println(protocol);

//            if ((message.trim()).equals (endMessage)){
//               done = true;
//               helper.done();
//            }
//            else {
//               echo = helper.getEcho(message);
//               System.out.println(echo);
//            }
            } // end while
        } // end try
        catch (Exception ex) {
            ex.printStackTrace();
        } // end catch
    } //end main
} // end class      
