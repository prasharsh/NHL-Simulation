package com.datamodel.leaguedatamodel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;


public class Team implements ITeam {

    final static Logger logger = Logger.getLogger(Team.class);
    private final String FORWARD = "forward";
    private final String DEFENSE = "defense";
    private final String GOALIE = "goalie";
    private final int ACTIVE_SKATERS_COUNT = 18;
    private final int ACTIVE_GOALIES_COUNT = 2;
    private final int FORWARDS_COUNT = 16;
    private final int DEFENSE_COUNT = 10;
    private final int GOALIES_COUNT = 4;
    private int teamId;
    private String teamName;
    private String teamCreatedBy;
    private int lossPointCount = 0;
    private IGeneralManager generalManager;
    private IHeadCoach headCoach;
    private List<IPlayer> players;
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
    public List<IPlayer> getPlayers() {
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
    public void setActiveRoster() {
        List<IPlayer> forwardPlayersList = getActivePlayersWithPosition(players, FORWARD);
        List<IPlayer> defensePlayersList = getActivePlayersWithPosition(players, DEFENSE);
        List<IPlayer> goaliePlayersList = getActivePlayersWithPosition(players, GOALIE);
        List<IPlayer> skaterPlayersList = new ArrayList<>(forwardPlayersList);
        skaterPlayersList.addAll(defensePlayersList);
        List<IPlayer> activeRosterList = new ArrayList<>();
        List<IPlayer> activeSkaterPlayers = getStrongestPlayersByStrength(skaterPlayersList);
        List<IPlayer> activeGoaliePlayers = getStrongestPlayersByStrength(goaliePlayersList);
        activeRosterList.addAll(activeSkaterPlayers.subList(0, ACTIVE_SKATERS_COUNT));
        activeRosterList.addAll(activeGoaliePlayers.subList(0, ACTIVE_GOALIES_COUNT));
        for (IPlayer activeRoster : activeRosterList) {
            activeRoster.setRosterStatus(true);
            logger.debug(activeRoster.getPlayerName() + " from " + teamName + " added to the active roster.");
        }
        List<IPlayer> inActiveRosterList = new ArrayList<>(players);
        inActiveRosterList.removeAll(activeRosterList);
        for (IPlayer inactiveRoster : inActiveRosterList) {
            inactiveRoster.setRosterStatus(false);
            logger.debug(inactiveRoster.getPlayerName() + " from " + teamName + " added to the inactive roster.");
        }
    }

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
                logger.debug("tradePlayers() called by team: " + teamName);
                trading.tradePlayers();
            } else {
                LeagueDataModelAbstractFactory factory = LeagueDataModelAbstractFactory.instance();
                IDrafting drafting = factory.createDrafting();
                logger.debug("trade drafts called by team: " + teamName);
                trading.tradeDraft(this, drafting);
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
            logger.debug("minimum skating stat for " + teamName + " set to " + minSkatingStat);
            logger.debug("minimum shooting stat for " + teamName + " set to " + minShootingStat);
            logger.debug("minimum checking stat for " + teamName + " set to " + minCheckingStat);
            logger.debug("minimum saving stat for " + teamName + " set to " + minSavingStat);
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
        if (teamCurrentStat == 0) {
            return 0.0;
        }
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
    public List<IPlayer> getFreeAgentsHiredAfterTrade(List<IPlayer> myPlayers, ILeague league) throws Exception {
        int noOfForwardPlayers = getActivePlayersCountWithPosition(myPlayers, FORWARD);
        int noOfDefensePlayers = getActivePlayersCountWithPosition(myPlayers, DEFENSE);
        int noOfGoaliePlayers = getActivePlayersCountWithPosition(myPlayers, GOALIE);

        List<IPlayer> hiredFreeAgents = new ArrayList<>();
        List<IPlayer> strongestForwardFreeAgents;
        List<IPlayer> strongestDefenseFreeAgents;
        List<IPlayer> strongestGoalieFreeAgents;

        if (noOfForwardPlayers > 0) {
            strongestForwardFreeAgents = league.getActiveStrongestFreeAgents(FORWARD);
            if (noOfForwardPlayers > strongestForwardFreeAgents.size()) {
                logger.info("Players cannot be traded due to insufficient forward free agents for team: " + teamName);
                throw new Exception();
            }
            hiredFreeAgents.addAll(strongestForwardFreeAgents.subList(0, noOfForwardPlayers));
        }
        if (noOfDefensePlayers > 0) {
            strongestDefenseFreeAgents = league.getActiveStrongestFreeAgents(DEFENSE);
            if (noOfDefensePlayers > strongestDefenseFreeAgents.size()) {
                logger.info("Players cannot be traded due to insufficient defense free agents for team: " + teamName);
                throw new Exception();
            }
            hiredFreeAgents.addAll(strongestDefenseFreeAgents.subList(0, noOfDefensePlayers));
        }
        if (noOfGoaliePlayers > 0) {
            strongestGoalieFreeAgents = league.getActiveStrongestFreeAgents(GOALIE);
            if (noOfGoaliePlayers > strongestGoalieFreeAgents.size()) {
                logger.info("Players cannot be traded due to insufficient goalie free agents for team: " + teamName);
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
            logger.info("dropping weakest forward players to free agents list for team: " + teamName);
            dropWeakestPlayersToFreeAgentList(league, FORWARD, forwardPlayersCount - FORWARDS_COUNT);
        } else if (forwardPlayersCount < FORWARDS_COUNT) {
            logger.info("hiring strongest forward players from free agents list for team: " + teamName);
            hireStrongestPlayersFromFreeAgentList(league, FORWARD, FORWARDS_COUNT - forwardPlayersCount);
        }
        if (defensePlayersCount > DEFENSE_COUNT) {
            logger.info("dropping weakest defense players to free agents list for team: " + teamName);
            dropWeakestPlayersToFreeAgentList(league, DEFENSE, defensePlayersCount - DEFENSE_COUNT);
        } else if (defensePlayersCount < DEFENSE_COUNT) {
            logger.info("hiring strongest defense players from free agents list for team: " + teamName);
            hireStrongestPlayersFromFreeAgentList(league, DEFENSE, DEFENSE_COUNT - defensePlayersCount);
        }
        if (goaliePlayersCount > GOALIES_COUNT) {
            logger.info("dropping weakest goalie players to free agents list for team: " + teamName);
            dropWeakestPlayersToFreeAgentList(league, GOALIE, goaliePlayersCount - GOALIES_COUNT);
        } else if (goaliePlayersCount < GOALIES_COUNT) {
            logger.info("hiring strongest goalie players from free agents list for team: " + teamName);
            hireStrongestPlayersFromFreeAgentList(league, GOALIE, GOALIES_COUNT - goaliePlayersCount);
        }
        logger.debug("setting players to active and inactive after trade for team: " + teamName);
        setActiveRoster();
    }

    @Override
    public void hireStrongestPlayersFromFreeAgentList(ILeague league, String position, int count) {
        List<IPlayer> strongestFreeAgents = league.getActiveStrongestFreeAgents(position);
        List<IPlayer> hiredStrongestFreeAgents = strongestFreeAgents.subList(0, count);
        players.addAll(hiredStrongestFreeAgents);
        hiredStrongestFreeAgents.forEach((freeAgent) -> logger.info(freeAgent.getPlayerName() + " from the free agents list added to the team: " + teamName));
        league.getFreeAgents().removeAll(strongestFreeAgents.subList(0, count));
        hiredStrongestFreeAgents.forEach((freeAgent) -> logger.info(freeAgent.getPlayerName() + " removed from the free agents list"));
    }

    @Override
    public void dropWeakestPlayersToFreeAgentList(ILeague league, String position, int count) {
        List<IPlayer> weakestPlayers = getActiveWeakestPlayers(position);
        List<IPlayer> droppedWeakestPlayers = weakestPlayers.subList(0, count);
        league.getFreeAgents().addAll(droppedWeakestPlayers);
        droppedWeakestPlayers.forEach((player) -> logger.info(player.getPlayerName() + " added to free agents list from team: " + teamName));
        players.removeAll(droppedWeakestPlayers);
        droppedWeakestPlayers.forEach((player) -> logger.info(player.getPlayerName() + " removed from team: " + teamName));
    }

    @Override
    public List<IPlayer> getActiveWeakestPlayers(String position) {
        List<IPlayer> playersWithPosition = new ArrayList<>();
        for (IPlayer player : players) {
            if (player.getPlayerPosition().equals(position)) {
                if (player.isPlayerRetired()) {
                    continue;
                }
                playersWithPosition.add(player);
            }
        }
        playersWithPosition.sort(Comparator.comparingDouble(IPlayer::getPlayerStrength));
        return playersWithPosition;
    }

    @Override
    public int getActivePlayersCountWithPosition(List<IPlayer> players, String position) {
        int playersCount = 0;
        for (IPlayer player : players) {
            if (player.getPlayerPosition().equals(position)) {
                if (player.isPlayerRetired()) {
                    continue;
                }
                playersCount++;
            }
        }
        return playersCount;
    }

    @Override
    public List<IPlayer> getStrongestPlayersByStrength(List<IPlayer> players) {
        List<IPlayer> strongestPlayers = new ArrayList<>(players);
        strongestPlayers.sort(Comparator.comparingDouble(IPlayer::getPlayerStrength).reversed());
        return strongestPlayers;
    }

    @Override
    public List<IPlayer> getActivePlayersWithPosition(List<IPlayer> players, String position) {
        List<IPlayer> activePlayersWithPosition = new ArrayList<>();
        for (IPlayer player : players) {
            if (player.getPlayerPosition().equals(position)) {
                if (player.isPlayerRetired()) {
                    continue;
                }
                activePlayersWithPosition.add(player);
            }
        }
        return activePlayersWithPosition;
    }

    @Override
    public List<IPlayer> getPlayingSix() {
        List<IPlayer> playingSix = new ArrayList<>();
        this.players.sort((player1, player2) -> {
            double playerStrength1 = player1.getPlayerStrength();
            double playerStrength2 = player2.getPlayerStrength();
            if (playerStrength1 > playerStrength2) {
                return -1;
            } else {
                return 0;
            }
        });
        int goalieCounter = 0;
        int forwardCounter = 0;
        int defenseCounter = 0;

        for (IPlayer player : this.players) {
            if (player.getRosterStatus() && player.getPlayerPosition().equals(GOALIE) && goalieCounter < 1) {
                playingSix.add(player);
                goalieCounter++;
            } else if (player.getRosterStatus() && player.isNotInPlayingSix() && player.getPlayerPosition().equals(DEFENSE) && defenseCounter < 2) {
                playingSix.add(player);
                player.setNotInPlayingSix(false);
                defenseCounter++;
            } else if (player.getRosterStatus() && player.isNotInPlayingSix() && player.getPlayerPosition().equals(FORWARD) && forwardCounter < 3) {
                playingSix.add(player);
                player.setNotInPlayingSix(false);
                forwardCounter++;
            }
        }

        if (playingSix.size() < 6) {
            this.resetTeamPlayingStatus();
            this.getPlayingSix();
        }

        return playingSix;
    }

    @Override
    public void resetTeamPlayingStatus() {
        for (IPlayer player : this.players) {
            player.setNotInPlayingSix(true);
        }
    }

}