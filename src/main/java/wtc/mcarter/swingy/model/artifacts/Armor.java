package wtc.mcarter.swingy.model.artifacts;

import lombok.Getter;

public class Armor {
    @Getter
    private int defense;

    public Armor(int defense) {
        this.defense = defense;
    }
}
