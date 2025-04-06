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
            Identifier.of(Praecantatio.MOD_ID, "ball-of-fire"),
            EntityType.Builder.<BallOfFireEntity>create(BallOfFireEntity::new, SpawnGroup.MISC)
                    .setDimensions(0.25F, 0.25F)
                    .build()
    );

    public static final EntityType<NukeEntity> NUKE_ENTITY_TYPE = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(Praecantatio.MOD_ID, "nuke-entity"),
            EntityType.Builder.<NukeEntity>create(NukeEntity::new, SpawnGroup.MISC)
                    .setDimensions(0.25F, 0.25F)
                    .build()
    );


    public static void registerEntities() {
    }
}
