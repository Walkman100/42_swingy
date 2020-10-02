package wtc.mcarter.swingy.view;

import wtc.mcarter.swingy.model.artifacts.Artifact;
import wtc.mcarter.swingy.model.characters.Hero;
import wtc.mcarter.swingy.model.characters.Villain;

public interface WindowManager {
    void showSelectHero();
    void showNewHero();
    void showGame(Hero hero);
    void setConsole();
    Artifact SimulateFight(Hero hero, Villain villain);
}
