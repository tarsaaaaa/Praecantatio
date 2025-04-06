package net.tarsa.spell.elemental.fire;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.tarsa.Praecantatio;
import net.tarsa.entity.BallOfFireEntity;
import net.tarsa.spell.AbstractSpell;

public class BallOfFire extends AbstractFireSpell {
    public BallOfFire() {
        super(new Identifier(Praecantatio.MOD_ID, "spell.ball-of-fire"), 10f, 0, true);
    }

    @Override
    public boolean cast(PlayerEntity caster, World world) {
        if (!world.isClient && super.cast(caster, world)) {
            BallOfFireEntity ballOfFire = new BallOfFireEntity(world, caster);

            Vec3d direction = caster.getRotationVec(1.0F);
            ballOfFire.setVelocity(direction.x, direction.y + 0.2, direction.z, 0.8F, 5.0F);

            world.spawnEntity(ballOfFire);
            caster.sendMessage(Text.of("Demn boy casted a BallOfFire spell huh?"), true);
            return true;
        }
        return false;
    }
}
