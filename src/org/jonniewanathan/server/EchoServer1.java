package org.jonniewanathan.server;

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
            }
        } // end try
        catch (Exception ex) {
            ex.printStackTrace();
        } // end catch
    } //end main
} // end class      
