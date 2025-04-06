package net.tarsa;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.MinecraftServer;
import net.tarsa.entity.PraecantatioEntities;
import net.tarsa.event.Commands;
import net.tarsa.networking.ServerNetworking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Praecantatio implements ModInitializer {
	public static final String MOD_ID = "praecantatio";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	@Override
	public void onInitialize() {
		LOGGER.info("Loading Some Magic!");

		PraecantatioEntities.registerEntities();

		ServerNetworking.registerServerPacketReceiver();

		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
			Commands.register(dispatcher);
		});
	}
}