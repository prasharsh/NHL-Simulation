package com.statemachine;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.datamodel.gameplayconfig.IAgingConfig;
import com.datamodel.leaguedatamodel.IConference;
import com.datamodel.leaguedatamodel.IDivision;
import com.datamodel.leaguedatamodel.IGame;
import com.datamodel.leaguedatamodel.ILeague;
import com.datamodel.leaguedatamodel.IPlayer;
import com.datamodel.leaguedatamodel.ITeam;
import com.datamodel.leaguedatamodel.LeagueDataModelAbstractFactory;
import com.inputoutputmodel.DisplayRoster;
import com.inputoutputmodel.IDisplayRoaster;
import com.inputoutputmodel.IPropertyLoader;
import com.inputoutputmodel.PropertyLoader;


public class AgingState implements IState {

    final static Logger logger = Logger.getLogger(AgingState.class);
    
    private static final String END_OF_SEASON = "playoffEndDate";
    private static final int DECREASE_PLAYER_STAT_ON_BIRTH_DAY = 1;


    @Override
    public void entry() {
        LeagueDataModelAbstractFactory factory = LeagueDataModelAbstractFactory.instance();
        IGame game = factory.createGame();
        IDisplayRoaster displayRoaster = new DisplayRoster();
        ILeague league = game.getLeagues().get(0);
        String currDate = game.getLeagues().get(0).getCurrentDate().toString();
        int currMonth = Integer.parseInt(currDate.split("-")[1]);
        int currDay = Integer.parseInt(currDate.split("-")[2]);
        IAgingConfig aging = game.getLeagues().get(0).getGamePlayConfig().getAging();
        List<IPlayer> freeAgents = league.getFreeAgents();
        List<IPlayer> freeAgentList = new ArrayList<>();
        for (IPlayer freeAgent : freeAgents) {
            if (freeAgent.isPlayerBirthDay(currMonth, currDay)) {
                if (aging.isStatDecayOnBirthDay()) {
                    freeAgent.decreasePlayerStat(DECREASE_PLAYER_STAT_ON_BIRTH_DAY);
                }
            }
            freeAgent.agePlayer(1);
            if (aging.isPlayerRetires(freeAgent.getPlayerAgeYear()) && (freeAgent.isPlayerRetired() == false)) {
                logger.info("FreeAgent " + freeAgent.getPlayerName() + " retired!!");
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
                    List<IPlayer> players = new ArrayList<>(team.getPlayers());
                    List<IPlayer> playersList = new ArrayList<>();
                    for (IPlayer player : players) {
                        if (player.isPlayerBirthDay(currMonth, currDay)) {
                            if (aging.isStatDecayOnBirthDay()) {
                                player.decreasePlayerStat(DECREASE_PLAYER_STAT_ON_BIRTH_DAY);
                            }
                        }
                        player.agePlayer(1);
                        if (aging.isPlayerRetires(player.getPlayerAgeYear()) && (player.isPlayerRetired() == false)) {
                            playersList.add(player);
                            logger.info(
                                    player.getPlayerName() + " from team " + team.getTeamName() + " retired!!");
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
    }

    @Override
    public IState doTask() {
        StateMachineAbstractFactory stateFactory = StateMachineAbstractFactory.instance();
        LeagueDataModelAbstractFactory factory = LeagueDataModelAbstractFactory.instance();
        IGame game = factory.createGame();
        IDisplayRoaster displayRoaster = new DisplayRoster();
        Date currentDate = game.getLeagues().get(0).getCurrentDate();
        String[] date = game.getLeagues().get(0).getSimulationStartDate().toString().split("-");
        ILeague league = game.getLeagues().get(0);
        int year = Integer.parseInt(date[0]);
        IPropertyLoader propertyLoader = new PropertyLoader();
        Date endOfSeason = Date.valueOf("" + (year + 1) + propertyLoader.getPropertyValue(END_OF_SEASON));
        if (currentDate.compareTo(endOfSeason) == 0) {
        	logger.info("Last day of Stanley Cup.");
        	league.getTeamStandings().sort((standing1, standing2) -> {
                double points1 = standing1.getTotalPoints();
                double points2 = standing2.getTotalPoints();
                if (points1 > points2) {
                    return -1;
                } else {
                    return 0;
                }
            });
            logger.info("The stanley cup winner for season " + league.getSeason() + " is "
                    + league.getTeamStandings().get(0).getTeam().getTeamName());
            displayRoaster.displayMessageToUser("The stanley cup winner for season " + league.getSeason() + " is "
                    + league.getTeamStandings().get(0).getTeam().getTeamName());

            stateFactory.createTrophySystemState().entry();
            IState draftPickState = stateFactory.createDraftPickState();
            draftPickState.entry();
            IState advanceToNextSeason = stateFactory.createAdvanceNextSeasonState();
            advanceToNextSeason.entry();
            logger.info("End of season.");
            return stateFactory.createInitializeSeasonState();
        } else {
            return stateFactory.createAdvanceTimeState();
        }
    }
}