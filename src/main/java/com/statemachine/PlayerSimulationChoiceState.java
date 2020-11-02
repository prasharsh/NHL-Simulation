package com.statemachine;

import java.util.Scanner;

import com.inputoutputmodel.DisplayRoster;
import com.inputoutputmodel.IDisplayRoaster;

public class PlayerSimulationChoiceState implements IState {

	StateMachine stateMachine;
	Scanner userInput = new Scanner(System.in);

	public PlayerSimulationChoiceState(StateMachine stateMachine) {
		this.stateMachine = stateMachine;
	}

	@Override
	public void entry() {
	}

	@Override
	public IState doTask() {
		IDisplayRoaster displayRoaster = new DisplayRoster();
		displayRoaster.displayMessageToUser("How many seasons you want to simulate?");
		int noOfSeason = displayRoaster.takeNumberInputFromUser();
		stateMachine.getGame().getLeagues().get(0).setSeasonToSimulate(noOfSeason);
		stateMachine.getGame().getLeagues().get(0).setSeason(1);
		return stateMachine.getInitializeSeason();
	}

	@Override
	public void exit() {
	}

}