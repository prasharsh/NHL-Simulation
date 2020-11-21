package com.statemachine;

import com.datamodel.gameplayconfig.IAgingConfig;
import com.datamodel.leaguedatamodel.*;
import com.inputoutputmodel.DisplayRoster;
import com.inputoutputmodel.IDisplayRoaster;
import com.inputoutputmodel.IPropertyLoader;
import com.inputoutputmodel.PropertyLoader;

import java.sql.Date;
import java.util.ArrayList;

public class AdvanceNextSeasonState implements IState {

    private static final String SEASON_START_DATE = "seasonStartDate";
    IStateMachine stateMachine;

    public AdvanceNextSeasonState(IStateMachine stateMachine) {
        this.stateMachine = stateMachine;
    }

    @Override
    public void entry() {
        IDisplayRoaster displayRoaster = new DisplayRoster();
        Date currDate = stateMachine.getGame().getLeagues().get(0).getCurrentDate();
        String[] date = stateMachine.getGame().getLeagues().get(0).getSimulationStartDate().toString().split("-");
        int year = Integer.parseInt(date[0]);
        IPropertyLoader propertyLoader = new PropertyLoader();
        Date nextSeasonStartDate = Date.valueOf("" + (year + 1) + propertyLoader.getPropertyValue(SEASON_START_DATE));
        long timeDiff = nextSeasonStartDate.getTime() - currDate.getTime();
        int daysToAge = (int) (timeDiff / (24 * 60 * 60 * 1000));
        stateMachine.setCurrentState(stateMachine.getPersist());
        stateMachine.getCurrentState().entry();
        IGame game = stateMachine.getGame();
        game.getLeagues().get(0).setSeason(game.getLeagues().get(0).getSeason() + 1);
        game.getLeagues().get(0).setSimulationStartDate(nextSeasonStartDate);
        game.getLeagues().get(0).setCurrentDate(nextSeasonStartDate);
        if (game.getLeagues().get(0).getSeason() > game.getLeagues().get(0).getSeasonToSimulate()) {
            displayRoaster.displayMessageToUser("end of DHL");
            System.exit(0);
        }
        ILeague league = game.getLeagues().get(0);
        IAgingConfig aging = game.getLeagues().get(0).getGamePlayConfig().getAging();
        ITrading trading = new Trading();
        ArrayList<IPlayer> freeAgents = league.getFreeAgents();
        for (IPlayer freeAgent : freeAgents) {
            freeAgent.agePlayer(daysToAge);
            if (aging.isPlayerRetires(freeAgent.getPlayerAgeYear())) {
                displayRoaster.displayMessageToUser("FreeAgent " + freeAgent.getPlayerName() + " retired!!");
                freeAgent.setPlayerRetired(true);
            }
        }
        ArrayList<IConference> conferences = league.getConferences();
        for (IConference conference : conferences) {
            ArrayList<IDivision> divisions = conference.getDivisions();
            for (IDivision division : divisions) {
                ArrayList<ITeam> teams = division.getTeams();
                for (ITeam team : teams) {
                    ArrayList<IPlayer> players = team.getPlayers();
                    for (IPlayer player : players) {
                        player.agePlayer(daysToAge);

                        if (aging.isPlayerRetires(player.getPlayerAgeYear()) && (player.isPlayerRetired() == false)) {
                            displayRoaster.displayMessageToUser(
                                    player.getPlayerName() + " from team " + team.getTeamName() + " retired!!");
                            player.setPlayerRetired(true);
                            ArrayList<IPlayer> freeAgentsWithSamePosition = trading
                                    .getFreeAgentsWithPosition(freeAgents, player.getPlayerPosition());
                            if (freeAgentsWithSamePosition == null || freeAgentsWithSamePosition.size() == 0) {
                                displayRoaster.displayMessageToUser("No freeAgents available for replacement!");
                                System.exit(1);
                            }
                            IPlayer freeAgent = trading.sortFreeAgentsOnStrength(freeAgentsWithSamePosition, 1, false)
                                    .get(0);
                            team.addPlayer(freeAgent);
                            league.removeFreeAgent(freeAgent);
                        }
                    }
                }
            }
        }
        stateMachine.getGame().getLeagues().get(0).setCurrentDate(nextSeasonStartDate);
    }

    @Override
    public void exit() {
    }

    @Override
    public IState doTask() {
        return stateMachine.getInitializeSeason();
    }
}