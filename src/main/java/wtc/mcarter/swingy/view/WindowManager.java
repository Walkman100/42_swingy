package wtc.mcarter.swingy.view;

import wtc.mcarter.swingy.model.characters.Hero;

public interface WindowManager {
    void showSelectHero();
    void showNewHero();
    void showSelectMission(Hero hero);
}