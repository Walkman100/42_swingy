package wtc.mcarter.swingy.model.characters;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import wtc.mcarter.swingy.model.artifacts.Armor;
import wtc.mcarter.swingy.model.artifacts.Weapon;

@Getter
@Setter
public abstract class Hero extends Character {
    @NotNull
    private Weapon weapon;
    @NotNull
    private Armor armor;

    int level;
    int experience;
}
