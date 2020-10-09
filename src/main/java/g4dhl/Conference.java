package g4dhl;

import java.util.ArrayList;

import g4db.IGameDB;

public class Conference implements IConference, ILoadDataFromDB {

	private int conferenceId;
	private String conferenceName;
	private ArrayList<IDivision> divisions;

	public Conference() {
		conferenceName = null;
		divisions = new ArrayList<>();
	}

	@Override
	public int getConferenceId() {
		return conferenceId;
	}

	@Override
	public void setConferenceId(int conferenceId) {
		this.conferenceId = conferenceId;
	}

	@Override
	public String getConferenceName() {
		return conferenceName;
	}

	@Override
	public void setConferenceName(String conferenceName) {
		this.conferenceName = conferenceName;
	}

	@Override
	public ArrayList<IDivision> getDivisions() {
		return divisions;
	}

	@Override
	public IDivision getDivision(int index) {
		return divisions.get(index);
	}

	@Override
	public void addDivision(IDivision division) {
		divisions.add(division);
	}

	@Override
	public void setDivisions(ArrayList<IDivision> divisions) {
		this.divisions = divisions;
	}

	@Override
	public void loadFromDB(IGameDB gameDB) {
		gameDB.loadDivisionsFromDB(this);
	}
}
