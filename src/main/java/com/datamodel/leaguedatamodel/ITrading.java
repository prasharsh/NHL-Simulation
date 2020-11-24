package com.datamodel.leaguedatamodel;
import java.util.ArrayList;

import com.datamodel.gameplayconfig.ITradingConfig;

public interface ITrading {

	boolean generateDraftPickOfferToUser(ITeam team, int teamPickRound, ArrayList<IPlayer> playersToTrade);

	boolean generateDraftPickOfferToAi();

	void startTrading(ITradingConfig trading, ILeague league, ArrayList<ITeam> teams);

	void acceptTradeOffer(ITeam offeringTeam, ArrayList<IPlayer> offeringTeamPlayers, ITeam opponentTeam,
			ArrayList<IPlayer> opponentTeamPlayers);

	double calculateTotalStrengthOfPlayers(ArrayList<IPlayer> players);

	ArrayList<IPlayer> getPlayersWithPosition(ArrayList<IPlayer> players, String position);

	ArrayList<IPlayer> sortPlayersOnStrength(ArrayList<IPlayer> playersToBeSorted, int playersCount,
			final boolean ascending);

	void dropWeakestPlayersToFreeAgentList(ILeague league, ITeam team, String playerPosition, int count);

	void hireStrongestPlayersFromFreeAgentList(ILeague league, ITeam team, String freeAgentPosition, int count);

	ArrayList<IPlayer> sortFreeAgentsOnStrength(ArrayList<IPlayer> freeAgentsToBeSorted, int freeAgentsCount,
			final boolean ascending);

	ArrayList<IPlayer> getFreeAgentsWithPosition(ArrayList<IPlayer> freeAgents, String position);

	//	*****************************************************************************************************************

	ITeam getOfferingTeam();

	ITeam getAcceptingTeam();

	ArrayList<IPlayer> getOfferedPlayers();

	ArrayList<IPlayer> getRequestedPlayers();

	ArrayList<ArrayList<Integer>> getPossibleTradeCombinations();

	ArrayList<ArrayList<Integer>> setPossibleTradeCombinations(int totalNoOfPlayers, int maxPlayersAllowedPerTrade,
															   ArrayList<ArrayList<Integer>> allTradingCombinations);

	boolean isTradePossible(ITeam team);

	void generateBestTradeOffer(ITeam team);

	boolean generateAiTradeOfferToUser(ArrayList<IPlayer> aiTeamPlayers, ArrayList<IPlayer> userPlayers);

	boolean generateAiTradeOfferToAi(ITeam team);

	boolean isInterestedInPlayersTrade();

	void tradePlayers();

	void tradeDraft(ITeam team);
}