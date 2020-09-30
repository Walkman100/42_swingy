package wtc.mcarter.swingy.controller;

import wtc.mcarter.swingy.Main;
import wtc.mcarter.swingy.exceptions.HeroTypeNotFoundException;
import wtc.mcarter.swingy.model.Game;
import wtc.mcarter.swingy.model.artifacts.Artifact;
import wtc.mcarter.swingy.model.characters.BlackMage;
import wtc.mcarter.swingy.model.characters.Hero;
import wtc.mcarter.swingy.model.characters.Orc;
import wtc.mcarter.swingy.model.characters.Villain;
import wtc.mcarter.swingy.storage.HeroStorage;
import wtc.mcarter.swingy.util.Algos;
import wtc.mcarter.swingy.util.Misc;
import wtc.mcarter.swingy.util.PadStr;
import wtc.mcarter.swingy.util.CharacterFactory.HeroTypes;

public class ConsoleModeController {
    private GamePlayController gamePlayController;
    private Game game;
    private boolean shouldQuit;

    private void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public ConsoleModeController(GamePlayController gamePlayController) {
        this.gamePlayController = gamePlayController;
    }

    public void Start() {
        game = new Game();
        Hero hero = getHeroSelection();
        if (hero == null)
            return;

        game.posX = 0;
        game.posY = 0;
        shouldQuit = false;

        while (hero.getHp() > 0 && shouldQuit == false) {
            showLevel(hero);
        }
        if (shouldQuit)
            return;

        HeroStorage.RemoveHero(hero);
        if (showHeroDied() == true)
            gamePlayController.SetConsole();
    }

    private Hero getHeroSelection() {
        clear();
        Main.logger.logMessage("Showing hero selection...");
        Main.logger.writeLine("Choose a character - enter the number and press enter. Use 'n' to create a new character, and 'e', 'd' or 'q' to exit" + System.lineSeparator());

        Main.logger.writeLine("  # | Name                 | Class  | Level |  XP | ATK | DEF |  HP |");
        for (int i = 0; i < HeroStorage.getHeroList().size(); i++) {
            Hero hero = HeroStorage.getHeroList().get(i);
            Main.logger.writeLine(PadStr.padLeft(i, 3) + " | " +
                    PadStr.padRight(hero.getName(), 21) + "| " +
                    PadStr.padRight(hero.getClass().getSimpleName(), 7) + "| " +
                    PadStr.padRight(hero.getLevel(), 6) + "| " +
                    PadStr.padLeft(hero.getExperience(), 3) + " | " +
                    PadStr.padLeft(hero.getDamage(), 3) + " | " +
                    PadStr.padLeft(hero.getDefense(), 3) + " | " +
                    PadStr.padLeft(hero.getHp(), 3) + " |");
        }

        if (HeroStorage.getHeroList().size() == 0) {
            Main.logger.writeLine("No characters found.");
        }

        String input = Misc.getInput();
        Main.logger.logMessage("Got selection input: " + input);

        if (input.toLowerCase().equals("n")) {
            showCreateHero();
            return getHeroSelection();
        } else if (input.toLowerCase().equals("e") || input.toLowerCase().equals("d") || input.toLowerCase().equals("q")) {
            return null;
        }

        try {
            int sel = Integer.parseInt(input);
            return HeroStorage.getHeroList().get(sel);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            Misc.getInput("Invalid input! ");
            return getHeroSelection();
        }
    }

    private void showCreateHero() {
        clear();
        Main.logger.logMessage("Showing hero creation...");
        Main.logger.writeLine("Choose hero type:" + System.lineSeparator());

        int sel = 0;
        for (HeroTypes heroType : HeroTypes.values()) {
            Main.logger.writeLine(sel + " " + heroType.name());
            sel++;
        }

        String input = Misc.getInput();
        Main.logger.logMessage("Got new hero type input: " + input);

        HeroTypes heroType;
        try {
            sel = Integer.parseInt(input);
            heroType = HeroTypes.values()[sel];
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            Misc.getInput("Invalid input! ");
            showCreateHero();
            return;
        }

        Main.logger.writeLine(System.lineSeparator() + "Enter hero name:");
        input = Misc.getInput();
        Main.logger.logMessage("Got new hero name input: " + input);

        try {
            new CharacterController().newHero(heroType.name(), input);
        } catch (HeroTypeNotFoundException e) {}
    }

