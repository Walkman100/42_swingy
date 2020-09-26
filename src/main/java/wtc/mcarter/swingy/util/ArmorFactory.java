package wtc.mcarter.swingy.util;

import wtc.mcarter.swingy.model.artifacts.Armor;

public class ArmorFactory {
    public static Armor newArmor(int defense) {
        return new Armor(defense);
    }
}
