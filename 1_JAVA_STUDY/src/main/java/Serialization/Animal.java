package Serialization;

import java.io.Serializable;

public class Animal implements Serializable {
    private final String name;

    public Animal(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        else if (null == obj || obj.getClass() != this.getClass())
            return false;
        return this.name.equals(((Animal)obj).name);
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                '}';
    }
}