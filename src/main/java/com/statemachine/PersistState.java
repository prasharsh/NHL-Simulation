package com.statemachine;

import com.inputoutputmodel.DisplayToUser;
import com.inputoutputmodel.IDisplayToUser;
import com.persistencemodel.GameDB;
import com.persistencemodel.IGameDB;

public class PersistState implements IState {

 

    @Override
    public void entry() {
       
        IDisplayToUser displayToUser = new DisplayToUser();
        displayToUser.displayMsgToUser("Saving season data to DB started");
        IGameDB gameDB = new GameDB();
       // this.stateMachine.game.saveToDb(gameDB);
        displayToUser.displayMsgToUser("Saving season data to DB completed");
    }

 

    @Override
    public IState doTask() {
    	StateMachineAbstractFactory stateFactory = StateMachineAbstractFactory.instance();
        return stateFactory.createAdvanceTimeState();
    }
}