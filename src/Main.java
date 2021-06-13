import com.lockers.file.LockersApp;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Application name : File Manager - Lockers");
        System.out.println("Developer : RANJITH SUDA ; Email : suda.ranjith@gmail.com");

        System.out.println("********************** Application Start *******************");

        LockersApp lockersApp = new LockersApp();
        lockersApp.runApplication();

        System.out.println("*********************** Application Exit ******************* ");
    }
}
