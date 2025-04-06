package net.tarsa.spell.elemental.air;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.tarsa.spell.AbstractSpell;

public class AbstractAirSpell extends AbstractSpell {
    public AbstractAirSpell(Identifier id, float manaCost, int cooldown, boolean consumesMana) {
        super(id, manaCost, cooldown, consumesMana);
    }

    @Override
    public boolean cast(PlayerEntity caster, World world) {
        return super.cast(caster, world);
    }
}
