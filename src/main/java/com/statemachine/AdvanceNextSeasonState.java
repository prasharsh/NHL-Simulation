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

public class AdvanceNextSeasonState implements IState {
    StateMachine stateMachine;

    public AdvanceNextSeasonState(StateMachine stateMachine) {

        this.stateMachine = stateMachine;
    }

    @Override
    public void entry() {
        Date currDate = stateMachine.getGame().getLeagues().get(0).getCurrentDate();
        String[] date = stateMachine.getGame().getLeagues().get(0).getSimulationStartDate().toString().split("-");
        int year = Integer.parseInt(date[0]);
        Date nextSeasonStartDate = Date.valueOf("" + (year + 1) + "-09-30");
        long timeDiff = nextSeasonStartDate.getTime() - currDate.getTime();
        int daysToAge = (int) (timeDiff / (24 * 60 * 60 * 1000));
        stateMachine.setCurrentState(stateMachine.getPersist());
        stateMachine.getCurrentState().entry();
        Game game = stateMachine.getGame();
        game.getLeagues().get(0).setSeason(game.getLeagues().get(0).getSeason() + 1);
        game.getLeagues().get(0).setSimulationStartDate(nextSeasonStartDate);
        game.getLeagues().get(0).setCurrentDate(nextSeasonStartDate);
        if (game.getLeagues().get(0).getSeason() > game.getLeagues().get(0).getSeasonToSimulate()) {
            System.out.println("end of DHL");
            System.exit(0);
        }
        ILeague league = game.getLeagues().get(0);
        IAgingConfig aging = game.getLeagues().get(0).getGamePlayConfig().getAging();
        Trading trading = new Trading();
        ArrayList<IPlayer> freeAgents = league.getFreeAgents();
        for (IPlayer freeAgent : freeAgents) {
            freeAgent.agePlayer(daysToAge);
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
                        player.agePlayer(daysToAge);
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
