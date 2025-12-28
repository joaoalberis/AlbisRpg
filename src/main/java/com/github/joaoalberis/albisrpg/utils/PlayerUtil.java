package com.github.joaoalberis.albisrpg.utils;

import com.github.joaoalberis.albisrpg.capability.PlayerCapability;
import com.github.joaoalberis.albisrpg.capability.PlayerCapabilityInterface;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;

public class PlayerUtil {

    public static @NotNull LazyOptional<PlayerCapabilityInterface> getPlayerCapability( Player player){
        return player.getCapability(PlayerCapability.PLAYER_CAPABILITY);
    }

}
