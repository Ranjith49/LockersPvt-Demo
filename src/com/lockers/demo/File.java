package com.lockers.demo;

import java.util.Objects;

/***
 * Class to define the File , given its name
 */
public class File implements Comparable<File> {

    private String name;

    private File() {
        // Nothing to do ..
    }

    public File(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(File o) {
        return name.compareTo(o.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        File file = (File) o;
        return Objects.equals(name, file.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
