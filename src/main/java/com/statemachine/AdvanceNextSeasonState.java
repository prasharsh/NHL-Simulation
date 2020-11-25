package com.statemachine;

import com.datamodel.gameplayconfig.IAgingConfig;
import com.datamodel.leaguedatamodel.*;
import com.inputoutputmodel.DisplayRoster;
import com.inputoutputmodel.IDisplayRoaster;
import com.inputoutputmodel.IPropertyLoader;
import com.inputoutputmodel.PropertyLoader;

import java.sql.Date;
import java.util.ArrayList;

import static com.datamodel.leaguedatamodel.Constants.DECREASE_PLAYER_STAT_ON_BIRTH_DAY;

public class AdvanceNextSeasonState implements IState {

    private static final String SEASON_START_DATE = "seasonStartDate";
    IStateMachine stateMachine;

    public AdvanceNextSeasonState(IStateMachine stateMachine) {
        this.stateMachine = stateMachine;
    }

    @Override
    public void entry() {
        IDisplayRoaster displayRoaster = new DisplayRoster();
        Date currentDate = stateMachine.getGame().getLeagues().get(0).getCurrentDate();
        String[] date = stateMachine.getGame().getLeagues().get(0).getSimulationStartDate().toString().split("-");
        int year = Integer.parseInt(date[0]);
        IPropertyLoader propertyLoader = new PropertyLoader();
        Date nextSeasonStartDate = Date.valueOf("" + (year + 1) + propertyLoader.getPropertyValue(SEASON_START_DATE));
        long timeDiff = nextSeasonStartDate.getTime() - currentDate.getTime();
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
        ArrayList<IPlayer> freeAgents = league.getFreeAgents();
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
                            player.setPlayerRetired(true);
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