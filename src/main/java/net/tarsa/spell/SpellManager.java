package net.tarsa.spell;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import net.tarsa.interfaces.PlayerManaInterface;
import net.tarsa.spell.elemental.air.ShieldOfAir;
import net.tarsa.spell.elemental.fire.BallOfFire;

import java.util.HashMap;
import java.util.Map;

import static net.tarsa.util.PlayerStatics.*;


public class SpellManager {
    private final Map<Integer, Spell> spellSlots = new HashMap<>();
    private int selectedSlot = 0;
    private final Map<Spell, Integer> cooldowns = new HashMap<>();
    private static final int MAX_SLOTS = 5;
    public SpellManager() {
        ClientTickEvents.END_CLIENT_TICK.register(minecraftClient -> {
            tickCooldowns();
        });

        equipSpell(0, new ShieldOfAir());
    }

    public void equipSpell(int slot, Spell spell) {
        spellSlots.put(slot, spell);
    }

    public void selectSlot(int slot) {
        if (spellSlots.containsKey(slot)) {
            selectedSlot = slot;
        }
    }

    public void scrollSlot(int direction) {
        selectedSlot = (selectedSlot + direction + MAX_SLOTS) % MAX_SLOTS;
    }

    public void castSelectedSpell(PlayerEntity player, World world) {
        Spell spell = spellSlots.get(selectedSlot);
        if (spell != null && !isOnCooldown(spell) && playerMana.praecantatio$hasEnoughMana(spell.getManaCost())) {
            if (spell.cast(player, world)) {
                startCooldown(spell);
                if (spell.consumesMana()) {
                    playerMana.praecantatio$consumeMana(spell.getManaCost());
                }
            }
        } else if (spell != null && !playerMana.praecantatio$hasEnoughMana(spell.getManaCost())) {
            player.sendMessage(Text.of("Boy ain't got no enough mana: " + playerMana.praecantatio$getMana()), false);
        } else if (isOnCooldown(spell)) player.sendMessage(Text.of("Shitty spell on cooldown."), false);
    }

    private boolean isOnCooldown(Spell spell) {
        return cooldowns.getOrDefault(spell, -1) > 0;
    }

    private void startCooldown(Spell spell) {
        cooldowns.put(spell, spell.getCooldown());
    }

    public void tickCooldowns() {
        cooldowns.replaceAll((spell, time) -> Math.max(time - 1, -1));
    }
}
