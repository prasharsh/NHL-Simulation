package com.statemachine;

import com.datamodeltest.ITrading;
import com.datamodeltest.Trading;

public class ExecuteTradesState implements IState {
    StateMachine stateMachine;

    public ExecuteTradesState(StateMachine stateMachine) {
        this.stateMachine = stateMachine;
    }

    @Override
    public void entry() {
        ITrading trading = new Trading();
        trading.startTrading(stateMachine.getGame().getLeagues().get(0).getGamePlayConfig().getTrading(), stateMachine.getGame().getLeagues().get(0),
                stateMachine.getTeamList());
    }

    @Override
    public void exit() {
    }

    @Override
    public IState doTask() {
        return stateMachine.getAging();
    }
}
