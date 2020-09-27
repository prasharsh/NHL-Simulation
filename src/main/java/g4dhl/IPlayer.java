package g4dhl;

public interface IPlayer {
    void setPlayerName(String playerName);
    void setPlayerPosition(String playerPosition);
    void setPlayerCaptain(boolean playerCaptain);
    String getPlayerName();
    String getPlayerPosition();
    boolean isPlayerCaptain();
}
