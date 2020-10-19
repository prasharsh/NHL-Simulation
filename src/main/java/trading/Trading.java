package trading;

import g4db.GameDB;
import g4db.IGameDB;
import g4dhl.IPlayer;
import g4dhl.ITeam;
import g4dhl.Team;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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

        List<IPlayer> weakestPlayers = getWeakestPlayers(team, maxPlayersPerTrade);

    }

    public List<IPlayer> getWeakestPlayers(ITeam team, int playersCount) {

        ArrayList<IPlayer> weakestPlayers = new ArrayList<>(team.getPlayers());

        Collections.sort(weakestPlayers, new Comparator<IPlayer>() {

            private double getStrength(IPlayer player)
            {
                double strength;

                if( player.getPlayerPosition().equals("forward")){
                    strength = player.getPlayerSkating() + player.getPlayerShooting() + player.getPlayerChecking()/2.0;
                }
                else if( player.getPlayerPosition().equals("defense")){
                    strength = player.getPlayerSkating() + player.getPlayerChecking() + player.getPlayerShooting()/2.0;
                }
                else{
                    strength = player.getPlayerSkating() + player.getPlayerSaving();
                }
                return strength;
            }

            @Override
            public int compare(IPlayer o1, IPlayer o2) {
                return Double.compare(getStrength(o1), getStrength(o2));
            }
        });

        return weakestPlayers.subList(0, playersCount);

    }

    public void aiTeamTradeWithAiTeam(){

    }

    public void aiTeamTradeWithUserTeam(){

    }

    public void userTeamTradeWithAiTeam(){

    }


}
