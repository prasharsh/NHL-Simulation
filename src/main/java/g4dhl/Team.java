package g4dhl;

import java.util.ArrayList;

import g4db.IGameDB;

public class Team implements ITeam, ILoadDataFromDB {

	int teamId;
	private String teamName;
	private IGeneralManager generalManager;
	private IHeadCoach headCoach;
	private ArrayList<IPlayer> players = new ArrayList<>();

	@Override
	public String getTeamName() {
		return teamName;
	}

	@Override
	public int getTeamId() {
		return teamId;
	}

	@Override
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	@Override
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	@Override
	public IGeneralManager getGeneralManager() {
		return generalManager;
	}

	@Override
	public void setGeneralManager(IGeneralManager generalManager) {
		this.generalManager = generalManager;
	}

	@Override
	public IHeadCoach getHeadCoach() {
		return headCoach;
	}

	@Override
	public void setHeadCoach(IHeadCoach headCoach) {
		this.headCoach = headCoach;
	}

	@Override
	public ArrayList<IPlayer> getPlayers() {
		return players;
	}

	@Override
	public IPlayer getPlayer(int index) {
		return players.get(index);
	}

	@Override
	public void addPlayer(IPlayer player) {
		players.add(player);
	}

	@Override
	public void setPlayers(ArrayList<IPlayer> players) {
		this.players = players;
	}

	@Override
	public void loadFromDB(IGameDB gameDB) {
		gameDB.loadPlayersFromDB(this);
		gameDB.loadGeneralManagerFromDb(this);
		gameDB.loadHeadCoachFromDB(this);
	}
}
