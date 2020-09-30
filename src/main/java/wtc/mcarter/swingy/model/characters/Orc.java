package wtc.mcarter.swingy.model.characters;

public class Orc extends Villain {
    public Orc() {
        damage = 10;
        hp = 20;
        xpDrop = damage * hp;
    }
}
