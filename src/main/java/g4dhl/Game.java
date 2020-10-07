package g4dhl;
import g4db.IGameDB;

import java.util.ArrayList;

public class Game implements IGame{

    private ArrayList<ILeague> leagues;

    public Game(){
        this.leagues = new ArrayList<>();
    }

    @Override
    public ArrayList<ILeague> getLeagues() {
        return leagues;
    }

    @Override
    public ILeague getLeague(int index) {
        return leagues.get(index);
    }

    @Override
    public void setLeagues(ArrayList<ILeague> leagues) {
        this.leagues = leagues;
    }

    @Override
    public void addLeague(ILeague league){
        leagues.add(league);
    }

    public void loadLeagues(IGameDB game){
        game.loadLeaguesFromDB(this);
    }

}
