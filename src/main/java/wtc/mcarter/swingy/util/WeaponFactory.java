package wtc.mcarter.swingy.util;

import wtc.mcarter.swingy.exceptions.WeaponTypeNotFoundException;
import wtc.mcarter.swingy.model.artifacts.Weapon;
import wtc.mcarter.swingy.model.artifacts.WeaponType;

public class WeaponFactory {
    public static Weapon newWeapon(String type) throws WeaponTypeNotFoundException {
        switch (type) {
            case "Fist":
                return new Weapon(WeaponType.Fist);
            case "ShortSword":
            case "Short Sword":
                return new Weapon(WeaponType.ShortSword);
            case "LongSword":
            case "Long Sword":
                return new Weapon(WeaponType.LongSword);
            case "BroadSword":
            case "Broad Sword":
                return new Weapon(WeaponType.BroadSword);
            case "Cleaver":
                return new Weapon(WeaponType.Cleaver);
            case "Rapier":
                return new Weapon(WeaponType.Rapier);
            case "CrossBow":
            case "Cross Bow":
                return new Weapon(WeaponType.CrossBow);
            case "LongBow":
            case "Long Bow":
                return new Weapon(WeaponType.LongBow);
            default:
                throw new WeaponTypeNotFoundException(type);
        }
    }
}
