package g4db;


import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import g4dhl.*;


public class GameDB implements IGameDB {
    @Override
    public void loadLeaguesFromDB(IGame game) {
        ConnectionDB connection = new ConnectionDB();
        try {
            connection.getConnection();
            String procedureCall = "call SP_LEAGUE_LIST()";
            CallableStatement leagueQuery = connection.con.prepareCall(procedureCall);
            ResultSet leaguesList = leagueQuery.executeQuery();
            while(leaguesList.next()) {
                ILeague league = new League();
                league.setLeagueId(leaguesList.getInt("leagueId"));
                league.setLeagueName(leaguesList.getString("leagueName"));
                game.addLeague(league);
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            connection.closeConnection();
            System.exit(1);
        } finally {
            try { connection.closeConnection(); } catch (Exception ignored) { }
        }
    }

    @Override
    public void loadConferencesFromDB(ILeague league) {
        ConnectionDB connection = new ConnectionDB();
        try {
            connection.getConnection();
            String procedureCall = "call SP_CONFERENCE_GETBYLEAGUEID(?)";
            CallableStatement conferenceQuery = connection.con.prepareCall(procedureCall);
            conferenceQuery.setInt(1, league.getLeagueId());
            ResultSet conferencesList = conferenceQuery.executeQuery();
            while(conferencesList.next()) {
                IConference conference = new Conference();
                conference.setConferenceId(conferencesList.getInt("conferenceId"));
                conference.setConferenceName(conferencesList.getString("conferenceName"));
                league.addConference(conference);
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            connection.closeConnection();
            System.exit(1);
        } finally {
            try { connection.closeConnection(); } catch (Exception ignored) { }
        }
    }

    @Override
    public void loadFreeAgentsFromDB(ILeague league) {
        ConnectionDB connection = new ConnectionDB();
        try {
            connection.getConnection();
            String procedureCall = "call SP_FREEAGENT_GETBYLEAGUEID(?)";
            CallableStatement freeAgentQuery = connection.con.prepareCall(procedureCall);
            freeAgentQuery.setInt(1, league.getLeagueId());
            ResultSet freeAgentsList = freeAgentQuery.executeQuery();
            while(freeAgentsList.next()) {
                IFreeAgent freeAgent = new FreeAgent();
                freeAgent.setFreeAgentId(freeAgentsList.getInt("freeAgentId"));
                freeAgent.setFreeAgentName(freeAgentsList.getString("playerName"));
                freeAgent.setFreeAgentPosition(freeAgentsList.getString("playerPosition"));
                league.addFreeAgent(freeAgent);
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            connection.closeConnection();
            System.exit(1);
        } finally {
            try { connection.closeConnection(); } catch (Exception ignored) { }
        }
    }

    @Override
    public void loadDivisionsFromDB(IConference conference) {
        ConnectionDB connection = new ConnectionDB();
        try {
            connection.getConnection();
            String procedureCall = "call SP_DIVISION_GETBYCONFERENCEID(?)";
            CallableStatement divisionQuery = connection.con.prepareCall(procedureCall);
            divisionQuery.setInt(1, conference.getConferenceId());
            ResultSet divisionsList = divisionQuery.executeQuery();
            while(divisionsList.next()) {
                IDivision division = new Division();
                division.setDivisionId(divisionsList.getInt("divisionId"));
                division.setDivisionName(divisionsList.getString("divisionName"));
                conference.addDivision(division);
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            connection.closeConnection();
            System.exit(1);
        } finally {
            try { connection.closeConnection(); } catch (Exception ignored) { }
        }

    }

    @Override
    public void loadTeamsFromDB(IDivision division) {
        ConnectionDB connection = new ConnectionDB();
        try {
            connection.getConnection();
            String procedureCall = "call SP_TEAM_GETBYDIVISIONID(?)";
            CallableStatement teamQuery = connection.con.prepareCall(procedureCall);
            teamQuery.setInt(1, division.getDivisionId());
            ResultSet teamsList = teamQuery.executeQuery();
            while(teamsList.next()) {
                ITeam team = new Team();
                team.setTeamId(teamsList.getInt("teamId"));
                team.setTeamName(teamsList.getString("teamName"));
                division.addTeam(team);
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            connection.closeConnection();
            System.exit(1);
        } finally {
            try { connection.closeConnection(); } catch (Exception ignored) { }
        }

    }

    @Override
    public void loadPlayersFromDB(ITeam team) {
        ConnectionDB connection = new ConnectionDB();
        try {
            connection.getConnection();
            String procedureCall = "call SP_PLAYER_GETBYTEAMID(?)";
            CallableStatement playerQuery = connection.con.prepareCall(procedureCall);
            playerQuery.setInt(1, team.getTeamId());
            ResultSet playersList = playerQuery.executeQuery();
            while(playersList.next()) {
                IPlayer player = new Player();
                player.setPlayerId(playersList.getInt("playerId"));
                player.setPlayerName(playersList.getString("playerName"));
                player.setPlayerPosition(playersList.getString("playerPosition"));
                player.setPlayerCaptain(playersList.getBoolean("isCaptain"));
                team.addPlayer(player);
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            connection.closeConnection();
            System.exit(1);
        } finally {
            try { connection.closeConnection(); } catch (Exception ignored) { }
        }
    }

    @Override
    public void loadGeneralManagerFromDb(ITeam team) {
        ConnectionDB connection = new ConnectionDB();
        try {
            connection.getConnection();
            String procedureCall = "call SP_GENERALMANAGER_GETBYTEAMID(?)";
            CallableStatement generalManagerQuery = connection.con.prepareCall(procedureCall);
            generalManagerQuery.setInt(1, team.getTeamId());
            ResultSet generalManagerResult = generalManagerQuery.executeQuery();
            while(generalManagerResult.next()) {
                IGeneralManager generalManager = new GeneralManager();
                generalManager.setGeneralManagerId(generalManagerResult.getInt("generalManagerId"));
                generalManager.setGeneralManagerName(generalManagerResult.getString("generalManagerName"));
                team.setGeneralManager(generalManager);
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            connection.closeConnection();
            System.exit(1);
        } finally {
            try { connection.closeConnection(); } catch (Exception ignored) { }
        }

    }

    @Override
    public void loadHeadCoachFromDB(ITeam team) {
        ConnectionDB connection = new ConnectionDB();
        try {
            connection.getConnection();
            String procedureCall = "call SP_HEADCOACH_GETBYTEAMID(?)";
            CallableStatement headCoachQuery = connection.con.prepareCall(procedureCall);
            headCoachQuery.setInt(1, team.getTeamId());
            ResultSet headCoachResult = headCoachQuery.executeQuery();
            while(headCoachResult.next()) {
                IHeadCoach headCoach = new HeadCoach();
                headCoach.setHeadCoachId(headCoachResult.getInt("headCoachId"));
                headCoach.setHeadCoachName(headCoachResult.getString("headCoachName"));
                team.setHeadCoach(headCoach);
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            connection.closeConnection();
            System.exit(1);
        } finally {
            try { connection.closeConnection(); } catch (Exception ignored) { }
        }

    }

    @Override
    public void saveGame(IGame game) {
        ConnectionDB connection = new ConnectionDB();
        try {
            connection.getConnection();
            SaveToDB gameSaver = new SaveToDB(connection);
            gameSaver.saveLeagues(game);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            connection.closeConnection();
            System.exit(1);
        } finally {
            try { connection.closeConnection(); } catch (Exception ignored) { }
        }
    }

    private class SaveToDB {
        ConnectionDB connection;

        SaveToDB(ConnectionDB connection) {
            this.connection = connection;
        }

        private void saveLeagues(IGame game) throws SQLException {
            ArrayList<ILeague> leagues = game.getLeagues();
            for (ILeague league : leagues) {
                String procedureCall = "call SP_LEAGUE_INSERT(?)";
                CallableStatement leagueQuery = this.connection.con.prepareCall(procedureCall);
                leagueQuery.setString(1, league.getLeagueName());
                ResultSet leagueResult = leagueQuery.executeQuery();
                if (leagueResult.next()) {
                    league.setLeagueId(leagueResult.getInt("leagueId"));
                    league.setLeagueName(leagueResult.getString("leagueName"));
                    saveConferences(league);
                    saveFreeAgents(league);
                }
            }
        }

        private void saveConferences(ILeague league) throws SQLException {
            ArrayList<IConference> conferences = league.getConferences();
            for (IConference conference : conferences) {
                String procedureCall = "call SP_CONFERENCE_INSERT(?,?)";
                CallableStatement conferenceQuery = this.connection.con.prepareCall(procedureCall);
                conferenceQuery.setString(1, conference.getConferenceName());
                conferenceQuery.setInt(2, league.getLeagueId());
                ResultSet ConferenceResult = conferenceQuery.executeQuery();
                if (ConferenceResult.next()) {
                    conference.setConferenceId(ConferenceResult.getInt("conferenceId"));
                    conference.setConferenceName(ConferenceResult.getString("conferenceName"));
                    saveDivisions(conference);
                }
            }
        }

        private void saveDivisions(IConference conference) throws SQLException {
            ArrayList<IDivision> divisions = conference.getDivisions();
            for (IDivision division : divisions) {
                String procedureCall = "call SP_DIVISION_INSERT(?,?)";
                CallableStatement divisionQuery = this.connection.con.prepareCall(procedureCall);
                divisionQuery.setString(1, division.getDivisionName());
                divisionQuery.setInt(2, conference.getConferenceId());
                ResultSet divisionResult = divisionQuery.executeQuery();
                if (divisionResult.next()) {
                    division.setDivisionId(divisionResult.getInt("divisionId"));
                    division.setDivisionName(divisionResult.getString("divisionName"));
                    saveTeams(division);
                }
            }
        }

        private void saveTeams(IDivision division) throws SQLException {
            ArrayList<ITeam> teams = division.getTeams();
            for (ITeam team : teams) {
                String procedureCall = "call SP_TEAM_INSERT(?,?)";
                CallableStatement teamQuery = this.connection.con.prepareCall(procedureCall);
                teamQuery.setString(1, team.getTeamName());
                teamQuery.setInt(2, division.getDivisionId());
                ResultSet teamResult = teamQuery.executeQuery();
                if (teamResult.next()) {
                    team.setTeamId(teamResult.getInt("teamId"));
                    team.setTeamName(teamResult.getString("teamName"));
                    savePlayers(team);
                    saveGeneralManager(team);
                    saveHeadCoach(team);
                }
            }
        }

        private void saveGeneralManager(ITeam team) throws SQLException {
            IGeneralManager generalManager = team.getGeneralManager();
            String procedureCall = "call SP_GENERALMANAGER_INSERT(?,?)";
            CallableStatement generalManagerQuery = this.connection.con.prepareCall(procedureCall);
            generalManagerQuery.setString(1, generalManager.getGeneralManagerName());
            generalManagerQuery.setInt(2, team.getTeamId());
            ResultSet generalManagerResult = generalManagerQuery.executeQuery();
            if (generalManagerResult.next()) {
                generalManager.setGeneralManagerId(generalManagerResult.getInt("generalManagerId"));
                generalManager.setGeneralManagerName(generalManagerResult.getString("generalManagerName"));
            }
        }

        private void saveHeadCoach(ITeam team) throws SQLException {
            IHeadCoach headCoach = team.getHeadCoach();
            String procedureCall = "call SP_HEADCOACH_INSERT(?,?)";
            CallableStatement headCoachQuery = this.connection.con.prepareCall(procedureCall);
            headCoachQuery.setString(1, headCoach.getHeadCoachName());
            headCoachQuery.setInt(2, team.getTeamId());
            ResultSet headCoachResult = headCoachQuery.executeQuery();
            if (headCoachResult.next()) {
                headCoach.setHeadCoachId(headCoachResult.getInt("headCoachId"));
                headCoach.setHeadCoachName(headCoachResult.getString("headCoachName"));
            }
        }

        private void savePlayers(ITeam team) throws SQLException {
            ArrayList<IPlayer> players = team.getPlayers();
            for (IPlayer player : players) {
                String procedureCall = "call SP_PLAYER_INSERT(?,?,?,?)";
                CallableStatement playerQuery = this.connection.con.prepareCall(procedureCall);
                playerQuery.setString(1, player.getPlayerName());
                playerQuery.setString(2, player.getPlayerPosition());
                playerQuery.setBoolean(3, player.isPlayerCaptain());
                playerQuery.setInt(4, team.getTeamId());
                ResultSet playerResult = playerQuery.executeQuery();
                if (playerResult.next()) {
                    player.setPlayerId(playerResult.getInt("playerId"));
                    player.setPlayerName(playerResult.getString("playerName"));
                    player.setPlayerPosition(playerResult.getString("playerPosition"));
                    player.setPlayerCaptain(playerResult.getBoolean("isCaptain"));
                }
            }
        }

        private void saveFreeAgents(ILeague league) throws SQLException {
            ArrayList<IFreeAgent> freeAgents = league.getFreeAgents();
            for (IFreeAgent freeAgent : freeAgents) {
                String procedureCall = "call SP_FREEAGENT_INSERT(?,?,?,?)";
                CallableStatement freeAgentQuery = this.connection.con.prepareCall(procedureCall);
                freeAgentQuery.setString(1, freeAgent.getFreeAgentName());
                freeAgentQuery.setString(2, freeAgent.getFreeAgentPosition());
                freeAgentQuery.setInt(4, league.getLeagueId());
                ResultSet freeAgentResult = freeAgentQuery.executeQuery();
                if (freeAgentResult.next()) {
                    freeAgent.setFreeAgentId(freeAgentResult.getInt("freeAgentId"));
                    freeAgent.setFreeAgentName(freeAgentResult.getString("playerName"));
                    freeAgent.setFreeAgentPosition(freeAgentResult.getString("playerPosition"));
                }
            }
        }
    }

	@Override
	public void loadLeagueFromDB(IGame game) {
		 ConnectionDB connection = new ConnectionDB();
	        try {
	            connection.getConnection();
	            String procedureCall = "call SP_LEAGUE_GETBYNAME(?)";
	            CallableStatement leagueQuery = connection.con.prepareCall(procedureCall);
	            leagueQuery.setString(1, game.getLeagues().get(0).getLeagueName());
	            ResultSet leaguesList = leagueQuery.executeQuery();
	            if(leaguesList.next()) {
	                ILeague league = new League();
	                league.setLeagueId(leaguesList.getInt("leagueId"));
	                league.setLeagueName(leaguesList.getString("leagueName"));
	                game.getLeagues().get(0).setLeagueId(leaguesList.getInt("leagueId"));
	                //addLeague(league);
	            }
	        } catch (Exception e) {
	            System.out.println(e.getLocalizedMessage());
	            connection.closeConnection();
	            System.exit(1);
	        } finally {
	            try { connection.closeConnection(); } catch (Exception ignored) { }
	        }
		
	}
}
