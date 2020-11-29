package com.datamodel.leaguedatamodel;

import com.inputoutputmodel.DisplayToUser;
import com.inputoutputmodel.IDisplayToUser;
import com.inputoutputmodel.IPropertyLoader;
import com.inputoutputmodel.PropertyLoader;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class Player implements IPlayer {

    private int playerId;
    private String playerName;
    private String playerPosition;
    private boolean playerCaptain;
    private boolean playerIsInjured;
    private boolean playerWasInjured;
    private boolean playerRetired;
    private boolean playerRosterStatus;
    private int playerBirthYear;
    private int playerBirthDay;
    private int playerBirthMonth;
    private int playerAgeYear;
    private int playerAgeDays;
    private int playerSkating;
    private int playerShooting;
    private int playerChecking;
    private int playerSaving;
    private Date recoveryDate;
    private boolean isNotInPlayingSix = true;
    final String MAX_PLAYER_STAT_VALUE = "maxPlayerStatValue";
    final int TOTAL_DAYS_FOUR_YEAR = 1460;
    final int DAYS_IN_YEAR = 365;


    public Player() {
        playerName = null;
        playerPosition = null;
        playerCaptain = false;
        playerIsInjured = false;
        playerWasInjured = false;
        playerRetired = false;
        recoveryDate = null;
        playerRosterStatus = false;
    }

    private boolean checkIfPlayerNameIsNullOrEmpty(String playerName) {
        return playerName == null || playerName.trim().isEmpty();
    }

    private boolean checkIfPlayerPositionIsNullOrEmpty(String playerPosition) {
        return playerPosition == null || playerPosition.trim().isEmpty();
    }

    @Override
    public int getPlayerId() {
        return playerId;
    }

    @Override
    public String getPlayerName() {
        return playerName;
    }

    @Override
    public int getPlayerBirthYear() {
        return playerBirthYear;
    }

    @Override
    public int getPlayerBirthMonth() {
        return playerBirthMonth;
    }

    @Override
    public int getPlayerBirthDay() {
        return playerBirthDay;
    }

    @Override
    public Date getPlayerBirthDate() {
        String birthDate = getPlayerBirthYear() + "-" + getPlayerBirthMonth() + "-" + getPlayerBirthDay();
        return Date.valueOf(birthDate);
    }

    @Override
    public int getPlayerAgeYear() {
        return playerAgeYear;
    }

    @Override
    public int getPlayerAgeDays() {
        return playerAgeDays;
    }

    @Override
    public int getPlayerSkating() {
        return playerSkating;
    }

    @Override
    public int getPlayerShooting() {
        return playerShooting;
    }

    @Override
    public int getPlayerChecking() {
        return playerChecking;
    }

    @Override
    public int getPlayerSaving() {
        return playerSaving;
    }

    @Override
    public boolean getRosterStatus() {
        return playerRosterStatus;
    }

    @Override
    public double getPlayerStrength() {
        final String FORWARD = "forward";
        final String DEFENSE = "defense";
        final String GOALIE = "goalie";
        String playerPosition = getPlayerPosition();
        double playerStrength = 0.0;
        switch (playerPosition) {
            case FORWARD:
                playerStrength = getPlayerSkating() + getPlayerShooting() + (getPlayerChecking() / 2.0);
                break;
            case DEFENSE:
                playerStrength = getPlayerSkating() + getPlayerChecking() + (getPlayerShooting() / 2.0);
                break;
            case GOALIE:
                playerStrength = getPlayerSkating() + getPlayerSaving();
                break;
        }
        if (playerIsInjured) {
            playerStrength = playerStrength / 2;
        }
        if (playerRetired) {
            playerStrength = 0.0;
        }
        return playerStrength;
    }

    @Override
    public boolean setPlayerName(String playerName) {
        if (checkIfPlayerNameIsNullOrEmpty(playerName))
            return false;
        this.playerName = playerName;
        return true;
    }

    @Override
    public void setPlayerBirthYear(int playerBirthYear) {
        this.playerBirthYear = playerBirthYear;
    }

    @Override
    public void setPlayerBirthMonth(int playerBirthMonth) {
        this.playerBirthMonth = playerBirthMonth;
    }

    @Override
    public void setPlayerBirthDay(int playerBirthDay) {
        this.playerBirthDay = playerBirthDay;
    }

    @Override
    public void calculatePlayerAge(LocalDate birthDate, LocalDate currentDate) {
        long ageInDays = ChronoUnit.DAYS.between(birthDate, currentDate);
        long leapDays = ageInDays / TOTAL_DAYS_FOUR_YEAR;
        int years = (int) ((ageInDays - leapDays) / DAYS_IN_YEAR);
        int days = (int) (ageInDays - (years * DAYS_IN_YEAR) - leapDays);
        setPlayerAgeYear(years);
        setPlayerAgeDays(days);
    }

    @Override
    public boolean setPlayerAgeYear(int playerAgeYear) {
        this.playerAgeYear = playerAgeYear;
        return true;
    }

    @Override
    public boolean setPlayerAgeDays(int playerAgeDays) {
        this.playerAgeDays = playerAgeDays;
        return true;
    }

    @Override
    public boolean setPlayerSkating(int playerSkating) {
        this.playerSkating = playerSkating;
        return true;
    }

    @Override
    public boolean setPlayerShooting(int playerShooting) {
        this.playerShooting = playerShooting;
        return true;
    }

    @Override
    public boolean setPlayerChecking(int playerChecking) {
        this.playerChecking = playerChecking;
        return true;
    }

    @Override
    public boolean setPlayerSaving(int playerSaving) {
        this.playerSaving = playerSaving;
        return true;
    }

    @Override
    public boolean setPlayerId(int playerId) {
        this.playerId = playerId;
        return true;
    }

    @Override
    public String getPlayerPosition() {
        return playerPosition;
    }


    @Override
    public Date getRecoveryDate() {
        return recoveryDate;
    }


    @Override
    public boolean setPlayerPosition(String playerPosition) {
        if (checkIfPlayerPositionIsNullOrEmpty(playerPosition))
            return false;
        this.playerPosition = playerPosition;
        return true;
    }

    @Override
    public boolean isPlayerCaptain() {
        return playerCaptain;
    }

    @Override
    public boolean setPlayerCaptain(boolean playerCaptain) {
        this.playerCaptain = playerCaptain;
        return true;
    }

    @Override
    public boolean setPlayerIsInjured(boolean playerIsInjured) {
        this.playerIsInjured = playerIsInjured;
        return true;
    }

    @Override
    public boolean setPlayerWasInjured(boolean playerWasInjured) {
        this.playerWasInjured = playerWasInjured;
        return true;
    }

    @Override
    public boolean isPlayerInjured() {
        return playerIsInjured;
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
    public boolean wasPlayerInjured() {
        return playerWasInjured;
    }

    @Override
    public boolean setRecoveryDate(Date recoveryDate) {
        this.recoveryDate = recoveryDate;
        return true;
    }

    @Override
    public void setRosterStatus(boolean rosterStatus) {
        this.playerRosterStatus = rosterStatus;
    }

    @Override
    public void agePlayer(int days) {
        int playerAgeDays = getPlayerAgeDays();
        int playerAgeYear = getPlayerAgeYear();
        if (playerAgeDays + days < DAYS_IN_YEAR) {
            setPlayerAgeDays(playerAgeDays + days);
        } else if (playerAgeDays + days > DAYS_IN_YEAR) {
            setPlayerAgeDays(playerAgeDays + days - DAYS_IN_YEAR);
            setPlayerAgeYear(playerAgeYear + 1);
        }
    }

    @Override
    public int getMaxPlayerStatValue() {
        IPropertyLoader propertyLoader = new PropertyLoader();
        return Integer.parseInt(propertyLoader.getPropertyValue(MAX_PLAYER_STAT_VALUE));
    }

    @Override
    public boolean isPlayerRetired() {
        return playerRetired;
    }

    @Override
    public boolean setPlayerRetired(boolean playerRetired) {
        this.playerRetired = playerRetired;
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
        if (playerBirthMonth == month && playerBirthDay == day) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isNotInPlayingSix() {
        return isNotInPlayingSix;
    }

    @Override
    public void setNotInPlayingSix(boolean isNotInPlayingSix) {
        this.isNotInPlayingSix = isNotInPlayingSix;
    }

    @Override
    public String toString() {
        return "Player [playerName=" + playerName + ", playerPosition=" + playerPosition + ", playerRosterStatus="
                + playerRosterStatus + ", playerSkating=" + playerSkating + ", playerShooting=" + playerShooting
                + ", playerChecking=" + playerChecking + "]";
    }
}