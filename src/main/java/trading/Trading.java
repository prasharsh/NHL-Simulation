package trading;

import g4dhl.*;
import UserInputOutput.DisplayTradingOffers;
import UserInputOutput.IDisplayTradingOffers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Trading {

    private int lossPoint = 8;
    private double randomTradeOfferChance = 0.05;
    private int maxPlayersPerTrade = 2;
    private double randomAcceptanceChance = 0.05;
    private ArrayList<ITeam> teams;
    private ArrayList<IFreeAgent> freeAgents;
    private ILeague league;

    private static  final String IMPORT = "import";
    private static  final String USER = "user";
    private static  final String SKATERS = "skaters";
    private static  final String GOALIES = "goalies";
    private static  final int SKATERSCOUNT = 18;
    private static  final int GOALIESCOUNT = 2;

    // TODO: REMOVE THIS LATER
    public Trading(){
        this.freeAgents = new ArrayList<>();
        league = new League();
    }


    private boolean checkIfTeamsAreEmptyOrNull(ArrayList<ITeam> teams){
        return teams == null || teams.size() == 0;
    }

    private boolean checkIfPlayersAreEmptyOrNull(ArrayList<IPlayer> players){
        return players == null || players.size() == 0;
    }

    private boolean checkIfFreeAgentsAreEmptyOrNull(ArrayList<IFreeAgent> freeAgents){
        return freeAgents == null || freeAgents.size() == 0;
    }

    public void startTrading(ArrayList<ITeam> teams){
        if (checkIfTeamsAreEmptyOrNull(teams)){
            return;
        }
        // TODO: UPDATE THIS LATER
        this.teams = new ArrayList<>(teams);
        checkForAiTradeOffers();
    }

    private void checkForAiTradeOffers(){
        for (ITeam team: teams){
            if (team.getTeamCreatedBy().equals(IMPORT) && team.getLossPointCount() >= lossPoint){
                if(Math.random() < randomTradeOfferChance){
                    generateAiTradeOffer(team, teams);
                }
            }
        }
    }

    private void generateAiTradeOffer(ITeam aiTeam, ArrayList<ITeam> teams) {
        ArrayList<IPlayer> aiTeamWeakestPlayers = sortPlayersOnStrength(aiTeam.getPlayers(), maxPlayersPerTrade, true);
        IPlayer aiWeakestPlayer = aiTeamWeakestPlayers.get(0);

        for (int i=1; i< aiTeamWeakestPlayers.size(); i++){
            if (aiWeakestPlayer.getPlayerPosition().equals(aiTeamWeakestPlayers.get(i).getPlayerPosition())){
                continue;
            }
            aiTeamWeakestPlayers.removeAll(aiTeamWeakestPlayers.subList(i, aiTeamWeakestPlayers.size()));
            break;
        }

        ITeam StrongestTeam = null;
        ArrayList<IPlayer> StrongestTeamStrongestPlayers = null;
        double StrongestTeamStrongestPlayersStrength = 0.0;

        for (ITeam opponentTeam: teams){
            if (aiTeam.equals(opponentTeam)){
                continue;
            }

            ArrayList<IPlayer> opponentTeamPlayersWithPosition = getPlayersWithPosition(opponentTeam.getPlayers(), aiWeakestPlayer.getPlayerPosition());
            ArrayList<IPlayer> opponentTeamStrongestPlayers = sortPlayersOnStrength(opponentTeamPlayersWithPosition, aiTeamWeakestPlayers.size(), false);

            double opponentTeamStrongestPlayersStrength= calculateTotalStrengthOfPlayers(opponentTeamStrongestPlayers);

            if (opponentTeamStrongestPlayersStrength > StrongestTeamStrongestPlayersStrength){
                StrongestTeam = opponentTeam;
                StrongestTeamStrongestPlayers = opponentTeamStrongestPlayers;
                StrongestTeamStrongestPlayersStrength = opponentTeamStrongestPlayersStrength;
            }
        }

        if (StrongestTeam == null){
            return;
        }

        if (StrongestTeam.getTeamCreatedBy().equals(USER)){
            generateAiTradeOfferToUser(aiTeam, aiTeamWeakestPlayers, StrongestTeam, StrongestTeamStrongestPlayers);
        }
        else if (StrongestTeam.getTeamCreatedBy().equals(IMPORT)){
            generateAiTradeOfferToAi(aiTeam, aiTeamWeakestPlayers, StrongestTeam, StrongestTeamStrongestPlayers);
        }
        aiTeam.setLossPointCount(0);
    }

    private void generateAiTradeOfferToUser(ITeam aiTeam, ArrayList<IPlayer> aiTeamWeakestPlayers, ITeam opponentTeam, ArrayList<IPlayer> opponentTeamStrongestPlayers) {
        IDisplayTradingOffers offer = new DisplayTradingOffers();
        offer.displayOfferToUser(aiTeamWeakestPlayers, opponentTeamStrongestPlayers);
        boolean tradeAccepted = offer.inputTradeAcceptRejectBooleanFromUser();
        if (tradeAccepted){
            acceptTradeOffer(aiTeam, aiTeamWeakestPlayers, opponentTeam, opponentTeamStrongestPlayers);
            adjustAiTeamRoaster(aiTeam);
            adjustUserTeamRoaster(opponentTeam);
        }
    }

    private void generateAiTradeOfferToAi(ITeam offeringTeam, ArrayList<IPlayer> offeringTeamPlayers, ITeam opponentTeam, ArrayList<IPlayer> opponentTeamPlayers) {
        double offeringTeamPlayersStrength = calculateTotalStrengthOfPlayers(offeringTeamPlayers);
        double opponentTeamPlayersStrength = calculateTotalStrengthOfPlayers(opponentTeamPlayers);

        if (offeringTeamPlayersStrength > opponentTeamPlayersStrength){
            acceptTradeOffer(offeringTeam, offeringTeamPlayers, opponentTeam, opponentTeamPlayers);
            adjustAiTeamRoaster(offeringTeam);
            adjustAiTeamRoaster(opponentTeam);
        }
        else {
            if (Math.random() < randomAcceptanceChance) {
                acceptTradeOffer(offeringTeam, offeringTeamPlayers, opponentTeam, opponentTeamPlayers);
                adjustAiTeamRoaster(offeringTeam);
                adjustAiTeamRoaster(opponentTeam);
            }
        }
    }

    public void acceptTradeOffer(ITeam offeringTeam, ArrayList<IPlayer> offeringTeamPlayers, ITeam opponentTeam, ArrayList<IPlayer> opponentTeamPlayers) {
        for (IPlayer offeringTeamPlayer: offeringTeamPlayers){
            opponentTeam.addPlayer(offeringTeamPlayer);
        }

        for (IPlayer opponentTeamPlayer: opponentTeamPlayers){
            offeringTeam.addPlayer(opponentTeamPlayer);
        }

        for (IPlayer offeringTeamPlayer: offeringTeamPlayers){
            offeringTeam.removePlayer(offeringTeamPlayer);
        }

        for (IPlayer opponentTeamPlayer: opponentTeamPlayers){
            opponentTeam.removePlayer(opponentTeamPlayer);
        }
    }

    public double calculateTotalStrengthOfPlayers(ArrayList<IPlayer> players) {
        double strength = 0.0;
        if (checkIfPlayersAreEmptyOrNull(players)){
            return strength;
        }
        for (IPlayer player: players){
            strength+= player.getPlayerStrength();
        }
        return strength;
    }

    public ArrayList<IPlayer> getPlayersWithPosition(ArrayList<IPlayer> playersWithPosition, String position){
        if (checkIfPlayersAreEmptyOrNull(playersWithPosition) || position == null){
            return null;
        }
        ArrayList<IPlayer> players = new ArrayList<>();
        for (IPlayer player: playersWithPosition){
            if (player.getPlayerPosition().equals(position)){
                players.add(player);
            }
        }
        return players;
    }

    public ArrayList<IPlayer> sortPlayersOnStrength(ArrayList<IPlayer> playersToBeSorted, int playersCount, final boolean ascending) {
        if (checkIfPlayersAreEmptyOrNull(playersToBeSorted)){
            return null;
        }
        if (playersCount <= 0){
            return null;
        }
        ArrayList<IPlayer> players = new ArrayList<>(playersToBeSorted);
        Collections.sort(players, new Comparator<IPlayer>() {

            @Override
            public int compare(IPlayer player1, IPlayer player2) {
                if (ascending) {
                    return Double.compare(player1.getPlayerStrength(), player2.getPlayerStrength());
                }
                return Double.compare(player2.getPlayerStrength(), player1.getPlayerStrength());
            }
        });
        if (playersCount >= playersToBeSorted.size()){
            return players;
        }
        return (ArrayList<IPlayer>) players.subList(0, playersCount);
    }

    public void adjustAiTeamRoaster(ITeam aiTeam){
        int skatersCount = aiTeam.getSkatersCount();
        int goaliesCount = aiTeam.getGoaliesCount();

        if(skatersCount > SKATERSCOUNT){
            dropPlayersToFreeAgentList(aiTeam, SKATERS, skatersCount - SKATERSCOUNT);
        }
        else if(skatersCount < SKATERSCOUNT){
            hirePlayersFromFreeAgentList(aiTeam, SKATERS, SKATERSCOUNT - skatersCount);
        }

        if(goaliesCount > GOALIESCOUNT){
            dropPlayersToFreeAgentList(aiTeam, GOALIES, goaliesCount - GOALIESCOUNT);
        }
        else if(goaliesCount < GOALIESCOUNT){
            hirePlayersFromFreeAgentList(aiTeam, GOALIES, GOALIESCOUNT - goaliesCount);
        }
    }

    private void dropPlayersToFreeAgentList(ITeam team, String playerPosition, int count) {
        ArrayList<IPlayer> players = getPlayersWithPosition(team.getPlayers(), playerPosition);
        ArrayList<IPlayer> weakestPlayers = sortPlayersOnStrength(players, count, true);
        for (IPlayer player: weakestPlayers){
            PlayerToFreeAgent playerToFreeAgent = new PlayerToFreeAgent(player);
            // TODO: CHANGE THIS TO REAL FREE AGENTS
            league.addFreeAgent(playerToFreeAgent.getFreeAgent());
        }
        for (IPlayer player: weakestPlayers){
            team.removePlayer(player);
        }
    }

    private void hirePlayersFromFreeAgentList(ITeam team, String freeAgentPosition, int count) {
        // TODO: CHANGE THIS.FREEAGENTS
        ArrayList<IFreeAgent> freeAgents = getFreeAgentsWithPosition(league.getFreeAgents(), freeAgentPosition);
        ArrayList<IFreeAgent> strongestFreeAgents = sortFreeAgentsOnStrength(freeAgents, count, false);
        for (IFreeAgent freeAgent: strongestFreeAgents){
            FreeAgentToPlayer freeAgentToPlayer = new FreeAgentToPlayer(freeAgent);
            team.addPlayer(freeAgentToPlayer.getPlayer());
        }
        for (IFreeAgent freeAgent: strongestFreeAgents){
            league.removeFreeAgent(freeAgent);
        }

    }

    public ArrayList<IFreeAgent> sortFreeAgentsOnStrength(ArrayList<IFreeAgent> freeAgentsToBeSorted, int freeAgentsCount, final boolean ascending) {
        if (checkIfFreeAgentsAreEmptyOrNull(freeAgentsToBeSorted)){
            return null;
        }
        if (freeAgentsCount <= 0){
            return null;
        }
        ArrayList<IFreeAgent> freeAgents = new ArrayList<>(freeAgentsToBeSorted);
        Collections.sort(freeAgents, new Comparator<IFreeAgent>() {

            @Override
            public int compare(IFreeAgent freeAgent1, IFreeAgent freeAgent2) {
                if (ascending) {
                    return Double.compare(freeAgent1.getFreeAgentStrength(), freeAgent2.getFreeAgentStrength());
                }
                return Double.compare(freeAgent2.getFreeAgentStrength(), freeAgent1.getFreeAgentStrength());
            }
        });
        if (freeAgentsCount >= freeAgentsToBeSorted.size()){
            return freeAgents;
        }
        return (ArrayList<IFreeAgent>) freeAgents.subList(0, freeAgentsCount);
    }

    public ArrayList<IFreeAgent> getFreeAgentsWithPosition(ArrayList<IFreeAgent> freeAgentsWithPosition, String position) {
        if (checkIfFreeAgentsAreEmptyOrNull(freeAgentsWithPosition) || position == null){
            return null;
        }
        ArrayList<IFreeAgent> freeAgents = new ArrayList<>();
        for (IFreeAgent freeAgent: freeAgentsWithPosition){
            if (freeAgent.getFreeAgentPosition().equals(position)){
                freeAgents.add(freeAgent);
            }
        }
        return freeAgents;
    }

    public void adjustUserTeamRoaster(ITeam UserTeam){

    }

}
