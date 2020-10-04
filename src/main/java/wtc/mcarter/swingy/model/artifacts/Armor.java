package wtc.mcarter.swingy.model.artifacts;

import lombok.Getter;
import wtc.mcarter.swingy.model.characters.Hero;

public class Armor implements Artifact {
    @Getter
    private int defense;

    public Armor(int defense) {
        this.defense = defense;
    }

    public String getEffect() {
        return "Defense: +" + defense;
    }

    public void setToHero(Hero hero) {
        hero.setArmor(this);
    }
}
