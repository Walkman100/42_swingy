package wtc.mcarter.swingy.util;

import wtc.mcarter.swingy.model.artifacts.Armor;
import wtc.mcarter.swingy.model.artifacts.Artifact;
import wtc.mcarter.swingy.model.artifacts.Helm;
import wtc.mcarter.swingy.model.artifacts.Weapon;
import wtc.mcarter.swingy.model.artifacts.WeaponType;

public class ArtifactFactory {
    public static Artifact generate() {
        boolean dropped = Algos.getArtifactDrop();
        if (dropped == false)
            return null;

        int droppedType = Algos.getRandom(0, 2);
        switch (droppedType) {
            case 0:
                return new Weapon(WeaponType.values()[Algos.getRandom(0, WeaponType.values().length)]);
            case 1:
                return new Armor(Algos.getRandom(2, 10));
            case 2:
                return new Helm(Algos.getRandom(2, 10));
            default:
                return null;
        }
    }
}
