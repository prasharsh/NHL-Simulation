package com.datamodel.leaguedatamodel;

public interface IDataModelFactory {

    ISimulateMatch getSimulateMatch();

    IGameSchedule getGameSchedule();

    ITeamStanding getITeamStanding();

}
