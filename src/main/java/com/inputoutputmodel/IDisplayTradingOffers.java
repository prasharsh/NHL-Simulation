package com.inputoutputmodel;
import java.util.ArrayList;
import com.datamodel.leaguedatamodel.IPlayer;

public interface IDisplayTradingOffers {
	void displayOfferToUser(ArrayList<IPlayer> offeredPlayers, ArrayList<IPlayer> consideredPlayers);

	boolean inputTradeAcceptRejectBooleanFromUser();
}