package com.statemachine;
import com.datamodel.leaguedatamodel.Game;
import com.datamodel.leaguedatamodel.ImportJson;

public class JsonImportState implements IState {

	String path;
	StateMachine stateMachine;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setStateMachine(StateMachine stateMachine) {
		this.stateMachine = stateMachine;
	}

	public JsonImportState(StateMachine stateMachine, String filePath) {
		this.stateMachine = stateMachine;
		this.path = filePath;
	}

	@Override
	public IState doTask() {
		if (isNullOrEmpty(path)) {
			return stateMachine.getLoadTeam();
		} else {
			Game game = new Game();
			ImportJson json = new ImportJson();
			game.addLeague(json.parseJson(path));
			stateMachine.setGame(game);
			return stateMachine.getCreateTeam();
		}
	}

	@Override
	public void exit() {
	}

	public static boolean isNullOrEmpty(String str) {
		return (str == null || str.isEmpty());
	}

	@Override
	public void entry() {
		doTask();
	}
}