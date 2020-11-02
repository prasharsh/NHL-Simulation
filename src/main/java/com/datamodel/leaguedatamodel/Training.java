package com.datamodel.leaguedatamodel;

import java.sql.Date;
import java.util.ArrayList;

import com.datamodel.gameplayconfig.IGameplayConfig;
import com.datamodel.gameplayconfig.IInjuryConfig;

public class Training implements ITraining {

	@Override
	public float getRandomStatIncreaseProbability() {
		return ((float) Math.random());
	}

	@Override
	public void trainPlayers(IGame game) {
		ILeague currentLeague = game.getLeagues().get(0);
		Date currentDate = currentLeague.getCurrentDate();
		System.out.print("---------------------------------------------");
		System.out.print(" Stat increase check initiated for all the players on " + currentDate + " ");
		System.out.println("---------------------------------------------");
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
		System.out.print("---------------------------------------------");
		System.out.print(" Stat increase check completed for all the players ");
		System.out.println("---------------------------------------------");
	}

	private void increaseStatOrInjurePlayer(IPlayer player, IHeadCoach coach, IGameplayConfig gameplayConfig,
			Date currentDate, ITeam team) {
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
			System.out.println("Skating of " + player.getPlayerName() + " has been updated to " + newSkatingValue);
		} else if (randomValue > coachSkating) {
			player.checkPlayerInjury(randomInjuryChance, recoveryDate, currentDate, team);
		}
		if (randomValue < coachShooting) {
			int newShootingValue = getNewPlayerStatValue(player.getPlayerShooting(), maxPlayerStatValue);
			player.setPlayerShooting(newShootingValue);
			System.out.println("Shooting of " + player.getPlayerName() + " has been updated to " + newShootingValue);
		} else if (randomValue > coachShooting) {
			player.checkPlayerInjury(randomInjuryChance, recoveryDate, currentDate, team);
		}
		if (randomValue < coachSaving) {
			int newSavingValue = getNewPlayerStatValue(player.getPlayerSaving(), maxPlayerStatValue);
			player.setPlayerSaving(newSavingValue);
			System.out.println("Saving of " + player.getPlayerName() + " has been updated to " + newSavingValue);
		} else if (randomValue > coachSaving) {
			player.checkPlayerInjury(randomInjuryChance, recoveryDate, currentDate, team);
		}
		if (randomValue < coachChecking) {
			int newCheckingValue = getNewPlayerStatValue(player.getPlayerChecking(), maxPlayerStatValue);
			player.setPlayerChecking(newCheckingValue);
			System.out.println("Checking of " + player.getPlayerName() + " has been updated to " + newCheckingValue);
		} else if (randomValue > coachChecking) {
			player.checkPlayerInjury(randomInjuryChance, recoveryDate, currentDate, team);
		}
	}

	private int getNewPlayerStatValue(int statValue, int maxValue) {
		return Math.min((statValue + 1), maxValue);
	}
}
