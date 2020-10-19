package g4dhl;

public interface IPlayer {

    int getPlayerId();
    String getPlayerName();
    int getPlayerSkating();
    int getPlayerShooting();
    int getPlayerChecking();
    int getPlayerSaving();

    boolean setPlayerId(int playerId);
    boolean setPlayerName(String playerName);
    boolean setPlayerSkating(int playerSkating);
    boolean setPlayerShooting(int playerShooting);
    boolean setPlayerChecking(int playerChecking);
    boolean setPlayerSaving(int playerSaving);

    String getPlayerPosition();
    boolean setPlayerPosition(String playerPosition);

    boolean isPlayerCaptain();
    boolean setPlayerCaptain(boolean playerCaptain);
}
