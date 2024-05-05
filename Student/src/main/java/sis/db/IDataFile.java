package sis.db;

import java.io.IOException;

public interface IDataFile {
    void close() throws IOException;
    boolean fileExists();
    void remove() throws IOException;
//    int count();
//    void add(String key, Object obj);
//    Object find(String key);
}
