package com.persistencemodel;

import com.datamodel.leaguedatamodel.IConference;
import org.json.simple.JSONArray;

public interface IDivisionDB {
    void loadDivisions(JSONArray divisionArray, IConference conference);
}
