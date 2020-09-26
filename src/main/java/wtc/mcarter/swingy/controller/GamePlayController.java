package wtc.mcarter.swingy.controller;

import wtc.mcarter.swingy.Main;

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

                ConsoleModeController consoleModeController = new ConsoleModeController();
                consoleModeController.Start();
            } else if (mode == "gui") {
                mode = "";
                Main.logger.logMessage("Starting GUI mode");
            }
        } while (mode != "");
    }
}
