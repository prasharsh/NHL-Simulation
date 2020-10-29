package com.dal.dhl.states;

import com.dal.dhl.stateMachine.ImportJson;
import com.dal.dhl.stateMachine.StateMachine;

import g4dhl.Game;

public class JsonImport implements IState {

	String path;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	
	public void setStateMachine(StateMachine stateMachine) {
		this.stateMachine = stateMachine;

	}

	StateMachine stateMachine;

	public JsonImport(StateMachine stateMachine, String filePath) {
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
	//	stateMachine.getCurrState().entry();
	}

	public static boolean isNullOrEmpty(String str) {
		if (str != null && !str.isEmpty()) {
			return false;
		}
		return true;
	}

	@Override
	public void entry() {
		doTask();

	}
}
