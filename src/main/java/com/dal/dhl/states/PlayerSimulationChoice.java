package com.dal.dhl.states;

import java.util.Scanner;

import com.dal.dhl.stateMachine.DHLStateMachine;

public class PlayerSimulationChoice implements IStateTransistion{

	DHLStateMachine stateMachine;
	Scanner userInput = new Scanner(System.in);


	public PlayerSimulationChoice(DHLStateMachine stateMachine) {

		this.stateMachine = stateMachine;
	}

	@Override
	public void entry() {

		System.out.println("How many seasons you want to simulate?");
		doTask();
	}

	@Override
	public void doTask() {
		int noOfSeason = userInput.nextInt(); 
		for (int i = 1; i <= noOfSeason; i++) {
			System.out.println("Season " + i + " simulated!"); 
			stateMachine.getGame().getLeagues().get(0).setSeason(i);
			stateMachine.setCurrState(stateMachine.getInitializeSeason());
			exit();
		}

	}
	@Override
	public void exit() {
		stateMachine.getCurrState().entry();

	}


}
