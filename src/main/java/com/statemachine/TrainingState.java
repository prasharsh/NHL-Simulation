package com.statemachine;
import com.datamodel.gameplayconfig.ITrainingConfig;
import com.datamodel.leaguedatamodel.IGame;
import com.datamodel.leaguedatamodel.ITraining;
import com.datamodel.leaguedatamodel.LeagueDataModelAbstractFactory;
import com.datamodel.leaguedatamodel.Training;

public class TrainingState implements IState {
	
	@Override
	public void entry() {
		LeagueDataModelAbstractFactory factory = LeagueDataModelAbstractFactory.instance();
		IGame game = factory.createGame();
		ITrainingConfig trainingSchedule = game.getLeagues().get(0).getGamePlayConfig().getTraining();
		trainingSchedule.setNoOfDaysTrained(trainingSchedule.getNoOfDaysTrained() + 1);
	}

	@Override
	public IState doTask() {
		StateMachineAbstractFactory stateFactory = StateMachineAbstractFactory.instance();
		LeagueDataModelAbstractFactory factory = LeagueDataModelAbstractFactory.instance();
		IGame game = factory.createGame();
		ITrainingConfig trainingSchedule = game.getLeagues().get(0).getGamePlayConfig().getTraining();
		int statIncreaseCheck = trainingSchedule.getDaysUntilStatIncreaseCheck();
		int noOfDaysTrained = trainingSchedule.getNoOfDaysTrained();
		if (noOfDaysTrained >= statIncreaseCheck) {
			ITraining training = new Training();
			training.trainPlayers(game);
			trainingSchedule.setNoOfDaysTrained(0);
		}
		return stateFactory.createSimulateGameState();
	}
}