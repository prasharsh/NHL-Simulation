package g4dhl;

import java.util.ArrayList;

public class League implements ILeague{

    private String leagueName;
    private ArrayList<IConference> conferences;
    private ArrayList<IFreeAgent> freeAgents;

    @Override
    public String getLeagueName() {
        return leagueName;
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
    public void setConferences(ArrayList<IConference> conferences) {
        this.conferences = conferences;
    }

    @Override
    public ArrayList<IFreeAgent> getFreeAgents() {
        return freeAgents;
    }

    @Override
    public void setFreeAgents(ArrayList<IFreeAgent> freeAgents) {
        this.freeAgents = freeAgents;
    }
}
