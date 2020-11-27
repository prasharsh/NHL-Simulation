package com.datamodeltest.leaguedatamodeltest;
import com.datamodel.leaguedatamodel.IDrafting;
import com.datamodel.leaguedatamodel.IPlayer;
import com.datamodel.leaguedatamodel.ITeam;
import com.datamodel.leaguedatamodel.ITrading;
import com.inputoutputmodel.IDisplayTradingOffers;
import java.util.ArrayList;

public class TradingMock implements ITrading {

    private ITeam team;

    @Override
    public boolean generateDraftPickOfferToUser(ITeam team, int teamPickRound, ArrayList<IPlayer> playersToTrade, IDisplayTradingOffers displayTradingOffers) {
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
    public ArrayList<IPlayer> getOfferedPlayers() {
        return null;
    }

    @Override
    public void setOfferedPlayers(ArrayList<IPlayer> offeredPlayers) {

    }

    @Override
    public void setRequestedPlayers(ArrayList<IPlayer> requestedPlayers) {

    }

    @Override
    public ArrayList<IPlayer> getRequestedPlayers() {
        return null;
    }

    @Override
    public ArrayList<ArrayList<Integer>> getPossibleTradeCombinations() {
        return null;
    }

    @Override
    public ArrayList<ArrayList<Integer>> setPossibleTradeCombinations(int totalNoOfPlayers, int maxPlayersAllowedPerTrade, ArrayList<ArrayList<Integer>> allTradingCombinations) {
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
    public boolean generateAiTradeOfferToUser(ArrayList<IPlayer> aiTeamPlayers, ArrayList<IPlayer> userPlayers, IDisplayTradingOffers displayTradingOffers) {
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
