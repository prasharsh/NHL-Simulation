package com.dal.dhl.states;

import com.dal.dhl.stateMachine.StateMachine;

import g4dhl.Game;
import g4dhl.ITraining;

public class Training implements IState {
	StateMachine stateMachine;

	public Training(StateMachine stateMachine) {

		this.stateMachine = stateMachine;
	}

	@Override
	public void entry() {
		// TODO Auto-generated method stub
		Game game = stateMachine.getGame();
		ITraining trainingSchedule = game.getLeagues().get(0).getGamePlayConfig().getTraining();
		trainingSchedule.setNoOfDaysTrained(trainingSchedule.getNoOfDaysTrained() + 1);
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub

	}

	@Override
	public IState doTask() {
		Game game = stateMachine.getGame();
		ITraining trainingSchedule = game.getLeagues().get(0).getGamePlayConfig().getTraining();
		int statIncreaseCheck = trainingSchedule.getDaysUntilStatIncreaseCheck();
		int noOfDaysTrained = trainingSchedule.getNoOfDaysTrained();
		if (noOfDaysTrained >= statIncreaseCheck) {
			trainingSchedule.increaseStatOrInjurePlayer(game);
			trainingSchedule.setNoOfDaysTrained(0);
		}
		return stateMachine.getSimulateGame();
	}

}
