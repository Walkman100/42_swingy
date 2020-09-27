package wtc.mcarter.swingy.util;

import wtc.mcarter.swingy.Main;

public class Misc {
    public static String getInput() {
        return getInput(System.lineSeparator() + "> ");
    }

    public static String getInput(String beforeRead) {
        Main.logger.write(beforeRead);
        return System.console().readLine();
    }
}
