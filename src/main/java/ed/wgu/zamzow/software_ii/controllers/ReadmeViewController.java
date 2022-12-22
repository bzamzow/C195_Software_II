package ed.wgu.zamzow.software_ii.controllers;

import javafx.scene.control.TextArea;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Controller to view the readme file as an about
 *
 * @author Bret Zamzow
 */
public class ReadmeViewController {

    private static final String FILE_LOCATION = "./README.txt";

    public TextArea txtOutput;

    /**
     * Method to initialize the main form
     */
    public void initialize() {
        txtOutput.setText(ReadMe());
    }

    /**
     * Method to read the readme file so that it can be displayed in the form
     * @return ReadMe information
     */
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

    /**
     * Method to check if the file exists and can be read
     * @return True/False based on file existence
     */
    private static boolean FileExists() {
        File logFile = new File(FILE_LOCATION);
        return logFile.exists();
    }
}