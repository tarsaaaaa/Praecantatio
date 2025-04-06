package net.tarsa.renderer;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.tarsa.Praecantatio;
import net.tarsa.entity.BallOfFireEntity;
import net.tarsa.entity.NukeEntity;

@Environment(EnvType.CLIENT)
public class NukeRenderer extends EntityRenderer<NukeEntity> {
    private static final Identifier TEXTURE = new Identifier(Praecantatio.MOD_ID, "textures/entity/nuke.png");

    public NukeRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(NukeEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(NukeEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }
}
