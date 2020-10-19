package g4dhl;

public class GameplayConfig implements IGameplayConfig {
    private int gameConfigId;
    private IAging aging;
    private IInjury injury;
    private IGameResolver gameResolver;
    private ITraining training;
    private ITrading trading;

    @Override
    public int getGameConfigId() {
        return this.gameConfigId;
    }

    @Override
    public void setGameConfigId(int gameConfigId) {
        this.gameConfigId = gameConfigId;
    }

    @Override
    public IAging getAging() {
        return this.aging;
    }

    @Override
    public void setAging(IAging aging) {
        this.aging = aging;
    }

    @Override
    public IInjury getInjury() {
        return this.injury;
    }

    @Override
    public void setInjury(IInjury injury) {
        this.injury = injury;
    }

    @Override
    public IGameResolver getGameResolver() {
        return this.gameResolver;
    }

    @Override
    public void setGameResolver(IGameResolver gameResolver) {
        this.gameResolver = gameResolver;
    }

    @Override
    public ITraining getTraining() {
        return this.training;
    }

    @Override
    public void setTraining(ITraining training) {
        this.training = training;
    }

    @Override
    public ITrading getTrading() {
        return this.trading;
    }

    @Override
    public void setTrading(ITrading trading) {
        this.trading = trading;
    }
}
