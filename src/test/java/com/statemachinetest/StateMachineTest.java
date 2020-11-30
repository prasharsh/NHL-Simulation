package com.statemachinetest;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.statemachine.IStateMachine;
import com.statemachine.StateMachineAbstractFactory;
import com.statemachine.StateMachineFactory;

public class StateMachineTest {

    @Test
    public void startTest() {
        StateMachineAbstractFactory.setFactory(new StateMachineFactory());
        StateMachineAbstractFactory stateFactory = StateMachineAbstractFactory.instance();
        IStateMachine stateMachine = stateFactory.createStateMachine(null);
        assertNotNull(stateMachine);
    }

}