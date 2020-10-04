package leagueModel;

import java.util.ArrayList;

public interface IDivision {
    String getDivisionName();
    void setDivisionName(String divisionName);

    ArrayList<ITeam> getTeams();
    void setTeams(ArrayList<ITeam> teams);
}
