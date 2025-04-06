package net.tarsa.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.tarsa.Praecantatio;
import net.tarsa.interfaces.PlayerManaInterface;
import net.tarsa.interfaces.PlayerStatsInterface;
import net.tarsa.util.PlayerStatics;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerStatsMixin implements PlayerStatsInterface {
    @Unique NbtCompound PLAYER_STAT_COMPOUND;
    @Unique private static final String PLAYER_LEVEL_KEY = Praecantatio.MOD_ID+".player-stat.level";
    @Unique private static final String PLAYER_LEVEL_POINTS_KEY = Praecantatio.MOD_ID+".player-stat.level-points";
    @Unique private static final String PLAYER_INTELLIGENCE_KEY = Praecantatio.MOD_ID+".player-stat.intelligence";
    @Unique private static final String PLAYER_VITALITY_KEY = Praecantatio.MOD_ID+".player-stat.vitality";
    @Unique private static final String PLAYER_AGILITY_KEY = Praecantatio.MOD_ID+".player-stat.agility";
    @Unique private static final String PLAYER_STRENGTH_KEY = Praecantatio.MOD_ID+".player-stat.strength";
    @Unique private static final String PLAYER_DEFENCE_KEY = Praecantatio.MOD_ID+".player-stat.defence";


    @Override public NbtCompound praecantatio$getPlayerStatCompound() {
        if (PLAYER_STAT_COMPOUND == null) {
            PLAYER_STAT_COMPOUND = new NbtCompound();
            praecantatio$getPlayerStatCompound().putInt(PLAYER_LEVEL_KEY, 1);
            praecantatio$getPlayerStatCompound().putInt(PLAYER_LEVEL_POINTS_KEY, 0);
            praecantatio$getPlayerStatCompound().putInt(PLAYER_INTELLIGENCE_KEY, 1);
            praecantatio$getPlayerStatCompound().putInt(PLAYER_VITALITY_KEY, 1);
            praecantatio$getPlayerStatCompound().putInt(PLAYER_AGILITY_KEY, 1);
            praecantatio$getPlayerStatCompound().putInt(PLAYER_STRENGTH_KEY, 1);
            praecantatio$getPlayerStatCompound().putInt(PLAYER_DEFENCE_KEY, 1);
        }
        return PLAYER_STAT_COMPOUND;
    }
    @Override public int praecantatio$getPlayerLevel() {
        return praecantatio$getPlayerStatCompound().contains(PLAYER_LEVEL_KEY)? praecantatio$getPlayerStatCompound().getInt(PLAYER_LEVEL_KEY) : 0;
    }
    @Override public void praecantatio$setPlayerLevel(int amount) {
        praecantatio$getPlayerStatCompound().putInt(PLAYER_LEVEL_KEY, amount);
    }
    @Override public void praecantatio$addPlayerLevel(int amount) {
        praecantatio$setPlayerLevel(praecantatio$getPlayerLevel() + amount);
    }
    @Override public int praecantatio$getPlayerLevelPoints() {
        return praecantatio$getPlayerStatCompound().contains(PLAYER_LEVEL_POINTS_KEY)? praecantatio$getPlayerStatCompound().getInt(PLAYER_LEVEL_POINTS_KEY) : 0;
    }
    @Override public void praecantatio$setPlayerLevelPoints(int amount) {
        praecantatio$getPlayerStatCompound().putInt(PLAYER_LEVEL_POINTS_KEY, amount);
    }
    @Override public void praecantatio$addPlayerLevelPoints(int amount) {
        praecantatio$setPlayerLevelPoints(praecantatio$getPlayerLevelPoints() + amount);
    }
    @Override public boolean praecantatio$hasEnoughLevelPoints(int amount) {
        return praecantatio$getPlayerLevelPoints() >= amount;
    }
    @Override public void praecantatio$consumePlayerLevelPoints(int amount) {
        praecantatio$setPlayerLevelPoints(praecantatio$getPlayerLevelPoints() - amount);
    }
    @Override public int praecantatio$getPlayerIntelligence() {
        return praecantatio$getPlayerStatCompound().contains(PLAYER_INTELLIGENCE_KEY)? praecantatio$getPlayerStatCompound().getInt(PLAYER_INTELLIGENCE_KEY) : 0;
    }
    @Override public void praecantatio$setPlayerIntelligence(int amount) {
        praecantatio$getPlayerStatCompound().putInt(PLAYER_INTELLIGENCE_KEY, amount);
    }
    @Override public void praecantatio$addPlayerIntelligence(int amount) {
        praecantatio$setPlayerIntelligence(praecantatio$getPlayerIntelligence() + amount);
    }
    @Override public int praecantatio$getPlayerVitality() {
        return praecantatio$getPlayerStatCompound().contains(PLAYER_VITALITY_KEY)? praecantatio$getPlayerStatCompound().getInt(PLAYER_VITALITY_KEY) : 0;
    }
    @Override public void praecantatio$setPlayerVitality(int amount) {
        praecantatio$getPlayerStatCompound().putInt(PLAYER_VITALITY_KEY, amount);
    }
    @Override public void praecantatio$addPlayerVitality(int amount) {
        praecantatio$setPlayerVitality(praecantatio$getPlayerVitality() + amount);
    }
    @Override public int praecantatio$getPlayerAgility() {
        return praecantatio$getPlayerStatCompound().contains(PLAYER_AGILITY_KEY)? praecantatio$getPlayerStatCompound().getInt(PLAYER_AGILITY_KEY) : 0;
    }
    @Override public void praecantatio$setPlayerAgility(int amount) {
        praecantatio$getPlayerStatCompound().putInt(PLAYER_AGILITY_KEY, amount);
    }
    @Override public void praecantatio$addPlayerAgility(int amount) {
        praecantatio$setPlayerAgility(praecantatio$getPlayerAgility() + amount);
    }
    @Override public int praecantatio$getPlayerStrength() {
        return praecantatio$getPlayerStatCompound().contains(PLAYER_STRENGTH_KEY)? praecantatio$getPlayerStatCompound().getInt(PLAYER_STRENGTH_KEY) : 0;
    }
    @Override public void praecantatio$setPlayerStrength(int amount) {
        praecantatio$getPlayerStatCompound().putInt(PLAYER_STRENGTH_KEY, amount);
    }
    @Override public void praecantatio$addPlayerStrength(int amount) {
        praecantatio$setPlayerStrength(praecantatio$getPlayerStrength() + amount);
    }
    @Override public int praecantatio$getPlayerDefence() {
        return praecantatio$getPlayerStatCompound().contains(PLAYER_DEFENCE_KEY)? praecantatio$getPlayerStatCompound().getInt(PLAYER_DEFENCE_KEY) : 0;
    }
    @Override public void praecantatio$setPlayerDefence(int amount) {
        praecantatio$getPlayerStatCompound().putInt(PLAYER_DEFENCE_KEY, amount);
    }
    @Override public void praecantatio$addPlayerDefence(int amount) {
        praecantatio$setPlayerDefence(praecantatio$getPlayerDefence() + amount);
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("HEAD")) public void writeNbt(NbtCompound nbt, CallbackInfo ci) {
        if (PLAYER_STAT_COMPOUND != null) {
            nbt.put(Praecantatio.MOD_ID+".player-stat", PLAYER_STAT_COMPOUND);
        }
    }
    @Inject(method = "readCustomDataFromNbt", at = @At("HEAD")) public void readNbt(NbtCompound nbt, CallbackInfo ci) {
        if (nbt.contains(Praecantatio.MOD_ID+".player-stat")) {
            PLAYER_STAT_COMPOUND = nbt.getCompound(Praecantatio.MOD_ID+".player-stat");
        }
    }
    @Inject(method = "tick", at = @At("HEAD")) public void tickNbt(CallbackInfo ci) {
        PlayerManaInterface playerMana = ((PlayerManaInterface) this);
        NbtCompound manaCompound = playerMana.praecantatio$getManaCompound();
        if (!manaCompound.contains(Praecantatio.MOD_ID+".max-mana")) {
            manaCompound.putFloat(Praecantatio.MOD_ID+".max-mana", 100);
        }
        playerMana.praecantatio$setMaxMana(50 * (praecantatio$getPlayerIntelligence() + 1));
    }
}
