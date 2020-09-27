package wtc.mcarter.swingy.util;

import java.util.concurrent.ThreadLocalRandom;

public class Algos {
    public static int getGameSize(int level) {
        return (level - 1) * 5 + 10 - (level % 2);
    }
}
