package com.datamodel.gameplayconfig;

public class TrainingConfig implements ITrainingConfig {

	private int trainingId;
	private int daysUntilStatIncreaseCheck;
	private int noOfDaysTrained = 0;

	@Override
	public int getTrainingId() {
		return this.trainingId;
	}

	@Override
	public void setTrainingId(int trainingId) {
		this.trainingId = trainingId;
	}

	@Override
	public int getDaysUntilStatIncreaseCheck() {
		return this.daysUntilStatIncreaseCheck;
	}

	@Override
	public boolean setDaysUntilStatIncreaseCheck(int daysUntilStatIncrease) {
		if (isValidDaysUntilStatIncrease(daysUntilStatIncrease)) {
			this.daysUntilStatIncreaseCheck = daysUntilStatIncrease;
			return true;
		}
		return false;
	}

	@Override
	public int getNoOfDaysTrained() {
		return this.noOfDaysTrained;
	}

	@Override
	public boolean setNoOfDaysTrained(int noOfDaysTrained) {
		if (isValidDaysTrained(noOfDaysTrained)) {
			this.noOfDaysTrained = noOfDaysTrained;
			return true;
		}
		return false;
	}

	private boolean isValidDaysUntilStatIncrease(int days) {
		return (days >= 0);
	}

	private boolean isValidDaysTrained(int days) {
		return (days >= 0);
	}
}
