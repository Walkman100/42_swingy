package wtc.mcarter.swingy.model.characters;

import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
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
    @NotNull
    private String name;

    public Hero(String name) {
        this.name = name;
    }

    int level;
    int experience;

    @Setter(AccessLevel.NONE)
    protected int baseDefense;
    @Setter(AccessLevel.NONE)
    protected int baseAttack;

    public int getDamage() {
        return baseAttack + weapon.getDamage();
    }

    public int getDefense() {
        return baseDefense + armor.getDefense();
    }

    public void setHelm(int helmHP) {
        hp += helmHP;
    }
}
