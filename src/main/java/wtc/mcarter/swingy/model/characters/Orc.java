package wtc.mcarter.swingy.model.characters;

public class Orc extends Villain {
    public Orc() {
        damage = 10;
        hp = 30;
        xpDrop = damage * hp;
    }
}
