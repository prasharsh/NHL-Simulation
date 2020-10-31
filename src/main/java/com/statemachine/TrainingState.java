package com.statemachine;

import com.datamodeltest.leaguedatamodel.Game;
import com.datamodeltest.gameplayconfig.ITrainingConfig;

public class TrainingState implements IState {
    StateMachine stateMachine;


    public TrainingState(StateMachine stateMachine) {

        this.stateMachine = stateMachine;
    }

    @Override
    public void entry() {
        Game game = stateMachine.getGame();
        ITrainingConfig trainingSchedule = game.getLeagues().get(0).getGamePlayConfig().getTraining();
        trainingSchedule.setNoOfDaysTrained(trainingSchedule.getNoOfDaysTrained() + 1);
    }

    @Override
    public void exit() {

    }


    @Override
    public IState doTask() {
        Game game = stateMachine.getGame();
        ITrainingConfig trainingSchedule = game.getLeagues().get(0).getGamePlayConfig().getTraining();
        int statIncreaseCheck = trainingSchedule.getDaysUntilStatIncreaseCheck();
        int noOfDaysTrained = trainingSchedule.getNoOfDaysTrained();
        if (noOfDaysTrained >= statIncreaseCheck) {
            trainingSchedule.trainPlayers(game);
            trainingSchedule.setNoOfDaysTrained(0);
        }
        return stateMachine.getSimulateGame();
    }


}
