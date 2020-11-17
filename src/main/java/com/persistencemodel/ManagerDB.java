package com.persistencemodel;

import com.datamodel.leaguedatamodel.GeneralManager;
import com.datamodel.leaguedatamodel.IGeneralManager;
import com.datamodel.leaguedatamodel.ILeague;
import com.datamodel.leaguedatamodel.ITeam;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ManagerDB implements IManagerDB {

    @Override
    public void loadManagers(JSONArray managersArray, ILeague league) {
        for (Object currentManager : managersArray) {
            JSONObject managerObject = (JSONObject) currentManager;
            IGeneralManager manager = new GeneralManager();
            manager.setGeneralManagerId((int) (long) managerObject.get("generalManagerId"));
            manager.setGeneralManagerName((String) managerObject.get("generalManagerName"));
            league.setManager(manager);
        }
    }

    @Override
    public void loadGeneralManager(JSONObject managerObject, ITeam team) {
        IGeneralManager generalManager = new GeneralManager();
        generalManager.setGeneralManagerId((int) (long) managerObject.get("generalManagerId"));
        generalManager.setGeneralManagerName((String) managerObject.get("generalManagerName"));
        team.setGeneralManager(generalManager);
    }
}
