package com.statemachine;

import java.sql.Date;
import java.util.ArrayList;

import com.datamodeltest.Trading;
import com.datamodeltest.leaguedatamodel.Game;
import com.datamodeltest.gameplayconfig.IAgingConfig;
import com.datamodeltest.leaguedatamodel.IConference;
import com.datamodeltest.leaguedatamodel.IDivision;
import com.datamodeltest.leaguedatamodel.IPlayer;
import com.datamodeltest.leaguedatamodel.ILeague;
import com.datamodeltest.leaguedatamodel.ITeam;

public class AgingState implements IState {
    StateMachine stateMachine;

    public AgingState(StateMachine stateMachine) {

        this.stateMachine = stateMachine;
    }

    @Override
    public void entry() {
        Game game = stateMachine.getGame();
        ILeague league = game.getLeagues().get(0);
        IAgingConfig aging = game.getLeagues().get(0).getGamePlayConfig().getAging();
        Trading trading = new Trading();
        ArrayList<IPlayer> freeAgents = league.getFreeAgents();
        for (IPlayer freeAgent : freeAgents) {
            freeAgent.agePlayer(1);
            if (aging.isPlayerRetires(freeAgent.getPlayerAgeYear())) {
                league.removeFreeAgent(freeAgent);
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
                        player.agePlayer(1);
                        if (aging.isPlayerRetires(player.getPlayerAgeYear())) {
                            ArrayList<IPlayer> freeAgentsWithSamePosition = trading
                                    .getFreeAgentsWithPosition(freeAgents, player.getPlayerPosition());
                            IPlayer freeAgent = trading
                                    .sortFreeAgentsOnStrength(freeAgentsWithSamePosition, 1, false).get(0);
                            team.addPlayer(freeAgent);
                            team.removePlayer(player);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void exit() {

    }

    @Override
    public IState doTask() {
        Date currentDate = stateMachine.getGame().getLeagues().get(0).getCurrentDate();
        String[] date = stateMachine.getGame().getLeagues().get(0).getSimulationStartDate().toString().split("-");
        int year = Integer.parseInt(date[0]);
        Date endOfSeason = Date.valueOf("" + (year + 1) + "-06-01");
        System.out.println(currentDate);
        if (currentDate.compareTo(endOfSeason) == 0) {
            stateMachine.setCurrentState(stateMachine.getAdvanceNextSeason());
            stateMachine.getCurrentState().entry();
//			stateMachine.setCurrentState(stateMachine.getInitializeSeason());
            return stateMachine.getInitializeSeason();

        } else {
            return stateMachine.getPersist();

        }
    }

}
