package com.datamodel.leaguedatamodel;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import static com.datamodel.leaguedatamodel.Constants.PLAYERS_COUNT;
import static com.datamodel.leaguedatamodel.Constants.MINIMUM_STAT;
import static com.datamodel.leaguedatamodel.Constants.MAXIMUM_STAT;

public class Team implements ITeam {

	private int teamId;
	private String teamName;
	private String teamCreatedBy;
	private int lossPointCount = 0;
	private IGeneralManager generalManager;
	private IHeadCoach headCoach;
	private ArrayList<IPlayer> players;

	private int minSkatingStat = -1;
	private int minShootingStat = -1;
	private int minCheckingStat = -1;
	private int minSavingStat = -1;

	private int teamCurrentSkatingStat;
	private int teamCurrentShootingStat;
	private int teamCurrentCheckingStat;
	private int teamCurrentSavingStat;


	public Team() {
		this.players = new ArrayList<>();
	}

	private boolean checkIfTeamNameIsNullOrEmpty(String teamName) {
		return teamName == null || teamName.trim().isEmpty();
	}

	private boolean checkIfPlayerIsNull(IPlayer player) {
		return player == null;
	}

	private boolean checkIfPlayerNameIsNullOrEmpty(String playerName) {
		return playerName == null || playerName.trim().isEmpty();
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
		if (checkIfTeamNameIsNullOrEmpty(teamName)) {
			return false;
		}
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
	public int getPlayersCount() {
		return players.size();
	}

	@Override
	public int getPlayingGoaliesCount() {
		int count = 0;
		for (IPlayer player : players) {
			if (player.getPlayerPosition().equals("goalie")) {
				if (player.isPlayerRetired()) {
					continue;
				}
				count++;
			}
		}
		return count;
	}

	@Override
	public int getPlayingSkatersCount() {
		int count = 0;
		for (IPlayer player : players) {
			if (player.getPlayerPosition().equals("forward") || player.getPlayerPosition().equals("defense")) {
				if (player.isPlayerRetired()) {
					continue;
				}
				count++;
			}
		}
		return count;
	}

	@Override
	public boolean addPlayer(IPlayer player) {
		if (checkIfPlayerIsNull(player)) {
			return false;
		}
		if (checkIfPlayerNameIsNullOrEmpty(player.getPlayerName())) {
			return false;
		}

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

	//	*****************************************************************************************************************

	@Override
	public void proposeTrade(ITrading trading) {
		boolean isTradePossible = trading.isTradePossible(this);
		if (isTradePossible){
			trading.generateTradeOffer(this);
		}
	}

	@Override
	public void prepareForTrade() {
		teamCurrentSkatingStat = 0;
		teamCurrentShootingStat = 0;
		teamCurrentCheckingStat = 0;
		teamCurrentSavingStat = 0;

		for (IPlayer player: players){
			teamCurrentSkatingStat += player.getPlayerSkating();
			teamCurrentShootingStat += player.getPlayerShooting();
			teamCurrentCheckingStat += player.getPlayerChecking();
			teamCurrentSavingStat += player.getPlayerSaving();
		}

		if (minSkatingStat == -1 || minShootingStat == -1 || minCheckingStat == -1 || minSavingStat == -1){
			minSkatingStat = (int)((1+(Math.random()-0.5)/5)*teamCurrentSkatingStat);
			minShootingStat = (int)((1+(Math.random()-0.5)/5)*teamCurrentShootingStat);
			minCheckingStat = (int)((1+(Math.random()-0.5)/5)*teamCurrentCheckingStat);
			minSavingStat = (int)((1+(Math.random()-0.5)/5)*teamCurrentSavingStat);
		}
	}

	@Override
	public double getTradingGain(int differenceInSkatingStat, int differenceInShootingStat,
								 int differenceInCheckingStat, int differenceInSavingStat) {
		double teamGain = 0.0;
		teamGain += getTeamGain(differenceInSkatingStat, teamCurrentSkatingStat, minSkatingStat);
		teamGain += getTeamGain(differenceInShootingStat, teamCurrentShootingStat, minShootingStat);
		teamGain += getTeamGain(differenceInCheckingStat, teamCurrentCheckingStat, minCheckingStat);
		teamGain += getTeamGain(differenceInSavingStat, teamCurrentSavingStat, minSavingStat);
		return teamGain;
	}

	private double getTeamGain(int differenceInStat, int teamCurrentStat, int minStat) {
		if (teamCurrentStat > minStat){
			if (teamCurrentStat + differenceInStat < minStat){
				return differenceInStat * 1.0 / teamCurrentStat;
			}
		}
		else {
			if (teamCurrentStat + differenceInStat > minStat){
				return differenceInStat * 1.0 / teamCurrentStat;
			}
		}
		return 0.0;
	}

	@Override
	public IPlayer getPlayer(int index) {
		return players.get(index);
	}
}