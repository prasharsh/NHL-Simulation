package com.statemachine;
import com.datamodel.leaguedatamodel.Game;
import com.datamodel.leaguedatamodel.ImportJson;

public class JsonImportState implements IState {

	String path;

	public JsonImportState(String path) {
		this.path = path;
	}

	public JsonImportState() {
		super();
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
		IStateMachine stateMachine = stateFactory.createStateMachine(null);

		if (isNullOrEmpty(path)) {
			return stateFactory.createLoadTeamState();
		} else {
			Game game = new Game();
			ImportJson json = new ImportJson();
			game.addLeague(json.parseJson(path));
			stateMachine.setGame(game);
			return stateFactory.createTeamSate();
		}
	}

	public static boolean isNullOrEmpty(String str) {
		return (str == null || str.isEmpty());
	}

	@Override
	public void entry() {
		doTask();
	}
}