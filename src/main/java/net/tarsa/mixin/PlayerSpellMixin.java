package net.tarsa.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import net.tarsa.Praecantatio;
import net.tarsa.interfaces.PlayerManaInterface;
import net.tarsa.interfaces.PlayerSpellInterface;
import net.tarsa.util.PlayerStatics;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(PlayerEntity.class)
public class PlayerSpellMixin implements PlayerSpellInterface {
    @Unique
    private boolean ShieldOfAirActive = false;

    @Override
    public boolean praecantatio$isAirBarrierActive() {
        return ShieldOfAirActive;
    }

    @Override
    public void praecantatio$setAirBarrierActive(boolean active) {
        this.ShieldOfAirActive = active;
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("HEAD")) public void writeNbt(NbtCompound nbt, CallbackInfo ci) {
        nbt.putBoolean(Praecantatio.MOD_ID+".spell.shield-of-air.status", ShieldOfAirActive);
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("HEAD")) public void ReadNbt(NbtCompound nbt, CallbackInfo ci) {
        ShieldOfAirActive = nbt.getBoolean(Praecantatio.MOD_ID+".spell.shield-of-air.status");
    }

    @Inject(method = "tick", at = @At("HEAD")) public void RegisterTick(CallbackInfo ci) {
        if (this.praecantatio$isAirBarrierActive()) {
            float mana = PlayerStatics.playerMana.praecantatio$getMana();
            final float cost = 0.5f;
            if (mana <= cost) {
                this.praecantatio$setAirBarrierActive(false);
                PlayerStatics.playerMana.praecantatio$consumeMana(cost);
                ((PlayerEntity) (Object) this).sendMessage(Text.of("Air Barrier deactivated."), true);
                return;
            }
            PlayerStatics.playerMana.praecantatio$consumeMana(cost);

            List<Entity> nearby = ((PlayerEntity) (Object) this).getWorld().getOtherEntities(((PlayerEntity) (Object) this), ((PlayerEntity) (Object) this).getBoundingBox().expand(5));
            for (Entity entity : nearby) {
                double distanceSq = entity.squaredDistanceTo((PlayerEntity) (Object) this);
                final double STOP_DISTANCE_SQ = 0.0225;

                if (distanceSq > 4 * 4) continue;
                if (distanceSq < STOP_DISTANCE_SQ) {
                    entity.setVelocity(Vec3d.ZERO);
                    entity.velocityModified = true;
                    continue;
                }
                Vec3d toPlayer = ((PlayerEntity) (Object) this).getPos().subtract(entity.getPos()).normalize();
                Vec3d entityVelocity = entity.getVelocity().normalize();

                double dot = entityVelocity.dotProduct(toPlayer);
                if (dot <= 0) continue;

                Vec3d slowed = entity.getVelocity().multiply(0.2);
                entity.setVelocity(slowed);
                entity.velocityModified = true;
            }
        }
    }
}
