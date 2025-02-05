package wtc.mcarter.swingy.util;

import java.io.FileWriter;
import java.io.IOException;

public class Logger {
    private FileWriter fileWriter;

    public Logger(String logFilePath) throws IOException {
        fileWriter = new FileWriter(logFilePath);
    }

    public void close() {
        try {
            fileWriter.close();
        } catch (IOException e) {
            writeLine("There was an error closing the log file!");
        }
    }

    public void writeLine() {
        System.out.println();
    }

    public void writeLine(String line, Object... args) {
        line = String.format(line, args);
        System.out.println(line);
    }

    public void write(String text, Object... args) {
        text = String.format(text, args);
        System.out.print(text);
    }

    public void logMessage(String message, Boolean print) {
        try {
            fileWriter.write(message);
            fileWriter.write(System.lineSeparator());
            fileWriter.flush();
            if (print)
                writeLine(message);
        } catch (IOException e) {
            writeLine("Error writing to log file:");
            e.printStackTrace();
        }
    }

    public void logMessage(String message, Object... args) {
        message = String.format(message, args);
        logMessage(message, false);
    }
}
