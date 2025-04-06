package net.tarsa.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.tarsa.Praecantatio;
import net.tarsa.spell.elemental.fire.BallOfFire;

public class PraecantatioEntities {
    public static final EntityType<BallOfFireEntity> BALL_OF_FIRE = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(Praecantatio.MOD_ID, "ball-of-fire"),
            FabricEntityTypeBuilder.<BallOfFireEntity>create(SpawnGroup.MISC, BallOfFireEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25F, 0.25F))
                    .trackRangeBlocks(8)
                    .trackedUpdateRate(10)
                    .build()
    );

    public static void registerEntities() {
    }
}
