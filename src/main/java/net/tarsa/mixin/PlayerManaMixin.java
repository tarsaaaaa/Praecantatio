package net.tarsa.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.tarsa.Praecantatio;
import net.tarsa.interfaces.PlayerManaInterface;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerManaMixin implements PlayerManaInterface {

    @Unique
    private NbtCompound ManaCompound;
    @Unique
    private final String MANA_KEY = Praecantatio.MOD_ID+".mana";
    @Unique
    private final String MAX_MANA_KEY = Praecantatio.MOD_ID+".max-mana";
    @Unique
    private final String PLAYER_MANA_AMPLIFIER_KEY = Praecantatio.MOD_ID+".player-mana-amplifier";

    @Override
    public NbtCompound praecantatio$getManaCompound() {
        if (ManaCompound == null) {
            ManaCompound = new NbtCompound();
            praecantatio$setMana(100);
            praecantatio$setMaxMana(100);
            praecantatio$setPlayerManaRegenAmplifier(1f);
        }
        return ManaCompound;
    }

    @Override
    public float praecantatio$getMana() {
        return praecantatio$getManaCompound().contains(MANA_KEY)? praecantatio$getManaCompound().getFloat(MANA_KEY) : 0;
    }

    @Override
    public void praecantatio$setMaxMana(float amount) {
        praecantatio$getManaCompound().putFloat(MAX_MANA_KEY, amount);
    }

    @Override
    public void praecantatio$setMana(float amount) {
        praecantatio$getManaCompound().putFloat(MANA_KEY, Math.max(0, Math.min(amount, praecantatio$getManaCompound().getFloat(MAX_MANA_KEY))));
    }

    @Override
    public void praecantatio$addMana(float amount) {
        praecantatio$setMana(praecantatio$getManaCompound().getFloat(MANA_KEY) + amount);
    }

    @Override
    public void praecantatio$consumeMana(float amount) {
        praecantatio$setMana(praecantatio$getManaCompound().getFloat(MANA_KEY) - amount);
    }

    @Override
    public boolean praecantatio$hasEnoughMana(float cost) {
        return praecantatio$getManaCompound().getFloat(MANA_KEY) >= cost;
    }

    @Override
    public float praecantatio$getMaxMana() {
        return praecantatio$getManaCompound().contains(MAX_MANA_KEY)? praecantatio$getManaCompound().getFloat(MAX_MANA_KEY) : 0;
    }

    @Override
    public void praecantatio$tickManaRegen() {
        praecantatio$addMana(praecantatio$getManaCompound().getFloat(PLAYER_MANA_AMPLIFIER_KEY));
    }

    @Override
    public float praecantatio$getPlayerManaRegenAmplifier() {
        return praecantatio$getManaCompound().contains(PLAYER_MANA_AMPLIFIER_KEY)? praecantatio$getManaCompound().getFloat(PLAYER_MANA_AMPLIFIER_KEY) : 0;
    }

    @Override
    public void praecantatio$setPlayerManaRegenAmplifier(float amount) {
        praecantatio$getManaCompound().putFloat(PLAYER_MANA_AMPLIFIER_KEY, amount);
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("HEAD"))
    public void saveMana(NbtCompound nbt, CallbackInfo ci) {
        nbt.put(Praecantatio.MOD_ID+".mana-compound", ManaCompound);
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("HEAD"))
    public void loadMana(NbtCompound nbt, CallbackInfo ci) {
        ManaCompound = nbt.getCompound(Praecantatio.MOD_ID+".mana-compound");
    }

    @Inject(method = "tick", at = @At("HEAD"))
    public void regenerateMana(CallbackInfo ci) {
        NbtCompound manaCompound = praecantatio$getManaCompound();
        if (!manaCompound.contains(MANA_KEY) || !manaCompound.contains(MAX_MANA_KEY)) {
            manaCompound.putFloat(MAX_MANA_KEY, 100);
            manaCompound.putFloat(MANA_KEY, 100);
        }
        float mana = manaCompound.getFloat(MANA_KEY);
        float maxMana = manaCompound.getFloat(MAX_MANA_KEY);
        manaCompound.putFloat(MANA_KEY, Math.min(mana+praecantatio$getPlayerManaRegenAmplifier(), maxMana));
    }
}
