package net.tarsa.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public class NukeEntity extends ThrownItemEntity {
    public NukeEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public NukeEntity(World world, LivingEntity owner) {
        super(PraecantatioEntities.NUKE_ENTITY_TYPE, owner, world);
    }


    @Override
    protected void onCollision(HitResult hitResult) {
        if (!this.getWorld().isClient && hitResult instanceof BlockHitResult blockHit) {
            BlockPos hitPos = blockHit.getBlockPos();
            if (blockHit.getSide() == Direction.UP) {
                boom(hitPos);
                this.discard();
            }
        }
    }

    private void boom(BlockPos hitPos) {
        World world = this.getWorld();
        world.createExplosion(this.getOwner(), hitPos.getX(), hitPos.getY(), hitPos.getZ(), 100f, World.ExplosionSourceType.TNT);
    }

    @Override
    protected Item getDefaultItem() {
        return Items.FIRE_CHARGE;
    }
}
