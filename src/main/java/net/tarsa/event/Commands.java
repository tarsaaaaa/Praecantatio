package net.tarsa.event;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.tarsa.util.PlayerStatics;

import static net.tarsa.util.PlayerStatics.*;

public class Commands {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("praecantatio")
                .then(CommandManager.literal("playerStats")
                        .then(CommandManager.literal("manaRegenAmplifier")
                                .executes(Commands::getPlayerManaRegenAmplifier)
                                .then(CommandManager.argument("mana-regen-input", FloatArgumentType.floatArg(0))
                                        .executes(Commands::setPlayerManaRegenAmplifier)))
                        .then(CommandManager.literal("intelligence")
                                .executes(Commands::getPlayerIntelligence)
                                .then(CommandManager.argument("intelligence-input", IntegerArgumentType.integer(1))
                                        .executes(Commands::setPlayerIntelligence)))));
    }

    private static int getPlayerIntelligence(CommandContext<ServerCommandSource> context) {
        context.getSource().sendMessage(Text.of("Current intelligence: "+ playerStats.praecantatio$getPlayerIntelligence()));
        return 0;
    }

    private static int setPlayerIntelligence(CommandContext<ServerCommandSource> context) {
        int arg = IntegerArgumentType.getInteger(context, "intelligence-input");
        playerStats.praecantatio$setPlayerIntelligence(arg);
        return 0;
    }

    private static int getPlayerManaRegenAmplifier(CommandContext<ServerCommandSource> context) {
        context.getSource().sendMessage(Text.of("Current mana regen amplifier: "+ playerMana.praecantatio$getPlayerManaRegenAmplifier()));
        return 0;
    }

    private static int setPlayerManaRegenAmplifier(CommandContext<ServerCommandSource> context) {
        float arg = FloatArgumentType.getFloat(context, "mana-regen-input");
        playerMana.praecantatio$setPlayerManaRegenAmplifier(arg);
        return 0;
    }
}
