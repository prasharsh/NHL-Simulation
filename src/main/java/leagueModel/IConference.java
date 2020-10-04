package leagueModel;

import java.util.ArrayList;

public interface IConference {

    String getConferenceName();
    void setConferenceName(String conferenceName);

    ArrayList<IDivision> getDivisions();
    void setDivisions(ArrayList<IDivision> divisions);
}
