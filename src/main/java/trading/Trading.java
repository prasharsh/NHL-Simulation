package trading;

import g4dhl.IPlayer;
import g4dhl.ITeam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Trading {

    private int lossPoint = 8;
    private double randomTradeOfferChance = 0.05;
    private int maxPlayersPerTrade = 2;
    private double randomAcceptanceChance = 0.05;
    private ArrayList<ITeam> teams;

    public void startTrading(ArrayList<ITeam> teams){
        if (teams == null) return;
        this.teams = new ArrayList<>(teams);
        checkForAiTradeOffers();
    }

    private void checkForAiTradeOffers(){

        for (ITeam team: teams){
            if (team.getTeamCreatedBy().equals("import") && team.getLossPointCount() >= lossPoint){
                if(Math.random() < randomTradeOfferChance){
                    generateAiTradeOffer(team, teams);
                }
            }
        }
    }

    private void generateAiTradeOffer(ITeam aiTeam, ArrayList<ITeam> teams) {

        ArrayList<IPlayer> aiTeamWeakestPlayers = sortPlayersOnStrength(aiTeam.getPlayers(), maxPlayersPerTrade, true);

        IPlayer aiWeakestPlayer = aiTeamWeakestPlayers.get(0);

        for ( int i=1; i< aiTeamWeakestPlayers.size(); i++){

            if (aiWeakestPlayer.getPlayerPosition().equals(aiTeamWeakestPlayers.get(i).getPlayerPosition())){
                continue;
            }
            aiTeamWeakestPlayers.removeAll(aiTeamWeakestPlayers.subList(i, aiTeamWeakestPlayers.size()));
            break;
        }

        for (ITeam opponentTeam: teams){

            if (aiTeam.equals(opponentTeam)){
                continue;
            }

            boolean isTradeAccepted = false;

            ArrayList<IPlayer> opponentTeamPlayersWithPosition = getPlayersWithPosition(opponentTeam.getPlayers(), aiWeakestPlayer.getPlayerPosition());
            ArrayList<IPlayer> opponentTeamStrongestPlayers = sortPlayersOnStrength(opponentTeamPlayersWithPosition, aiTeamWeakestPlayers.size(), false);

            if (opponentTeam.getTeamCreatedBy().equals("import")){
                isTradeAccepted = generateAiTradeOfferToAi(aiTeam, aiTeamWeakestPlayers, opponentTeam, opponentTeamStrongestPlayers);
            }
            else if (opponentTeam.getTeamCreatedBy().equals("user")){
                isTradeAccepted = generateAiTradeOfferToUser(aiTeam, aiTeamWeakestPlayers, opponentTeam, opponentTeamStrongestPlayers);
            }

            if (isTradeAccepted){
                // swap the players, reset the counter for lossPointCount of aiTeam and return
            }
        }
    }

    private boolean generateAiTradeOfferToAi(ITeam offeringTeam, ArrayList<IPlayer> offeringTeamWeakestPlayers, ITeam opponentTeam, ArrayList<IPlayer> opponentTeamStrongestPlayers) {

        double offeringTeamPlayersStrength = calculateTotalStrengthOfPlayers(offeringTeamWeakestPlayers);
        double opponentTeamPlayersStrength = calculateTotalStrengthOfPlayers(opponentTeamStrongestPlayers);

        if (offeringTeamPlayersStrength > opponentTeamPlayersStrength){

            for (IPlayer opponentTeamPlayer: opponentTeamStrongestPlayers){
                offeringTeam.addPlayer(opponentTeamPlayer);
            }

            for (IPlayer offeringTeamPlayer: offeringTeamWeakestPlayers){
                opponentTeam.addPlayer(offeringTeamPlayer);
            }

            return true;
        }

        if (Math.random() < randomAcceptanceChance){
            // accept the trade offer
            return true;
        }
        else{
            // reject the trade
            return false;
        }

    }

    public double calculateTotalStrengthOfPlayers(ArrayList<IPlayer> players) {

        double strength = 0.0;

        for (IPlayer player: players){
            strength+= player.getPlayerStrength();
        }

        return strength;
    }

    private boolean generateAiTradeOfferToUser(ITeam aiTeam, ArrayList<IPlayer> aiTeamWeakestPlayers, ITeam opponentTeam, ArrayList<IPlayer> opponentTeamStrongestPlayers) {

        return true;
    }

    public ArrayList<IPlayer> getPlayersWithPosition(ArrayList<IPlayer> playersWithPosition, String position){

        ArrayList<IPlayer> players = new ArrayList<>();

        for (IPlayer player: playersWithPosition){
            if (player.getPlayerPosition().equals(position)){
                players.add(player);
            }
        }

        return players;
    }

    public ArrayList<IPlayer> sortPlayersOnStrength(ArrayList<IPlayer> playersToBeSorted, int playersCount, final boolean ascending) {

        ArrayList<IPlayer> players = new ArrayList<>(playersToBeSorted);

        Collections.sort(players, new Comparator<IPlayer>() {

            @Override
            public int compare(IPlayer player1, IPlayer player2) {
                if (ascending) return Double.compare(player1.getPlayerStrength(), player2.getPlayerStrength());
                return Double.compare(player2.getPlayerStrength(), player1.getPlayerStrength());
            }
        });

        if (playersCount >= playersToBeSorted.size()){
            return players;
        }

        return (ArrayList<IPlayer>) players.subList(0, playersCount);
    }


}
