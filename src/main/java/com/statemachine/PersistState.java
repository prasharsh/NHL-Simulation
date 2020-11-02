package com.statemachine;

import com.datamodel.SerializeState;
import com.persistencemodel.GameDB;
import com.persistencemodel.IGameDB;

public class PersistState implements IState {
	StateMachine stateMachine;

	public PersistState(StateMachine stateMachine) {

		this.stateMachine = stateMachine;
	}

	@Override
	public void entry() {
		System.out.println("Start saving");
		IGameDB gameDB = new GameDB();
		this.stateMachine.game.saveToDb(gameDB);
		System.out.println("saving completed");

		SerializeState state = new SerializeState();
		state.exportGameToJSON(this.stateMachine.game,
				"C:\\Users\\Harpreet\\DHL\\csci5308\\src\\test\\resources\\finalGameJson.txt");
	}

	@Override
	public void exit() {

	}

	@Override
	public IState doTask() {
		return stateMachine.getAdvanceTime();
	}

}