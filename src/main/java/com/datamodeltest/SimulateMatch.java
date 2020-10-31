package com.datamodeltest;

import com.datamodeltest.leaguedatamodel.IGame;
import com.datamodeltest.leaguedatamodel.ITeam;

public class SimulateMatch {


    public void simulateMatchResult(ITeam team, double teamStrength, ITeam opponentTeam, double opponentTeamStrength, float randomWinChance, IGame game) {

        boolean isNotReverseGame = true;
        boolean isOpponentTeamWin = false;
        boolean isOpponentTeamLoss = false;
        if (Math.random() < randomWinChance) {
            isNotReverseGame = true;
        }
        if (teamStrength > opponentTeamStrength && isNotReverseGame) {
            opponentTeam.setLossPointCount(team.getLossPointCount() + 1);
            isOpponentTeamLoss = true;
        } else {
            team.setLossPointCount(team.getLossPointCount() + 1);
            isOpponentTeamWin = true;
        }
        for (ITeamStanding teamStanding : game.getLeagues().get(0).getTeamStandings()) {
            if (teamStanding.getTeam().equals(opponentTeam) && isOpponentTeamWin) {
                teamStanding.setGamesPlayed(teamStanding.getGamesPlayed() + 1);
                teamStanding.setGamesWon(teamStanding.getGamesWon() + 1);
                teamStanding.setTotalPoints(teamStanding.getTotalPoints() + 2);
            } else if (teamStanding.getTeam().equals(opponentTeam) && isOpponentTeamLoss) {
                teamStanding.setGamesPlayed(teamStanding.getGamesPlayed() + 1);
                teamStanding.setGamesLost(teamStanding.getGamesLost() + 1);
            }

            if (teamStanding.getTeam().equals(team) && isOpponentTeamLoss) {
                teamStanding.setGamesPlayed(teamStanding.getGamesPlayed() + 1);
                teamStanding.setGamesWon(teamStanding.getGamesWon() + 1);
                teamStanding.setTotalPoints(teamStanding.getTotalPoints() + 2);
            }
            if (teamStanding.getTeam().equals(team) && isOpponentTeamWin) {
                teamStanding.setGamesPlayed(teamStanding.getGamesPlayed() + 1);
                teamStanding.setGamesLost(teamStanding.getGamesLost() + 1);
            }
        }
    }
}
