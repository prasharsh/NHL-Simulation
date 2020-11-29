package com.datamodel.leaguedatamodel;

import com.inputoutputmodel.DisplayToUser;
import com.inputoutputmodel.IDisplayToUser;

import java.util.ArrayList;
import java.util.List;

public class SimulateMatch implements ISimulateMatch {

    private static final String FORWARD = "forward";
    private static final String DEFENSE = "defense";
    private static final String GOALIE = "goalie";

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
        IDisplayToUser displayToUser = new DisplayToUser();

        int period = 0;
        int periodClock = 0;
        int time = 0;
        int team1SkatingStats = 0;
        int team2SkatingStats = 0;
        int team1AvgShotsOnGoal = 30;
        int team2AvgShotsOnGoal = 30;
        int team1GoalCount = 0;
        int team2GoalCount = 0;
        int shotsCount = 0;
        int penaltyCount = 0;
        int saveCount = 0;
        int goalsCount = 0;

        boolean isOpponentTeamWin = false;
        boolean isOpponentTeamLoss = false;
        boolean isMatchWinnerComputed = false;
        boolean isTeam1PowerPlay = false;
        boolean isTeam2PowerPlay = false;
        boolean isPlayingSixKnown = false;

        while (period < 3) {
            periodClock = 0;
            while (periodClock < 20) {
                if (time == 0) {
                    team1Playing6 = team.getPlayingSix();
                    team2Playing6 = team.getPlayingSix();
                    isPlayingSixKnown = true;
                } else if (time % 18 == 0) {
                    team1Playing6 = opponentTeam.getPlayingSix();
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
                        team2GoalCount = (int) team2AvgShotsOnGoal / 10 - 1;
                    } else {
                        team1AvgShotsOnGoal -= shotDiff;
                        team2AvgShotsOnGoal += shotDiff;
                        team1GoalCount = (int) team1AvgShotsOnGoal / 10 - 1;
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
                if (time % 2 == 0 && team1AvgShotsOnGoal > 0) {
                    System.out.println("team1 shot on goal");
                    if (Math.random() > penaltyChance) {
                        int defensemenIndex = (int) ((Math.random() * 10) % 2);
                        System.out.println("team 1 get a penalty, t2 defensemen " + team2Defensemen.get(defensemenIndex).getPlayerName() + " off the feild");
                        isTeam1PowerPlay = true;
                        penaltyCount += 1;
                    }
                    if (isTeam1PowerPlay && team1GoalCount > 0) {
                        int forwardsIndex = (int) ((Math.random() * 10) % 3);
                        System.out.println("team1 scored, goal by " + team1Forwards.get(forwardsIndex).getPlayerName());
                        team1GoalCount--;
                    } else {
                        System.out.println("goal saved by team2, goalie " + team2Goalie.getPlayerName());
                        saveCount += 1;
                    }
                    team1AvgShotsOnGoal--;
                } else if (team2AvgShotsOnGoal > 0) {
                    System.out.println("team 2 shot on goal");
                    if (Math.random() > penaltyChance) {
                        int defensemenIndex = (int) ((Math.random() * 10) % 2);
                        System.out.println("team 2 get a penalty, t1 defensemen " + team1Defensemen.get(defensemenIndex).getPlayerName() + " off the feild");
                        isTeam2PowerPlay = true;
                        penaltyCount += 1;
                    }
                    if (isTeam2PowerPlay && team2GoalCount > 0) {
                        int forwardsIndex = (int) ((Math.random() * 10) % 3);
                        System.out.println("team2 scored, goal by " + team2Forwards.get(forwardsIndex).getPlayerName());
                        team2GoalCount--;
                    } else {
                        System.out.println("goal saved by team1, goalie " + team1Goalie.getPlayerName());
                        saveCount += 1;
                    }
                    team2AvgShotsOnGoal--;
                } else {
                    System.out.println("********* no shots on goals from anyone");
                }

                time++;
                periodClock++;
            }
            period++;
        }

        gameSchedule.setGoalsPerGame(goalsCount);
        gameSchedule.setPenaltiesPerGame(penaltyCount);
        gameSchedule.setSaves(saveCount);
        gameSchedule.setShots(shotsCount);

        for (ITeamStanding teamStanding : game.getLeagues().get(0).getTeamStandings()) {
            if (teamStanding.getTeam().equals(opponentTeam) && isOpponentTeamWin) {
                teamStanding.setGamesPlayed(teamStanding.getGamesPlayed() + 1);
                teamStanding.setGamesWon(teamStanding.getGamesWon() + 1);
                teamStanding.setTotalPoints(teamStanding.getTotalPoints() + 2);
                displayToUser.displayMsgToUser("Match Day : " + team.getTeamName() + " Vs " + opponentTeam.getTeamName() + " on "
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
                displayToUser.displayMsgToUser("Match Day : " + team.getTeamName() + " Vs " + opponentTeam.getTeamName() + " on "
                        + game.getLeagues().get(0).getCurrentDate() + " was won by "
                        + teamStanding.getTeam().getTeamName());
            }
            if (teamStanding.getTeam().equals(team) && isOpponentTeamWin) {
                teamStanding.setGamesPlayed(teamStanding.getGamesPlayed() + 1);
                teamStanding.setGamesLost(teamStanding.getGamesLost() + 1);
            }
        }

        opponentTeam.resetTeamPlayingStatus();
        team.resetTeamPlayingStatus();
        return isMatchWinnerComputed;
    }
}