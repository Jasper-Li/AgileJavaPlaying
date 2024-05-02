package org.example.chapter9;

public class Name {
    private String name;
    Name(String name){
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Name other ) {
            return other.name == name;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
