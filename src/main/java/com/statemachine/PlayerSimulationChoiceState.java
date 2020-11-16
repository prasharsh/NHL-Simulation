package com.statemachine;
import com.inputoutputmodel.DisplayToUser;
import com.inputoutputmodel.IDisplayToUser;

public class PlayerSimulationChoiceState implements IState {

	StateMachine stateMachine;

	public PlayerSimulationChoiceState(StateMachine stateMachine) {
		this.stateMachine = stateMachine;
	}

	@Override
	public void entry() {
	}

	@Override
	public IState doTask() {
		IDisplayToUser displayToUser = new DisplayToUser();
		displayToUser.displayMsgToUser("How many seasons you want to simulate?");
		int noOfSeason = displayToUser.takeNumberInputFromUser();
		stateMachine.getGame().getLeagues().get(0).setSeasonToSimulate(noOfSeason);
		stateMachine.getGame().getLeagues().get(0).setSeason(1);
		return stateMachine.getInitializeSeason();
	}

	@Override
	public void exit() {
	}
}