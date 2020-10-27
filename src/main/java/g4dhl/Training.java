package g4dhl;

import java.util.ArrayList;

public class Training implements ITraining {
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
	public void increaseStatOrInjurePlayer(IGame game) {
		ILeague currentLeague = game.getLeagues().get(0);
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
						updatePlayerStatus(player, headCoach, gameplayConfig);
					}
				}
			}
		}
	}

	private void updatePlayerStatus(IPlayer player, IHeadCoach coach, IGameplayConfig gameplayConfig) {
		IInjury playerInjury = gameplayConfig.getInjury();
		float randomValue = (float) Math.random();
		boolean isPlayerInjured = player.isPlayerInjured();
		float coachSkating = coach.getHeadCoachSkating();
		float coachShooting = coach.getHeadCoachShooting();
		float coachSaving = coach.getHeadCoachSaving();
		float coachChecking = coach.getHeadCoachChecking();
		if (!isPlayerInjured || randomValue <= coachSkating) {
			player.setPlayerSkating(player.getPlayerSkating() + 1);
		} else if (isPlayerInjured || randomValue > coachSkating) {
			isPlayerInjured = true;
			player.checkPlayerInjury(playerInjury.getRandomInjuryChance());
		}
		if (!isPlayerInjured || randomValue <= coachShooting) {
			player.setPlayerShooting(player.getPlayerShooting() + 1);
		} else if (isPlayerInjured || randomValue > coachShooting) {
			isPlayerInjured = true;
			player.checkPlayerInjury(playerInjury.getRandomInjuryChance());
		}
		if (!isPlayerInjured || randomValue <= coachSaving) {
			player.setPlayerSaving(player.getPlayerSaving() + 1);
		} else if (isPlayerInjured || randomValue > coachSaving) {
			isPlayerInjured = true;
			player.checkPlayerInjury(playerInjury.getRandomInjuryChance());
		}
		if (!isPlayerInjured || randomValue <= coachChecking) {
			player.setPlayerChecking(player.getPlayerChecking() + 1);
		} else if (isPlayerInjured || randomValue > coachChecking) {
			isPlayerInjured = true;
			player.checkPlayerInjury(playerInjury.getRandomInjuryChance());
		}
	}

	private boolean isValidDaysUntilStatIncrease(int days) {
		return days >= 0;
	}

	private boolean isValidDaysTrained(int days) {
		return days >= 0;
	}
}
