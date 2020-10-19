package trading;

import g4db.GameDB;
import g4db.IGameDB;
import g4dhl.IPlayer;
import g4dhl.ITeam;
import g4dhl.Team;

import java.util.ArrayList;

public class Trading {

    private int lossPoint = 8;
    private double randomTradeOfferChance = 0.05;
    private int maxPlayersPerTrade = 2;
    private double randomAcceptanceChance = 0.05;
    private ArrayList<ITeam> teams;
    private IGameDB gameDB;

    public Trading(){
        teams = new ArrayList<>();
        gameDB = new GameDB();
    }

    public void generateTradeOffers(ArrayList<ITeam> teams) {

        for (ITeam team: teams){
           if (team.getTeamCreatedBy().equals("import") && team.getLossPointCount() >= lossPoint){
                if(Math.random() < randomTradeOfferChance){
                    generateTradeOffer(team, teams);
                }
            }
        }


    }

    private void generateTradeOffer(ITeam team, ArrayList<ITeam> teams) {
        int currentTeamIndex = teams.indexOf(team);
        team.getPlayers();
        ArrayList<IPlayer> weakestPlayers = getWeakestPlayers(team, maxPlayersPerTrade);
        for (int i=0; i<teams.size(); i++){
            if(i == currentTeamIndex){
            }

        }
    }

    private ArrayList<IPlayer> getWeakestPlayers(ITeam team, int playersCount) {
        ArrayList<IPlayer> players = team.getPlayers();
        ArrayList<IPlayer> weakestPlayers = new ArrayList<>();

        return null;

    }

    public void aiTeamTradeWithAiTeam(){

    }

    public void aiTeamTradeWithUserTeam(){

    }

    public void userTeamTradeWithAiTeam(){

    }


}
