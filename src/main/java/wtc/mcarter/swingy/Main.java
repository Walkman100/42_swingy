package wtc.mcarter.swingy;

import java.io.IOException;
import java.util.Arrays;

import wtc.mcarter.swingy.exceptions.HeroTypeNotFoundException;
import wtc.mcarter.swingy.exceptions.WeaponTypeNotFoundException;
import wtc.mcarter.swingy.storage.HeroStorage;
import wtc.mcarter.swingy.util.Logger;

public class Main {
    public static Logger logger;

    private static void printHelpText() {
        System.out.println("Swingy (https://github.com/Walkman100/42_swingy)" + System.lineSeparator());
        System.out.println("Command Line Arguments:");
        System.out.println("    console               Use Console game mode (default)");
        System.out.println("    gui                   Use GUI game mode. Uses the SWING library");
        System.out.println("    --log <file>          Sets log file");
        System.out.println("    --heroStore <file>    Sets file to read and write heroes to and from");
        System.out.println("    --help                Prints this help text");
    }

    public static void main(String[] args) {
        String heroStorageFile = "heroes.txt";
        String loggerFile = "log.txt";
        String waitingFor = "";

        for (String string : args) {
            switch (waitingFor) {
                case "":
                    switch (string) {
                        case "--log":
                            waitingFor = "log";
                            break;
                        case "--heroStore":
                            waitingFor = "heroFile";
                            break;
                        case "--help":
                        case "-help":
                        case "-h":
                        case "/h":
                            printHelpText();
                            System.exit(0);
                    }
                    break;
                case "log":
                    waitingFor = "";
                    loggerFile = string;
                    break;
                case "heroFile":
                    waitingFor = "";
                    heroStorageFile = string;
                    break;
            }
        }

        try {
            logger = new Logger(loggerFile);
        } catch (IOException e) {
            System.out.println("Couldn't create log file! Use --log parameter to set log file");
            System.exit(1);
        }

        logger.logMessage("SWINGY by mcarter BOOT");
        logger.logMessage("https://github.com/Walkman100/42_swingy");
        logger.logMessage("Arguments:");
        logger.logMessage(Arrays.toString(args));

        try {
            HeroStorage.LoadHeroes(heroStorageFile);
        } catch (NumberFormatException | IOException | HeroTypeNotFoundException | WeaponTypeNotFoundException e) {
            logger.logMessage("Could not load heroes: " + e.getMessage(), true);
        }



        try {
            HeroStorage.SaveHeroes(heroStorageFile);
        } catch (IOException e) {
            logger.logMessage("Could not save heroes: " + e.getMessage(), true);
        }
        logger.close();
    }
}
