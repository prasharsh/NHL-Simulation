package com.datamodel.leaguedatamodel;

import com.inputoutputmodel.DisplayToUser;
import com.inputoutputmodel.IDisplayToUser;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class FreeAgent implements IPlayer {

    private int freeAgentId;
    private String freeAgentName;
    private String freeAgentPosition;
    private boolean freeAgentIsInjured;
    private boolean freeAgentWasInjured;
    private boolean freeAgentCaptain;
    private boolean freeAgentRetired;
    private boolean freeAgentRosterStatus;
    private int freeAgentBirthYear;
    private int freeAgentBirthDay;
    private int freeAgentBirthMonth;
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
        freeAgentRetired = false;
        freeAgentRosterStatus = false;
    }

    private boolean checkIfFreeAgentNameIsNullOrEmpty(String freeAgentName) {
        return freeAgentName == null || freeAgentName.trim().isEmpty();
    }

    private boolean checkIfFreeAgentPositionIsNullOrEmpty(String freeAgentPosition) {
        return freeAgentPosition == null || freeAgentPosition.trim().isEmpty();
    }

    @Override
    public double getPlayerStrength() {
        String freeAgentPosition = this.getPlayerPosition();
        double freeAgentStrength = 0.0;
        final String FORWARD = "forward";
        final String DEFENSE = "defense";
        final String GOALIE = "goalie";
        switch (freeAgentPosition) {
            case FORWARD:
                freeAgentStrength = this.getPlayerSkating() + this.getPlayerShooting() + (this.getPlayerChecking() / 2.0);
                break;
            case DEFENSE:
                freeAgentStrength = this.getPlayerSkating() + this.getPlayerChecking() + (this.getPlayerShooting() / 2.0);
                break;
            case GOALIE:
                freeAgentStrength = this.getPlayerSkating() + this.getPlayerSaving();
                break;
        }
        if (freeAgentIsInjured) {
            freeAgentStrength = freeAgentStrength / 2;
        }
        if (freeAgentRetired) {
            freeAgentStrength = 0.0;
        }
        return freeAgentStrength;
    }

    @Override
    public boolean setPlayerId(int freeAgentId) {
        this.freeAgentId = freeAgentId;
        return true;
    }

    @Override
    public boolean setPlayerName(String freeAgentName) {
        if (checkIfFreeAgentNameIsNullOrEmpty(freeAgentName)) {
            return false;
        }
        this.freeAgentName = freeAgentName;
        return true;
    }


    @Override
    public void setPlayerBirthMonth(int freeAgentBirthMonth) {
        this.freeAgentBirthMonth = freeAgentBirthMonth;
    }

    @Override
    public void setPlayerBirthDay(int freeAgentBirthDay) {
        this.freeAgentBirthDay = freeAgentBirthDay;
    }

    @Override
    public void setPlayerBirthYear(int freeAgentBirthYear) {
        this.freeAgentBirthYear = freeAgentBirthYear;
    }

    @Override
    public void calculatePlayerAge(LocalDate birthDate, LocalDate currentDate) {
        int TOTAL_DAYS_FOUR_YEAR = 1460;
        int DAYS_IN_YEAR = 365;
        long ageInDays = ChronoUnit.DAYS.between(birthDate, currentDate);
        long leapDays = ageInDays / TOTAL_DAYS_FOUR_YEAR;
        int years = (int) ((ageInDays - leapDays) / DAYS_IN_YEAR);
        int days = (int) (ageInDays - (years * DAYS_IN_YEAR) - leapDays);
        setPlayerAgeYear(years);
        setPlayerAgeDays(days);
    }

    @Override
    public boolean setPlayerAgeYear(int freeAgentAgeYear) {
        this.freeAgentAgeYear = freeAgentAgeYear;
        return true;
    }

    @Override
    public boolean setPlayerAgeDays(int freeAgentAgeDays) {
        this.freeAgentAgeDays = freeAgentAgeDays;
        return true;
    }

    @Override
    public boolean setPlayerSkating(int freeAgentSkating) {
        this.freeAgentSkating = freeAgentSkating;
        return true;
    }

    @Override
    public boolean setPlayerShooting(int freeAgentShooting) {
        this.freeAgentShooting = freeAgentShooting;
        return true;
    }

    @Override
    public boolean setPlayerChecking(int freeAgentChecking) {
        this.freeAgentChecking = freeAgentChecking;
        return true;
    }

    @Override
    public boolean setPlayerSaving(int freeAgentSaving) {
        this.freeAgentSaving = freeAgentSaving;
        return true;
    }

    @Override
    public boolean setPlayerPosition(String freeAgentPosition) {
        if (checkIfFreeAgentPositionIsNullOrEmpty(freeAgentPosition)) {
            return false;
        }
        this.freeAgentPosition = freeAgentPosition;
        return true;
    }

    @Override
    public boolean setPlayerCaptain(boolean playerCaptain) {
        this.freeAgentCaptain = playerCaptain;
        return true;
    }

    @Override
    public int getPlayerId() {
        return freeAgentId;
    }

    @Override
    public String getPlayerName() {
        return freeAgentName;
    }

    @Override
    public String getPlayerPosition() {
        return freeAgentPosition;
    }

    @Override
    public int getPlayerBirthYear() {
        return freeAgentBirthYear;
    }

    @Override
    public int getPlayerBirthMonth() {
        return freeAgentBirthMonth;
    }

    @Override
    public int getPlayerBirthDay() {
        return freeAgentBirthDay;
    }

    @Override
    public Date getPlayerBirthDate() {
        String birthDate = getPlayerBirthYear() + "-" + getPlayerBirthMonth() + "-" + getPlayerBirthDay();
        return Date.valueOf(birthDate);
    }

    @Override
    public int getPlayerAgeYear() {
        return freeAgentAgeYear;
    }

    @Override
    public int getPlayerAgeDays() {
        return freeAgentAgeDays;
    }

    @Override
    public int getPlayerSkating() {
        return freeAgentSkating;
    }

    @Override
    public int getPlayerShooting() {
        return freeAgentShooting;
    }

    @Override
    public int getPlayerChecking() {
        return freeAgentChecking;
    }

    @Override
    public int getPlayerSaving() {
        return freeAgentSaving;
    }

    @Override
    public int getMaxPlayerStatValue() {
        int MAX_PLAYER_STAT_VALUE = 20;
        return MAX_PLAYER_STAT_VALUE;
    }

    @Override
    public boolean setPlayerIsInjured(boolean freeAgentIsInjured) {
        this.freeAgentIsInjured = freeAgentIsInjured;
        return true;
    }

    @Override
    public boolean setPlayerWasInjured(boolean freeAgentWasInjured) {
        this.freeAgentWasInjured = freeAgentWasInjured;
        return true;
    }

    @Override
    public boolean checkPlayerInjury(float randomInjuryChance, Date recoveryDate, Date currentDate, ITeam team) {
        if (isPlayerInjured() || wasPlayerInjured() || isPlayerRetired()) {
            if (isRecoveryDateIsNotNull(getRecoveryDate())) {
                if (currentDate.compareTo(getRecoveryDate()) == 0) {
                    setPlayerIsInjured(false);
                }
            }
        } else {
            if (Math.random() < randomInjuryChance) {
                IDisplayToUser displayToUser = new DisplayToUser();
                displayToUser.displayMsgToUser(getPlayerName() + " from team " + team.getTeamName() + " got injured!!!");
                setPlayerIsInjured(true);
                setPlayerWasInjured(true);
                setRecoveryDate(recoveryDate);
                return true;
            }
        }
        return false;
    }


    public boolean isRecoveryDateIsNotNull(Date recoveryDate) {
        if (recoveryDate == null) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isPlayerInjured() {
        return freeAgentIsInjured;
    }

    @Override
    public boolean wasPlayerInjured() {
        return freeAgentWasInjured;
    }

    @Override
    public boolean setRecoveryDate(Date recoveryDate) {
        this.recoveryDate = recoveryDate;
        return true;
    }

    @Override
    public void setRosterStatus(boolean rosterStatus) {
        this.freeAgentRosterStatus = rosterStatus;
    }

    @Override
    public boolean isPlayerCaptain() {
        return freeAgentCaptain;
    }

    @Override
    public Date getRecoveryDate() {
        return recoveryDate;
    }

    @Override
    public boolean getRosterStatus() {
        return freeAgentRosterStatus;
    }

    @Override
    public void agePlayer(int days) {
        int freeAgentAgeDays = getPlayerAgeDays();
        int freeAgentAgeYear = getPlayerAgeYear();
        if (freeAgentAgeDays + days < 365) {
            setPlayerAgeDays(freeAgentAgeDays + days);
        } else if (freeAgentAgeDays + days > 365) {
            setPlayerAgeDays(freeAgentAgeDays + days - 365);
            setPlayerAgeYear(freeAgentAgeYear + 1);
        }
    }

    @Override
    public boolean isPlayerRetired() {
        return freeAgentRetired;
    }

    @Override
    public boolean setPlayerRetired(boolean playerRetired) {
        this.freeAgentRetired = playerRetired;
        return true;
    }

    @Override
    public void decreasePlayerStat(int statValue) {
        if (getPlayerChecking() > statValue) {
            setPlayerChecking(getPlayerChecking() - statValue);
        }
        if (getPlayerSaving() > statValue) {
            setPlayerSaving(getPlayerSaving() - statValue);
        }
        if (getPlayerShooting() > statValue) {
            setPlayerShooting(getPlayerShooting() - statValue);
        }
        if (getPlayerSkating() > statValue) {
            setPlayerSkating(getPlayerSkating() - statValue);
        }
    }

    @Override
    public boolean isPlayerBirthDay(int month, int day) {
        if (freeAgentBirthMonth == month && freeAgentBirthDay == day) {
            return true;
        }
        return false;
    }

	@Override
	public boolean isNotInPlayingSix() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setNotInPlayingSix(boolean isNotInPlayingSix) {
		// TODO Auto-generated method stub
		
	}
}