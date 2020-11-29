package com.statemachine;

import com.datamodel.gameplayconfig.IAgingConfig;
import com.datamodel.leaguedatamodel.*;
import com.inputoutputmodel.DisplayRoster;
import com.inputoutputmodel.IDisplayRoaster;
import com.inputoutputmodel.IPropertyLoader;
import com.inputoutputmodel.PropertyLoader;
import org.apache.log4j.Logger;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class DraftPickState implements IState {

    final static Logger logger = Logger.getLogger(DraftPickState.class);
    private static final String DRAFT_PICK_DATE = "draftPickDate";
    private static final int DRAFT_ROUNDS = 7;
    private static final int DECREASE_PLAYER_STAT_ON_BIRTH_DAY = 1;

    @Override
    public void entry() {
        StateMachineAbstractFactory stateFactory = StateMachineAbstractFactory.instance();
        IStateMachine stateMachine = stateFactory.createStateMachine(null);
        LeagueDataModelAbstractFactory factory = LeagueDataModelAbstractFactory.instance();
        IGame game = factory.createGame();
        IDisplayRoaster displayRoaster = new DisplayRoster();
        String currentDate = game.getLeagues().get(0).getCurrentDate().toString();
        int year = Integer.parseInt(currentDate.split("-")[0]);
        IPropertyLoader propertyLoader = new PropertyLoader();
        Date draftPickDate = Date.valueOf("" + year + propertyLoader.getPropertyValue(DRAFT_PICK_DATE));
        long timeDiff = draftPickDate.getTime() - Date.valueOf(currentDate).getTime();
        int daysToAge = (int) (timeDiff / (24 * 60 * 60 * 1000));
        ILeague league = game.getLeagues().get(0);
        IAgingConfig aging = game.getLeagues().get(0).getGamePlayConfig().getAging();
        List<IPlayer> freeAgents = league.getFreeAgents();
        List<IPlayer> freeAgentList = new ArrayList<>();
        for (IPlayer freeAgent : freeAgents) {
            Date freeAgentBirthDate = freeAgent.getPlayerBirthDate();
            if (freeAgentBirthDate.after(Date.valueOf(currentDate)) && freeAgentBirthDate.before(draftPickDate)) {
                if (aging.isStatDecayOnBirthDay()) {
                    freeAgent.decreasePlayerStat(DECREASE_PLAYER_STAT_ON_BIRTH_DAY);
                }
            }
            freeAgent.agePlayer(daysToAge);
            if (aging.isPlayerRetires(freeAgent.getPlayerAgeYear())) {
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
                    List<IPlayer> players = team.getPlayers();
                    List<IPlayer> playersList = new ArrayList<>();
                    for (IPlayer player : players) {
                        Date playerBirthDate = player.getPlayerBirthDate();
                        if (playerBirthDate.after(Date.valueOf(currentDate)) && playerBirthDate.before(draftPickDate)) {
                            if (aging.isStatDecayOnBirthDay()) {
                                player.decreasePlayerStat(DECREASE_PLAYER_STAT_ON_BIRTH_DAY);
                            }
                        }
                        player.agePlayer(daysToAge);
                        if (aging.isPlayerRetires(player.getPlayerAgeYear()) && (player.isPlayerRetired() == false)) {
                            logger.info(
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
        game.getLeagues().get(0).setCurrentDate(draftPickDate);
        List<ITeamStanding> teamStandings = new ArrayList<>(league.getTeamStandings());
        teamStandings.sort(Comparator.comparingDouble(ITeamStanding::getTotalPoints));
        int playersToGenerate = DRAFT_ROUNDS * teamStandings.size();
        IRandomPlayer generateRandomPlayer = factory.createRandomPlayer();
        List<IPlayer> draftPlayers = generateRandomPlayer.getRandomPlayers(playersToGenerate, currentDate);
        IDrafting drafting = factory.createDrafting();
        for (int i = 0; i < DRAFT_ROUNDS; i++) {
            for (ITeamStanding teamStanding : teamStandings) {
                ITeam teamOwner = teamStanding.getTeam();
                ITeam teamPicker = drafting.getDraftPickByRound(teamOwner, i);
                IPlayer draftPlayer = draftPlayers.get(0);
                draftPlayers.remove(draftPlayer);
                teamPicker.addPlayer(draftPlayer);
                logger.info(teamPicker.getTeamName() + " got player " + draftPlayer.getPlayerName() + " in the draft round " + i + " after the end of season " + league.getSeason());
                teamPicker.setActiveRoster();
                teamPicker.dropWeakestPlayersToFreeAgentList(league, draftPlayer.getPlayerPosition(), 1);
            }
        }
    }

    @Override
    public IState doTask() {
        return null;
    }


}
