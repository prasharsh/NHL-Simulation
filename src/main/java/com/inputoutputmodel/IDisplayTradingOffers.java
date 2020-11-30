package com.inputoutputmodel;

import com.datamodel.leaguedatamodel.IPlayer;
import com.datamodel.leaguedatamodel.ITeam;

import java.util.List;

public interface IDisplayTradingOffers {
	void displayOfferToUser(List<IPlayer> offeredPlayers, List<IPlayer> consideredPlayers);

	boolean inputTradeAcceptRejectBooleanFromUser();

	void displayDraftOfferToUser(ITeam team, int teamPickRound, List<IPlayer> playersToTrade);
}