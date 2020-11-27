package com.inputoutputmodel;
import java.util.List;

import com.datamodel.leaguedatamodel.IPlayer;
import com.datamodel.leaguedatamodel.ITeam;

public interface IDisplayTradingOffers {
	void displayOfferToUser(List<IPlayer> offeredPlayers, List<IPlayer> consideredPlayers);

	boolean inputTradeAcceptRejectBooleanFromUser();

    void displayDraftOfferToUser(ITeam team, int teamPickRound, List<IPlayer> playersToTrade);
}