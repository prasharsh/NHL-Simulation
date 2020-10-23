package g4dhl;

import java.sql.Date;
import java.util.ArrayList;

import g4db.IGameDB;

public class League implements ILeague, ILoadDataFromDB {

	private int leagueId;
	private String leagueName;
	private ArrayList<IConference> conferences;
	private ArrayList<IFreeAgent> freeAgents;
	private ArrayList<IGeneralManager> managers;
	private ArrayList<IHeadCoach> coaches;
	private Date curreantDate;
	private IGameplayConfig gameplayConfig;

	public League() {
		leagueName = null;
		conferences = new ArrayList<>();
		freeAgents = new ArrayList<>();
		managers = new ArrayList<>();
		coaches = new ArrayList<>();
	}

	private boolean checkIfLeagueNameIsNullOrEmpty(String leagueName) {
		return leagueName == null || leagueName.trim().isEmpty();
	}

	private boolean checkIfConferenceIsNull(IConference conference) {
		return conference == null;
	}

	private boolean checkIfConferenceNameIsNullOrEmpty(String conferenceName) {
		return conferenceName == null || conferenceName.trim().isEmpty();
	}

	private boolean checkIfConferenceNameAlreadyExists(String conferenceName) {
		for (IConference conference : conferences) {
			if (conference.getConferenceName().equals(conferenceName)) {
				return true;
			}
		}
		return false;
	}

	private boolean checkIfFreeAgentIsNull(IFreeAgent freeAgent) {
		return freeAgent == null;
	}

	private boolean checkIfFreeAgentNameIsNullOrEmpty(String freeAgent) {
		return freeAgent == null || freeAgent.trim().isEmpty();
	}

	private boolean checkIfFreeAgentNameAlreadyExists(String freeAgentName) {
		for (IFreeAgent freeAgent : freeAgents) {
			if (freeAgent.getFreeAgentName().equals(freeAgentName)) {
				return true;
			}
		}
		return false;
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
	public boolean setLeagueId(int leagueId) {
		this.leagueId = leagueId;
		return true;
	}

	@Override
	public boolean setLeagueName(String leagueName) {
		if (checkIfLeagueNameIsNullOrEmpty(leagueName))
			return false;
		this.leagueName = leagueName;
		return true;
	}

	@Override
	public ArrayList<IConference> getConferences() {
		return conferences;
	}

	@Override
	public boolean addConference(IConference conference) {

		if (checkIfConferenceIsNull(conference))
			return false;
		if (checkIfConferenceNameIsNullOrEmpty(conference.getConferenceName()))
			return false;
		if (checkIfConferenceNameAlreadyExists(conference.getConferenceName()))
			return false;

		conferences.add(conference);
		return true;
	}

	@Override
	public ArrayList<IFreeAgent> getFreeAgents() {
		return freeAgents;
	}

	@Override
	public boolean addFreeAgent(IFreeAgent freeAgent) {

		if (checkIfFreeAgentIsNull(freeAgent))
			return false;
		if (checkIfFreeAgentNameIsNullOrEmpty(freeAgent.getFreeAgentName()))
			return false;
		if (checkIfFreeAgentNameAlreadyExists(freeAgent.getFreeAgentName()))
			return false;

		freeAgents.add(freeAgent);
		return true;
	}

	@Override
	public Date getCurrentDate() {
		return curreantDate;
	}

	@Override
	public boolean setCurrentDate(Date currentDate) {
		this.curreantDate = currentDate;
		return true;
	}

	@Override
	public ArrayList<IGeneralManager> getManagers() {
		return managers;
	}

	@Override
	public boolean setManager(IGeneralManager manager) {
		managers.add(manager);
		return true;
	}

	@Override
	public ArrayList<IHeadCoach> getCoaches() {
		return coaches;
	}

	@Override
	public boolean setCoach(IHeadCoach coach) {
		coaches.add(coach);
		return true;
	}

	@Override
	public IGameplayConfig getGamePlayConfig() {
		return this.gameplayConfig;
	}

	@Override
	public boolean setGamePlayConfig(IGameplayConfig gameplayConfig) {
		this.gameplayConfig = gameplayConfig;
		return true;
	}

	@Override
	public void loadFromDB(IGameDB gameDB) {
		gameDB.loadConferencesFromDB(this);
	}

}
