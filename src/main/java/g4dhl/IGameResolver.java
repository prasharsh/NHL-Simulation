package g4dhl;

public interface IGameResolver {
    int getGameResolverId();
    void setGameResolverId(int gameResolverId);

    float getRandomWinChance();
    boolean setRandomWinChance(float randomWinChance);
}
