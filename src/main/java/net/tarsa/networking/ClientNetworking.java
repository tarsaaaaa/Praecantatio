package net.tarsa.networking;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;

import static net.tarsa.networking.NetworkingIdentifiers.*;

public class ClientNetworking {
    public static void castSpell(PlayerEntity player){
        ClientPlayNetworking.send(CAST_SPELL, PacketByteBufs.empty());
    }
}
