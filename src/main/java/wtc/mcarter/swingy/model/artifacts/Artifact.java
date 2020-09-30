package wtc.mcarter.swingy.model.artifacts;

import wtc.mcarter.swingy.model.characters.Hero;

public interface Artifact {
    public String getEffect();
    public void setToHero(Hero hero);
}
