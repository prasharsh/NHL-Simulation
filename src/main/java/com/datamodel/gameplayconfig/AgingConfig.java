package com.datamodel.gameplayconfig;

public class AgingConfig implements IAgingConfig {

	private int agingId;
	private int averageRetirementAge;
	private int maximumAge;

	@Override
	public int getAverageRetirementAge() {
		return this.averageRetirementAge;
	}

	@Override
	public boolean setAverageRetirementAge(int averageRetirementAge) {
		if (isValidAge(averageRetirementAge)) {
			this.averageRetirementAge = averageRetirementAge;
			return true;
		}
		return false;
	}

	@Override
	public int getMaximumAge() {
		return this.maximumAge;
	}

	@Override
	public boolean setMaximumAge(int maximumAge) {
		if (isValidAge(maximumAge)) {
			this.maximumAge = maximumAge;
			return true;
		}
		return false;
	}

	@Override
	public int getAgingId() {
		return this.agingId;
	}

	@Override
	public void setAgingId(int agingId) {
		this.agingId = agingId;
	}

	private boolean isValidAge(int givenAge) {
		return givenAge > 0;
	}

	@Override
	public boolean isPlayerRetires(int playerAgeYear) {
		if (playerAgeYear >= maximumAge) {
			return true;
		} else if (playerAgeYear > averageRetirementAge && Math.random() < 0.6) {
			return true;
		} else {
			return averageRetirementAge - playerAgeYear < 2 && Math.random() < 0.4;
		}
	}
}