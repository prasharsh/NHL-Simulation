package g4dhl;

public interface IPlayer {

    int getPlayerId();
    String getPlayerName();

    boolean setPlayerId(int playerId);
    boolean setPlayerName(String playerName);

    String getPlayerPosition();
    boolean setPlayerPosition(String playerPosition);

    boolean isPlayerCaptain();
    boolean setPlayerCaptain(boolean playerCaptain);
}
