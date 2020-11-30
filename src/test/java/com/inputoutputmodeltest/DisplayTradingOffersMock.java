package com.inputoutputmodeltest;
import java.util.List;

import com.datamodel.leaguedatamodel.IPlayer;
import com.datamodel.leaguedatamodel.ITeam;
import com.inputoutputmodel.IDisplayTradingOffers;

public class DisplayTradingOffersMock implements IDisplayTradingOffers {

    @Override
    public void displayOfferToUser(List<IPlayer> offeredPlayers, List<IPlayer> consideredPlayers) {
        return;
    }

    @Override
    public boolean inputTradeAcceptRejectBooleanFromUser() {
        return false;
    }

    @Override
    public void displayDraftOfferToUser(ITeam team, int teamPickRound, List<IPlayer> playersToTrade) {
        return;
    }
}
