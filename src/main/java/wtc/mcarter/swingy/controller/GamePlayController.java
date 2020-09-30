package wtc.mcarter.swingy.controller;

import wtc.mcarter.swingy.Main;
import wtc.mcarter.swingy.model.characters.Hero;
import wtc.mcarter.swingy.model.characters.Villain;
import wtc.mcarter.swingy.util.Algos;

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
            }
        } while (mode != "");
    }

    public void SimulateFight(Hero hero, Villain villain) {
        Main.logger.logMessage("Simulating fight between hero " + hero.getClass().getSimpleName() + " and villain " + villain.getClass().getSimpleName());

        while (hero.getHp() > 0 && villain.getHp() > 0) {
            if (Algos.getDoesWeaponHit(hero.getWeapon())) {
                Main.logger.logMessage("Hero weapon hits. Enemy hp:" + villain.getHp() + " Hero damage:" + hero.getDamage());
                villain.setHp(villain.getHp() - hero.getDamage());
                Main.logger.logMessage("New enemy hp:" + villain.getHp());
            }

            if (villain.getHp() > 0) {
                int dmg = Algos.getDamage(villain.getDamage(), hero.getDefense());
                Main.logger.logMessage("Enemy still alive. Hero hp:" + hero.getHp() + " Enemy damage:" + dmg);
                hero.setHp(hero.getHp() - dmg);
                Main.logger.logMessage("New hero hp:" + hero.getHp());
            }
        }
    }
}
