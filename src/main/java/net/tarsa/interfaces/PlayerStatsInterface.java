package net.tarsa.interfaces;

import net.minecraft.nbt.NbtCompound;

public interface PlayerStatsInterface {
    NbtCompound praecantatio$getPlayerStatCompound();
    int praecantatio$getPlayerLevel();
    void praecantatio$setPlayerLevel(int amount);
    void praecantatio$addPlayerLevel(int amount);
    int praecantatio$getPlayerLevelPoints();
    void praecantatio$setPlayerLevelPoints(int amount);
    void praecantatio$addPlayerLevelPoints(int amount);
    void praecantatio$consumePlayerLevelPoints(int amount);
    boolean praecantatio$hasEnoughLevelPoints(int amount);
    int praecantatio$getPlayerIntelligence();
    void praecantatio$setPlayerIntelligence(int amount);
    void praecantatio$addPlayerIntelligence(int amount);
    int praecantatio$getPlayerVitality();
    void praecantatio$setPlayerVitality(int amount);
    void praecantatio$addPlayerVitality(int amount);
    int praecantatio$getPlayerAgility();
    void praecantatio$setPlayerAgility(int amount);
    void praecantatio$addPlayerAgility(int amount);
    int praecantatio$getPlayerStrength();
    void praecantatio$setPlayerStrength(int amount);
    void praecantatio$addPlayerStrength(int amount);
    int praecantatio$getPlayerDefence();
    void praecantatio$setPlayerDefence(int amount);
    void praecantatio$addPlayerDefence(int amount);
}
