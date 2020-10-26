package g4dhl;

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

	double getPlayerStrength();

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

	boolean checkPlayerInjury(float randomInjuryChance);

	boolean isPlayerCaptain();

	boolean isPlayerInjured();

	boolean wasPlayerInjured();

}
