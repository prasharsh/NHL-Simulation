package com.dal.dhl.stateMachine;

import com.dal.dhl.states.CreateTeams;
import com.dal.dhl.states.IStateTransistion;
import com.dal.dhl.states.JsonImport;
import com.dal.dhl.states.LoadTeams;
import com.dal.dhl.states.PlayerChoice;
import com.dal.dhl.states.PlayerSimulationChoice;

import g4dhl.Game;

public class DHLStateMachine {

	IStateTransistion importJson;
	IStateTransistion createTeam;
	IStateTransistion loadTeam;
	IStateTransistion playerChoice;
	IStateTransistion playerSimulationChoice;
	IStateTransistion currState;
	Game game;



	public DHLStateMachine(String path) {

		importJson = new JsonImport(this, path);
		createTeam = new CreateTeams(this);
		loadTeam = new LoadTeams(this);
		playerChoice = new PlayerChoice(this);
		playerSimulationChoice = new PlayerSimulationChoice(this);
		currState = importJson;
	}

	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}

	public IStateTransistion getImportJson() {
		return importJson;
	}
	public void setImportJson(IStateTransistion importJson) {
		this.importJson = importJson;
	}
	public IStateTransistion getCreateTeam() {
		return createTeam;
	}
	public void setCreateTeam(IStateTransistion createTeam) {
		this.createTeam = createTeam;
	}
	public IStateTransistion getLoadTeam() {
		return loadTeam;
	}
	public void setLoadTeam(IStateTransistion loadTeam) {
		this.loadTeam = loadTeam;
	}
	public IStateTransistion getPlayerChoice() {
		return playerChoice;
	}
	public void setPlayerChoice(IStateTransistion playerChoice) {
		this.playerChoice = playerChoice;
	}
	public IStateTransistion getPlayerSimulationChoice() {
		return playerSimulationChoice;
	}
	public void setPlayerSimulationChoice(IStateTransistion playerSimulationChoice) {
		this.playerSimulationChoice = playerSimulationChoice;
	}
	public IStateTransistion getCurrState() {
		return currState;
	}
	public void setCurrState(IStateTransistion currState) {
		this.currState = currState;
	}

	void start(){
		currState.enrty();
	}




}
