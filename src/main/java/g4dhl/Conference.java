package g4dhl;

import java.util.ArrayList;

public class Conference implements IConference{

    private String conferenceName;
    private ArrayList<IDivision> divisions;

    @Override
    public int getConferenceId() {
        return 0;
    }

    @Override
    public void setConferenceId(int id) {

    }

    @Override
    public String getConferenceName() {
        return conferenceName;
    }

    @Override
    public void setConferenceName(String conferenceName) {
        this.conferenceName = conferenceName;
    }

    @Override
    public ArrayList<IDivision> getDivisions() {
        return divisions;
    }

    @Override
    public IDivision getDivision(int index) {
        return null;
    }

    @Override
    public void addDivision(IDivision division) {

    }

    @Override
    public void setDivisions(ArrayList<IDivision> divisions) {
        this.divisions = divisions;
    }
}
