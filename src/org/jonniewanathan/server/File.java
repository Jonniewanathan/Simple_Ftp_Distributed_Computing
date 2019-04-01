package org.jonniewanathan.server;

import java.io.FileOutputStream;
import java.io.IOException;

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

    public void saveFile(String username) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(".\\" + username + "\\" + this.fileName);
        System.out.println(this.fileName);
        outputStream.write(this.fileData);

        outputStream.close();
    }
}
