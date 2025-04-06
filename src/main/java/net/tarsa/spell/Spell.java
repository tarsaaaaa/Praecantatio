package net.tarsa.spell;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public interface Spell {
    Identifier getId();
    float getManaCost();
    int getCooldown();
    boolean consumesMana();
    boolean cast(PlayerEntity caster, World world);
}
