package com.github.joaoalberis.albisrpg.events;

import com.github.joaoalberis.albisrpg.Albisrpg;
import com.github.joaoalberis.albisrpg.capability.PlayerCapability;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class PlayerHealthRenderer {

    private static ResourceLocation healthTexture = new ResourceLocation(Albisrpg.MODID,"textures/gui/overlay/health_bar.png");

    @SubscribeEvent
    public static void renderHealth(RenderGuiOverlayEvent.Pre event){
        Minecraft minecraft = Minecraft.getInstance();
        Gui gui = minecraft.gui;
        if (event.getOverlay().id().equals(VanillaGuiOverlay.PLAYER_HEALTH.id())){
            LocalPlayer player = minecraft.player;
            if (gui instanceof ForgeGui forgeGui && player != null){
                player.getCapability(PlayerCapability.PLAYER_CAPABILITY).ifPresent(c -> {
                    int screenWidth = minecraft.getWindow().getGuiScaledWidth();
                    int screenHeight = minecraft.getWindow().getGuiScaledHeight();
                    int leftPos = screenWidth / 2 - 91;
                    int topPos = screenHeight - 40;
                    String healthString = Mth.ceil(c.getHealth()) + " / " + Mth.ceil(c.getMaxHealth());
                    GuiGraphics guiGraphics = event.getGuiGraphics();
                    guiGraphics.blit(healthTexture, leftPos, topPos,
                            80, 10, 0, 0, 81, 12, 81, 132);
                    guiGraphics.drawString(minecraft.font, healthString,leftPos + 20, topPos + 1, 0xFFFFFF);
                    event.setCanceled(true);
                });
            }
        }
    }

}
