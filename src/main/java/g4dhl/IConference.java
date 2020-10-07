package g4dhl;

import java.util.ArrayList;

public interface IConference {

    int getConferenceId();
    void setConferenceId(int id);
    String getConferenceName();
    void setConferenceName(String conferenceName);

    ArrayList<IDivision> getDivisions();
    IDivision getDivision(int index);
    void addDivision(IDivision division);
    void setDivisions(ArrayList<IDivision> divisions);
}
