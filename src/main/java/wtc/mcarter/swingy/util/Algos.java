package wtc.mcarter.swingy.util;

import java.util.concurrent.ThreadLocalRandom;

import wtc.mcarter.swingy.model.artifacts.Weapon;

public class Algos {
    public static int getGameSize(int level) {
        return (level - 1) * 5 + 10 - (level % 2);
    }

    public static int getXPRequired(int level) {
        return level * 1000 + (int)Math.pow(level - 1, 2) * 450;
    }

    public static int getLevel(int xp) {
        if (xp <= 450)
            return 0;

        int level = 0;
        while (getXPRequired(level) <= xp) {
            level++;
        }

        return level - 1;
    }

    public static int getRandom(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static boolean getEncounteredEnemy() {
        return getRandom(0, 3) == 3;
    }

    public static boolean getMustFight() {
        return getRandom(0, 1) == 1;
    }

    public static boolean getDoesWeaponHit(Weapon weapon) {
        return getRandom(0, 100) <= weapon.getHitChance();
    }

    public static int getDamage(int atk, int def) {
        // https://gamedev.stackexchange.com/a/129322/88288
        return atk * atk / (atk + def);
    }
}
