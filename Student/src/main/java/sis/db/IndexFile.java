package sis.db;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class IndexFile implements IDataFile{
    private String filename;
    private File file;
    private Map<String, Entry> map = new HashMap<>();

    public IndexFile(String filename) throws IOException {
        this.filename = filename;
        file = new File(filename);
        if(file.exists()) {
            ObjectInputStream input = null;
            try {
                input = new ObjectInputStream(
                    new FileInputStream(file)
                );
                map = (Map<String, Entry>) input.readObject();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } finally {
                if(input != null)
                    input.close();
            }
        }
    }

    @Override
    public void close() throws IOException {
        var output = new ObjectOutputStream(new FileOutputStream(file));
        output.writeObject(map);
        output.close();
    }

    @Override
    public boolean fileExists() {
        return file.exists();
    }

    @Override
    public void remove() {
        file.delete();
    }

    public int count() {
        return map.size();
    }

    public void add(String key, Entry entry) {
        map.put(key, entry);
    }

    public Entry find(String key) {
        return map.getOrDefault(key, null);
    }
    public static class Entry implements Serializable {
        private long position;
        private int size;
        Entry(long position, int size){
            this.position =position;
            this.size =size;
        }

        @Override
        public boolean equals(Object obj) {
            if(obj instanceof  Entry other) {
                return other.position == position
                    && other.size == size;
            }
            return false;
        }

        public int size() {
            return size;
        }

        public long position() {
            return  position;
        }
//        private void writeObject(ObjectOutputStream output) throws IOException {
//            output.defaultWriteObject();
//        }
//        private void readObject(ObjectInputStream input) throws IOException, ClassNotFoundException {
//            input.defaultReadObject();
//        }
    }
}
