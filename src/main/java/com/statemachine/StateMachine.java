package com.statemachine;

import com.datamodel.leaguedatamodel.IGame;
import com.datamodel.leaguedatamodel.ITeam;

import java.util.HashSet;
import java.util.List;

public class StateMachine implements IStateMachine {

    private static StateMachine instance;
    private IState currentState;
    private List<ITeam> teamList;
    private HashSet<ITeam> gameDayTeams;
    IGame game;

    public StateMachine(String path) {
        IState jsonImport = new JsonImportState(path);
        currentState = jsonImport;
    }

    private void transistionToState(IState toState) {
        
        currentState = toState;
        currentState.entry();
    }

    public void start() {
        while (currentState != null) {
            IState transistionState = currentState.doTask();
            if (transistionState != currentState) {
                transistionToState(transistionState);
            }
        }
    }

    public List<ITeam> getTeamList() {
        return teamList;
    }

    public void setTeamList(List<ITeam> teamList) {
        this.teamList = teamList;
    }

    public HashSet<ITeam> getGameDayTeams() {
        return gameDayTeams;
    }

    public void setGameDayTeams(HashSet<ITeam> gameDayTeams) {
        this.gameDayTeams = gameDayTeams;
    }

    public IGame getGame() {
        return game;
    }

    public void setGame(IGame game) {
        this.game = game;
    }

    public IState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(IState currentState) {
        this.currentState = currentState;
    }

}