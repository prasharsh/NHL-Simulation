package g4dhl;

import java.util.ArrayList;

public interface IConference {

    int getConferenceId();
    boolean setConferenceId(int conferenceId);
    String getConferenceName();
    boolean setConferenceName(String conferenceName);

    ArrayList<IDivision> getDivisions();
    boolean addDivision(IDivision division);
}
