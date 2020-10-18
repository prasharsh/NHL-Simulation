package g4dhl;

import java.util.ArrayList;

import g4db.IGameDB;

public interface IGame {

    ArrayList<ILeague> getLeagues();
    void getLeagueByName(IGameDB gameDB);
    boolean addLeague(ILeague league);
}