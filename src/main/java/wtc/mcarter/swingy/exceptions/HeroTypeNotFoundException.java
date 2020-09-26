package wtc.mcarter.swingy.exceptions;

public class HeroTypeNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public HeroTypeNotFoundException(String type) {
        super("Hero type \"" + type + "\" not found!");
    }
}
