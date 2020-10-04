package leagueModel;

import java.util.ArrayList;

public interface ILeague {

    String getLeagueName();
    void setLeagueName(String leagueName);

    ArrayList<IConference> getConferences();
    void setConferences(ArrayList<IConference> conferences);

    ArrayList<IFreeAgent> getFreeAgents();
    void setFreeAgents(ArrayList<IFreeAgent> freeAgents);
}
