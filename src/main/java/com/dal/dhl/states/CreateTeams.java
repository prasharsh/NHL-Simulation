package com.dal.dhl.states;

import com.dal.dhl.stateMachine.CreateTeam;
import com.dal.dhl.stateMachine.DHLStateMachine;

import g4db.GameDB;
import g4dhl.Game;

public class CreateTeams implements IStateTransistion {

	Game game = new Game();
	String filePath;
	DHLStateMachine stateMachine;

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public CreateTeams(DHLStateMachine stateMachine) {

		this.stateMachine = stateMachine;
	}

	@Override
	public void entry() {

		doTask();
	}

	@Override
	public void exit() {
		stateMachine.getCurrState().entry();
	}

	@Override
	public void doTask() {
		GameDB gameDB = new GameDB();
		CreateTeam newTeam = new CreateTeam();
		Game game = stateMachine.getGame();
		newTeam.createNewTeam(game);
		game.saveToDb(gameDB);
		System.out.println("League Data Saved with new team!!");
		stateMachine.setCurrState(stateMachine.getPlayerSimulationChoice());
		exit();

	}

}
