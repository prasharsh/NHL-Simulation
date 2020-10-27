package com.dal.dhl.states;

import com.dal.dhl.stateMachine.DHLStateMachine;
import g4dhl.Game;
import g4dhl.ITraining;

public class Training implements IStateTransistion{
	DHLStateMachine stateMachine;



	public Training(DHLStateMachine stateMachine) {

		this.stateMachine = stateMachine;
	}

	@Override
	public void entry() {
		// TODO Auto-generated method stub
		Game game = stateMachine.getGame();
		ITraining trainingSchedule = game.getLeagues().get(0).getGamePlayConfig().getTraining();
		trainingSchedule.setNoOfDaysTrained(trainingSchedule.getNoOfDaysTrained() + 1);
		doTask();
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void doTask() {
		Game game = stateMachine.getGame();
		ITraining trainingSchedule = game.getLeagues().get(0).getGamePlayConfig().getTraining();
		int statIncreaseCheck = trainingSchedule.getDaysUntilStatIncreaseCheck();
		int noOfDaysTrained = trainingSchedule.getNoOfDaysTrained();
		if (noOfDaysTrained >= statIncreaseCheck) {
			trainingSchedule.increaseStatOrInjurePlayer(game);
			trainingSchedule.setNoOfDaysTrained(0);
		}
	}

	

}
