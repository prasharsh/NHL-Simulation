package g4dhl;

public class Player implements IPlayer{

    String playerName;
    String playerPosition;
    boolean playerCaptain;

    public Player(){
        playerName = null;
        playerPosition = null;
        playerCaptain = false;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerPosition() {
        return playerPosition;
    }

    public void setPlayerPosition(String playerPosition) {
        this.playerPosition = playerPosition;
    }

    public boolean isPlayerCaptain() {
        return playerCaptain;
    }

    public void setPlayerCaptain(boolean playerCaptain) {
        this.playerCaptain = playerCaptain;
    }
}
