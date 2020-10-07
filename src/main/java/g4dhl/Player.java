package g4dhl;

import g4db.IGameDB;

public class Player implements IPlayer{

    private int playerId;
    private String playerName;
    private String playerPosition;
    private boolean playerCaptain;

    public Player(){
        playerName = null;
        playerPosition = null;
        playerCaptain = false;
    }

    @Override
    public int getPlayerId() {
        return playerId;
    }

    @Override
    public String getPlayerName() {
        return playerName;
    }

    @Override
    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    @Override
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    @Override
    public String getPlayerPosition() {
        return playerPosition;
    }

    @Override
    public void setPlayerPosition(String playerPosition) {
        this.playerPosition = playerPosition;
    }

    @Override
    public boolean isPlayerCaptain() {
        return playerCaptain;
    }

    @Override
    public void setPlayerCaptain(boolean playerCaptain) {
        this.playerCaptain = playerCaptain;
    }
}
