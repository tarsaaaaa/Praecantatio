package net.tarsa.spell.elemental.fire;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.tarsa.Praecantatio;
import net.tarsa.entity.BallOfFireEntity;
import net.tarsa.entity.NukeEntity;

public class Nuke extends AbstractFireSpell{
    public Nuke() {
        super(new Identifier(Praecantatio.MOD_ID, "spell.ball-of-fire"), 10f, 10, true);
    }

    @Override
    public boolean cast(PlayerEntity caster, World world) {
        super.cast(caster, world);
        if (!world.isClient) {
            NukeEntity nuke = new NukeEntity(world, caster);

            Vec3d direction = caster.getRotationVec(1.0F);
            nuke.setVelocity(direction.x, direction.y + 0.2, direction.z, 0.8F, 5.0F);

            world.spawnEntity(nuke);
            caster.sendMessage(Text.of("Demn boy casted a Nuke spell huh?"), true);
            return true;
        }
        return false;
    }
}
