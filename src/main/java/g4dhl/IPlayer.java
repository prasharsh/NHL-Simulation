package g4dhl;

public interface IPlayer {

    int getPlayerId();
    String getPlayerName();

    void setPlayerId(int playerId);
    void setPlayerName(String playerName);

    String getPlayerPosition();
    void setPlayerPosition(String playerPosition);

    boolean isPlayerCaptain();
    void setPlayerCaptain(boolean playerCaptain);
}
