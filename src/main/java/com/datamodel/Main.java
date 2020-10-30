package com.datamodel;

import com.statemachine.StateMachine;

public class Main {

    public static boolean isNullOrEmpty(String str) {
		return str == null || str.trim().isEmpty();
	}

    public static void main(String[] args){
        String filePath = null;
        try {
            filePath = args[0];
        } catch (ArrayIndexOutOfBoundsException ae) {
            // do nothing, state machine will take care
        }

        StateMachine stateMachine = new StateMachine(filePath);
        stateMachine.start();

    }
}
