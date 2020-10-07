package g4dhl;

import java.util.ArrayList;

public interface IDivision {

    int getDivisionId();
    String getDivisionName();

    void setDivisionId(int divisionId);
    void setDivisionName(String divisionName);

    ArrayList<ITeam> getTeams();
    void addTeam(ITeam team);
}
