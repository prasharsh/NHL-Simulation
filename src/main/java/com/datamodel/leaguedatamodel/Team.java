package com.datamodel.leaguedatamodel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.lang.reflect.Array;
import java.util.List;

import static com.datamodel.leaguedatamodel.Constants.*;

public class Team implements ITeam {

	private int teamId;
	private String teamName;
	private String teamCreatedBy;
	private int lossPointCount = 0;
	private IGeneralManager generalManager;
	private IHeadCoach headCoach;
	private ArrayList<IPlayer> players;
	private ITeam[] teamPicks;

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
		this.teamPicks = new ITeam[DRAFT_ROUNDS];
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

	@Override
	public ITeam[] getTeamPicks() {
		return teamPicks;
	}

	@Override
	public ITeam getTeamPickByPosition(int position) {
		return teamPicks[position];
	}

	@Override
	public void initializeTeamPick() {
		Arrays.fill(teamPicks, this);
	}

	@Override
	public void setTeamPick(ITeam team, int position) {
		Array.set(teamPicks, position, team);
	}

    @Override
    public void setActiveRoster(ArrayList<IPlayer> playerArrayList) {
        ArrayList<IPlayer> forwardPlayersList = getActivePlayersWithPosition(playerArrayList,FORWARD);
        ArrayList<IPlayer> defensePlayersList = getActivePlayersWithPosition(playerArrayList,DEFENSE);
        ArrayList<IPlayer> goaliePlayersList = getActivePlayersWithPosition(playerArrayList,GOALIE);
        ArrayList<IPlayer> skaterPlayersList = new ArrayList<>(forwardPlayersList);
        skaterPlayersList.addAll(defensePlayersList);
        List<IPlayer> activeRosterList = new ArrayList<>();
        ArrayList<IPlayer> activeSkaterPlayers = getStrongestPlayersByStrength(skaterPlayersList);
        ArrayList<IPlayer> activeGoaliePlayers = getStrongestPlayersByStrength(goaliePlayersList);
        activeRosterList.addAll(activeSkaterPlayers.subList(0,ACTIVE_SKATERS_COUNT));
        activeRosterList.addAll(activeGoaliePlayers.subList(0,ACTIVE_GOALIES_COUNT));
        for (IPlayer activeRoster : activeRosterList) {
            activeRoster.setRosterStatus(TRUE);
        }
        List<IPlayer> inactiveRosterList = new ArrayList<>(playerArrayList);
        inactiveRosterList.removeAll(activeRosterList);
        for (IPlayer inactiveRoster : inactiveRosterList) {
            inactiveRoster.setRosterStatus(FALSE);
        }
    }
    //	*****************************************************************************************************************

    @Override
    public IPlayer getPlayer(int index) {
        return players.get(index);
    }

	@Override
	public void proposeTrade(ITrading trading) {
		boolean isTradePossible = trading.isTradePossible(this);
		if (isTradePossible) {
			trading.generateBestTradeOffer(this);
			boolean isInterestedInPlayersTrade = trading.isInterestedInPlayersTrade();
			if (isInterestedInPlayersTrade) {
				trading.tradePlayers();
			} else {
				trading.tradeDraft(this);
			}
		}
	}

	@Override
	public void prepareForTrade() {
		teamCurrentSkatingStat = 0;
		teamCurrentShootingStat = 0;
		teamCurrentCheckingStat = 0;
		teamCurrentSavingStat = 0;

		for (IPlayer player : players) {
			teamCurrentSkatingStat += player.getPlayerSkating();
			teamCurrentShootingStat += player.getPlayerShooting();
			teamCurrentCheckingStat += player.getPlayerChecking();
			teamCurrentSavingStat += player.getPlayerSaving();
		}

		if (minSkatingStat == -1 || minShootingStat == -1 || minCheckingStat == -1 || minSavingStat == -1) {
			minSkatingStat = (int) ((1 + (Math.random() - 0.5) / 5) * teamCurrentSkatingStat);
			minShootingStat = (int) ((1 + (Math.random() - 0.5) / 5) * teamCurrentShootingStat);
			minCheckingStat = (int) ((1 + (Math.random() - 0.5) / 5) * teamCurrentCheckingStat);
			minSavingStat = (int) ((1 + (Math.random() - 0.5) / 5) * teamCurrentSavingStat);
		}
	}

	@Override
	public double getTradingGain(int differenceInSkatingStat, int differenceInShootingStat,
								 int differenceInCheckingStat, int differenceInSavingStat) {
		double teamGain = 0.0;
		teamGain += getTeamGainByStat(differenceInSkatingStat, teamCurrentSkatingStat, minSkatingStat);
		teamGain += getTeamGainByStat(differenceInShootingStat, teamCurrentShootingStat, minShootingStat);
		teamGain += getTeamGainByStat(differenceInCheckingStat, teamCurrentCheckingStat, minCheckingStat);
		teamGain += getTeamGainByStat(differenceInSavingStat, teamCurrentSavingStat, minSavingStat);
		return teamGain;
	}

	@Override
	public double getTeamGainByStat(int differenceInStat, int teamCurrentStat, int minStat) {
		if (teamCurrentStat > minStat) {
			if (teamCurrentStat + differenceInStat < minStat) {
				return differenceInStat * 1.0 / teamCurrentStat;
			}
		} else {
			if (teamCurrentStat + differenceInStat > minStat) {
				return differenceInStat * 1.0 / teamCurrentStat;
			}
		}
		return 0.0;
	}

