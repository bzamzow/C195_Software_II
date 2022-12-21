package ed.wgu.zamzow.software_ii.controllers;

import ed.wgu.zamzow.software_ii.utils.Logging;
import javafx.scene.control.TextArea;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadmeViewController {

    private static final String FILE_LOCATION = "./README.txt";

    public TextArea txtOutput;

    public void initialize() {
        txtOutput.setText(ReadMe());
    }

    public static String ReadMe() {
        String file = "";
        if (FileExists()) {
            File logFile = new File(FILE_LOCATION);
            try {
                Scanner reader = new Scanner(logFile);
                while (reader.hasNextLine()) {
                    file += reader.nextLine() + "\n";
                }
                reader.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return file;
    }

    private static boolean FileExists() {
        File logFile = new File(FILE_LOCATION);
        return logFile.exists();
    }
}