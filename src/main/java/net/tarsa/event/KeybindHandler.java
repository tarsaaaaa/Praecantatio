package net.tarsa.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.screen.v1.ScreenMouseEvents;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.tarsa.Praecantatio;
import net.tarsa.networking.ClientNetworking;
import net.tarsa.spell.SpellManager;
import org.lwjgl.glfw.GLFW;

public class KeybindHandler {
    private static final String PRAECANTATIO_KEY_CATEGORY = Praecantatio.MOD_ID+".key-category";

    private static final String PRAECANTATIO_KEY_CAST_SPELL = Praecantatio.MOD_ID+".key.cast-spell";

    private static KeyBinding castKey;
    private static KeyBinding altKeyBind;
    private static SpellManager spellManager = new SpellManager();

    private static void registerKeyInputs() {
        ClientTickEvents.END_CLIENT_TICK.register((client) -> {
            while (castKey.wasPressed()) {
                ClientNetworking.castSpell(client.player);
            }
        });
    }

    public static void registerKeybinds() {
        castKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                PRAECANTATIO_KEY_CAST_SPELL,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_R,
                PRAECANTATIO_KEY_CATEGORY
        ));

        altKeyBind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.magicmod.alt",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_LEFT_ALT,
                PRAECANTATIO_KEY_CATEGORY
        ));
        registerKeyInputs();
    }

    public static void onScroll(PlayerEntity player, int direction) {
        spellManager.scrollSlot(direction);
        player.sendMessage(Text.of("Switched spell slot!"), true);
    }
}
