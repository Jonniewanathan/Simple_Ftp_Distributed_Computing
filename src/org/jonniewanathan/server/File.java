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

    //C:\Users\Jonathan\Desktop\Projects\Simple_Ftp_Distributed_Computing\src\org\jonniewanathan\server\Jonathan
    // org/jonniewanathan/server/Jonathan/test_doc.txt

    public void saveFile(String username) throws IOException {
        String path = System.getProperty("user.dir");
        path = path + "/src/org/jonniewanathan/server/" + username + "/" + this.fileName;
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
