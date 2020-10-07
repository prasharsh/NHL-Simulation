package g4dhl;

import java.util.ArrayList;

public class Division implements IDivision {

	private String divisionName;
	private ArrayList<ITeam> teams = new ArrayList<>();

	@Override
	public String getDivisionName() {
		return divisionName;
	}

	@Override
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}

	@Override
	public ArrayList<ITeam> getTeams() {
		return teams;
	}

	@Override
	public void setTeams(ArrayList<ITeam> teams) {
		this.teams = teams;
	}

	@Override
	public void addTeam(ITeam team) {
		teams.add(team);
	}
}
