package com.lockers.file;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class LockersApp {

    private final File rootDir;
    private final Scanner scanner = new Scanner(System.in);
    private boolean processInput = true;

    private final String[] options = {
            "1 . List the files / Folder in current Directory",
            "2 . Options for File Manipulation",
            "3 . Close the application"
    };

    private final String[] fileOptions = {
            "1. Add a File",
            "2. Delete a File",
            "3. Search a File",
            "4. Go Back to Main Menu"
    };

    public LockersApp() {
        String currentDir = System.getProperty("user.dir");
        rootDir = new File(currentDir + "\\" + "files");
        rootDir.mkdirs();
    }

    public void runApplication() {
        do {
            int userInput = displayMainMenu();
            switch (userInput) {
                case 1 -> processFilesInDir(rootDir);
                case 2 -> processFileMenu(rootDir);
                case 3 -> processInput = false;
                default -> System.out.println("Please enter valid input ..");
            }
        } while (processInput);
    }

    private void processFileMenu(File root) {
        boolean process = true;
        do {
            int option = displayChildMenu();
            switch (option) {
                case 1:
                    System.out.print("      --- Enter name of file to add : ");
                    String addFile = scanner.next();
                    addFileInDir(addFile);
                    break;
                case 2:
                    System.out.print("      --- Enter name of file to delete : ");
                    String delFile = scanner.next();
                    deleteFileInDir(delFile);
                    break;
                case 3:
                    System.out.print("      --- Enter name of file to search : ");
                    String searchFile = scanner.next();
                    searchFileInDir(searchFile);
                    break;
                case 4:
                    process = false;
                    break;
                default:
                    System.out.print("      --- Enter proper file option..");
                    break;
            }
            System.out.println();
        } while (process);
    }

    private int displayMainMenu() {
        System.out.println("Select from Menu Below : ");
        for (String option : options) {
            System.out.println(option);
        }
        System.out.print("Enter your option : ");
        return scanner.nextInt();
    }

    private int displayChildMenu() {
        System.out.println("    Select file option from Below : ");
        for (String option : fileOptions) {
            System.out.println("    " + option);
        }
        System.out.print("    Enter ur choice : ");
        return scanner.nextInt();
    }

    private void processFilesInDir(File dir) {
        String[] files = dir.list();
        if (files == null || files.length == 0) {
            System.out.println("Directory : " + dir.getAbsolutePath() + " is Empty");
            return;
        }
        System.out.println("---------- FILES START -----------");
        Arrays.sort(files, String::compareTo);
        for (String file : files)
            System.out.println(file);
        System.out.println("---------- FILES END -----------");
    }

    private void addFileInDir(String fileName) {
        fileName = fileName.toLowerCase(Locale.ROOT);
        File newFile = new File(rootDir.getAbsolutePath() + File.separator + fileName);

        try {
            boolean isCreated = newFile.createNewFile();
            if (isCreated)
                System.out.println("File Created with name  : " + fileName);
            else
                System.out.println("File exists with name : " + fileName);
        } catch (IOException e) {
            System.out.println("File Creation failed");
        }
    }

    private void searchFileInDir(String fileNameToSearch) {
        fileNameToSearch = fileNameToSearch.toLowerCase(Locale.ROOT);

        File[] files = rootDir.listFiles();
        if (files == null || files.length == 0) {
            System.out.println(fileNameToSearch + " file NOT FOUND");
            return;
        }

        boolean isAnswerFound = false;
        for (File f : files) {
            if (f.getName().equals(fileNameToSearch)) {
                isAnswerFound = true;
                break;
            }
        }

        if (isAnswerFound)
            System.out.println(fileNameToSearch + " file IS FOUND");
        else
            System.out.println(fileNameToSearch + " file NOT FOUND");

    }

    private void deleteFileInDir(String fileName) {
        fileName = fileName.toLowerCase(Locale.ROOT);
        File toDelete = new File(rootDir.getAbsolutePath() + File.separator + fileName);
        boolean isDeleted = toDelete.delete();
        if (isDeleted)
            System.out.println(fileName + " is Deleted");
        else
            System.out.println(fileName + " is Not Deleted");

    }
}
