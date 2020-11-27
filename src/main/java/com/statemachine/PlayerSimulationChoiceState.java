package com.statemachine;
import com.inputoutputmodel.DisplayToUser;
import com.inputoutputmodel.IDisplayToUser;

public class PlayerSimulationChoiceState implements IState {

	@Override
	public void entry() {
	}

	@Override
	public IState doTask() {
		StateMachineAbstractFactory stateFactory = StateMachineAbstractFactory.instance();
		IStateMachine stateMachine = stateFactory.createStateMachine(null);
       
		IDisplayToUser displayToUser = new DisplayToUser();
		displayToUser.displayMsgToUser("How many seasons you want to simulate?");
		int noOfSeason = displayToUser.takeNumberInputFromUser();
		stateMachine.getGame().getLeagues().get(0).setSeasonToSimulate(noOfSeason);
		stateMachine.getGame().getLeagues().get(0).setSeason(1);
		return stateFactory.createInitializeSeasonState();
	}


}