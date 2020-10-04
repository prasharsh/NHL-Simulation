package leagueModel;

import java.util.ArrayList;

public class Game implements IGame{
    private ArrayList<ILeague> leagues;

    @Override
    public ArrayList<ILeague> getLeagues() {
        return leagues;
    }

    @Override
    public void setLeagues(ArrayList<ILeague> leagues) {
        this.leagues = leagues;
    }
}
