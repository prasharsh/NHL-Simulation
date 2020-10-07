package g4dhl;

import java.util.ArrayList;

public interface ILeague {

    int getLeagueId();
    String getLeagueName();

    void setLeagueId(int leagueId);
    void setLeagueName(String leagueName);

    ArrayList<IConference> getConferences();
    IConference getConference(int index);
    void addConference(IConference conference);
    void setConferences(ArrayList<IConference> conferences);

    ArrayList<IFreeAgent> getFreeAgents();
    IFreeAgent getFreeAgent(int index);
    void addFreeAgent(IFreeAgent freeAgent);
    void setFreeAgents(ArrayList<IFreeAgent> freeAgents);
}
