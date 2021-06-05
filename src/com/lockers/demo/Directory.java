package com.lockers.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Directory {

    private String name;
    private List<File> files;
    private List<Directory> childDirectories;

    private Directory() {
        // Nothing to do ..
    }

    public Directory(String name) {
        this.name = name;
        this.files = new ArrayList<>();
        this.childDirectories = new ArrayList<>();
    }

    public void addFileInDirectory(File file) throws IllegalArgumentException {
        if (file == null) {
            throw new IllegalArgumentException("File cannot be null");
        }

        boolean fileExists = files.contains(file);
        if (fileExists) {
            throw new IllegalStateException("File already exists");
        }
        files.add(file);
    }

    public void addChildDirectory(String name) throws IllegalArgumentException {
        if (name == null || name.length() == 0) {
            throw new IllegalArgumentException("DirectoryName cannot be null");
        }

        Directory newDirectory = new Directory(name);
        boolean dirExists = childDirectories.contains(newDirectory);
        if (dirExists) {
            throw new IllegalStateException("Directory already exist");
        }

        this.childDirectories.add(newDirectory);
    }

    public String getName() {
        return name;
    }

    public List<File> getFiles() {
        return files;
    }

    public List<Directory> getChildDirectories() {
        return childDirectories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Directory directory = (Directory) o;
        return Objects.equals(name, directory.name);
    }
}
