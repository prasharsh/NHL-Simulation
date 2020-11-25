package com.inputoutputmodeltest;
import com.datamodel.leaguedatamodel.IPlayer;
import com.datamodel.leaguedatamodel.ITeam;
import com.inputoutputmodel.IDisplayTradingOffers;

import java.util.ArrayList;

public class DisplayTradingOffersMock implements IDisplayTradingOffers {

    @Override
    public void displayOfferToUser(ArrayList<IPlayer> offeredPlayers, ArrayList<IPlayer> consideredPlayers) {

    }

    @Override
    public boolean inputTradeAcceptRejectBooleanFromUser() {
        return false;
    }

    @Override
    public void displayDraftOfferToUser(ITeam team, int teamPickRound, ArrayList<IPlayer> playersToTrade) {

    }
}
