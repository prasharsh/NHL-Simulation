package com.statemachine;

import com.datamodel.gameplayconfig.IAgingConfig;
import com.datamodel.leaguedatamodel.*;
import com.inputoutputmodel.DisplayRoster;
import com.inputoutputmodel.IDisplayRoaster;
import com.inputoutputmodel.IPropertyLoader;
import com.inputoutputmodel.PropertyLoader;

import java.sql.Date;
import java.util.ArrayList;



public class AgingState implements IState {

    private static final String END_OF_SEASON = "playoffEndDate";
    private static final int DECREASE_PLAYER_STAT_ON_BIRTH_DAY = 1;


    IStateMachine stateMachine;

    public AgingState(IStateMachine stateMachine) {

        this.stateMachine = stateMachine;
    }

    @Override
    public void entry() {
        IDisplayRoaster displayRoaster = new DisplayRoster();
        IGame game = stateMachine.getGame();
        ILeague league = game.getLeagues().get(0);
        String currDate = stateMachine.getGame().getLeagues().get(0).getCurrentDate().toString();
        int currMonth = Integer.parseInt(currDate.split("-")[1]);
        int currDay = Integer.parseInt(currDate.split("-")[2]);
        IAgingConfig aging = game.getLeagues().get(0).getGamePlayConfig().getAging();
        ArrayList<IPlayer> freeAgents = league.getFreeAgents();
        ArrayList<IPlayer> freeAgentList = new ArrayList<>();
        for (IPlayer freeAgent : freeAgents) {
            if (freeAgent.isPlayerBirthDay(currMonth, currDay)) {
                if (aging.isStatDecayOnBirthDay()) {
                    freeAgent.decreasePlayerStat(DECREASE_PLAYER_STAT_ON_BIRTH_DAY);
                }
            }
            freeAgent.agePlayer(1);
            if (aging.isPlayerRetires(freeAgent.getPlayerAgeYear()) && (freeAgent.isPlayerRetired() == false)) {
                displayRoaster.displayMessageToUser("FreeAgent " + freeAgent.getPlayerName() + " retired!!");
                freeAgentList.add(freeAgent);
            }
        }
        freeAgents.removeAll(freeAgentList);
        ArrayList<IConference> conferences = league.getConferences();
        for (IConference conference : conferences) {
            ArrayList<IDivision> divisions = conference.getDivisions();
            for (IDivision division : divisions) {
                ArrayList<ITeam> teams = division.getTeams();
                for (ITeam team : teams) {
                    ArrayList<IPlayer> players = new ArrayList<>(team.getPlayers());
                    ArrayList<IPlayer> playersList = new ArrayList<>();
                    for (IPlayer player : players) {
                        if (player.isPlayerBirthDay(currMonth, currDay)) {
                            if (aging.isStatDecayOnBirthDay()) {
                                player.decreasePlayerStat(DECREASE_PLAYER_STAT_ON_BIRTH_DAY);
                            }
                        }
                        player.agePlayer(1);
                        if (aging.isPlayerRetires(player.getPlayerAgeYear()) && (player.isPlayerRetired() == false)) {
                            playersList.add(player);
                            displayRoaster.displayMessageToUser(
                                    player.getPlayerName() + " from team " + team.getTeamName() + " retired!!");
                            ArrayList<IPlayer> freeAgentsWithSamePosition = league
                                    .getActiveFreeAgentsWithPosition(freeAgents, player.getPlayerPosition());
                            if (freeAgentsWithSamePosition == null || freeAgentsWithSamePosition.size() == 0) {
                                displayRoaster.displayMessageToUser("No freeAgents available for replacement!");
                                System.exit(1);
                            }
                            IPlayer freeAgent = league.getStrongestFreeAgent(freeAgentsWithSamePosition);
                            team.addPlayer(freeAgent);
                            league.removeFreeAgent(freeAgent);
                        }
                    }
                    players.removeAll(playersList);
                }
            }
        }
    }

    @Override
    public void exit() {
    }

    @Override
    public IState doTask() {
        IDisplayRoaster displayRoaster = new DisplayRoster();
        Date currentDate = stateMachine.getGame().getLeagues().get(0).getCurrentDate();
        String[] date = stateMachine.getGame().getLeagues().get(0).getSimulationStartDate().toString().split("-");
        ILeague league = stateMachine.getGame().getLeagues().get(0);
        int year = Integer.parseInt(date[0]);
        IPropertyLoader propertyLoader = new PropertyLoader();
        Date endOfSeason = Date.valueOf("" + (year + 1) + propertyLoader.getPropertyValue(END_OF_SEASON));
        if (currentDate.compareTo(endOfSeason) == 0) {
            league.getTeamStandings().sort((standing1, standing2) -> {
                double points1 = standing1.getTotalPoints();
                double points2 = standing2.getTotalPoints();
                if (points1 > points2) {
                    return -1;
                } else {
                    return 0;
                }
            });
            displayRoaster.displayMessageToUser("The stanley cup winner for season " + league.getSeason() + " is "
                    + league.getTeamStandings().get(0).getTeam().getTeamName());
            stateMachine.setCurrentState(stateMachine.getDraftPick());
            stateMachine.getCurrentState().entry();
            stateMachine.setCurrentState(stateMachine.getAdvanceNextSeason());
            stateMachine.getCurrentState().entry();
            return stateMachine.getInitializeSeason();
        } else {
            return stateMachine.getAdvanceTime();
        }
    }
}