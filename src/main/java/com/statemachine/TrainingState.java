package com.statemachine;
import com.datamodel.gameplayconfig.ITrainingConfig;
import com.datamodel.leaguedatamodel.IGame;
import com.datamodel.leaguedatamodel.ITraining;
import com.datamodel.leaguedatamodel.Training;

public class TrainingState implements IState {
	
	IStateMachine stateMachine;

	public TrainingState(IStateMachine stateMachine) {
		this.stateMachine = stateMachine;
	}

	@Override
	public void entry() {
		IGame game = stateMachine.getGame();
		ITrainingConfig trainingSchedule = game.getLeagues().get(0).getGamePlayConfig().getTraining();
		trainingSchedule.setNoOfDaysTrained(trainingSchedule.getNoOfDaysTrained() + 1);
	}

	@Override
	public void exit() {
	}

	@Override
	public IState doTask() {
		IGame game = stateMachine.getGame();
		ITrainingConfig trainingSchedule = game.getLeagues().get(0).getGamePlayConfig().getTraining();
		int statIncreaseCheck = trainingSchedule.getDaysUntilStatIncreaseCheck();
		int noOfDaysTrained = trainingSchedule.getNoOfDaysTrained();
		if (noOfDaysTrained >= statIncreaseCheck) {
			ITraining training = new Training();
			training.trainPlayers(game);
			trainingSchedule.setNoOfDaysTrained(0);
		}
		return stateMachine.getSimulateGame();
	}
}