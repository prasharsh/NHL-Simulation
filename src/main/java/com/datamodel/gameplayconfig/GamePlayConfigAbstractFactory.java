package com.datamodel.gameplayconfig;

public abstract class GamePlayConfigAbstractFactory {

	private static GamePlayConfigAbstractFactory uniqueInstance = null;

	public static GamePlayConfigAbstractFactory instance() {
		return uniqueInstance;
	}

	public static void setFactory(GamePlayConfigAbstractFactory gamePlayConfigFactory) {
		uniqueInstance = gamePlayConfigFactory;
	}

	public abstract IGamePlayConfig createGamePlayConfig();

	public abstract IAgingConfig createAgingConfig();

	public abstract IGameResolverConfig createGameResolverConfig();

	public abstract IGeneralManagerConfig createGeneralManagerConfig();

	public abstract IInjuryConfig createInjuryConfig();

	public abstract ITrainingConfig createTrainingConfig();

	public abstract ITradingConfig createTradingConfig();
}
