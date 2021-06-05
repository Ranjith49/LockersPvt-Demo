package com.lockers.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class to hold Directory Info
 * name             -> Name of directory
 * files            -> List of child files at that level
 * childDirectories -> List of child directories at that level
 */
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

    /**
     * Method to add the file in the current directory
     *
     * @param file - file to be added
     * @throws IllegalArgumentException
     */
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

    /**
     * Method to add a child directory
     *
     * @param name - name of directory to be added
     * @throws IllegalArgumentException
     */
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
