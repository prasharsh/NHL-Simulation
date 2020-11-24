package com.inputoutputmodel;

import com.datamodel.leaguedatamodel.IPlayer;
import com.datamodel.leaguedatamodel.ITeam;

import java.util.ArrayList;

public interface IDisplayTradingOffers {
    void displayOfferToUser(ArrayList<IPlayer> offeredPlayers,ArrayList<IPlayer> consideredPlayers);

    boolean inputTradeAcceptRejectBooleanFromUser();

    void displayDraftOfferToUser(ITeam team,int teamPickRound,ArrayList<IPlayer> playersToTrade);
}