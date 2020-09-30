package wtc.mcarter.swingy.model.artifacts;

import lombok.Getter;
import wtc.mcarter.swingy.model.characters.Hero;

public class Helm implements Artifact {
    @Getter
    private int hpBoost;

    public Helm(int hpBoost) {
        this.hpBoost = hpBoost;
    }

    public String getEffect() {
        return "HP Boost: " + hpBoost;
    }

    public void setToHero(Hero hero) {
        hero.setHelm(this);
    }
}
