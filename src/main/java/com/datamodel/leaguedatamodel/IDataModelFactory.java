package com.datamodel.leaguedatamodel;

public interface IDataModelFactory {

	public ISimulateMatch getSimulateMatch();
	
	public IGameSchedule getGameSchedule();
	
	public ITeamStanding getITeamStanding();
	
}
