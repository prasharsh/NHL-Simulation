package g4dhl;

import java.util.ArrayList;

public class League implements ILeague {

	private String leagueName;
	private ArrayList<IConference> conferences = new ArrayList<>();
	private ArrayList<IFreeAgent> freeAgents = new ArrayList<>();

	@Override
	public String getLeagueName() {
		return leagueName;
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
	public void setConferences(ArrayList<IConference> conferences) {
		this.conferences = conferences;
	}

	@Override
	public ArrayList<IFreeAgent> getFreeAgents() {
		return freeAgents;
	}

	@Override
	public void setFreeAgents(ArrayList<IFreeAgent> freeAgents) {
		this.freeAgents = freeAgents;
	}

	@Override
	public void addFreeAgent(IFreeAgent freeAgent) {
		freeAgents.add(freeAgent);
	}

	@Override
	public void addConference(IConference conference) {
		conferences.add(conference);
	}
}
