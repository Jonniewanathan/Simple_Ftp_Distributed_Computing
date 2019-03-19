package org.jonniewanathan.server;

import java.net.InetAddress;

public class User {
    private String username;
    private String password;
    private InetAddress address;
    private int port;
    public User(String username, String password, InetAddress address, int port){
        this.setUsername(username);
        this.setPassword(password);
        this.setAddress(address);
        this.setPort(port);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public InetAddress getAddress() {
        return address;
    }

    public void setAddress(InetAddress address) {
        this.address = address;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
