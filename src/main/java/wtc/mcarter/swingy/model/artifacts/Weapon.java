package wtc.mcarter.swingy.model.artifacts;

import lombok.Getter;
import wtc.mcarter.swingy.model.characters.Hero;

public class Weapon implements Artifact {
    @Getter
    private WeaponType type;

    public Weapon(WeaponType weaponType) {
        this.type = weaponType;
    }

    public String getEffect() {
        return "Damage: " + getDamage();
    }

    public void setToHero(Hero hero) {
        hero.setWeapon(this);
    }

    public Boolean isRanged() {
        return type == WeaponType.CrossBow || type == WeaponType.LongBow;
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
