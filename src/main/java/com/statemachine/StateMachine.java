package com.statemachine;

import java.util.HashSet;
import java.util.List;

import com.datamodel.leaguedatamodel.Game;
import com.datamodel.leaguedatamodel.IGame;
import com.datamodel.leaguedatamodel.ITeam;

public class StateMachine implements IStateMachine {

    private static StateMachine instance;
    private IState currentState;
    private List<ITeam> teamList;
    private HashSet<ITeam> gameDayTeams;

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

 
    public IState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(IState currentState) {
        this.currentState = currentState;
    }

}