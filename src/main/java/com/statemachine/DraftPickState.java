package com.statemachine;

import com.datamodel.gameplayconfig.IAgingConfig;
import com.datamodel.leaguedatamodel.*;
import com.inputoutputmodel.DisplayRoster;
import com.inputoutputmodel.IDisplayRoaster;
import com.inputoutputmodel.IPropertyLoader;
import com.inputoutputmodel.PropertyLoader;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Comparator;

import static com.datamodel.leaguedatamodel.Constants.DECREASE_PLAYER_STAT_ON_BIRTH_DAY;
import static com.datamodel.leaguedatamodel.Constants.DRAFT_ROUNDS;

public class DraftPickState implements IState {
    IStateMachine stateMachine;
    private static final String DRAFT_PICK_DATE = "draftPickDate";


    public DraftPickState(IStateMachine stateMachine) {
        this.stateMachine = stateMachine;
    }

    @Override
    public void entry() {
        IDisplayRoaster displayRoaster = new DisplayRoster();
        String currentDate = stateMachine.getGame().getLeagues().get(0).getCurrentDate().toString();
        int year = Integer.parseInt(currentDate.split("-")[0]);
        IPropertyLoader propertyLoader = new PropertyLoader();
        Date draftPickDate = Date.valueOf("" + year + propertyLoader.getPropertyValue(DRAFT_PICK_DATE));
        long timeDiff = draftPickDate.getTime() - Date.valueOf(currentDate).getTime();
        int daysToAge = (int) (timeDiff / (24 * 60 * 60 * 1000));
        IGame game = stateMachine.getGame();
        ILeague league = game.getLeagues().get(0);
        IAgingConfig aging = game.getLeagues().get(0).getGamePlayConfig().getAging();
        ArrayList<IPlayer> freeAgents = league.getFreeAgents();
        ArrayList<IPlayer> freeAgentList = new ArrayList<>();
        for (IPlayer freeAgent : freeAgents) {
            Date freeAgentBirthDate = freeAgent.getPlayerBirthDate();
            if (freeAgentBirthDate.after(Date.valueOf(currentDate)) && freeAgentBirthDate.before(draftPickDate)) {
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
        ArrayList<IConference> conferences = league.getConferences();
        for (IConference conference : conferences) {
            ArrayList<IDivision> divisions = conference.getDivisions();
            for (IDivision division : divisions) {
                ArrayList<ITeam> teams = division.getTeams();
                for (ITeam team : teams) {
                    ArrayList<IPlayer> players = team.getPlayers();
                    ArrayList<IPlayer> playersList = new ArrayList<>();
                    for (IPlayer player : players) {
                        Date playerBirthDate = player.getPlayerBirthDate();
                        if (playerBirthDate.after(Date.valueOf(currentDate)) && playerBirthDate.before(draftPickDate)) {
                            if (aging.isStatDecayOnBirthDay()) {
                                player.decreasePlayerStat(DECREASE_PLAYER_STAT_ON_BIRTH_DAY);
                            }
                        }
                        player.agePlayer(daysToAge);
                        if (aging.isPlayerRetires(player.getPlayerAgeYear()) && (player.isPlayerRetired() == false)) {
                            displayRoaster.displayMessageToUser(
                                    player.getPlayerName() + " from team " + team.getTeamName() + " retired!!");
                            playersList.add(player);
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
        stateMachine.getGame().getLeagues().get(0).setCurrentDate(draftPickDate);
        ArrayList<ITeamStanding> teamStandings = new ArrayList<>(league.getTeamStandings());
        teamStandings.sort(Comparator.comparingDouble(ITeamStanding::getTotalPoints));
        int playersToGenerate = DRAFT_ROUNDS * teamStandings.size();
        IGenerateRandomPlayer generateRandomPlayer = new GenerateRandomPlayer();
        ArrayList<IPlayer> draftPlayers = generateRandomPlayer.getRandomPlayers(playersToGenerate, currentDate);
        IDrafting drafting = new Drafting();
        for (int i = 0; i < DRAFT_ROUNDS; i++) {
            for (ITeamStanding teamStanding : teamStandings) {
                ITeam teamOwner = teamStanding.getTeam();
                ITeam teamPicker = drafting.getDraftPickByRound(teamOwner, i);
                IPlayer draftPlayer = draftPlayers.get(0);
                draftPlayers.remove(draftPlayer);
                teamPicker.addPlayer(draftPlayer);
                teamPicker.setActiveRoster();
                teamPicker.dropWeakestPlayersToFreeAgentList(league, draftPlayer.getPlayerPosition(), 1);
            }
        }
    }

    @Override
    public IState doTask() {
        return null;
    }

    @Override
    public void exit() {

    }
}
