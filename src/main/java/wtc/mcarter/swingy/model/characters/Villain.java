package wtc.mcarter.swingy.model.characters;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import wtc.mcarter.swingy.model.artifacts.Armor;

@Getter
@Setter
public abstract class Villain extends Character {
    @NotNull
    private Armor armor;

    int damage;
}
