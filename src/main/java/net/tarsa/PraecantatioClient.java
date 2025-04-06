package net.tarsa;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.tarsa.entity.PraecantatioEntities;
import net.tarsa.event.KeybindHandler;
import net.tarsa.renderer.BallOfFireRenderer;
import net.tarsa.renderer.NukeRenderer;
import net.tarsa.screens.Overlays;
import net.tarsa.util.PlayerStatics;

public class PraecantatioClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {
            PlayerStatics.createPlayer(client.player);
        });

        EntityRendererRegistry.register(PraecantatioEntities.BALL_OF_FIRE, BallOfFireRenderer::new);
        EntityRendererRegistry.register(PraecantatioEntities.NUKE_ENTITY_TYPE, NukeRenderer::new);

        KeybindHandler.registerKeybinds();

        HudRenderCallback.EVENT.register(Overlays::ManaBar);
    }
}
