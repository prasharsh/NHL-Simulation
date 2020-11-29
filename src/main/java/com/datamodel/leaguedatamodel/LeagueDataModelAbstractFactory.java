package com.datamodel.leaguedatamodel;

public abstract class LeagueDataModelAbstractFactory {

    private static LeagueDataModelAbstractFactory uniqueInstance = null;

    public static LeagueDataModelAbstractFactory instance() {
        return uniqueInstance;
    }

    public static void setFactory(LeagueDataModelAbstractFactory leagueDataModelFactory) {
        uniqueInstance = leagueDataModelFactory;
    }

    public abstract ISimulateMatch createSimulateMatch();

    public abstract IGameSchedule createGameSchedule();

    public abstract ITeamStanding createTeamStanding();

    public abstract ITimeConcept createTimeConcept();

    public abstract ILeague createLeague();

    public abstract IGame createGame();

    public abstract IConference createConference();

    public abstract IDivision createDivision();

    public abstract ITeam createTeam();

    public abstract IPlayer createPlayer();

    public abstract IPlayer createFreeAgent();

    public abstract IGeneralManager createGeneralManager();

    public abstract IHeadCoach createHeadCoach();

    public abstract ITrading createTrading();

    public abstract ITraining createTraining();

    public abstract IDrafting createDrafting();

    public abstract IRandomPlayer createRandomPlayer();

}
