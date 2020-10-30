package com.dal.dhl.states;

import com.dal.dhl.stateMachine.CreateTeam;
import com.dal.dhl.stateMachine.StateMachine;

import g4db.GameDB;
import g4dhl.Game;

public class CreateTeams implements IState {

	Game game = new Game();
	String filePath;
	StateMachine stateMachine;

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public CreateTeams(StateMachine stateMachine2) {

		this.stateMachine = stateMachine2;
	}

	@Override
	public void entry() {

	}

	@Override
	public void exit() {
		//	stateMachine.getCurrState().entry();
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
