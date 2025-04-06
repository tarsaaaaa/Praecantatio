package net.tarsa.renderer;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.tarsa.entity.BallOfFireEntity;

@Environment(EnvType.CLIENT)
public class BallOfFireRenderer extends EntityRenderer<BallOfFireEntity> {
    private static final Identifier TEXTURE = new Identifier("magicmod", "textures/entity/molotov.png");

    public BallOfFireRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(BallOfFireEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(BallOfFireEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }
}