package g4dhl;
import g4db.IGameDB;

import java.util.ArrayList;

public class Game implements IGame, ILoadDataFromDB{

    private ArrayList<ILeague> leagues;

    public Game(){
        this.leagues = new ArrayList<>();
    }

    private boolean checkIfLeagueIsNull(ILeague league) {
        return league == null;
    }

    private boolean checkIfLeagueNameIsEmptyOrNull(String leagueName) {
        return leagueName == null || leagueName.trim().isEmpty();
    }

    private boolean checkIfLeagueNameAlreadyExists(String leagueName){
        for(ILeague league: leagues){
            if (league.getLeagueName().equals(leagueName)){
                return true;
            }
        }
        return false;
    }

    @Override
    public ArrayList<ILeague> getLeagues() {
        return leagues;
    }

    @Override
    public boolean addLeague(ILeague league){

        if(checkIfLeagueIsNull(league)) return false;
        if(checkIfLeagueNameIsEmptyOrNull(league.getLeagueName())) return false;
        if(checkIfLeagueNameAlreadyExists(league.getLeagueName())) return false;

        leagues.add(league);
        return true;
    }

    @Override
    public void loadFromDB(IGameDB gameDB) {
        gameDB.loadLeaguesFromDB(this);
    }
}
