package net.tarsa.spell.elemental.fire;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.tarsa.spell.AbstractSpell;

public class AbstractFireSpell extends AbstractSpell {
    public AbstractFireSpell(Identifier id, float manaCost, int cooldown, boolean consumesMana) {
        super(id, manaCost, cooldown, consumesMana);
    }

    @Override
    public boolean cast(PlayerEntity caster, World world) {
        super.cast(caster, world);
        if (caster.isSubmergedInWater()) {
            caster.sendMessage(Text.of("Can't use fire spells under water."));
            return false;
        }
        return true;
    }
}
