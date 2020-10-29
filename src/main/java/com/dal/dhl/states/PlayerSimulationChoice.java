package com.dal.dhl.states;

import java.util.Scanner;

import com.dal.dhl.stateMachine.StateMachine;

public class PlayerSimulationChoice implements IState{

	StateMachine stateMachine;
	Scanner userInput = new Scanner(System.in);


	public PlayerSimulationChoice(StateMachine stateMachine) {

		this.stateMachine = stateMachine;
	}

	@Override
	public void entry() {

		System.out.println("How many seasons you want to simulate?");
	}

	@Override
	public IState doTask() {
		int noOfSeason = userInput.nextInt();
		stateMachine.getGame().getLeagues().get(0).setSeasonToSimulate(noOfSeason);
		stateMachine.getGame().getLeagues().get(0).setSeason(1);
		return stateMachine.getInitializeSeason();

	}
	@Override
	public void exit() {
		//stateMachine.getCurrState().entry();

	}


}
