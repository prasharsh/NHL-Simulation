package com.datamodel.gameplayconfig;

import com.datamodel.leaguedatamodel.*;

import java.sql.Date;
import java.util.ArrayList;

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
        } else {
            return false;
        }
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
        } else {
            return false;
        }
    }

    @Override
    public void trainPlayers(IGame game) {
        ILeague currentLeague = game.getLeagues().get(0);
        Date currentDate = currentLeague.getCurrentDate();
        IGameplayConfig gameplayConfig = currentLeague.getGamePlayConfig();
        ArrayList<IConference> conferencesInLeague = currentLeague.getConferences();
        for (IConference conference : conferencesInLeague) {
            ArrayList<IDivision> divisionsInConference = conference.getDivisions();
            for (IDivision division : divisionsInConference) {
                ArrayList<ITeam> teamsInDivision = division.getTeams();
                for (ITeam team : teamsInDivision) {
                    IHeadCoach headCoach = team.getHeadCoach();
                    ArrayList<IPlayer> playersInTeam = team.getPlayers();
                    for (IPlayer player : playersInTeam) {
                        increaseStatOrInjurePlayer(player, headCoach, gameplayConfig, currentDate);
                    }
                }
            }
        }
    }

    @Override
    public float getRandomStatIncreaseProbability() {
        return (float) Math.random();
    }

    private void increaseStatOrInjurePlayer(IPlayer player, IHeadCoach coach, IGameplayConfig gameplayConfig, Date currentDate) {
        IInjuryConfig playerInjury = gameplayConfig.getInjury();
        float randomInjuryChance = playerInjury.getRandomInjuryChance();
        Date recoveryDate = playerInjury.getRecoveryDate(currentDate);
        float randomValue = getRandomStatIncreaseProbability();
        float coachSkating = coach.getHeadCoachSkating();
        float coachShooting = coach.getHeadCoachShooting();
        float coachSaving = coach.getHeadCoachSaving();
        float coachChecking = coach.getHeadCoachChecking();
        int maxPlayerStatValue = player.getMaxPlayerStatValue();
        if (randomValue < coachSkating) {
            int playerStatValue = getNewPlayerStatValue(player.getPlayerSkating(), maxPlayerStatValue);
            player.setPlayerSkating(playerStatValue);
        } else if (randomValue > coachSkating) {
            player.checkPlayerInjury(randomInjuryChance, recoveryDate, currentDate);
        }
        if (randomValue < coachShooting) {
            int playerStatValue = getNewPlayerStatValue(player.getPlayerShooting(), maxPlayerStatValue);
            player.setPlayerShooting(playerStatValue);
        } else if (randomValue > coachShooting) {
            player.checkPlayerInjury(randomInjuryChance, recoveryDate, currentDate);
        }
        if (randomValue < coachSaving) {
            int playerStatValue = getNewPlayerStatValue(player.getPlayerSaving(), maxPlayerStatValue);
            player.setPlayerSaving(playerStatValue);
        } else if (randomValue > coachSaving) {
            player.checkPlayerInjury(randomInjuryChance, recoveryDate, currentDate);
        }
        if (randomValue < coachChecking) {
            int playerStatValue = getNewPlayerStatValue(player.getPlayerChecking(), maxPlayerStatValue);
            player.setPlayerChecking(playerStatValue);
        } else if (randomValue > coachChecking) {
            player.checkPlayerInjury(randomInjuryChance, recoveryDate, currentDate);
        }
    }

    private int getNewPlayerStatValue(int statValue, int maxValue) {
        return Math.min((statValue + 1), maxValue);
    }

    private boolean isValidDaysUntilStatIncrease(int days) {
        return days >= 0;
    }

    private boolean isValidDaysTrained(int days) {
        return days >= 0;
    }
}
