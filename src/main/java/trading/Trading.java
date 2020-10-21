package trading;

import g4dhl.ITeam;

import java.util.ArrayList;

public class Trading {

    private int lossPoint = 8;
    private double randomTradeOfferChance = 0.05;
    private int maxPlayersPerTrade = 2;
    private double randomAcceptanceChance = 0.05;
    private ArrayList<ITeam> teams;

    public Trading(){
        teams = new ArrayList<>();
    }

    public void startTrading(){
        checkForAiTradeOffers();
    }

    public void checkForAiTradeOffers(){

        for (ITeam team: teams){
            if (team.getTeamCreatedBy().equals("import") && team.getLossPointCount() >= lossPoint){
                if(Math.random() < randomTradeOfferChance){
//                    generateAiTradeOffer(team, teams);
                }
            }
        }
    }

}
