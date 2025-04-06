package net.tarsa.spell;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class AbstractSpell implements Spell {
    protected final Identifier id;
    protected final float manaCost;
    protected final int cooldown;
    protected final boolean consumesMana;

    public AbstractSpell(Identifier id, float manaCost, int cooldown, boolean consumesMana) {
        this.id = id;
        this.manaCost = manaCost;
        this.cooldown = cooldown;
        this.consumesMana = consumesMana;
    }

    @Override
    public Identifier getId() {
        return id;
    }

    @Override
    public float getManaCost() {
        return manaCost;
    }

    @Override
    public int getCooldown() {
        return cooldown;
    }

    @Override
    public boolean consumesMana() {
        return consumesMana;
    }

    @Override
    public boolean cast(PlayerEntity caster, World world) {
        return false;
    }
}
