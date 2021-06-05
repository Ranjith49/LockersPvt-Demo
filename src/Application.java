import com.lockers.demo.Directory;
import com.lockers.demo.File;

import java.util.*;

public class Application {

    static Directory mainDirectory;
    static Directory curDirectory;

    private static final String[] optionsSelected = {
            "1. Add File to the Main directory",
            "2. Add File to the Current directory ",
            "3. Add a Child Directory to the Current Directory",
            "4. Move to the Directory 'D' given , Return Not Found [If not present] from root",
            "5. Move currentDir to the Root directory",
            "6. Delete a File from the current Directory",
            "7. Delete a directory from the current directory",
            "8. Search for a file in current directory",
            "9. Search for a file from Root directory",
            "10.Print all files in current Directory -> Ascending",
            "11.Print all files from mainDirectory -> Ascending",
            "12.Exit Option"
    };

    public static void main(String[] args) {
        mainDirectory = new Directory("root");
        curDirectory = mainDirectory;

        addDummyData();
        do {
            System.out.print("\n Enter your choice \t");
            printUserOptions();
            Scanner scanner = new Scanner(System.in);
            int input = scanner.nextInt();
            if (input < 1 || input > optionsSelected.length) {
                continue;
            }

            if (input == 12) {
                break;
            }

            switch (input) {
                case 1:
                    System.out.println("Enter the file name to be inserted for Main Dir");
                    String fileName = scanner.next();
                    addFileInDirectory(mainDirectory, fileName.toLowerCase(Locale.ROOT));
                    break;
                case 2:
                    System.out.print("Enter the file name to be inserted for Current Dir");
                    String currentFileName = scanner.next();
                    addFileInDirectory(curDirectory, currentFileName.toLowerCase(Locale.ROOT));
                    break;
                case 3:
                    System.out.println("Enter the directoryName to be inserted to Current Directory ");
                    String dirName = scanner.next();
                    addChildDirectory(curDirectory, dirName.toLowerCase(Locale.ROOT));
                    break;
                case 4:
                    System.out.println("Enter the directoryName where to go from Main Directory");
                    String toDirName = scanner.next();
                    navigateToDirectory(toDirName.toLowerCase(Locale.ROOT));
                    break;
                case 5:
                    curDirectory = mainDirectory;
                    break;
                case 6:
                    System.out.println("Enter the file name to be deleted from Current Dir");
                    String delFile = scanner.next();
                    deleteFileInDirectory(curDirectory, delFile);
                    break;
                case 7:
                    System.out.println("Enter the directory to be deleted in current Dir");
                    String delDir = scanner.next();
                    deleteDirectoryInDir(curDirectory, delDir);
                    break;
                case 8:
                    System.out.println("Enter the file name to be deleted in currentDir");
                    String searchFile = scanner.next();
                    searchFile(curDirectory, searchFile.toLowerCase(Locale.ROOT), false);
                    break;
                case 9:
                    System.out.println("Enter the file name to be deleted from root");
                    String toSearchFile = scanner.next();
                    searchFile(mainDirectory, toSearchFile.toLowerCase(Locale.ROOT), true);
                    break;
                case 10:
                    ArrayList<File> files = new ArrayList<>(curDirectory.getFiles());
                    Collections.sort(files);
                    System.out.println("Files in Directory :-> " + curDirectory.getName());
                    for (File file : files) {
                        System.out.print(file.getName() + "\t");
                    }
                    System.out.println("END");
                    break;
                case 11:
                    ArrayList<File> allFiles = new ArrayList<>();
                    LinkedList<Directory> current = new LinkedList<>();
                    current.add(mainDirectory);

                    while (!current.isEmpty()) {
                        Directory curDir = current.removeFirst();
                        current.addAll(curDir.getChildDirectories());
                        allFiles.addAll(curDir.getFiles());
                    }
                    Collections.sort(allFiles);
                    System.out.println("Total Files in Main Directory :-> root");
                    for (File file : allFiles) {
                        System.out.print(file.getName() + "\t");
                    }
                    System.out.println("END");

                    break;
            }
        } while (true);
    }

    private static void printUserOptions() {
        for (String option : optionsSelected) {
            System.out.println(option);
        }
    }

    private static void addFileInDirectory(Directory directory, String fileName) {
        try {
            File newFile = new File(fileName);
            directory.addFileInDirectory(newFile);
        } catch (IllegalStateException | IllegalArgumentException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    private static void addChildDirectory(Directory directory, String directoryName) {
        try {
            directory.addChildDirectory(directoryName);
        } catch (IllegalStateException | IllegalArgumentException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    private static void deleteFileInDirectory(Directory directory, String fileName) {
        Iterator<File> files = directory.getFiles().listIterator();
        boolean isFileRemoved = false;

        while (files.hasNext()) {
            File next = files.next();
            if (next.getName().equals(fileName)) {
                files.remove();
                isFileRemoved = true;
                break;
            }
        }

        if (isFileRemoved)
            System.out.println("File name : " + fileName + " is deleted");
        else
            System.out.println("File name : " + fileName + " is not found");
    }

    private static void deleteDirectoryInDir(Directory directory, String dirName) {
        Iterator<Directory> directories = directory.getChildDirectories().listIterator();
        boolean isDirRemoved = false;

        while (directories.hasNext()) {
            Directory dir = directories.next();
            if (dir.getName().equals(dirName)) {
                directories.remove();
                isDirRemoved = true;
            }
        }


        if (isDirRemoved)
            System.out.println("Directory name : " + dirName + " is deleted");
        else
            System.out.println("Directory name : " + dirName + " is not found");
    }

    private static void navigateToDirectory(String nameofDirectory) {
        LinkedList<Directory> directories = new LinkedList<>();
        directories.add(mainDirectory);

        boolean isDirFound = false;
        while (!directories.isEmpty()) {
            Directory cur = directories.removeFirst();
            if (cur.getName().equals(nameofDirectory)) {
                isDirFound = true;
                curDirectory = cur;
                break;
            }
            directories.addAll(cur.getChildDirectories());
        }

        if (!isDirFound) {
            System.out.println("Not able to find directory from root :-> " + nameofDirectory);
        }
    }

    private static void searchFile(Directory directory, String fileName, boolean canNavigateDown) {
        boolean isFileFound = false;
        File toSearch = new File(fileName);

        LinkedList<Directory> directories = new LinkedList<>();
        directories.add(directory);
        while (!directories.isEmpty()) {
            Directory cur = directories.removeFirst();
            List<File> curFiles = cur.getFiles();
            if (curFiles.contains(toSearch)) {
                isFileFound = true;
                break;
            }

            if (canNavigateDown) {
                directories.addAll(cur.getChildDirectories());
            }
        }

        if (isFileFound) {
            System.out.println("File with " + fileName + " + Found");
        } else {
            System.out.println("File with " + fileName + " + Not found");
        }
    }


    private static void addDummyData() {
        mainDirectory.addChildDirectory("D1");
        mainDirectory.addChildDirectory("D2");
        mainDirectory.addFileInDirectory(new File("F1"));
        mainDirectory.addFileInDirectory(new File("F2"));
    }
}
