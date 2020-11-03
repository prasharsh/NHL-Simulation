package com.datamodel.leaguedatamodel;
import com.statemachine.StateMachine;

public class Main {

	public static boolean isNullOrEmpty(String str) {
		return str == null || str.trim().isEmpty();
	}

	public static void main(String[] args) {
		String filePath = null;
		try {
			filePath = args[0];
		} catch (ArrayIndexOutOfBoundsException ae) {
			filePath = null;
		}
		StateMachine stateMachine = new StateMachine(filePath);
		stateMachine.start();
	}
}