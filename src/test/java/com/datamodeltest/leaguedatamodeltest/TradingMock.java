package com.datamodeltest.leaguedatamodeltest;
import com.datamodel.leaguedatamodel.IDrafting;
import com.datamodel.leaguedatamodel.IPlayer;
import com.datamodel.leaguedatamodel.ITeam;
import com.datamodel.leaguedatamodel.ITrading;
import com.inputoutputmodel.IDisplayTradingOffers;
import java.util.ArrayList;
import java.util.List;

public class TradingMock implements ITrading {

    private ITeam team;

    @Override
    public boolean generateDraftPickOfferToUser(ITeam team, int teamPickRound, List<IPlayer> playersToTrade, IDisplayTradingOffers displayTradingOffers) {
        return false;
    }

    @Override
    public boolean generateDraftPickOfferToAi() {
        return false;
    }

    @Override
    public void setOfferingTeam(ITeam offeringTeam) {
        team = offeringTeam;
    }

    @Override
    public ITeam getOfferingTeam() {
        return team;
    }

    @Override
    public void setAcceptingTeam(ITeam acceptingTeam) {

    }

    @Override
    public ITeam getAcceptingTeam() {
        return null;
    }

    @Override
    public List<IPlayer> getOfferedPlayers() {
        return null;
    }

    @Override
    public void setOfferedPlayers(List<IPlayer> offeredPlayers) {

    }

    @Override
    public void setRequestedPlayers(List<IPlayer> requestedPlayers) {

    }

    @Override
    public List<IPlayer> getRequestedPlayers() {
        return null;
    }

    @Override
    public List<List<Integer>> setPossibleTradeCombinations(int totalNoOfPlayers, int maxPlayersAllowedPerTrade, List<List<Integer>> allTradingCombinations) {
        return null;
    }

    @Override
    public boolean isTradePossible(ITeam team) {
        return true;
    }

    @Override
    public void generateBestTradeOffer(ITeam team) {

    }

    @Override
    public boolean generateAiTradeOfferToUser(List<IPlayer> aiTeamPlayers, List<IPlayer> userPlayers, IDisplayTradingOffers displayTradingOffers) {
        return false;
    }

    @Override
    public boolean generateAiTradeOfferToAi(ITeam team) {
        return false;
    }

    @Override
    public boolean isInterestedInPlayersTrade() {
        return false;
    }

    @Override
    public void tradePlayers() {

    }

    @Override
    public void tradeDraft(ITeam team, IDrafting drafting) {
        setOfferingTeam(team);
    }
}
