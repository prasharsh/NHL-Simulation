package com.datamodel.gameplayconfig;

public interface ITrainingConfig {

	int getTrainingId();

	void setTrainingId(int trainingId);

	int getDaysUntilStatIncreaseCheck();

	boolean setDaysUntilStatIncreaseCheck(int daysUntilStatIncrease);

	int getNoOfDaysTrained();

	boolean setNoOfDaysTrained(int NoOfDaysTrained);
}