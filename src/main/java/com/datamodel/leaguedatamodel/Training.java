package com.datamodel.leaguedatamodel;
import java.sql.Date;

import com.datamodel.gameplayconfig.IGamePlayConfig;
import com.datamodel.gameplayconfig.IInjuryConfig;
import com.datamodel.trophysystem.CoachStandingSubject;
import com.inputoutputmodel.ITrainingUI;
import com.inputoutputmodel.TrainingUI;

import java.util.List;

public class Training implements ITraining {

	private ITrainingUI trainingUI;
	private static CoachStandingSubject coachStandingSubject = CoachStandingSubject.instance();

	public Training(){
		trainingUI = new TrainingUI();
	}

	@Override
	public float getRandomStatIncreaseProbability() {
		return ((float) Math.random());
	}

	@Override
	public void trainPlayers(IGame game) {
		ILeague currentLeague = game.getLeagues().get(0);
		Date currentDate = currentLeague.getCurrentDate();
		trainingUI.displayHeader("Stat increase check initiated for all the players on " + currentDate);
		IGamePlayConfig gameplayConfig = currentLeague.getGamePlayConfig();
		List<IConference> conferencesInLeague = currentLeague.getConferences();
		for (IConference conference : conferencesInLeague) {
			List<IDivision> divisionsInConference = conference.getDivisions();
			for (IDivision division : divisionsInConference) {
				List<ITeam> teamsInDivision = division.getTeams();
				for (ITeam team : teamsInDivision) {
					IHeadCoach headCoach = team.getHeadCoach();
					List<IPlayer> playersInTeam = team.getPlayers();
					for (IPlayer player : playersInTeam) {
						increaseStatOrInjurePlayer(player, headCoach, gameplayConfig, currentDate, team);
					}
				}
			}
		}
		trainingUI.displayHeader("Stat increase check completed for all the players");
	}

	private void increaseStatOrInjurePlayer(IPlayer player, IHeadCoach coach, IGamePlayConfig gameplayConfig,
											Date currentDate, ITeam team) {
		IInjuryConfig playerInjury = gameplayConfig.getInjury();
		float randomInjuryChance = playerInjury.getRandomInjuryChance();
		Date recoveryDate = playerInjury.getRecoveryDate(currentDate);
		int maxPlayerStatValue = player.getMaxPlayerStatValue();
		updatePlayerSkating(player, coach, maxPlayerStatValue, randomInjuryChance, recoveryDate, currentDate, team);
		updatePlayerShooting(player, coach, maxPlayerStatValue, randomInjuryChance, recoveryDate, currentDate, team);
		updatePlayerChecking(player, coach, maxPlayerStatValue, randomInjuryChance, recoveryDate, currentDate, team);
		updatePlayerSaving(player, coach, maxPlayerStatValue, randomInjuryChance, recoveryDate, currentDate, team);
	}

	private void updatePlayerSkating(IPlayer player, IHeadCoach coach, int maxPlayerStatValue, float injuryChance,
									 Date recoveryDate, Date currentDate, ITeam team) {
		float randomValue = getRandomStatIncreaseProbability();
		float coachSkating = coach.getHeadCoachSkating();
		if (randomValue <= coachSkating) {
			int newSkatingValue = getNewPlayerStatValue(player.getPlayerSkating(), maxPlayerStatValue);
			player.setPlayerSkating(newSkatingValue);
			trainingUI.displayStatUpdates(player.getPlayerName(), "Skating", newSkatingValue);
			coachStandingSubject.notifyCoachStanding(coach);
		} else {
			player.checkPlayerInjury(injuryChance, recoveryDate, currentDate, team);
		}
	}

	private void updatePlayerShooting(IPlayer player, IHeadCoach coach, int maxPlayerStatValue, float injuryChance,
									  Date recoveryDate, Date currentDate, ITeam team) {
		float randomValue = getRandomStatIncreaseProbability();
		float coachShooting = coach.getHeadCoachShooting();
		if (randomValue < coachShooting) {
			int newShootingValue = getNewPlayerStatValue(player.getPlayerShooting(), maxPlayerStatValue);
			player.setPlayerShooting(newShootingValue);
			trainingUI.displayStatUpdates(player.getPlayerName(), "Shooting", newShootingValue);
			coachStandingSubject.notifyCoachStanding(coach);
		} else {
			player.checkPlayerInjury(injuryChance, recoveryDate, currentDate, team);
		}
	}

	private void updatePlayerChecking(IPlayer player, IHeadCoach coach, int maxPlayerStatValue, float injuryChance,
									  Date recoveryDate, Date currentDate, ITeam team) {
		float randomValue = getRandomStatIncreaseProbability();
		float coachChecking = coach.getHeadCoachChecking();
		if (randomValue < coachChecking) {
			int newCheckingValue = getNewPlayerStatValue(player.getPlayerChecking(), maxPlayerStatValue);
			player.setPlayerChecking(newCheckingValue);
			trainingUI.displayStatUpdates(player.getPlayerName(), "Checking", newCheckingValue);
			coachStandingSubject.notifyCoachStanding(coach);
		} else {
			player.checkPlayerInjury(injuryChance, recoveryDate, currentDate, team);
		}
	}

	private void updatePlayerSaving(IPlayer player, IHeadCoach coach, int maxPlayerStatValue, float injuryChance,
									Date recoveryDate, Date currentDate, ITeam team) {
		float randomValue = getRandomStatIncreaseProbability();
		float coachSaving = coach.getHeadCoachSaving();
		if (randomValue < coachSaving) {
			int newSavingValue = getNewPlayerStatValue(player.getPlayerSaving(), maxPlayerStatValue);
			player.setPlayerSaving(newSavingValue);
			trainingUI.displayStatUpdates(player.getPlayerName(), "Saving", newSavingValue);
			coachStandingSubject.notifyCoachStanding(coach);
		} else {
			player.checkPlayerInjury(injuryChance, recoveryDate, currentDate, team);
		}
	}

	private int getNewPlayerStatValue(int statValue, int maxValue) {
		return Math.min((statValue + 1), maxValue);
	}
}