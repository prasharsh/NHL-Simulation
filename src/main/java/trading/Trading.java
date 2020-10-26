package trading;

import g4dhl.IPlayer;
import g4dhl.ITeam;
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

    private static  final String IMPORT = "import";
    private static  final String USER = "user";


    private boolean checkIfTeamsAreEmptyOrNull(ArrayList<ITeam> teams){
        return teams == null || teams.size() == 0;
    }

    private boolean checkIfPlayersAreEmptyOrNull(ArrayList<IPlayer> players){
        return players == null || players.size() == 0;
    }

    public void startTrading(ArrayList<ITeam> teams){
        if (checkIfTeamsAreEmptyOrNull(teams)){
            return;
        }
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

    }

    public void adjustUserTeamRoaster(ITeam UserTeam){

    }

}
