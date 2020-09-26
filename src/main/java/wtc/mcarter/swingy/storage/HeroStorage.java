package wtc.mcarter.swingy.storage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import lombok.Getter;
import wtc.mcarter.swingy.Main;
import wtc.mcarter.swingy.exceptions.HeroTypeNotFoundException;
import wtc.mcarter.swingy.exceptions.WeaponTypeNotFoundException;
import wtc.mcarter.swingy.model.characters.Hero;
import wtc.mcarter.swingy.util.ArmorFactory;
import wtc.mcarter.swingy.util.CharacterFactory;
import wtc.mcarter.swingy.util.WeaponFactory;

public class HeroStorage {
    @Getter
    private static ArrayList<Hero> heroList = new ArrayList<>();

    public static void LoadHeroes(String fileName) throws FileNotFoundException, IOException, NumberFormatException, HeroTypeNotFoundException, WeaponTypeNotFoundException {
        Main.logger.logMessage("Loading heroes...");
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while ((line = reader.readLine()) != null) {
                String[] lineSegments = line.split(",");
                Hero hero = CharacterFactory.newHero(lineSegments[1], lineSegments[0]);

                hero.setLevel(Integer.parseInt(lineSegments[2]));
                hero.setExperience(Integer.parseInt(lineSegments[3]));
                hero.setHp(Integer.parseInt(lineSegments[4]));
                hero.setWeapon(WeaponFactory.newWeapon(lineSegments[5]));
                hero.setArmor(ArmorFactory.newArmor(Integer.parseInt(lineSegments[6])));

                heroList.add(hero);
            }
        }
        Main.logger.logMessage("Loaded " + heroList.size() + " heroes.");
    }

    public static void SaveHeroes(String fileName) throws IOException {
        Main.logger.logMessage("Saving heroes...");
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            for (Hero hero : heroList) {
                fileWriter.write(hero.getName() +
                        "," + hero.getClass().getSimpleName() +
                        "," + hero.getLevel() +
                        "," + hero.getExperience() +
                        "," + hero.getHp() +
                        "," + hero.getWeapon().getType().name() +
                        "," + hero.getArmor().getDefense());

                fileWriter.write(System.lineSeparator());
            }
        }
        Main.logger.logMessage("Saved " + heroList.size() + " heroes.");
    }

    public static void AddHero(Hero hero) {
        Main.logger.logMessage("Adding hero...");
        heroList.add(hero);
    }

    public static void RemoveHero(Hero hero) {
        Main.logger.logMessage("Removing hero...");
        heroList.remove(hero);
    }
}
