package wtc.mcarter.swingy.controller;

import wtc.mcarter.swingy.model.characters.Hero;

public class ConsoleModeController {
    private void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void Start(GamePlayController gamePlayController) {
        Hero hero = getHeroSelection();
        if (hero == null)
            return;

        do {
            showLevel(hero);
        } while (hero.getHp() > 0);

        if (showHeroDied() == true)
            gamePlayController.SetConsole();
    }

    private Hero getHeroSelection() {
        clear();
    }

    private void showLevel(Hero hero) {
        clear();
    }

    private boolean showHeroDied() {
        clear();
        return false;
    }
}