	@Override
	public ArrayList<IPlayer> getFreeAgentsHiredAfterTrade(ArrayList<IPlayer> myPlayers, ILeague league) throws Exception {

		int noOfForwardPlayers = getActivePlayersCountWithPosition(myPlayers, FORWARD);
		int noOfDefensePlayers = getActivePlayersCountWithPosition(myPlayers, DEFENSE);
		int noOfGoaliePlayers = getActivePlayersCountWithPosition(myPlayers, GOALIE);

		ArrayList<IPlayer> hiredFreeAgents = new ArrayList<>();
		ArrayList<IPlayer> strongestForwardFreeAgents;
		ArrayList<IPlayer> strongestDefenseFreeAgents;
		ArrayList<IPlayer> strongestGoalieFreeAgents;

		if (noOfForwardPlayers > 0) {
			strongestForwardFreeAgents = league.getActiveStrongestFreeAgents(FORWARD);
			if (noOfForwardPlayers > strongestForwardFreeAgents.size()) {
				throw new Exception();
			}
			hiredFreeAgents.addAll(strongestForwardFreeAgents.subList(0, noOfForwardPlayers));
		}
		if (noOfDefensePlayers > 0) {
			strongestDefenseFreeAgents = league.getActiveStrongestFreeAgents(DEFENSE);
			if (noOfDefensePlayers > strongestDefenseFreeAgents.size()) {
				throw new Exception();
			}
			hiredFreeAgents.addAll(strongestDefenseFreeAgents.subList(0, noOfDefensePlayers));
		}
		if (noOfGoaliePlayers > 0) {
			strongestGoalieFreeAgents = league.getActiveStrongestFreeAgents(GOALIE);
			if (noOfGoaliePlayers > strongestGoalieFreeAgents.size()) {
				throw new Exception();
			}
			hiredFreeAgents.addAll(strongestGoalieFreeAgents.subList(0, noOfGoaliePlayers));
		}
		return hiredFreeAgents;
	}

	@Override
	public void completeRoster(ILeague league) {

		int forwardPlayersCount = getActivePlayersCountWithPosition(players, FORWARD);
		int defensePlayersCount = getActivePlayersCountWithPosition(players, DEFENSE);
		int goaliePlayersCount = getActivePlayersCountWithPosition(players, GOALIE);

		if (forwardPlayersCount > FORWARDS_COUNT) {
			dropWeakestPlayersToFreeAgentList(league, FORWARD, forwardPlayersCount - FORWARDS_COUNT);
		} else if (forwardPlayersCount < FORWARDS_COUNT) {
			hireStrongestPlayersFromFreeAgentList(league, FORWARD, FORWARDS_COUNT - forwardPlayersCount);
		}
		if (defensePlayersCount > DEFENSE_COUNT) {
			dropWeakestPlayersToFreeAgentList(league, DEFENSE, defensePlayersCount - DEFENSE_COUNT);
		} else if (defensePlayersCount < DEFENSE_COUNT) {
			hireStrongestPlayersFromFreeAgentList(league, DEFENSE, DEFENSE_COUNT - defensePlayersCount);
		}
		if (goaliePlayersCount > GOALIES_COUNT) {
			dropWeakestPlayersToFreeAgentList(league, GOALIE, goaliePlayersCount - GOALIES_COUNT);
		} else if (goaliePlayersCount < GOALIES_COUNT) {
			hireStrongestPlayersFromFreeAgentList(league, GOALIE, GOALIES_COUNT - goaliePlayersCount);
		}
	}

	@Override
	public void hireStrongestPlayersFromFreeAgentList(ILeague league, String position, int count) {
		ArrayList<IPlayer> strongestFreeAgents = league.getActiveStrongestFreeAgents(position);
		players.addAll(strongestFreeAgents.subList(0, count));
		league.getFreeAgents().removeAll(strongestFreeAgents.subList(0, count));
	}

	@Override
	public void dropWeakestPlayersToFreeAgentList(ILeague league, String position, int count) {
		ArrayList<IPlayer> weakestPlayers = getActiveWeakestPlayers(position);
		league.getFreeAgents().addAll(weakestPlayers.subList(0, count));
		players.removeAll(weakestPlayers.subList(0, count));
	}

	@Override
	public ArrayList<IPlayer> getActiveWeakestPlayers(String position) {
		ArrayList<IPlayer> playersWithPosition = new ArrayList<>();
		for (IPlayer player : players) {
			if (player.getPlayerPosition().equals(position)) {
				if (player.isPlayerRetired()){
					continue;
				}
				playersWithPosition.add(player);
			}
		}
		playersWithPosition.sort(Comparator.comparingDouble(IPlayer::getPlayerStrength));
		return playersWithPosition;
	}

	@Override
	public int getActivePlayersCountWithPosition(ArrayList<IPlayer> players, String position) {
		int playersCount = 0;
		for (IPlayer player : players) {
			if (player.getPlayerPosition().equals(position)) {
				if (player.isPlayerRetired()){
					continue;
				}
				playersCount++;
			}
		}
		return playersCount;
	}

	@Override
	public ArrayList<IPlayer> getStrongestPlayersByStrength(ArrayList<IPlayer> players) {
		ArrayList<IPlayer> strongestPlayers = new ArrayList<>(players);
		strongestPlayers.sort(Comparator.comparingDouble(IPlayer::getPlayerStrength).reversed());
		return strongestPlayers;
	}

	@Override
	public ArrayList<IPlayer> getActivePlayersWithPosition(ArrayList<IPlayer> players, String position) {

		ArrayList<IPlayer> activePlayersWithPosition = new ArrayList<>();

		for (IPlayer player: players){
			if (player.getPlayerPosition().equals(position)){
				if (player.isPlayerRetired()){
					continue;
				}
				activePlayersWithPosition.add(player);
			}
		}
		return activePlayersWithPosition;
	}
}