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

public class BallOfFireEntity extends ThrownItemEntity {
    public BallOfFireEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public BallOfFireEntity(World world, LivingEntity owner) {
        super(PraecantatioEntities.BALL_OF_FIRE, owner, world);
    }

    @Override
    protected Item getDefaultItem() {
        return Items.FIRE_CHARGE; // Representing a fire-based item
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        if (!this.getWorld().isClient && hitResult instanceof BlockHitResult blockHit) {
            // Get the position of impact
            BlockPos hitPos = blockHit.getBlockPos();
            if (blockHit.getSide() == Direction.UP) {
                igniteGround(hitPos);
                this.discard();
            }
        }
    }

    private void igniteGround(BlockPos hitPos) {
        World world = this.getWorld();

        for (int x = -2; x <= 2; x++) {
            for (int z = -2; z <= 2; z++) {
                BlockPos firePos = hitPos.add(x, 1, z);
                if (world.getBlockState(firePos).isAir()) {
                    world.setBlockState(firePos, net.minecraft.block.Blocks.FIRE.getDefaultState());
                }
            }
        }
    }
}
