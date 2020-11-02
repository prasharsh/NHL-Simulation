package com.datamodel.leaguedatamodel;

public class SimulateMatch {

	public boolean simulateMatchResult(ITeam team, double teamStrength, ITeam opponentTeam, double opponentTeamStrength,
			float randomWinChance, IGame game) {

		boolean isNotReverseGame = true;
		boolean isOpponentTeamWin = false;
		boolean isOpponentTeamLoss = false;
		boolean isMatchWinnerComputed = false;
		if (Math.random() < randomWinChance) {
			isNotReverseGame = true;
		}
		if (teamStrength > opponentTeamStrength && isNotReverseGame) {
			opponentTeam.setLossPointCount(team.getLossPointCount() + 1);
			isOpponentTeamLoss = true;
			isMatchWinnerComputed = true;
		} else {
			team.setLossPointCount(team.getLossPointCount() + 1);
			isOpponentTeamWin = true;
			isMatchWinnerComputed = true;
		}
		for (ITeamStanding teamStanding : game.getLeagues().get(0).getTeamStandings()) {
			if (teamStanding.getTeam().equals(opponentTeam) && isOpponentTeamWin) {
				teamStanding.setGamesPlayed(teamStanding.getGamesPlayed() + 1);
				teamStanding.setGamesWon(teamStanding.getGamesWon() + 1);
				teamStanding.setTotalPoints(teamStanding.getTotalPoints() + 2);
				System.out.println(team.getTeamName() + " Vs " + opponentTeam.getTeamName() + " on "
						+ game.getLeagues().get(0).getCurrentDate() + " was won by "
						+ teamStanding.getTeam().getTeamName());
			} else if (teamStanding.getTeam().equals(opponentTeam) && isOpponentTeamLoss) {
				teamStanding.setGamesPlayed(teamStanding.getGamesPlayed() + 1);
				teamStanding.setGamesLost(teamStanding.getGamesLost() + 1);
			}

			if (teamStanding.getTeam().equals(team) && isOpponentTeamLoss) {
				teamStanding.setGamesPlayed(teamStanding.getGamesPlayed() + 1);
				teamStanding.setGamesWon(teamStanding.getGamesWon() + 1);
				teamStanding.setTotalPoints(teamStanding.getTotalPoints() + 2);
				System.out.println(team.getTeamName() + " Vs " + opponentTeam.getTeamName() + " on "
						+ game.getLeagues().get(0).getCurrentDate() + " was won by "
						+ teamStanding.getTeam().getTeamName());
			}
			if (teamStanding.getTeam().equals(team) && isOpponentTeamWin) {
				teamStanding.setGamesPlayed(teamStanding.getGamesPlayed() + 1);
				teamStanding.setGamesLost(teamStanding.getGamesLost() + 1);
			}
		}
		return isMatchWinnerComputed;
	}
}
