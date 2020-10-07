package g4dhl;

import java.util.ArrayList;

public class League implements ILeague{

    private String leagueName;
    private ArrayList<IConference> conferences;
    private ArrayList<IFreeAgent> freeAgents;

    @Override
    public int getLeagueId() {
        return 0;
    }

    @Override
    public String getLeagueName() {
        return leagueName;
    }

    @Override
    public void setLeagueId(int leagueId) {

    }

    @Override
    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    @Override
    public ArrayList<IConference> getConferences() {
        return conferences;
    }

    @Override
    public IConference getConference(int index) {
        return null;
    }

    @Override
    public void addConference(IConference conference) {

    }

    @Override
    public void setConferences(ArrayList<IConference> conferences) {
        this.conferences = conferences;
    }

    @Override
    public ArrayList<IFreeAgent> getFreeAgents() {
        return freeAgents;
    }

    @Override
    public IFreeAgent getFreeAgent(int index) {
        return null;
    }

    @Override
    public void addFreeAgent(IFreeAgent freeAgent) {

    }

    @Override
    public void setFreeAgents(ArrayList<IFreeAgent> freeAgents) {
        this.freeAgents = freeAgents;
    }
}
