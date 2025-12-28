package com.github.joaoalberis.albisrpg.classes;

import net.minecraft.network.chat.Component;

public enum PlayerClass {
    Warrior(Component.translatable("class.albisrpg.character.warrior").getString()),
    Mage(Component.translatable("class.albisrpg.character.mage").getString()),
    Rogue(Component.translatable("class.albisrpg.character.rogue").getString());

    private String name;

    PlayerClass( String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName( String name ) {
        this.name = name;
    }
}
