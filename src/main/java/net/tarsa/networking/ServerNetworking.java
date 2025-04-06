package net.tarsa.networking;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.tarsa.spell.SpellManager;

import static net.tarsa.networking.NetworkingIdentifiers.*;

public class ServerNetworking {
    private static SpellManager spellManager = new SpellManager();
    public static void registerServerPacketReceiver() {
        ServerPlayNetworking.registerGlobalReceiver(CAST_SPELL, (server, player, handler, buf, responseSender) -> {
            server.execute(() -> {
                spellManager.castSelectedSpell(player, player.getWorld());
            });
        });
    }
}
