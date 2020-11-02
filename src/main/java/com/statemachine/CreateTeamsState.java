package com.statemachine;

import com.datamodel.leaguedatamodel.CreateTeam;
import com.datamodel.leaguedatamodel.Game;
import com.persistencemodel.GameDB;

public class CreateTeamsState implements IState {

	Game game = new Game();
	String filePath;
	StateMachine stateMachine;

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public CreateTeamsState(StateMachine stateMachine2) {

		this.stateMachine = stateMachine2;
	}

	@Override
	public void entry() {

	}

	@Override
	public void exit() {
		// stateMachine.getCurrState().entry();
	}

	@Override
	public IState doTask() {
		GameDB gameDB = new GameDB();
		CreateTeam newTeam = new CreateTeam();
		Game game = stateMachine.getGame();
		newTeam.createNewTeam(game);
		return stateMachine.getPlayerSimulationChoice();
	}

}
