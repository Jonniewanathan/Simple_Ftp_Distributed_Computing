package org.jonniewanathan.client;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

public class File {
    private String fileName;
    private byte[] fileData;
    private int size;

    public File(String fileName, byte[] fileData, int size){
        this.fileName = fileName;
        this.fileData = fileData;
        this.size = size;
    }

    public String getFileName(){
        return this.fileName;
    }

    public byte[] getFileData(){
        return this.fileData;
    }

    public void setFileData(byte[] data){
        this.fileData = data;
    }

    public int getSize(){
        return this.size;
    }

    public String upload(){
        try{
            EchoClientHelper1 helper = EchoClientHelper1.getHelper("LocalHost", "7");
            File file = Protocol.addProtocol("400", this);
            String echo = helper.getEcho(file.getFileData());
            echo = ClientMessage.extractProtocol(echo);
            if(echo.equals("401")){
                return "Successful Upload";
            }
            else if(echo.equals("402")){
                return "Upload was not Successful at this time";
            }
            return echo;
        }
        catch (SocketException e){
            String issue = "Socket Exception";
            System.out.println(issue);
            return issue;
        }
        catch (UnknownHostException f){
            String issue = "UnknownHost";
            System.out.println(issue);
            return issue;
        }
        catch(IOException g){
            String issue = "IOException";
            System.out.println(issue);
            return issue;
        }

    }
}
