package net.tarsa.util;

import net.minecraft.entity.player.PlayerEntity;
import net.tarsa.interfaces.PlayerManaInterface;
import net.tarsa.interfaces.PlayerStatsInterface;

public class PlayerStatics {
    public static PlayerManaInterface playerMana;
    public static PlayerStatsInterface playerStats;

    public static void createPlayer(PlayerEntity player) {
        playerMana = (PlayerManaInterface) player;
        playerStats = (PlayerStatsInterface) player;
    }
}
