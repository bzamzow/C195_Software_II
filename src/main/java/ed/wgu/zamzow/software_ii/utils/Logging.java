package ed.wgu.zamzow.software_ii.utils;

import java.io.*;
import java.util.Scanner;

/**
 * Class that allows for reading and writing of logs
 *
 * @author Bret Zamzow
 */
public class Logging {
    private static final String LOG_LOCATION = "./login_activity.txt";

    /**
     * Method to add an entry to the log file
     * @param timestamp
     * @param successful
     * @param message
     */
    public static void AddToLog(String timestamp, String successful, String message) {
        try {
            CreateFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_LOCATION, true));
            writer.append(timestamp + " : " + successful + " : " + message + "\n");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method to read the log file
     * @return
     */
    public static String ReadLog() {
        String logs = "";
        if (FileExists()) {
            File logFile = new File(LOG_LOCATION);
            try {
                Scanner reader = new Scanner(logFile);
                while (reader.hasNextLine()) {
                    logs += reader.nextLine() + "\n";
                }
                reader.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return logs;
    }

    /**
     * Method to check if the log file exists
     * @return
     */
    private static boolean FileExists() {
        File logFile = new File(LOG_LOCATION);
        return logFile.exists();
    }

    /**
     * Method to create the log file if it doesn't exist
     */
    private static void CreateFile() {
        File logFile = new File(LOG_LOCATION);
        if (!FileExists()) {
            try {
                logFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
