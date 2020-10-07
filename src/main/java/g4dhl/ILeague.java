package g4dhl;

import java.util.ArrayList;

public interface ILeague {

    int getLeagueId();
    String getLeagueName();

    void setLeagueId(int leagueId);
    void setLeagueName(String leagueName);

    ArrayList<IConference> getConferences();
    void addConference(IConference conference);

    ArrayList<IFreeAgent> getFreeAgents();
    void addFreeAgent(IFreeAgent freeAgent);
}
