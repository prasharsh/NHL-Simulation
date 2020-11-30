package com.statemachine;

import com.datamodel.leaguedatamodel.Game;
import com.datamodel.leaguedatamodel.IGame;
import com.datamodel.leaguedatamodel.ImportJson;
import com.datamodel.leaguedatamodel.LeagueDataModelAbstractFactory;

public class JsonImportState implements IState {

	String path;

	public JsonImportState(String path) {
		this.path = path;
	}

	public JsonImportState() {
		super();
	}

	public static boolean isNullOrEmpty(String str) {
		return (str == null || str.isEmpty());
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public IState doTask() {
		StateMachineAbstractFactory stateFactory = StateMachineAbstractFactory.instance();

		if(isNullOrEmpty(path)) {
			return stateFactory.createLoadTeamState();
		} else {
			Game game = new Game();
			ImportJson json = new ImportJson();
			game.addLeague(json.parseJson(path));
			LeagueDataModelAbstractFactory factory = LeagueDataModelAbstractFactory.instance();
			IGame gameModel = factory.createGame();
			gameModel.addLeague(game.getLeagues().get(0));
			return stateFactory.createTeamSate();
		}
	}

	@Override
	public void entry() {
		doTask();
	}
}