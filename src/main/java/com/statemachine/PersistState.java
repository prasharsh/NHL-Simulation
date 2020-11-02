package com.statemachine;

import com.datamodel.leaguedatamodel.ExportGameToFile;
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
		System.out.println("Saving json started");
		ExportGameToFile state = new ExportGameToFile();
		state.exportGameToJSON(this.stateMachine.game, "finalGameJson.txt");
		System.out.println("Saving json completed");
	}

	@Override
	public void exit() {
	}

	@Override
	public IState doTask() {
		return stateMachine.getAdvanceTime();
	}

}