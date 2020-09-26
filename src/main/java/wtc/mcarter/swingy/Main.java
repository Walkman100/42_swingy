package wtc.mcarter.swingy;

import java.io.IOException;

import wtc.mcarter.swingy.exceptions.HeroTypeNotFoundException;
import wtc.mcarter.swingy.exceptions.WeaponTypeNotFoundException;
import wtc.mcarter.swingy.storage.HeroStorage;
import wtc.mcarter.swingy.util.Logger;

public class Main {
    public static Logger logger;

    public static void main(String[] args) {
        String heroStorageFile = "heroes.txt";
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
