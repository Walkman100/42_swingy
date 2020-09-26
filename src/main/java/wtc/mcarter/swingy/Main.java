package wtc.mcarter.swingy;

import java.io.IOException;

import wtc.mcarter.swingy.util.Logger;

public class Main {
    public static Logger logger;

    public static void main(String[] args) {
        String loggerFile = "log.txt";

        try {
            logger = new Logger(loggerFile);
        } catch (IOException e) {
            System.out.println("Couldn't create log file!");
            System.exit(1);
        }

        logger.logMessage("SWINGY by mcarter BOOT");
        logger.logMessage("https://github.com/Walkman100/42_swingy");
        logger.logMessage("Arguments:");
        logger.logMessage(Arrays.toString(args));

        logger.close();
    }
}
