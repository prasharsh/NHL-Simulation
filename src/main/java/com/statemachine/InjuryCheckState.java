package com.statemachine;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;

import com.datamodeltest.leaguedatamodel.Game;
import com.datamodeltest.gameplayconfig.IInjuryConfig;
import com.datamodeltest.leaguedatamodel.IPlayer;
import com.datamodeltest.leaguedatamodel.ITeam;

public class InjuryCheckState implements IState {
    StateMachine stateMachine;

    public InjuryCheckState(StateMachine stateMachine) {

        this.stateMachine = stateMachine;
    }

    @Override
    public void entry() {
        Game game = stateMachine.getGame();
        Date currentDate = game.getLeagues().get(0).getCurrentDate();
        IInjuryConfig injuryChance = game.getLeagues().get(0).getGamePlayConfig().getInjury();
        float randomInjuryChance = injuryChance.getRandomInjuryChance();
        HashSet<ITeam> teams = stateMachine.getGameDayTeams();

        for (ITeam team : teams) {
            ArrayList<IPlayer> players = team.getPlayers();
            for (IPlayer player : players) {
                Date recoveryDate = injuryChance.getRecoveryDate(currentDate);
                player.checkPlayerInjury(randomInjuryChance, recoveryDate, currentDate);
            }
        }

        //	stateMachine.setCurrState(stateMachine.getAdvanceTime());
        //	stateMachine.getCurrState().doTask();

    }

    @Override
    public void exit() {

    }

    @Override
    public IState doTask() {
        return null;
    }

}