    private void showLevel(Hero hero) {
        clear();
        Main.logger.logMessage("Showing game level. X:" + game.posX + " Y:" + game.posY);
        Main.logger.writeLine("Swingy\t" + hero.getName() + ":" + hero.getClass().getSimpleName() + "\tlv" + hero.getLevel() + "\txp" + hero.getExperience());
        Main.logger.writeLine("HP: " + hero.getHp() + "\tAtk: " + hero.getDamage() + "\tDef: " + hero.getDefense() + System.lineSeparator());

        // render
        game.gameSize = Algos.getGameSize(hero.getLevel());
        for (int y = -(game.gameSize / 2); y <= game.gameSize / 2; y++) {
            for (int x = -(game.gameSize / 2); x <= game.gameSize / 2; x++) {
                if (x == game.posX && y == game.posY)
                    Main.logger.write("XX");
                else
                    Main.logger.write("▒▒");
            }
            Main.logger.writeLine();
        }

        // take user input
        Main.logger.write(System.lineSeparator() + "Enter move command (l/r/u/d/n/e/s/w, q):");
        String input = Misc.getInput();
        Main.logger.logMessage("Got movement input: " + input);

        // action on moved to square
        switch (input.toLowerCase()) {
            case "l":
            case "w":
                Main.logger.writeLine("Moving Left...");
                tryMove(hero, game.posX - 1, game.posY);
                return;
            case "r":
            case "e":
                Main.logger.writeLine("Moving Right...");
                tryMove(hero, game.posX + 1, game.posY);
                return;
            case "u":
            case "n":
                Main.logger.writeLine("Moving Up...");
                tryMove(hero, game.posX, game.posY - 1);
                return;
            case "d":
            case "s":
                Main.logger.writeLine("Moving Down...");
                tryMove(hero, game.posX, game.posY + 1);
                return;
            case "q":
                shouldQuit = true;
                return;
            default:
                Misc.getInput("Invalid input! ");
                return;
        }
    }

    private void tryMove(Hero hero, int x, int y) {
        if (x < -(game.gameSize / 2) || y < -(game.gameSize / 2) || x > game.gameSize / 2 || y > game.gameSize / 2) {
            Main.logger.logMessage("Invalid location to move to. Aborted.");
            Main.logger.write("Invalid location! ");
        } else if (Algos.getEncounteredEnemy()) {
            Main.logger.logMessage("Found enemy!");
            Villain villain;
            if (Algos.getRandom(0, 1) == 1)
                villain = new BlackMage();
            else
                villain = new Orc();
            Main.logger.logMessage("Enemy type: " + villain.getClass().getSimpleName());

            Main.logger.write("Encountered enemy " + villain.getClass().getSimpleName() + "! Attempt to run (y/*)?");
            String input = Misc.getInput();
            Main.logger.logMessage("Got run input: " + input);

            if (input.toLowerCase().equals("y")) {
                if (Algos.getMustFight()) {
                    Main.logger.logMessage("Run failed. Continuing with fight...");
                    Main.logger.writeLine("You were caught!");
                } else {
                    Main.logger.logMessage("Run back successful.");
                    Misc.getInput("Ran back successfully. ");
                    return;
                }
            }

            Main.logger.writeLine("Fighting enemy " + villain.getClass().getSimpleName() + "...");
            Artifact droppedArtifact = gamePlayController.SimulateFight(hero, villain);

            if (droppedArtifact != null) {
                Main.logger.logMessage("Enemy defeated, artifact dropped: " + droppedArtifact.getClass().getSimpleName() + " Effect: " + droppedArtifact.getEffect());

                Main.logger.write("Defeated enemy dropped an artifact. Type: " + droppedArtifact.getClass().getSimpleName() + " Effect: " + droppedArtifact.getEffect() + ". Equip (y/*)?");
                input = Misc.getInput();
                Main.logger.logMessage("Got pick up artifact input: " + input);

                if (input.toLowerCase().equals("y")) {
                    droppedArtifact.setToHero(hero);
                }
            } else if (villain.getHp() <= 0) {
                Main.logger.logMessage("Enemy defeated, no artifact dropped.");
                Main.logger.write("Defeated enemy! ");
            }

            if (villain.getHp() <= 0) {
                Main.logger.logMessage("Moved to X:" + x + " Y:" + y);
                Main.logger.write("Moved to new location. ");
                game.posX = x;
                game.posY = y;
            } else if (hero.getHp() <= 0) {
                return; // skip readLine at the end
            }
        } else {
            Main.logger.logMessage("Nothing found at target. Moved to X:" + x + " Y:" + y);
            Main.logger.write("Moved! ");
            game.posX = x;
            game.posY = y;
        }
        System.console().readLine();
    }

    private boolean showHeroDied() {
        clear();
        Main.logger.logMessage("Hero died.");
        Main.logger.write("You died! Start again (y/*)?");
        String input = Misc.getInput();
        Main.logger.logMessage("Got retry input: " + input);

        return input.toLowerCase().equals("y");
    }
}
