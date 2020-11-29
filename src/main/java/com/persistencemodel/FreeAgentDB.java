package com.persistencemodel;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.datamodel.leaguedatamodel.FreeAgent;
import com.datamodel.leaguedatamodel.ILeague;
import com.datamodel.leaguedatamodel.IPlayer;

public class FreeAgentDB implements IFreeAgentDB {

    private final static Logger logger = Logger.getLogger(FreeAgentDB.class);

    @Override
    public void loadFreeAgents(JSONArray freeAgentsArray, ILeague league) {
        for (Object currentPlayer : freeAgentsArray) {
            JSONObject playerObject = (JSONObject) currentPlayer;
            IPlayer freeAgent = new FreeAgent();
            freeAgent.setPlayerId((int) (long) playerObject.get("freeAgentId"));
            freeAgent.setPlayerName((String) playerObject.get("freeAgentName"));
            freeAgent.setPlayerPosition((String) playerObject.get("freeAgentPosition"));
            freeAgent.setPlayerBirthYear((int) (long) playerObject.get("freeAgentBirthYear"));
            freeAgent.setPlayerBirthMonth((int) (long) playerObject.get("freeAgentBirthMonth"));
            freeAgent.setPlayerBirthDay((int) (long) playerObject.get("freeAgentBirthDay"));
            freeAgent.setPlayerAgeYear((int) (long) playerObject.get("freeAgentAgeYear"));
            freeAgent.setPlayerAgeDays((int) (long) playerObject.get("freeAgentAgeDays"));
            freeAgent.setPlayerSkating((int) (long) playerObject.get("freeAgentSkating"));
            freeAgent.setPlayerSkating((int) (long) playerObject.get("freeAgentShooting"));
            freeAgent.setPlayerSkating((int) (long) playerObject.get("freeAgentChecking"));
            freeAgent.setPlayerSkating((int) (long) playerObject.get("freeAgentSaving"));
            freeAgent.setPlayerWasInjured((boolean) playerObject.get("freeAgentWasInjured"));
            freeAgent.setPlayerIsInjured((boolean) playerObject.get("freeAgentIsInjured"));
            freeAgent.setRecoveryDate(getFormattedDate((String) playerObject.get("recoveryDate")));
            freeAgent.setPlayerRetired((boolean) playerObject.get("freeAgentRetired"));
            league.addFreeAgent(freeAgent);
        }
    }

    private Date getFormattedDate(String dateValue) {
        Date formattedDate = null;
        if (dateValue == null) {
            return formattedDate;
        }
        try {
            java.util.Date dateNew = new SimpleDateFormat("MMM dd, yyyy").parse(dateValue);
            formattedDate = new Date(dateNew.getTime());
        } catch (java.text.ParseException e) {
            logger.error(e.getLocalizedMessage());
        }
        return formattedDate;
    }
}
