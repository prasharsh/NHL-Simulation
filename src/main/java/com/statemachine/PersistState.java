package com.statemachine;

public class PersistState implements IState {


    @Override
    public void entry() {

//        IDisplayToUser displayToUser = new DisplayToUser();
//        displayToUser.displayMsgToUser("Saving season data to DB started");
//        IGameDB gameDB = new GameDB();
//        this.stateMachine.game.saveToDb(gameDB);
//        displayToUser.displayMsgToUser("Saving season data to DB completed");
    }


    @Override
    public IState doTask() {
        StateMachineAbstractFactory stateFactory = StateMachineAbstractFactory.instance();
        return stateFactory.createAdvanceTimeState();
    }
}