package org.jonniewanathan.client;

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
}
