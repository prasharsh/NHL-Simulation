package com.statemachine;
import com.datamodel.leaguedatamodel.CreateTeam;
import com.datamodel.leaguedatamodel.IGame;
import com.datamodel.leaguedatamodel.LeagueDataModelAbstractFactory;

public class CreateTeamsState implements IState {

	String filePath;

	@Override
	public IState doTask() {
		StateMachineAbstractFactory stateFactory = StateMachineAbstractFactory.instance();
		CreateTeam newTeam = new CreateTeam();
		LeagueDataModelAbstractFactory factory = LeagueDataModelAbstractFactory.instance();
		IGame game = factory.createGame();
		newTeam.createNewTeam(game);
		return stateFactory.createPlayerSimulationChoiceState();
	}
}