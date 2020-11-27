package com.datamodel.leaguedatamodel;
import com.inputoutputmodel.IDisplayTradingOffers;

import java.util.ArrayList;

public interface ITrading {

	boolean generateDraftPickOfferToUser(ITeam team, int teamPickRound, ArrayList<IPlayer> playersToTrade, IDisplayTradingOffers displayTradingOffers);

	boolean generateDraftPickOfferToAi();

	void setOfferingTeam(ITeam offeringTeam);

	ITeam getOfferingTeam();

	void setAcceptingTeam(ITeam acceptingTeam);

	ITeam getAcceptingTeam();

	ArrayList<IPlayer> getOfferedPlayers();

	void setOfferedPlayers(ArrayList<IPlayer> offeredPlayers);

	void setRequestedPlayers(ArrayList<IPlayer> requestedPlayers);

	ArrayList<IPlayer> getRequestedPlayers();

	ArrayList<ArrayList<Integer>> getPossibleTradeCombinations();

	ArrayList<ArrayList<Integer>> setPossibleTradeCombinations(int totalNoOfPlayers, int maxPlayersAllowedPerTrade,
															   ArrayList<ArrayList<Integer>> allTradingCombinations);

	boolean isTradePossible(ITeam team);

	void generateBestTradeOffer(ITeam team);

	boolean generateAiTradeOfferToUser(ArrayList<IPlayer> aiTeamPlayers, ArrayList<IPlayer> userPlayers, IDisplayTradingOffers displayTradingOffers);

	boolean generateAiTradeOfferToAi(ITeam team);

	boolean isInterestedInPlayersTrade();

	void tradePlayers();

	void tradeDraft(ITeam team, IDrafting drafting);
}