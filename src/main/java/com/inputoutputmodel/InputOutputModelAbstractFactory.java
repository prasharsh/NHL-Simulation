package com.inputoutputmodel;

public abstract class InputOutputModelAbstractFactory {

	private static InputOutputModelAbstractFactory uniqueInstance = null;

	public static InputOutputModelAbstractFactory instance() {
		return uniqueInstance;
	}

	public static void setFactory(InputOutputModelAbstractFactory inputOutputModelFactory) {
		uniqueInstance = inputOutputModelFactory;
	}

	public abstract ICreateTeamUI createTeamUI();

	public abstract IDisplayRoaster createDisplayRoster();

	public abstract IDisplayToUser createDisplayToUser();

	public abstract IDisplayTradingOffers createDisplayTradingOffers();

	public abstract IPropertyLoader createPropertyLoader();

	public abstract ITrainingUI createTrainingUI();

}
