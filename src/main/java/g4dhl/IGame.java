package g4dhl;

import java.util.ArrayList;

public interface IGame {
    ArrayList<ILeague> getLeagues();
    ILeague getLeague (int index);
    void addLeague(ILeague league);
    void setLeagues(ArrayList<ILeague> leagues);
}