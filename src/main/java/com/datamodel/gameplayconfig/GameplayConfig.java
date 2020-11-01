package com.datamodel.gameplayconfig;

public class GameplayConfig implements IGameplayConfig {
	private int gameConfigId;
	private IAgingConfig aging;
	private IInjuryConfig injury;
	private IGameResolverConfig gameResolver;
	private ITrainingConfig training;
	private ITradingConfig trading;

	@Override
	public int getGameConfigId() {
		return this.gameConfigId;
	}

	@Override
	public void setGameConfigId(int gameConfigId) {
		this.gameConfigId = gameConfigId;
	}

	@Override
	public IAgingConfig getAging() {
		return this.aging;
	}

	@Override
	public void setAging(IAgingConfig aging) {
		this.aging = aging;
	}

	@Override
	public IInjuryConfig getInjury() {
		return this.injury;
	}

	@Override
	public void setInjury(IInjuryConfig injury) {
		this.injury = injury;
	}

	@Override
	public IGameResolverConfig getGameResolver() {
		return this.gameResolver;
	}

	@Override
	public void setGameResolver(IGameResolverConfig gameResolver) {
		this.gameResolver = gameResolver;
	}

	@Override
	public ITrainingConfig getTraining() {
		return this.training;
	}

	@Override
	public void setTraining(ITrainingConfig training) {
		this.training = training;
	}

	@Override
	public ITradingConfig getTrading() {
		return this.trading;
	}

	@Override
	public void setTrading(ITradingConfig trading) {
		this.trading = trading;
	}
}
