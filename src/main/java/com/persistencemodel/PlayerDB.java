package com.persistencemodel;

import com.datamodel.leaguedatamodel.IPlayer;
import com.datamodel.leaguedatamodel.ITeam;
import com.datamodel.leaguedatamodel.Player;
import com.inputoutputmodel.DisplayToUser;
import com.inputoutputmodel.IDisplayToUser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class PlayerDB implements IPlayerDB {

    private final IDisplayToUser displayToUser = new DisplayToUser();

    @Override
    public void loadPlayers(JSONArray playersArray, ITeam team) {
        for (Object currentPlayer : playersArray) {
            JSONObject playerObject = (JSONObject) currentPlayer;
            IPlayer player = new Player();
            player.setPlayerId((int) (long) playerObject.get("playerId"));
            player.setPlayerName((String) playerObject.get("playerName"));
            player.setPlayerPosition((String) playerObject.get("playerPosition"));
            player.setPlayerCaptain((boolean) playerObject.get("playerCaptain"));
            player.setPlayerBirthYear((int) (long) playerObject.get("playerBirthYear"));
            player.setPlayerBirthMonth((int) (long) playerObject.get("playerBirthMonth"));
            player.setPlayerBirthDay((int) (long) playerObject.get("playerBirthDay"));
            player.setPlayerAgeYear((int) (long) playerObject.get("playerAgeYear"));
            player.setPlayerAgeDays((int) (long) playerObject.get("playerAgeDays"));
            player.setPlayerSkating((int) (long) playerObject.get("playerSkating"));
            player.setPlayerSkating((int) (long) playerObject.get("playerShooting"));
            player.setPlayerSkating((int) (long) playerObject.get("playerChecking"));
            player.setPlayerSkating((int) (long) playerObject.get("playerSaving"));
            player.setPlayerWasInjured((boolean) playerObject.get("playerWasInjured"));
            player.setPlayerIsInjured((boolean) playerObject.get("playerIsInjured"));
            player.setRecoveryDate(getFormattedDate((String) playerObject.get("recoveryDate")));
            player.setPlayerRetired((boolean) playerObject.get("playerRetired"));
            team.addPlayer(player);
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
            displayToUser.displayMsgToUser(e.getLocalizedMessage());
        }
        return formattedDate;
    }
}
