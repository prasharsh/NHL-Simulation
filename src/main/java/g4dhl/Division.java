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

	@Override
	public int getDivisionId() {
		return divisionId;
	}

	@Override
	public String getDivisionName() {
		return divisionName;
	}

	@Override
	public void setDivisionId(int divisionId) {
		this.divisionId = divisionId;
	}

	@Override
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}

	@Override
	public ITeam getTeam(int index) {
		return teams.get(index);
	}

	@Override
	public ArrayList<ITeam> getTeams() {
		return teams;
	}

	@Override
	public void addTeam(ITeam team) {
		teams.add(team);
	}

	@Override
	public void setTeams(ArrayList<ITeam> teams) {
		this.teams = teams;
	}

	@Override
	public void loadFromDB(IGameDB gameDB) {
		gameDB.loadTeamsFromDB(this);
	}
}
