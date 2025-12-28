package com.github.joaoalberis.albisrpg.network;

import com.github.joaoalberis.albisrpg.classes.PlayerClass;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class C2SUpdateTranslation {
    private final String currentLanguage;

    public C2SUpdateTranslation( String currentLanguage ) {
        this.currentLanguage = currentLanguage;
    }

    public static void encode( C2SUpdateTranslation msg, FriendlyByteBuf buf ) {
        buf.writeUtf(msg.currentLanguage);
    }

    public C2SUpdateTranslation( FriendlyByteBuf buf ) {
        this.currentLanguage = buf.readUtf();
    }

    public static void handle( C2SUpdateTranslation msg, Supplier<NetworkEvent.Context> ctx ) {
        ctx.get().enqueueWork(() -> {
            var player = ctx.get().getSender();

            if (player == null) return;
            if ( player.getLanguage().equalsIgnoreCase(msg.currentLanguage) ) {
                PlayerClass.Warrior.setName(Component.translatable("class.albisrpg.character.warrior").getString());
                PlayerClass.Mage.setName(Component.translatable("class.albisrpg.character.mage").getString());
                PlayerClass.Rogue.setName(Component.translatable("class.albisrpg.character.rogue").getString());
            }

        });
        ctx.get().setPacketHandled(true);
    }

}
