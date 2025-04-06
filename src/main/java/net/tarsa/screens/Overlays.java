package net.tarsa.screens;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;
import net.tarsa.Praecantatio;
import net.tarsa.interfaces.PlayerManaInterface;
import net.tarsa.spell.SpellManager;

import static net.tarsa.util.PlayerStatics.*;

public class Overlays {
    public static void ManaBar(DrawContext context, float v) {
        MinecraftClient client = MinecraftClient.getInstance();
        TextRenderer textRenderer = client.textRenderer;
        if (client.player == null || playerMana == null) return;
        float mana = playerMana.praecantatio$getMana();
        float maxMana = playerMana.praecantatio$getMaxMana();
        int screenHeight = client.getWindow().getScaledHeight();
        int barWidth = 100;
        int barHeight = 8;
        int barX = 2;
        int barY = screenHeight - barHeight - 2;
        context.drawText(textRenderer, String.valueOf(mana), barX, barY - barHeight, 0xFFFFFFFF, false);
        context.fill(barX, barY, barX + barWidth , barY + barHeight, 0xFF000000);
        context.fill(barX, barY, barX + (int) ((mana / maxMana) * 100), barY + barHeight, 0xFF64C4E2);
    }
}
