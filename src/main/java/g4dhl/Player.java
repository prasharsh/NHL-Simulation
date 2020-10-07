package g4dhl;

public class Player implements IPlayer{

    private String playerName;
    private String playerPosition;
    private boolean playerCaptain;

    @Override
    public int getPlayerId() {
        return 0;
    }

    @Override
    public String getPlayerName() {
        return playerName;
    }

    @Override
    public void setPlayerId(int id) {

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
