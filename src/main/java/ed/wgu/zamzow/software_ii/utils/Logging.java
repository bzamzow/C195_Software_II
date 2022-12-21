package ed.wgu.zamzow.software_ii.utils;

import java.io.*;
import java.util.Scanner;

public class Logging {
    private static final String LOG_LOCATION = "./login_activity.txt";

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

    private static boolean FileExists() {
        File logFile = new File(LOG_LOCATION);
        return logFile.exists();
    }

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
