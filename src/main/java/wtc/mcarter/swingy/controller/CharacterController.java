package wtc.mcarter.swingy.controller;

import wtc.mcarter.swingy.exceptions.HeroTypeNotFoundException;
import wtc.mcarter.swingy.model.characters.Hero;
import wtc.mcarter.swingy.storage.HeroStorage;
import wtc.mcarter.swingy.util.CharacterFactory;

public class CharacterController {
    public void newHero(String characterType, String characterName) throws HeroTypeNotFoundException {
        Hero hero = CharacterFactory.newHero(characterType, characterName);
        HeroStorage.AddHero(hero);
    }
}
