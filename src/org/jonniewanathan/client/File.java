package org.jonniewanathan.client;

import java.io.FileOutputStream;
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
            else if(echo.equals("102")){
                return "User Not logged in";
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

    public String download(String path){
        try{
            EchoClientHelper1 helper = EchoClientHelper1.getHelper("LocalHost", "7");
            String message = ClientMessage.addProtocol("500", this.fileName);
            String echo = helper.getEcho(message);
            String protocol = ClientMessage.extractProtocol(echo);
            if(protocol.equals("501")){
                this.fileData = ClientMessage.extractFileData(echo).getBytes();
                this.fileName = ClientMessage.extractFileName(echo);
                this.size = this.fileData.length;
                this.saveFile(path);
                return "Successful Download";
            }
            else if(protocol.equals("502")){
                return "Download was Unsuccessful";
            }
            else if(echo.equals("102")){
                return "User Not logged in";
            }
            return protocol;
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
    public void saveFile(String path) throws IOException {
        System.out.println(path);
        java.io.File file = new java.io.File(path);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream outputStream = new FileOutputStream(file);
        outputStream.write(this.fileData);

        outputStream.close();
    }
}
