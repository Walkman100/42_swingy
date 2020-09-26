package wtc.mcarter.swingy.exceptions;

public class WeaponTypeNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public WeaponTypeNotFoundException(String type) {
        super("Weapon type \"" + type + "\" not found!");
    }
}
