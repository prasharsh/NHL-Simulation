package com.statemachine;

import com.datamodel.gameplayconfig.IAgingConfig;
import com.datamodel.leaguedatamodel.*;
import com.inputoutputmodel.DisplayRoster;
import com.inputoutputmodel.IDisplayRoaster;
import com.inputoutputmodel.IPropertyLoader;
import com.inputoutputmodel.PropertyLoader;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class AdvanceNextSeasonState implements IState {

    private static final String SEASON_START_DATE = "seasonStartDate";
    private static final int DECREASE_PLAYER_STAT_ON_BIRTH_DAY = 1;

   

    @Override
    public void entry() {
    	StateMachineAbstractFactory stateFactory = StateMachineAbstractFactory.instance();
		IStateMachine stateMachine = stateFactory.createStateMachine(null);
        IDisplayRoaster displayRoaster = new DisplayRoster();
        Date currentDate = stateMachine.getGame().getLeagues().get(0).getCurrentDate();
        String[] date = stateMachine.getGame().getLeagues().get(0).getSimulationStartDate().toString().split("-");
        int year = Integer.parseInt(date[0]);
        IPropertyLoader propertyLoader = new PropertyLoader();
        Date nextSeasonStartDate = Date.valueOf("" + (year + 1) + propertyLoader.getPropertyValue(SEASON_START_DATE));
        long timeDiff = nextSeasonStartDate.getTime() - currentDate.getTime();
        int daysToAge = (int) (timeDiff / (24 * 60 * 60 * 1000));
        //stateMachine.setCurrentState(stateMachine.getPersist());
        //stateMachine.getCurrentState().entry();
        IState persistState = stateFactory.createPersistState();
        persistState.entry();
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
        List<IPlayer> freeAgents = league.getFreeAgents();
        List<IPlayer> freeAgentList = new ArrayList<>();
        for (IPlayer freeAgent : freeAgents) {
            Date freeAgentBirthDate = freeAgent.getPlayerBirthDate();
            if (freeAgentBirthDate.after(currentDate) && freeAgentBirthDate.before(nextSeasonStartDate)) {
                if (aging.isStatDecayOnBirthDay()) {
                    freeAgent.decreasePlayerStat(DECREASE_PLAYER_STAT_ON_BIRTH_DAY);
                }
            }
            freeAgent.agePlayer(daysToAge);
            if (aging.isPlayerRetires(freeAgent.getPlayerAgeYear())) {
                displayRoaster.displayMessageToUser("FreeAgent " + freeAgent.getPlayerName() + " retired!!");
                freeAgentList.add(freeAgent);
            }
        }
        freeAgents.removeAll(freeAgentList);
        List<IConference> conferences = league.getConferences();
        for (IConference conference : conferences) {
            List<IDivision> divisions = conference.getDivisions();
            for (IDivision division : divisions) {
                List<ITeam> teams = division.getTeams();
                for (ITeam team : teams) {
                    List<IPlayer> players = team.getPlayers();
                    List<IPlayer> playersList = new ArrayList<>();
                    for (IPlayer player : players) {
                        Date playerBirthDate = player.getPlayerBirthDate();
                        if (playerBirthDate.after(currentDate) && playerBirthDate.before(nextSeasonStartDate)) {
                            if (aging.isStatDecayOnBirthDay()) {
                                player.decreasePlayerStat(DECREASE_PLAYER_STAT_ON_BIRTH_DAY);
                            }
                        }
                        player.agePlayer(daysToAge);
                        if (aging.isPlayerRetires(player.getPlayerAgeYear()) && (player.isPlayerRetired() == false)) {
                            displayRoaster.displayMessageToUser(
                                    player.getPlayerName() + " from team " + team.getTeamName() + " retired!!");
                            playersList.add(player);
                            List<IPlayer> freeAgentsWithSamePosition = league
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
        stateMachine.getGame().getLeagues().get(0).setCurrentDate(nextSeasonStartDate);
    }


    @Override
    public IState doTask() {
    	StateMachineAbstractFactory stateFactory = StateMachineAbstractFactory.instance();
        return stateFactory.createInitializeSeasonState();
    }
}