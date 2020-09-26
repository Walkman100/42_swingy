package wtc.mcarter.swingy.model.artifacts;

import lombok.Getter;

public class Weapon {
    @Getter
    private WeaponType type;

    public Weapon(WeaponType weaponType) {
        this.type = weaponType;
    }

    public Boolean isRanged() {
        if (type == WeaponType.CrossBow || type == WeaponType.LongBow)
            return true;
        else
            return false;
    }

    public int getDamage() {
        switch (type) {
            case Fist:
                return 0;
            case ShortSword:
                return 1;
            case LongBow:
                return 2;
            case Rapier:
                return 2;
            case LongSword:
                return 5;
            case BroadSword:
                return 10;
            case CrossBow:
                return 10;
            case Cleaver:
                return 20;
            default:
                return -1;
        }
    }

    public int getHitChance() {
        switch (type) {
            case Fist:
                return 90;
            case ShortSword:
                return 95;
            case LongBow:
                return 90;
            case Rapier:
                return 90;
            case LongSword:
                return 80;
            case BroadSword:
                return 70;
            case CrossBow:
                return 70;
            case Cleaver:
                return 50;
            default:
                return -1;
        }
    }
}
