package sis.db;

import sis.util.ByteArray;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.util.logging.Logger;

public class DataFile implements IDataFile{
    private static Logger logger = Logger.getLogger("DtaFile");
    private String filename;
    private RandomAccessFile randomAccessFile;
    public DataFile(String filename) throws FileNotFoundException {
        this.filename = filename;
        randomAccessFile = new RandomAccessFile(filename, "rw");
        logger.fine(STR."Create randomAccessFile: \{filename}");
    }

    @Override
    public void close() throws IOException {
        randomAccessFile.close();
        logger.fine(STR."close randomAccessFile: \{filename}");
    }

    @Override
    public boolean fileExists() {
        return new File(filename).exists();
    }

    @Override
    public void remove() throws IOException {
//        close();
        Files.deleteIfExists(new File(filename).toPath());
//        var buf = new File(filename);
//        if(buf.exists() && !buf.delete()) {
//            throw new RuntimeException(STR."Failed to delete file: \{filename}");
//        }
    }

    public long size() throws IOException {
        return randomAccessFile.length();
    }

    public byte[] findBytes(IndexFile.Entry entry) throws IOException {
        byte[] bytes = new byte[entry.size()];
        randomAccessFile.seek(entry.position());
        randomAccessFile.readFully(bytes);
        return bytes;

    }
    public Object find(IndexFile.Entry entry) throws IOException {
        byte[] bytes = new byte[entry.size()];
        randomAccessFile.seek(entry.position());
        randomAccessFile.readFully(bytes);

        return ByteArray.toObject(bytes);
    }

    public void append(ByteArray byteArray) throws IOException {
        randomAccessFile.seek(randomAccessFile.length());
        randomAccessFile.write(byteArray.getBytes());
    }
}
