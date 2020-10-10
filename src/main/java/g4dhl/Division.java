package g4dhl;

import java.util.ArrayList;

import g4db.IGameDB;

public class Division implements IDivision, ILoadDataFromDB {

	private int divisionId;
	private String divisionName;
	private ArrayList<ITeam> teams;

	public Division() {
		divisionName = null;
		teams = new ArrayList<>();
	}

	private boolean checkIfDivisionNameIsNullOrEmpty(String divisionName) {
		return divisionName == null || divisionName.trim().isEmpty();
	}

	private boolean checkIfTeamIsNull(ITeam team) {
		return team == null;
	}

	private boolean checkIfTeamNameAlreadyExists(String teamName){
		for(ITeam team: teams){
			if (team.getTeamName().equals(teamName)){
				return true;
			}
		}
		return false;
	}


	@Override
	public int getDivisionId() {
		return divisionId;
	}

	@Override
	public String getDivisionName() {
		return divisionName;
	}

	@Override
	public boolean setDivisionId(int divisionId) {
		this.divisionId = divisionId;
		return true;
	}

	@Override
	public boolean setDivisionName(String divisionName) {
		if(checkIfDivisionNameIsNullOrEmpty(divisionName)) return false;
		this.divisionName = divisionName;
		return true;
	}

	@Override
	public ArrayList<ITeam> getTeams() {
		return teams;
	}

	@Override
	public boolean addTeam(ITeam team) {
		if(checkIfTeamIsNull(team)) return false;
		if(checkIfDivisionNameIsNullOrEmpty(team.getTeamName())) return false;
		if(checkIfTeamNameAlreadyExists(team.getTeamName())) return false;

		teams.add(team);
		return true;
	}

	@Override
	public void loadFromDB(IGameDB gameDB) {
		gameDB.loadTeamsFromDB(this);
	}
}
