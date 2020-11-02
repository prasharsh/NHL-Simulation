package com.datamodel.leaguedatamodel;

import java.sql.Date;
import java.util.ArrayList;

import com.datamodel.gameplayconfig.IGameplayConfig;
import com.datamodel.gameplayconfig.IInjuryConfig;
import com.inputoutputmodel.ITrainingUI;
import com.inputoutputmodel.TrainingUI;

public class Training implements ITraining {

	@Override
	public float getRandomStatIncreaseProbability() {
		return ((float) Math.random());
	}

	@Override
	public void trainPlayers(IGame game) {
		ILeague currentLeague = game.getLeagues().get(0);
		Date currentDate = currentLeague.getCurrentDate();
		ITrainingUI trainingUI = new TrainingUI();
		trainingUI.displayHeader("Stat increase check initiated for all the players on " + currentDate);
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
						increaseStatOrInjurePlayer(player, headCoach, gameplayConfig, currentDate, team);
					}
				}
			}
		}
		trainingUI.displayHeader("Stat increase check completed for all the players");
	}

	private void increaseStatOrInjurePlayer(IPlayer player, IHeadCoach coach, IGameplayConfig gameplayConfig,
			Date currentDate, ITeam team) {
		ITrainingUI trainingUI = new TrainingUI();
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
			int newSkatingValue = getNewPlayerStatValue(player.getPlayerSkating(), maxPlayerStatValue);
			player.setPlayerSkating(newSkatingValue);
			trainingUI.displayStatUpdates(player.getPlayerName(), "Skating", newSkatingValue);
		} else if (randomValue > coachSkating) {
			player.checkPlayerInjury(randomInjuryChance, recoveryDate, currentDate, team);
		}
		if (randomValue < coachShooting) {
			int newShootingValue = getNewPlayerStatValue(player.getPlayerShooting(), maxPlayerStatValue);
			player.setPlayerShooting(newShootingValue);
			trainingUI.displayStatUpdates(player.getPlayerName(), "Shooting", newShootingValue);
		} else if (randomValue > coachShooting) {
			player.checkPlayerInjury(randomInjuryChance, recoveryDate, currentDate, team);
		}
		if (randomValue < coachSaving) {
			int newSavingValue = getNewPlayerStatValue(player.getPlayerSaving(), maxPlayerStatValue);
			player.setPlayerSaving(newSavingValue);
			trainingUI.displayStatUpdates(player.getPlayerName(), "Saving", newSavingValue);
		} else if (randomValue > coachSaving) {
			player.checkPlayerInjury(randomInjuryChance, recoveryDate, currentDate, team);
		}
		if (randomValue < coachChecking) {
			int newCheckingValue = getNewPlayerStatValue(player.getPlayerChecking(), maxPlayerStatValue);
			player.setPlayerChecking(newCheckingValue);
			trainingUI.displayStatUpdates(player.getPlayerName(), "Checking", newCheckingValue);
		} else if (randomValue > coachChecking) {
			player.checkPlayerInjury(randomInjuryChance, recoveryDate, currentDate, team);
		}
	}

	private int getNewPlayerStatValue(int statValue, int maxValue) {
		return Math.min((statValue + 1), maxValue);
	}
}
