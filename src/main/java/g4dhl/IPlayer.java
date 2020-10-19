package g4dhl;

public interface IPlayer {

	int getPlayerId();

	String getPlayerName();

	String getPlayerPosition();

	int getPlayerAge();

	int getPlayerSkating();

	int getPlayerShooting();

	int getPlayerChecking();

	int getPlayerSaving();

	boolean setPlayerId(int playerId);

	boolean setPlayerName(String playerName);

	boolean setPlayerAge(int playerAge);

	boolean setPlayerSkating(int playerSkating);

	boolean setPlayerShooting(int playerShooting);

	boolean setPlayerChecking(int playerChecking);

	boolean setPlayerSaving(int playerSaving);

	boolean setPlayerPosition(String playerPosition);

	boolean setPlayerCaptain(boolean playerCaptain);

	boolean isPlayerCaptain();
}
