package sis.db;

import sis.util.ByteArray;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Logger;

public class DataBase implements IDataFile{
    private DataFile dataFile;
    private IndexFile indexFile;
    public DataBase(String basename) throws IOException {
        dataFile = new DataFile(STR."\{basename}_data.bin");
        indexFile = new IndexFile(STR."\{basename}_index.bin");
    }

    @Override
    public void close() throws IOException {
        dataFile.close();
        indexFile.close();
    }

    @Override
    public boolean fileExists() {
        var dataFileExist = dataFile.fileExists();
        var indexFileExist = indexFile.fileExists();
        Logger.getLogger("DataBase").fine(STR."datafile: \{dataFileExist}, indexfile: \{indexFileExist}");
        return  dataFileExist&& indexFileExist;
    }

    @Override
    public void remove() throws IOException {
        dataFile.remove();
        indexFile.remove();
    }

    public int count() {
        return indexFile.count();
    }

    public void add(String key, Object obj) throws IOException {
        long position = dataFile.size();
        ByteArray byteArray = new ByteArray(obj);
        dataFile.append(byteArray);
        var entry = new IndexFile.Entry(position, byteArray.size());
        indexFile.add(key, entry);
    }

    public Object find(String key) throws IOException {
        IndexFile.Entry entry = indexFile.find(key);
        if(entry == null) return null;
        return dataFile.find(entry);
    }
}
