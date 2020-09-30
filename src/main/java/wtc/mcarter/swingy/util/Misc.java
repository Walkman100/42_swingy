package wtc.mcarter.swingy.util;

import wtc.mcarter.swingy.Main;
import wtc.mcarter.swingy.model.characters.Hero;

public class Misc {
    public static String getInput() {
        return getInput(System.lineSeparator() + "> ");
    }

    public static String getInput(String beforeRead) {
        Main.logger.write(beforeRead);
        return System.console().readLine();
    }

    public static void addXP(Hero hero, int amount) {
        hero.setExperience(hero.getExperience() + amount);
        hero.setLevel(Algos.getLevel(hero.getExperience()));
    }
}
