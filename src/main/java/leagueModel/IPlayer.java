package leagueModel;

public interface IPlayer {
    String getPlayerName();
    void setPlayerName(String playerName);
    String getPlayerPosition();
    void setPlayerPosition(String playerPosition);
    boolean isPlayerCaptain();
    void setPlayerCaptain(boolean playerCaptain);
}
