package wtc.mcarter.swingy.controller;

import wtc.mcarter.swingy.Main;
import wtc.mcarter.swingy.model.characters.Hero;
import wtc.mcarter.swingy.model.characters.Villain;

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
    }
}
