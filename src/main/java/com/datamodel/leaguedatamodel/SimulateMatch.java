package com.datamodel.leaguedatamodel;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.datamodel.trophysystem.GoalsSavedSubject;
import com.datamodel.trophysystem.GoalsScoredSubject;
import com.datamodel.trophysystem.PlayerPenaltySubject;
import com.datamodel.trophysystem.TeamStandingSubject;

public class SimulateMatch implements ISimulateMatch {

	final static Logger logger = Logger.getLogger(SimulateMatch.class);

	private static final String FORWARD = "forward";
	private static final String DEFENSE = "defense";
	private static final String GOALIE = "goalie";
	private static final int AVG_SHOTS = 30;
	private static final int NO_OF_PERIODS = 3;
	private static final int MINUTES_IN_PERIOD =20;
	private static final int TIME_BETWEEN_SUSTITUTION = 18;
	
	@Override
	public boolean simulateMatchResult(IGameSchedule gameSchedule, IGame game, double penaltyChance) {
		ITeam team = gameSchedule.getTeamA();
		ITeam opponentTeam = gameSchedule.getTeamB();
		IPlayer team1Goalie = null;
		IPlayer team2Goalie = null;
		List<IPlayer> team1Forwards = new ArrayList<>();
		List<IPlayer> team1Defensemen = new ArrayList<>();
		List<IPlayer> team2Forwards = new ArrayList<>();
		List<IPlayer> team2Defensemen = new ArrayList<>();
		List<IPlayer> team1Playing6 = new ArrayList<>();
		List<IPlayer> team2Playing6 = new ArrayList<>();

		GoalsSavedSubject goalsSavedSubject = GoalsSavedSubject.instance();
		GoalsScoredSubject goalsScoredSubject = GoalsScoredSubject.instance();
		PlayerPenaltySubject playerPenaltySubject = PlayerPenaltySubject.instance();
		TeamStandingSubject teamStandingSubject = TeamStandingSubject.instance();

		int period = 0;
		int periodClock = 0;
		int time = 0;
		int team1SkatingStats = 0;
		int team2SkatingStats = 0;
		int team1AvgShotsOnGoal = AVG_SHOTS;
		int team2AvgShotsOnGoal = AVG_SHOTS;
		int team1GoalCount = 0;
		int team2GoalCount = 0;
		int shotsCount = 0;
		int penaltyCount = 0;
		int saveCount = 0;
		int goalsCount = 0;
		int noOfPeriods = NO_OF_PERIODS;
		int minsInPeriods = MINUTES_IN_PERIOD;
		
		boolean isOpponentTeamWin = false;
		boolean isOpponentTeamLoss = false;
		boolean isMatchWinnerComputed = false;
		boolean isTeam1PowerPlay = false;
		boolean isTeam2PowerPlay = false;
		boolean isPlayingSixKnown = false;

		while (period < noOfPeriods) {
			periodClock = 0;
			while (periodClock < minsInPeriods) {
				if (time == 0) {
					team1Playing6 = team.getPlayingSix();
					team2Playing6 = opponentTeam.getPlayingSix();
					isPlayingSixKnown = true;
				} else if (time % TIME_BETWEEN_SUSTITUTION == 0) {
					team1Playing6 = team.getPlayingSix();
					team2Playing6 = opponentTeam.getPlayingSix();
				}

				if (isPlayingSixKnown) {
					for (IPlayer player : team1Playing6) {
						team1SkatingStats += player.getPlayerSkating();
						if (player.getPlayerPosition().equals(FORWARD)) {
							team1Forwards.add(player);
						} else if (player.getPlayerPosition().equals(DEFENSE)) {
							team1Defensemen.add(player);
						} else if (player.getPlayerPosition().equals(GOALIE)) {
							team1Goalie = player;
						}
					}
					for (IPlayer player : team2Playing6) {
						team2SkatingStats += player.getPlayerSkating();
						if (player.getPlayerPosition().equals(FORWARD)) {
							team2Forwards.add(player);
						} else if (player.getPlayerPosition().equals(DEFENSE)) {
							team2Defensemen.add(player);
						} else if (player.getPlayerPosition().equals(GOALIE)) {
							team2Goalie = player;
						}
					}

					int shotDiff = (int) (Math.random() * 10);
					if (shotDiff == 0) {
						shotDiff = 1;
					}
					if (team1SkatingStats > team2SkatingStats) {
						team1AvgShotsOnGoal += shotDiff;
						team2AvgShotsOnGoal -= shotDiff;
						team1GoalCount = (int) team1AvgShotsOnGoal / 10 + 1;
						team2GoalCount = (int) team2AvgShotsOnGoal / 10;
					} else {
						team1AvgShotsOnGoal -= shotDiff;
						team2AvgShotsOnGoal += shotDiff;
						team1GoalCount = (int) team1AvgShotsOnGoal / 10;
						team2GoalCount = (int) team2AvgShotsOnGoal / 10 + 1;
					}

					shotsCount = team1AvgShotsOnGoal + team2AvgShotsOnGoal;
					goalsCount = team1GoalCount + team2GoalCount;

					if (team1GoalCount > team2GoalCount) {
						opponentTeam.setLossPointCount(team.getLossPointCount() + 1);
						isOpponentTeamLoss = true;
						isMatchWinnerComputed = true;
					} else {
						team.setLossPointCount(team.getLossPointCount() + 1);
						isOpponentTeamWin = true;
						isMatchWinnerComputed = true;
					}

					isPlayingSixKnown = false;
				}
				try {
					if (time % 2 == 0 && team1AvgShotsOnGoal > 0) {
						if (Math.random() > penaltyChance) {
							int defensemenIndex = (int) ((Math.random() * 10) % 2);
							playerPenaltySubject.notifyPenaltyPublisher(team2Defensemen.get(defensemenIndex));
							isTeam1PowerPlay = true;
							penaltyCount += 1;
						}
						if (isTeam1PowerPlay && team1GoalCount > 0) {
							int forwardsIndex = (int) ((Math.random() * 10) % 3);
							goalsScoredSubject.notifyGoalsScoredPublisher(team1Forwards.get(forwardsIndex));
							team1GoalCount--;
						} else {
							goalsSavedSubject.notifyGoalsSavedPublisher(team2Goalie);
							saveCount += 1;
						}
						team1AvgShotsOnGoal--;
					} else if (team2AvgShotsOnGoal > 0) {
						if (Math.random() > penaltyChance) {
							int defensemenIndex = (int) ((Math.random() * 10) % 2);
							playerPenaltySubject.notifyPenaltyPublisher(team1Defensemen.get(defensemenIndex));
							isTeam2PowerPlay = true;
							penaltyCount += 1;
						}
						if (isTeam2PowerPlay && team2GoalCount > 0) {
							int forwardsIndex = (int) ((Math.random() * 10) % 3);
							goalsScoredSubject.notifyGoalsScoredPublisher(team2Forwards.get(forwardsIndex));
							team2GoalCount--;
						} else {
							goalsSavedSubject.notifyGoalsSavedPublisher(team1Goalie);
							saveCount += 1;
						}
						team2AvgShotsOnGoal--;
					} else {
					}
				}catch (Exception e) {
					logger.warn("Team playing with less than required players on Ice.");
					continue;
				}
				time++;
				periodClock++;
			}
			period++;
		}

		gameSchedule.setGoalsPerGame(goalsCount);
		gameSchedule.setPenaltiesPerGame((penaltyCount/10)*2);
		gameSchedule.setSaves(saveCount);
		gameSchedule.setShots(shotsCount);
		ITeam teamWon = null;
		ITeam teamLost = null;
		for (ITeamStanding teamStanding : game.getLeagues().get(0).getTeamStandings()) {
			if (teamStanding.getTeam().equals(opponentTeam) && isOpponentTeamWin) {
				teamStanding.setGamesPlayed(teamStanding.getGamesPlayed() + 1);
				teamStanding.setGamesWon(teamStanding.getGamesWon() + 1);
				teamStanding.setTotalPoints(teamStanding.getTotalPoints() + 2);
				logger.info("Match Day : " + team.getTeamName() + " Vs " + opponentTeam.getTeamName() + " on "
						+ game.getLeagues().get(0).getCurrentDate() + " was won by "
						+ teamStanding.getTeam().getTeamName());
				teamWon = teamStanding.getTeam();
			} else if (teamStanding.getTeam().equals(opponentTeam) && isOpponentTeamLoss) {
				teamStanding.setGamesPlayed(teamStanding.getGamesPlayed() + 1);
				teamStanding.setGamesLost(teamStanding.getGamesLost() + 1);
				teamLost = teamStanding.getTeam();
			}

			if (teamStanding.getTeam().equals(team) && isOpponentTeamLoss) {
				teamStanding.setGamesPlayed(teamStanding.getGamesPlayed() + 1);
				teamStanding.setGamesWon(teamStanding.getGamesWon() + 1);
				teamStanding.setTotalPoints(teamStanding.getTotalPoints() + 2);
				logger.info("Match Day : " + team.getTeamName() + " Vs " + opponentTeam.getTeamName() + " on "
						+ game.getLeagues().get(0).getCurrentDate() + " was won by "
						+ teamStanding.getTeam().getTeamName());
				teamWon = teamStanding.getTeam();
			}
			if (teamStanding.getTeam().equals(team) && isOpponentTeamWin) {
				teamStanding.setGamesPlayed(teamStanding.getGamesPlayed() + 1);
				teamStanding.setGamesLost(teamStanding.getGamesLost() + 1);
				teamLost = teamStanding.getTeam();
			}
		}

		teamStandingSubject.notifyTeamStandingPublisher(teamWon, teamLost);
		opponentTeam.resetTeamPlayingStatus();
		team.resetTeamPlayingStatus();
		return isMatchWinnerComputed;
	}
}