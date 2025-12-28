package com.github.joaoalberis.albisrpg.events;

import com.github.joaoalberis.albisrpg.classes.PlayerClass;
import com.github.joaoalberis.albisrpg.network.C2SUpdateTranslation;
import com.github.joaoalberis.albisrpg.network.NetworkHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class LanguageChangeEvent {

    private static String lastLanguage;
    private static String lastWarriorLanguage = PlayerClass.Warrior.getName();
    private static String lastMageLanguage = PlayerClass.Mage.getName();
    private static String lastRogueLanguage = PlayerClass.Rogue.getName();
    private static final Minecraft minecraft = Minecraft.getInstance();

    @SubscribeEvent
    public static void onLanguageChangeEvent( TickEvent.ClientTickEvent event ) {
        String currentLanguage = minecraft.getLanguageManager().getSelected();
        String currentWarriorLanguage = Component.translatable("class.albisrpg.character.warrior").getString();
        String currentMageLanguage = Component.translatable("class.albisrpg.character.mage").getString();
        String currentRogueLanguage = Component.translatable("class.albisrpg.character.rogue").getString();

        if ( minecraft.player == null ) return;

        if ( currentLanguage.equalsIgnoreCase(lastLanguage) ) return;

        if ( currentWarriorLanguage.equalsIgnoreCase(lastWarriorLanguage) &&
                currentMageLanguage.equalsIgnoreCase(lastMageLanguage) &&
                currentRogueLanguage.equalsIgnoreCase(lastRogueLanguage)
        ) return;

        lastWarriorLanguage = currentWarriorLanguage;
        lastMageLanguage = currentMageLanguage;
        lastRogueLanguage = currentRogueLanguage;
        lastLanguage = currentLanguage;

        PlayerClass.Warrior.setName(lastWarriorLanguage);
        PlayerClass.Mage.setName(lastMageLanguage);
        PlayerClass.Rogue.setName(lastRogueLanguage);

        NetworkHandler.INSTANCE.sendToServer(new C2SUpdateTranslation(lastLanguage));
    }
}