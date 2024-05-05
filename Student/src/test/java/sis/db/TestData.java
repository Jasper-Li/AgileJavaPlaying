package sis.db;

import java.io.Serializable;

public class TestData implements Serializable {
    public String key;
    public String field1;
    public int field2;
    public TestData(String key, String field1, int field2) {
        this.key = key;
        this.field1 = field1;
        this.field2 = field2;
    }
    String key() {return key;};

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof TestData other) {
            return other.key.equals(key)
                && other.field1.equals(field1)
                && other.field2 == field2;
        }
        return false;
    }
}
