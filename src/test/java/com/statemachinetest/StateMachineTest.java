package com.statemachinetest;

import com.statemachine.IStateMachine;
import com.statemachine.StateMachineAbstractFactory;
import com.statemachine.StateMachineFactory;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class StateMachineTest {


    @Test
    public void startTest() {
        StateMachineAbstractFactory.setFactory(new StateMachineFactory());
        StateMachineAbstractFactory stateFactory = StateMachineAbstractFactory.instance();
        IStateMachine stateMachine = stateFactory.createStateMachine(null);
        assertNotNull(stateMachine);
    }

}