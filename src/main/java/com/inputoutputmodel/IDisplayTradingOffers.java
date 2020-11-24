package com.inputoutputmodel;
import java.util.ArrayList;
import com.datamodel.leaguedatamodel.IPlayer;
import com.datamodel.leaguedatamodel.ITeam;

public interface IDisplayTradingOffers {
	void displayOfferToUser(ArrayList<IPlayer> offeredPlayers, ArrayList<IPlayer> consideredPlayers);

	boolean inputTradeAcceptRejectBooleanFromUser();

    void displayDraftOfferToUser(ITeam team, int teamPickRound, ArrayList<IPlayer> playersToTrade);
}