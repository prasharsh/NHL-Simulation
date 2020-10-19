package g4dhl;

public class GameResolver implements IGameResolver {
    private int gameResolverId;
    private float randomWinChance;

    @Override
    public int getGameResolverId() {
        return this.gameResolverId;
    }

    @Override
    public void setGameResolverId(int gameResolverId) {
        this.gameResolverId = gameResolverId;
    }

    @Override
    public float getRandomWinChance() {
        return this.randomWinChance;
    }

    @Override
    public boolean setRandomWinChance(float randomWinChance) {
        if (isValidWinChanceValue(randomWinChance)) {
            this.randomWinChance = randomWinChance;
            return true;
        } else {
            return false;
        }
    }

    private boolean isValidWinChanceValue(float randomWinChance) {
        return randomWinChance >= 0 && randomWinChance <= 1;
    }
}
