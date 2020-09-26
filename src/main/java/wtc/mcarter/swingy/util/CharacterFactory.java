package wtc.mcarter.swingy.util;

import wtc.mcarter.swingy.exceptions.HeroTypeNotFoundException;
import wtc.mcarter.swingy.exceptions.WeaponTypeNotFoundException;
import wtc.mcarter.swingy.model.characters.Elf;
import wtc.mcarter.swingy.model.characters.Hero;
import wtc.mcarter.swingy.model.characters.Knight;

public class CharacterFactory {
    public static enum HeroTypes {
        Elf, Knight
    }

    public static Hero newHero(String type, String name) throws HeroTypeNotFoundException {
        try {
            HeroTypes heroType = HeroTypes.valueOf(type);
            Hero hero;

            switch (heroType) {
                case Elf:
                    hero = new Elf(name);
                    break;
                case Knight:
                    hero = new Knight(name);
                    break;
                default:
                    throw new HeroTypeNotFoundException(type);
            }

            hero.setArmor(ArmorFactory.newArmor(0));
            try { hero.setWeapon(WeaponFactory.newWeapon("Fist")); } catch (WeaponTypeNotFoundException e) {}
            return hero;
        } catch (IllegalArgumentException e) {
            throw new HeroTypeNotFoundException(type);
        }
    }
}
