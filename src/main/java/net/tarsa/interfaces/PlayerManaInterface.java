package net.tarsa.interfaces;

import net.minecraft.nbt.NbtCompound;

public interface PlayerManaInterface {
    NbtCompound praecantatio$getManaCompound();
    float praecantatio$getMana();
    void praecantatio$setMaxMana(float amount);
    void praecantatio$setMana(float amount);
    void praecantatio$addMana(float amount);
    void praecantatio$consumeMana(float amount);
    boolean praecantatio$hasEnoughMana(float cost);
    float praecantatio$getMaxMana();
    void praecantatio$tickManaRegen();
    float praecantatio$getPlayerManaRegenAmplifier();
    void praecantatio$setPlayerManaRegenAmplifier(float amount);
}
