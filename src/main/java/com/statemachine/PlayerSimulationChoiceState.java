package com.statemachine;
import com.datamodel.leaguedatamodel.IGame;
import com.datamodel.leaguedatamodel.LeagueDataModelAbstractFactory;
import com.inputoutputmodel.DisplayToUser;
import com.inputoutputmodel.IDisplayToUser;

public class PlayerSimulationChoiceState implements IState {

	@Override
	public void entry() {
	}

	@Override
	public IState doTask() {
		StateMachineAbstractFactory stateFactory = StateMachineAbstractFactory.instance();
		LeagueDataModelAbstractFactory factory = LeagueDataModelAbstractFactory.instance();
		IGame game = factory.createGame();
		IDisplayToUser displayToUser = new DisplayToUser();
		if(game.getLeagues().get(0).getSeasonToSimulate()==0) {
			displayToUser.displayMsgToUser("How many seasons you want to simulate?");
			int noOfSeason = displayToUser.takeNumberInputFromUser();
			game.getLeagues().get(0).setSeasonToSimulate(noOfSeason);
			game.getLeagues().get(0).setSeason(1);
		}
		return stateFactory.createInitializeSeasonState();
	}


}