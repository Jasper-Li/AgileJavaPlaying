package sis.util;

import java.io.*;

public class ByteArray {
    byte[] bytes = null;
    public ByteArray(Object obj) throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        ObjectOutputStream outputStream = new ObjectOutputStream(byteStream);
        outputStream.writeObject(obj);
        outputStream.flush();
        bytes = byteStream.toByteArray();
    }
    public byte[] getBytes() {
        return bytes;
    }
    public int size() {
        return bytes.length;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof ByteArray other)
            return other.bytes.equals(bytes);
        return false;
    }

    public static Object toObject(byte[] bytes) throws IOException {

        var byteStream = new ByteArrayInputStream(bytes);
        ObjectInputStream input = new ObjectInputStream(byteStream);
        try {
            return input.readObject();
        } catch (ClassNotFoundException e) {
            return null;
        } finally {
            if(input != null) {
                input.close();
            }
        }
    }

}
