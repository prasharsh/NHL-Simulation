package g4dhl;

import java.sql.Date;

public class FreeAgent implements IFreeAgent {

	private int freeAgentId;
	private String freeAgentName;
	private String freeAgentPosition;
	private boolean freeAgentIsInjured;
	private boolean freeAgentWasInjured;

	private int freeAgentAgeYear;
	private int freeAgentAgeDays;
	private int freeAgentSkating;
	private int freeAgentShooting;
	private int freeAgentChecking;
	private int freeAgentSaving;
	private Date recoveryDate;

	public FreeAgent() {
		freeAgentName = null;
		freeAgentPosition = null;
	}

	private boolean checkIfFreeAgentNameIsNullOrEmpty(String freeAgentName) {
		return freeAgentName == null || freeAgentName.trim().isEmpty();
	}

	private boolean checkIfFreeAgentPositionIsNullOrEmpty(String freeAgentPosition) {
		return freeAgentPosition == null || freeAgentPosition.trim().isEmpty();
	}

	enum Position {
		FORWARD, DEFENSE, GOALIE
	}

	@Override
	public double getFreeAgentStrength() {
		String freeAgentPosition = this.getFreeAgentPosition().toUpperCase();
		double freeAgentStrength = 0.0;
		FreeAgent.Position position = FreeAgent.Position.valueOf(freeAgentPosition);
		if (position == FreeAgent.Position.FORWARD) {
			freeAgentStrength = this.getFreeAgentSkating() + this.getFreeAgentShooting()
					+ (this.getFreeAgentChecking() / 2.0);
		} else if (position == FreeAgent.Position.DEFENSE) {
			freeAgentStrength = this.getFreeAgentSkating() + this.getFreeAgentChecking()
					+ (this.getFreeAgentShooting() / 2.0);
		} else if (position == FreeAgent.Position.GOALIE) {
			freeAgentStrength = this.getFreeAgentSkating() + this.getFreeAgentSaving();
		}
		return freeAgentStrength;
	}

	@Override
	public boolean setFreeAgentId(int freeAgentId) {
		this.freeAgentId = freeAgentId;
		return true;
	}

	@Override
	public boolean setFreeAgentName(String freeAgentName) {
		if (checkIfFreeAgentNameIsNullOrEmpty(freeAgentName))
			return false;
		this.freeAgentName = freeAgentName;
		return true;
	}

	@Override
	public boolean setFreeAgentAgeYear(int freeAgentAgeYear) {
		this.freeAgentAgeYear = freeAgentAgeYear;
		return true;
	}

	@Override
	public boolean setFreeAgentAgeDays(int freeAgentAgeDays) {
		this.freeAgentAgeDays = freeAgentAgeDays;
		return true;
	}

	@Override
	public boolean setFreeAgentSkating(int freeAgentSkating) {
		this.freeAgentSkating = freeAgentSkating;
		return true;
	}

	@Override
	public boolean setFreeAgentShooting(int freeAgentShooting) {
		this.freeAgentShooting = freeAgentShooting;
		return true;
	}

	@Override
	public boolean setFreeAgentChecking(int freeAgentChecking) {
		this.freeAgentChecking = freeAgentChecking;
		return true;
	}

	@Override
	public boolean setFreeAgentSaving(int freeAgentSaving) {
		this.freeAgentSaving = freeAgentSaving;
		return true;
	}

	@Override
	public boolean setFreeAgentPosition(String freeAgentPosition) {
		if (checkIfFreeAgentPositionIsNullOrEmpty(freeAgentPosition))
			return false;
		this.freeAgentPosition = freeAgentPosition;
		return true;
	}

	@Override
	public int getFreeAgentId() {
		return freeAgentId;
	}

	@Override
	public String getFreeAgentName() {
		return freeAgentName;
	}

	@Override
	public String getFreeAgentPosition() {
		return freeAgentPosition;
	}

	@Override
	public int getFreeAgentAgeYear() {
		return freeAgentAgeYear;
	}

	@Override
	public int getFreeAgentAgeDays() {
		return freeAgentAgeDays;
	}

	@Override
	public int getFreeAgentSkating() {
		return freeAgentSkating;
	}

	@Override
	public int getFreeAgentShooting() {
		return freeAgentShooting;
	}

	@Override
	public int getFreeAgentChecking() {
		return freeAgentChecking;
	}

	@Override
	public int getFreeAgentSaving() {
		return freeAgentSaving;
	}

	@Override
	public boolean setFreeAgentIsInjured(boolean freeAgentIsInjured) {
		this.freeAgentIsInjured = freeAgentIsInjured;
		return true;
	}

	@Override
	public boolean setFreeAgentWasInjured(boolean freeAgentWasInjured) {
		this.freeAgentWasInjured = freeAgentWasInjured;
		return true;
	}

	@Override
	public boolean isFreeAgentInjured() {
		return freeAgentIsInjured;
	}

	@Override
	public boolean wasFreeAgentInjured() {
		return freeAgentWasInjured;
	}

	@Override
	public boolean setRecoveryDate(Date recoveryDate) {
		this.recoveryDate = recoveryDate;
		return true;
	}

	@Override
	public Date getRecoveryDate() {
		return recoveryDate;
	}

	@Override
	public void ageFreeAgent(int days) {
		int freeAgentAgeDays = getFreeAgentAgeDays();
		int freeAgentAgeYear = getFreeAgentAgeYear();
		if (freeAgentAgeDays + days < 365) {
			setFreeAgentAgeDays(freeAgentAgeDays + days);
		} else if (freeAgentAgeDays + days > 365) {
			setFreeAgentAgeDays(freeAgentAgeDays + days - 365);
			setFreeAgentAgeYear(freeAgentAgeYear + 1);
		}
	}
}
