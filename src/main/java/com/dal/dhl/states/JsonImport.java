package com.dal.dhl.states;

import com.dal.dhl.stateMachine.DHLStateMachine;
import com.dal.dhl.stateMachine.ImportJson;

import g4dhl.Game;

public class JsonImport implements IStateTransistion{

	String path;
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public DHLStateMachine getStateMachine() {
		return stateMachine;
	}

	public void setStateMachine(DHLStateMachine stateMachine) {
		this.stateMachine = stateMachine;

	}

	DHLStateMachine stateMachine;
	public JsonImport(DHLStateMachine dhlStateMachine, String filePath) {
		this.stateMachine = dhlStateMachine;
		this.path = filePath;
	}

	@Override
	public void doTask() {
		if (isNullOrEmpty(path)) {
			stateMachine.setCurrState(stateMachine.getLoadTeam());
			this.exit();
		} else {
			Game game = new Game();
			ImportJson json = new ImportJson();
			game.addLeague(json.parseJson(path));
			stateMachine.setCurrState(stateMachine.getCreateTeam());
			stateMachine.setGame(game);
			this.exit();
		}

	}

	@Override
	public void exit() {
		stateMachine.getCurrState().entry();
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
