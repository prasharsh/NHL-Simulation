package com.statemachine;
import com.inputoutputmodel.DisplayToUser;
import com.inputoutputmodel.IDisplayToUser;
import com.persistencemodel.GameDB;
import com.persistencemodel.IGameDB;

public class PersistState implements IState {

	StateMachine stateMachine;

	public PersistState(StateMachine stateMachine) {
		this.stateMachine = stateMachine;
	}

	@Override
	public void entry() {
		IDisplayToUser displayToUser = new DisplayToUser();
		displayToUser.displayMsgToUser("Saving season data to DB started");
		IGameDB gameDB = new GameDB();
		this.stateMachine.game.saveToDb(gameDB);
		displayToUser.displayMsgToUser("Saving season data to DB completed");
	}

	@Override
	public void exit() {
	}

	@Override
	public IState doTask() {
		return stateMachine.getAdvanceTime();
	}
}