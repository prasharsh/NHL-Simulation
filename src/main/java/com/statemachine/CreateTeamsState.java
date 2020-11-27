package com.statemachine;
import com.datamodel.leaguedatamodel.CreateTeam;
import com.datamodel.leaguedatamodel.Game;
import com.datamodel.leaguedatamodel.IGame;

public class CreateTeamsState implements IState {

	Game game = new Game();
	String filePath;
	

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	

	@Override
	public void entry() {
	}



	@Override
	public IState doTask() {
		StateMachineAbstractFactory stateFactory = StateMachineAbstractFactory.instance();
		IStateMachine stateMachine = stateFactory.createStateMachine(null);
       
		CreateTeam newTeam = new CreateTeam();
		IGame game = stateMachine.getGame();
		newTeam.createNewTeam(game);
		return stateFactory.createPlayerSimulationChoiceState();
	}
}