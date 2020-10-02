package wtc.mcarter.swingy.controller;

import javax.swing.JDialog;
import javax.swing.UIManager;

import wtc.mcarter.swingy.Main;
import wtc.mcarter.swingy.model.artifacts.Artifact;
import wtc.mcarter.swingy.model.characters.Hero;
import wtc.mcarter.swingy.model.characters.Villain;
import wtc.mcarter.swingy.util.Algos;
import wtc.mcarter.swingy.util.ArtifactFactory;
import wtc.mcarter.swingy.view.StartGame;

public class GamePlayController {
    private String mode = "console";

    public void SetConsole() {
        mode = "console";
    }

    public void SetGUI() {
        mode = "gui";
    }

    public void Start() {
        do {
            if (mode == "console") {
                mode = "";
                Main.logger.logMessage("Starting Console mode");

                ConsoleModeController consoleModeController = new ConsoleModeController(this);
                consoleModeController.Start();
            } else if (mode == "gui") {
                mode = "";
                Main.logger.logMessage("Starting GUI mode");

                UIManager.put("swing.boldMetal", Boolean.FALSE);
                JDialog frame = new StartGame(this);
                frame.setVisible(true); // on a JDialog, this will block until window is disposed
            }
        } while (mode != "");
    }

    public Artifact SimulateFight(Hero hero, Villain villain) {
        Main.logger.logMessage("Simulating fight between hero %s and villain %s", hero.getClass().getSimpleName(), villain.getClass().getSimpleName());

        while (hero.getHp() > 0 && villain.getHp() > 0) {
            if (Algos.getDoesWeaponHit(hero.getWeapon())) {
                Main.logger.logMessage("Hero weapon hits. Enemy hp:%s Hero damage:%s", villain.getHp(), hero.getDamage());
                villain.setHp(villain.getHp() - hero.getDamage());
                Main.logger.logMessage("New enemy hp:" + villain.getHp());
            }

            if (villain.getHp() > 0) {
                int dmg = Algos.getDamage(villain.getDamage(), hero.getDefense());
                Main.logger.logMessage("Enemy still alive. Hero hp:%s Enemy damage:%s", hero.getHp(), dmg);
                hero.setHp(hero.getHp() - dmg);
                Main.logger.logMessage("New hero hp:" + hero.getHp());
            }
        }

        if (hero.getHp() > 0) {
            return ArtifactFactory.generate();
        } else {
            return null;
        }
    }
}
