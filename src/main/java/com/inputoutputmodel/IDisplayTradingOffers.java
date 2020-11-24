package com.inputoutputmodel;

import com.datamodel.leaguedatamodel.IPlayer;
import com.datamodel.leaguedatamodel.ITeam;
<<<<<<< src/main/java/com/inputoutputmodel/IDisplayTradingOffers.java

import java.util.ArrayList;
=======
>>>>>>> src/main/java/com/inputoutputmodel/IDisplayTradingOffers.java

public interface IDisplayTradingOffers {
    void displayOfferToUser(ArrayList<IPlayer> offeredPlayers,ArrayList<IPlayer> consideredPlayers);

    boolean inputTradeAcceptRejectBooleanFromUser();

<<<<<<< src/main/java/com/inputoutputmodel/IDisplayTradingOffers.java
    void displayDraftOfferToUser(ITeam team,int teamPickRound,ArrayList<IPlayer> playersToTrade);
=======
	boolean inputTradeAcceptRejectBooleanFromUser();

    void displayDraftOfferToUser(ITeam team, int teamPickRound, ArrayList<IPlayer> playersToTrade);
>>>>>>> src/main/java/com/inputoutputmodel/IDisplayTradingOffers.java
}