package g4dhl;

import java.util.ArrayList;

import g4db.IGameDB;

public class Team implements ITeam, ILoadDataFromDB {

	private int teamId;
	private String teamName;
	private String teamCreatedBy;
	private int lossPointCount= 0;
	private IGeneralManager generalManager;
	private IHeadCoach headCoach;
	private ArrayList<IPlayer> players;

	public Team() {
		this.players = new ArrayList<>();
	}

	private boolean checkIfTeamNameIsNullOrEmpty(String teamName) {
		return teamName == null || teamName.trim().isEmpty();
	}

	private boolean checkIfPlayerIsNull(IPlayer player) {
		return player == null;
	}

	private boolean checkIfPlayerNameAlreadyExists(String playerName) {
		for (IPlayer player : players) {
			if (player.getPlayerName().equals(playerName)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String getTeamName() {
		return teamName;
	}

	@Override
	public String getTeamCreatedBy() {
		return teamCreatedBy;
	}

	@Override
	public int getLossPointCount() {
		return lossPointCount;
	}

	@Override
	public int getTeamId() {
		return teamId;
	}

	@Override
	public double getTeamStrength() {
		double teamStrength = 0.0;
		for (IPlayer player : players) {
			teamStrength = teamStrength + player.getPlayerStrength();
		}
		return teamStrength;
	}

	@Override
	public boolean setTeamName(String teamName) {
		if (checkIfTeamNameIsNullOrEmpty(teamName))
			return false;
		this.teamName = teamName;
		return true;
	}

	@Override
	public boolean setTeamCreatedBy(String teamCreatedBy) {
		this.teamCreatedBy = teamCreatedBy;
		return true;
	}

	@Override
	public boolean setLossPointCount(int lossPointCount) {
		this.lossPointCount = lossPointCount;
		return true;
	}

	@Override
	public boolean setTeamId(int teamId) {
		this.teamId = teamId;
		return true;
	}

	@Override
	public IGeneralManager getGeneralManager() {
		return generalManager;
	}

	@Override
	public boolean setGeneralManager(IGeneralManager generalManager) {
		this.generalManager = generalManager;
		return true;
	}

	@Override
	public IHeadCoach getHeadCoach() {
		return headCoach;
	}

	@Override
	public boolean setHeadCoach(IHeadCoach headCoach) {
		this.headCoach = headCoach;
		return true;
	}

	@Override
	public ArrayList<IPlayer> getPlayers() {
		return players;
	}

	@Override
	public boolean addPlayer(IPlayer player) {
		if (checkIfPlayerIsNull(player))
			return false;
		if (checkIfTeamNameIsNullOrEmpty(player.getPlayerName()))
			return false;
		if (checkIfPlayerNameAlreadyExists(player.getPlayerName()))
			return false;

		players.add(player);
		return true;
	}

	@Override
	public IPlayer removePlayer(IPlayer player) {
		if (players.contains(player)) {
			players.remove(player);
			return player;
		}
		return null;
	}

	@Override
	public void loadFromDB(IGameDB gameDB) {
		gameDB.loadPlayersFromDB(this);
		gameDB.loadGeneralManagerFromDb(this);
		gameDB.loadHeadCoachFromDB(this);
	}
}
