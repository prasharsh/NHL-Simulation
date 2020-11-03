package com.datamodel.leaguedatamodel;
import java.sql.Date;

public interface IPlayer {

	int getPlayerId();

	String getPlayerName();

	String getPlayerPosition();

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

	void agePlayer(int days);

	boolean setPlayerId(int playerId);

	boolean setPlayerName(String playerName);

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

	boolean isPlayerCaptain();

	boolean isPlayerInjured();

	boolean wasPlayerInjured();

	boolean setPlayerRetired(boolean playerRetired);
}