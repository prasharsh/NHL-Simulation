package com.dal.dhl.states;

import com.dal.dhl.stateMachine.StateMachine;

import g4db.GameDB;
import g4db.IGameDB;
import g4dhl.Game;

public class Persist implements IState {
	StateMachine stateMachine;

	public Persist(StateMachine stateMachine) {

		this.stateMachine = stateMachine;
	}

	@Override
	public void entry() {
		// TODO Auto-generated method stub

		Game game = stateMachine.getGame();
		IGameDB gameDB = new GameDB();
		game.saveToDb(gameDB);
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub

	}

	@Override
	public IState doTask() {
		return stateMachine.getAdvanceTime();
	}

}
