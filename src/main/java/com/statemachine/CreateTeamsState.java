package com.statemachine;
import com.datamodel.leaguedatamodel.CreateTeam;
import com.datamodel.leaguedatamodel.Game;
import com.datamodel.leaguedatamodel.IGame;

public class CreateTeamsState implements IState {

	Game game = new Game();
	String filePath;
	IStateMachine stateMachine;

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public CreateTeamsState(IStateMachine stateMachine) {
		this.stateMachine = stateMachine;
	}

	@Override
	public void entry() {
	}

	@Override
	public void exit() {
	}

	@Override
	public IState doTask() {
		CreateTeam newTeam = new CreateTeam();
		IGame game = stateMachine.getGame();
		newTeam.createNewTeam(game);
		return stateMachine.getPlayerSimulationChoice();
	}
}