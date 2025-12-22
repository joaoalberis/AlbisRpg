package com.github.joaoalberis.albisrpg.utils;

import com.github.joaoalberis.albisrpg.capability.PlayerCapability;
import com.github.joaoalberis.albisrpg.capability.PlayerCapabilityInterface;
import net.minecraft.world.entity.player.Player;

public class PlayerUtil {

    public static PlayerCapabilityInterface getPlayerCapability(Player player){
        return player.getCapability(PlayerCapability.PLAYER_CAPABILITY).orElseGet(null);
    }

}
