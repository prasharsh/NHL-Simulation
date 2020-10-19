package g4dhl;

public interface IGameplayConfig {
    int getGameConfigId();
    void setGameConfigId(int gameConfigId);

    IAging getAging();
    void setAging(IAging aging);

    IInjury getInjury();
    void setInjury(IInjury injury);

    IGameResolver getGameResolver();
    void setGameResolver(IGameResolver gameResolver);

    ITraining getTraining();
    void setTraining(ITraining training);

    ITrading getTrading();
    void setTrading(ITrading trading);
}
