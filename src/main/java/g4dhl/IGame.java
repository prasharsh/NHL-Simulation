package g4dhl;

import java.util.ArrayList;

public interface IGame {

    ArrayList<ILeague> getLeagues();
    boolean addLeague(ILeague league);
}