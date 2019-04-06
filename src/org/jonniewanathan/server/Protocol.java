package org.jonniewanathan.server;

public class Protocol {

    public static File addProtocol(String protocol, File file){
        protocol = protocol + "," + file.getFileName() + ",";
        file.setFileData(concatByteArrays(getBytesFromString(protocol), file.getFileData()));
        return file;
    }

    private static byte[] getBytesFromString(String string) {
        return string.getBytes();
    }

    public static byte[] concatByteArrays(byte[] byteArr1, byte[] byteArr2) {
        byte[] concatenatedByteArrays = new byte[byteArr1.length + byteArr2.length];
        System.arraycopy(byteArr1, 0, concatenatedByteArrays, 0, byteArr1.length);
        System.arraycopy(byteArr2, 0, concatenatedByteArrays, byteArr1.length, byteArr2.length);
        return concatenatedByteArrays;
    }
}
