package net.tarsa.spell.elemental.air;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.tarsa.Praecantatio;
import net.tarsa.interfaces.PlayerSpellInterface;

public class ShieldOfAir extends AbstractAirSpell{
    public ShieldOfAir() {
        super(new Identifier(Praecantatio.MOD_ID, "spell.shield-of-air"), 10f, 0, false);
    }

    @Override
    public boolean cast(PlayerEntity caster, World world) {
        super.cast(caster, world);
        if (world.isClient) return false;

        if (caster instanceof PlayerSpellInterface spellCaster) {
            if (spellCaster.praecantatio$isAirBarrierActive()) {
                spellCaster.praecantatio$setAirBarrierActive(false);
                caster.sendMessage(Text.of("Air Barrier deactivated."), true);
            } else {
                spellCaster.praecantatio$setAirBarrierActive(true);
                caster.sendMessage(Text.of("Air Barrier activated!"), true);
            }
            return true;
        }
        return false;
    }
}
