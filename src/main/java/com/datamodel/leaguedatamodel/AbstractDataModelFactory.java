package com.datamodel.leaguedatamodel;

public class AbstractDataModelFactory implements IDataModelFactory {

	private static AbstractDataModelFactory uniqueInstance = null;
	private ISimulateMatch simulateMatch;
	private IGameSchedule gameSchedule;
	private ITeamStanding teamStanding;
	
	private AbstractDataModelFactory() {
	
		simulateMatch = new SimulateMatch();
		gameSchedule = new GameSchedule();
		teamStanding = new TeamStanding();
	}

	public static AbstractDataModelFactory instance(){
		if (null == uniqueInstance)
		{
			uniqueInstance = new AbstractDataModelFactory();
		}
		return uniqueInstance;
	}
	
	public static AbstractDataModelFactory getNewInstance(){
			return new AbstractDataModelFactory();
	}
	
	@Override
	public ISimulateMatch getSimulateMatch() {
		return simulateMatch;
	}

	@Override
	public IGameSchedule getGameSchedule() {
		return gameSchedule;
	}

	@Override
	public ITeamStanding getITeamStanding() {
		return teamStanding;
	}

}
