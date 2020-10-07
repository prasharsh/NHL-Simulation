package g4dhl;

import java.util.ArrayList;

public interface IConference {

    int getConferenceId();
    void setConferenceId(int conferenceId);
    String getConferenceName();
    void setConferenceName(String conferenceName);

    ArrayList<IDivision> getDivisions();
    void addDivision(IDivision division);
}
