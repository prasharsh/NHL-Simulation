package com.datamodel.leaguedatamodel;

import java.sql.Date;
import java.time.LocalDate;

public interface IPlayer {

    int getPlayerId();

    String getPlayerName();

    String getPlayerPosition();

    int getPlayerBirthYear();

    int getPlayerBirthMonth();

    int getPlayerBirthDay();

    Date getPlayerBirthDate();

    void decreasePlayerStat(int statValue);

    int getPlayerAgeYear();

    int getPlayerAgeDays();

    int getPlayerSkating();

    int getPlayerShooting();

    int getPlayerChecking();

    int getPlayerSaving();

    boolean isPlayerRetired();

    int getMaxPlayerStatValue();

    double getPlayerStrength();

    Date getRecoveryDate();

    boolean getRosterStatus();

    void agePlayer(int days);

    boolean setPlayerId(int playerId);

    boolean setPlayerName(String playerName);

    void setPlayerBirthYear(int playerBirthYear);

    void setPlayerBirthMonth(int playerBirthMonth);

    void setPlayerBirthDay(int playerBirthDay);

    boolean isPlayerBirthDay(int month, int day);

    void calculatePlayerAge(LocalDate birthDate, LocalDate currentDate);

    boolean setPlayerAgeYear(int playerAgeYear);

    boolean setPlayerAgeDays(int playerDays);

    boolean setPlayerSkating(int playerSkating);

    boolean setPlayerShooting(int playerShooting);

    boolean setPlayerChecking(int playerChecking);

    boolean setPlayerSaving(int playerSaving);

    boolean setPlayerPosition(String playerPosition);

    boolean setPlayerCaptain(boolean playerCaptain);

    boolean setPlayerIsInjured(boolean playerIsInjured);

    boolean setPlayerWasInjured(boolean playerWasInjured);

    boolean checkPlayerInjury(float randomInjuryChance, Date recoveryDate, Date currentDate, ITeam team);

    boolean setRecoveryDate(Date recoveryDate);

    void setRosterStatus(boolean rosterStatus);

    boolean isPlayerCaptain();

    boolean isPlayerInjured();

    boolean wasPlayerInjured();

    boolean setPlayerRetired(boolean playerRetired);
}