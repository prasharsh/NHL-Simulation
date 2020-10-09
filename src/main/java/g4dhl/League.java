package g4dhl;

import java.util.ArrayList;

import g4db.IGameDB;

public class League implements ILeague, ILoadDataFromDB {

	private int leagueId;
	private String leagueName;
	private ArrayList<IConference> conferences;
	private ArrayList<IFreeAgent> freeAgents;

	public League() {
		leagueName = null;
		conferences = new ArrayList<>();
		freeAgents = new ArrayList<>();
	}

	@Override
	public int getLeagueId() {
		return leagueId;
	}

	@Override
	public String getLeagueName() {
		return leagueName;
	}

	@Override
	public void setLeagueId(int leagueId) {
		this.leagueId = leagueId;
	}

	@Override
	public void setLeagueName(String leagueName) {
		this.leagueName = leagueName;
	}

	@Override
	public ArrayList<IConference> getConferences() {
		return conferences;
	}

	@Override
	public IConference getConference(int index) {
		return conferences.get(index);
	}

	@Override
	public void addConference(IConference conference) {
		conferences.add(conference);
	}

	@Override
	public void setConferences(ArrayList<IConference> conferences) {
		this.conferences = conferences;
	}

	@Override
	public ArrayList<IFreeAgent> getFreeAgents() {
		return freeAgents;
	}

	@Override
	public IFreeAgent getFreeAgent(int index) {
		return freeAgents.get(index);
	}

	@Override
	public void addFreeAgent(IFreeAgent freeAgent) {
		freeAgents.add(freeAgent);
	}

	@Override
	public void setFreeAgents(ArrayList<IFreeAgent> freeAgents) {
		this.freeAgents = freeAgents;
	}

	@Override
	public void loadFromDB(IGameDB gameDB) {
		gameDB.loadConferencesFromDB(this);
	}
}
