package org.jonniewanathan.server;

import java.io.*;

/**
 * This module contains the application logic of an echo server
 * which uses a connectionless datagram socket for interprocess
 * communication.
 * A command-line argument is required to specify the server port.
 *
 * @author M. L. Liu
 */

public class EchoServer1 {
    public static void main(String[] args) {
        int serverPort = 7;    // default port
        if (args.length == 1)
            serverPort = Integer.parseInt(args[0]);
        try {
            // instantiates a datagram socket for both sending
            // and receiving data
            MyServerDatagramSocket mySocket = new MyServerDatagramSocket(serverPort);
            System.out.println("Echo server ready.");
            String sentMessage = "wrong request. Please Retry";
            ServerLogin login = new ServerLogin();
//            while(true){
//                DatagramMessage request =
//                        mySocket.receiveMessageAndSender();
//                String message = request.getMessage();
//                System.out.println("Request received");
//                System.out.println("message received: " + message);
//                if(message.equals("ping")){
//                    sentMessage = "Please Enter your Username: ";
//                }
//                else if(!message.equals("ping")){
//                    sentMessage = "Please Enter your Password: ";
//                }
//                mySocket.sendMessage(request.getAddress(),
//                        request.getPort(), sentMessage);
//
//            }

            while(true){
                //Receive Function
                DatagramMessage request =
                        mySocket.receiveMessageAndSender();
                String message = request.getMessage();
                System.out.println("Request received");
                System.out.println("message received: " + message);

                String protocol = ServerMessage.extractProtocol(request);
                System.out.println(protocol);
                //Ends Here

                //Send Function
                //Needs address and port to send to
                sentMessage = "101";

                //Ends here

                /*
                  codes:
                  100: server ping
                  200: login
                  300: logout
                 */
                switch (protocol){
                    case "100":
                        sentMessage = "101";
                        mySocket.sendMessage(request.getAddress(),
                          request.getPort(), sentMessage);
                        break;
                    case "200":
                        login.login(mySocket, request);
//                        if(login.checkLogin(mySocket, request)){
//                            sentMessage = "201";
//                        }
//                        else
//                            sentMessage = "202";
                        break;
                    case "300":
                        login.logout(mySocket, request);
                        break;
                    default:
                        sentMessage = "666";
                        mySocket.sendMessage(request.getAddress(),
                            request.getPort(), sentMessage);
                        System.out.println("Danger Will Robinson!!!");
                }
//                mySocket.sendMessage(request.getAddress(),
//                        request.getPort(), sentMessage);
            }
//            //Receive Function
//            DatagramMessage request =
//                    mySocket.receiveMessageAndSender();
//            String message = request.getMessage();
//            String newmessage = message.substring(0,3);
//            System.out.println(newmessage);
//            System.out.println("Request received");
//            System.out.println("message received: " + message);
//            //Ends Here
//
//            //Send Function
//            //Needs address and port to send to
//            sentMessage = "Please Enter your Username: ";
//            mySocket.sendMessage(request.getAddress(),
//                    request.getPort(), sentMessage);
//            //Ends here
//
//            request =
//                    mySocket.receiveMessageAndSender();
//            String username = request.getMessage();
//            System.out.println("Request received");
//            System.out.println("message received: " + username);
//            mySocket.sendMessage(request.getAddress(),
//                    request.getPort(), "Please Enter your Password: ");
//
//            request =
//                    mySocket.receiveMessageAndSender();
//            String password = request.getMessage();
//            System.out.println("Request received");
//            System.out.println("message received: " + password);
//
//            if(ServerLogin.checkLogin(username,password)){
//                sentMessage = "You are now Logged in!!";
//            }
//            else{
//                sentMessage = "Please try again later you have not been logged in!";
//            }
//
//            mySocket.sendMessage(request.getAddress(),
//                    request.getPort(), sentMessage);


        } // end try
        catch (
                Exception ex) {
            ex.printStackTrace();
        } // end catch
    } //end main
} // end class      
