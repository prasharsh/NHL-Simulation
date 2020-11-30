package com.inputoutputmodel;

public class InputOutputModelFactory extends InputOutputModelAbstractFactory {

	private ICreateTeamUI createTeamUI = null;
	private IDisplayRoaster displayRoaster = null;
	private IDisplayToUser displayToUser = null;
	private IDisplayTradingOffers displayTradingOffers = null;
	private IPropertyLoader propertyLoader = null;
	private ITrainingUI trainingUI = null;

	@Override
	public ICreateTeamUI createTeamUI() {
		if(createTeamUI == null) {
			createTeamUI = new CreateTeamUI();
		}
		return createTeamUI;
	}

	@Override
	public IDisplayRoaster createDisplayRoster() {
		if(displayRoaster == null) {
			displayRoaster = new DisplayRoster();
		}
		return displayRoaster;
	}

	@Override
	public IDisplayToUser createDisplayToUser() {
		if(displayToUser == null) {
			displayToUser = new DisplayToUser();
		}
		return displayToUser;
	}

	@Override
	public IDisplayTradingOffers createDisplayTradingOffers() {
		if(displayTradingOffers == null) {
			displayTradingOffers = new DisplayTradingOffers();
		}
		return displayTradingOffers;
	}

	@Override
	public IPropertyLoader createPropertyLoader() {
		if(propertyLoader == null) {
			propertyLoader = new PropertyLoader();
		}
		return propertyLoader;
	}

	@Override
	public ITrainingUI createTrainingUI() {
		if(trainingUI == null) {
			trainingUI = new TrainingUI();
		}
		return trainingUI;
	}
}
