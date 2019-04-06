package org.jonniewanathan.client;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ClientFile {

    public static File getFile(String filePath){
        try {
            byte[] data = getBytesFromFile(filePath);
            String fileName = getFileNameFromFile(filePath);
            return new File(fileName, data, data.length);
        }catch (IOException e){
            System.out.println("File could not be found");
            return new File("", new byte[1], 1);
        }
    }

    private static byte[] getBytesFromFile(String filepath) throws IOException{
        Path path = Paths.get(filepath);
        return Files.readAllBytes(path);
    }

    private static String getFileNameFromFile(String filepath){
        Path path = Paths.get(filepath);
        String name = path.getFileName().toString();
        return name;
    }
}
