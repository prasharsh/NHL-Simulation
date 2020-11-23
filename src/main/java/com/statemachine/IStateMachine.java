package com.statemachine;

import com.datamodel.leaguedatamodel.IGame;
import com.datamodel.leaguedatamodel.ITeam;

import java.util.ArrayList;
import java.util.HashSet;

public interface IStateMachine {


    void start();

    ArrayList<ITeam> getTeamList();

    void setTeamList(ArrayList<ITeam> teamList);

    HashSet<ITeam> getGameDayTeams();

    void setGameDayTeams(HashSet<ITeam> gameDayTeams);

    IGame getGame();

    void setGame(IGame game);

    IState getCurrentState();

    void setCurrentState(IState currentState);

    IState getCreateTeam();

    void setCreateTeam(IState createTeam);

    IState getLoadTeam();

    void setLoadTeam(IState loadTeam);

    IState getPlayerChoice();

    void setPlayerChoice(IState playerChoice);

    IState getPlayerSimulationChoice();

    void setPlayerSimulationChoice(IState playerSimulationChoice);

    IState getAdvanceNextSeason();

    void setAdvanceNextSeason(IState advanceNextSeason);

    IState getAdvanceTime();

    void setAdvanceTime(IState advanceTime);

    IState getAging();

    void setAging(IState aging);

    IState getExecuteTrades();

    void setExecuteTrades(IState executeTrades);

    IState getGeneratePlayoffSchedule();

    void setGeneratePlayoffSchedule(IState generatePlayoffSchedule);

    IState getInitializeSeason();

    void setInitializeSeason(IState initializeSeason);

    IState getInjuryCheck();

    void setInjuryCheck(IState injuryCheck);

    IState getPersist();

    void setPersist(IState persist);

    IState getSimulateGame();

    void setSimulateGame(IState simulateGame);

    IState getTraining();

    void setTraining(IState training);

    IState getJsonImport();

    void setJsonImport(IState jsonImport);

    IState getDraftPick();

    void setDraftPick(IState draftPick);

}