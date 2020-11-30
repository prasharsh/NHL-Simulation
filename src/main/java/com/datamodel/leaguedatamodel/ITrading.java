package com.datamodel.leaguedatamodel;
import java.util.List;

import com.inputoutputmodel.IDisplayTradingOffers;

public interface ITrading {

	boolean generateDraftPickOfferToUser(ITeam team, int teamPickRound, List<IPlayer> playersToTrade, IDisplayTradingOffers displayTradingOffers);

	boolean generateDraftPickOfferToAi();

	void setOfferingTeam(ITeam offeringTeam);

	ITeam getOfferingTeam();

	void setAcceptingTeam(ITeam acceptingTeam);

	ITeam getAcceptingTeam();

	List<IPlayer> getOfferedPlayers();

	void setOfferedPlayers(List<IPlayer> offeredPlayers);

	void setRequestedPlayers(List<IPlayer> requestedPlayers);

	List<IPlayer> getRequestedPlayers();

	List<List<Integer>> setPossibleTradeCombinations(int totalNoOfPlayers, int maxPlayersAllowedPerTrade,
															   List<List<Integer>> allTradingCombinations);

	boolean isTradePossible(ITeam team);

	void generateBestTradeOffer(ITeam team);

	boolean generateAiTradeOfferToUser(List<IPlayer> aiTeamPlayers, List<IPlayer> userPlayers, IDisplayTradingOffers displayTradingOffers);

	boolean generateAiTradeOfferToAi(ITeam team);

	boolean isInterestedInPlayersTrade();

	void tradePlayers();

	void tradeDraft(ITeam team, IDrafting drafting);
}