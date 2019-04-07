package org.jonniewanathan.server;

import java.io.IOException;

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
            String sentMessage;
            ServerLogin login = new ServerLogin();

            while (true) {
                //Receive Function
                DatagramMessage request =
                        mySocket.receiveMessageAndSender();

                String protocol = ServerMessage.extractProtocol(request);
                System.out.println(protocol);
                //Ends Here
                /*
                  codes:
                  100: server ping
                  200: login
                  300: logout
                  400: file Upload
                  500: file Download
                 */
                switch (protocol) {
                    case "100":
                        sentMessage = "101";
                        mySocket.sendMessage(request.getAddress(),
                                request.getPort(), sentMessage);
                        break;
                    case "200":
                        System.out.println(request.getPort());
                        System.out.println(request.getAddress());
                        login.login(mySocket, request);
                        break;
                    case "300":
                        login.logout(mySocket, request);
                        break;
                    case "400":
                        if (login.checkUserLoggedInAddressPort(request.getAddress(), request.getPort())) {
                            try {
                                User user = login.getUser(request.getAddress(), request.getPort());
                                String filename = ServerMessage.extractFileName(request);
                                String data = ServerMessage.extractFileData(request);
                                File file = new File(filename, data.getBytes(), data.length());
                                file.saveFile(user.getUsername());
                                System.out.println("File Saved Successfully!");
                                sentMessage = "401";
                            } catch (IOException e) {
                                sentMessage = "402";
                                System.out.println("File did not save");
                            }
                        } else {
                            sentMessage = "202";
                        }
                        mySocket.sendMessage(request.getAddress(),
                                request.getPort(), sentMessage);
                        break;
                    case "500":
                        System.out.println(request.getMessage());
                        if (login.checkUserLoggedInAddressPort(request.getAddress(), request.getPort())) {

                            User user = login.getUser(request.getAddress(), request.getPort());
                            String filename = ServerMessage.extractFileName(request);
                            File file = ServerFile.getFile(filename, user);
                            if(!file.getFileName().equals("")){
                                file.send(mySocket, request);
                                System.out.println("File Sent Successfully!");
                            }
                            else{
                                mySocket.sendMessage(request.getAddress(), request.getPort(), "502");
                            }

                        }
                        break;
                    default:
                        sentMessage = "666";
                        mySocket.sendMessage(request.getAddress(),
                                request.getPort(), sentMessage);
                        System.out.println("Danger Will Robinson!!!");
                }
            }
        } // end try
        catch (Exception ex) {
            ex.printStackTrace();
        } // end catch
    } //end main
} // end class      
