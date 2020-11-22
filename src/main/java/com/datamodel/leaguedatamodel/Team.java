package com.datamodel.leaguedatamodel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static com.datamodel.leaguedatamodel.Constants.*;


public class Team implements ITeam {

    private int teamId;
    private String teamName;
    private String teamCreatedBy;
    private int lossPointCount = 0;
    private IGeneralManager generalManager;
    private IHeadCoach headCoach;
    private final ArrayList<IPlayer> players;
    private ITeam[] teamPicks;
    private int minSkatingStat;
    private int minShootingStat;
    private int minCheckingStat;
    private int minSavingStat;
    private final double gainValue = 0.25;
    private final double minStatStrengthFactor = 0.8;

    public Team() {
        this.players = new ArrayList<>();
        this.teamPicks = new ITeam[6];
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
    public ITeam[] getTeamPick() {
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


    //	*****************************************************************************************************************

    private void setTeamStats() {
        Random random = new Random();
        minSkatingStat = (random.nextInt(MAXIMUM_STAT - MINIMUM_STAT) + MINIMUM_STAT) * PLAYERS_COUNT;
        minShootingStat = (random.nextInt(MAXIMUM_STAT - MINIMUM_STAT) + MINIMUM_STAT) * PLAYERS_COUNT;
        minCheckingStat = (random.nextInt(MAXIMUM_STAT - MINIMUM_STAT) + MINIMUM_STAT) * PLAYERS_COUNT;
        minSavingStat = (random.nextInt(MAXIMUM_STAT - MINIMUM_STAT) + MINIMUM_STAT) * PLAYERS_COUNT;
    }

    @Override
    public void proposeTrade(ITrading trading) {
        boolean isTradePossible = trading.isTradePossible(this);
        if (isTradePossible) {
            trading.generateTradeOffer(this);
        }
    }

    @Override
    public double getTradingGain(ArrayList<IPlayer> myPlayers, ArrayList<IPlayer> theirPlayers) {

        double teamGain = 0.0;

        int differenceInSkatingStat = 0;
        int differenceInShootingStat = 0;
        int differenceInCheckingStat = 0;
        int differenceInSavingStat = 0;

        int teamCurrentSkatingStat = 0;
        int teamCurrentShootingStat = 0;
        int teamCurrentCheckingStat = 0;
        int teamCurrentSavingStat = 0;

        for (IPlayer player : theirPlayers) {
            differenceInSkatingStat += player.getPlayerSkating();
            differenceInShootingStat += player.getPlayerShooting();
            differenceInCheckingStat += player.getPlayerChecking();
            differenceInSavingStat += player.getPlayerSaving();
        }

        for (IPlayer player : myPlayers) {
            differenceInSkatingStat -= player.getPlayerSkating();
            differenceInShootingStat -= player.getPlayerShooting();
            differenceInCheckingStat -= player.getPlayerChecking();
            differenceInSavingStat -= player.getPlayerSaving();
        }

        for (IPlayer player : players) {
            teamCurrentSkatingStat += player.getPlayerSkating();
            teamCurrentShootingStat += player.getPlayerShooting();
            teamCurrentCheckingStat += player.getPlayerChecking();
            teamCurrentSavingStat += player.getPlayerSaving();
        }

        teamGain += getTeamGain(differenceInSkatingStat, teamCurrentSkatingStat, minSkatingStat);
        teamGain += getTeamGain(differenceInShootingStat, teamCurrentShootingStat, minShootingStat);
        teamGain += getTeamGain(differenceInCheckingStat, teamCurrentCheckingStat, minCheckingStat);
        teamGain += getTeamGain(differenceInSavingStat, teamCurrentSavingStat, minSavingStat);
        return teamGain;
    }

    private double getTeamGain(int differenceInStat, int teamCurrentStat, int minStat) {
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

    //	@Override
//	public ArrayList<IPlayer> getActiveWeakestPlayers(int playersCount) {
//		ArrayList<IPlayer> players = new ArrayList<>();
//		for (IPlayer player:this.players){
//			if (player.isPlayerRetired()){
//				continue;
//			}
//			players.add(player);
//		}
//		players.sort(Comparator.comparingDouble(IPlayer::getPlayerStrength));
//		return new ArrayList<>(players.subList(0, playersCount));
//	}

//	@Override
//	public int getSkatingStat() {
//		int skatingStat = 0;
//		for (IPlayer player: players){
//			skatingStat += player.getPlayerSkating();
//		}
//		return skatingStat;
//	}
//
//	@Override
//	public int getShootingStat() {
//		int shootingStat = 0;
//		for (IPlayer player: players){
//			shootingStat += player.getPlayerShooting();
//		}
//		return shootingStat;
//	}
//
//	@Override
//	public int getCheckingStat() {
//		int checkingStat = 0;
//		for (IPlayer player: players){
//			checkingStat += player.getPlayerChecking();
//		}
//		return checkingStat;
//	}
//
//	@Override
//	public int getSavingStat() {
//		int savingStat = 0;
//		for (IPlayer player: players){
//			savingStat += player.getPlayerSaving();
//		}
//		return savingStat;
//	}
}