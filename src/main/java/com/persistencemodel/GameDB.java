package com.persistencemodel;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.datamodel.gameplayconfig.*;
import com.datamodel.leaguedatamodel.*;

public class GameDB implements IGameDB {
    @Override
    public void loadLeaguesFromDB(IGame game) {
        ConnectionDB connection = new ConnectionDB();
        try {
            connection.getConnection();
            String procedureCall = "call SP_LEAGUE_LIST()";
            CallableStatement leagueQuery = connection.con.prepareCall(procedureCall);
            ResultSet leaguesList = leagueQuery.executeQuery();
            while (leaguesList.next()) {
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
            try {
                connection.closeConnection();
            } catch (Exception ignored) {
            }
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
            while (conferencesList.next()) {
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
            try {
                connection.closeConnection();
            } catch (Exception ignored) {
            }
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
            while (freeAgentsList.next()) {
                IPlayer freeAgent = new FreeAgent();
                freeAgent.setPlayerId(freeAgentsList.getInt("playerId"));
                freeAgent.setPlayerName(freeAgentsList.getString("playerName"));
                freeAgent.setPlayerPosition(freeAgentsList.getString("playerPosition"));
                freeAgent.setPlayerAgeYear(freeAgentsList.getInt("playerAgeYear"));
                freeAgent.setPlayerAgeDays(freeAgentsList.getInt("playerAgeDays"));
                freeAgent.setPlayerSkating(freeAgentsList.getInt("playerSkating"));
                freeAgent.setPlayerShooting(freeAgentsList.getInt("playerShooting"));
                freeAgent.setPlayerChecking(freeAgentsList.getInt("playerChecking"));
                freeAgent.setPlayerSaving(freeAgentsList.getInt("playerSaving"));
                freeAgent.setPlayerWasInjured(freeAgentsList.getBoolean("wasInjured"));
                freeAgent.setPlayerIsInjured(freeAgentsList.getBoolean("isInjured"));
                freeAgent.setRecoveryDate(freeAgentsList.getDate("recoveryDate"));
                league.addFreeAgent(freeAgent);
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            connection.closeConnection();
            System.exit(1);
        } finally {
            try {
                connection.closeConnection();
            } catch (Exception ignored) {
            }
        }
    }

    @Override
    public void loadManagersFromDb(ILeague league) {
        ConnectionDB connection = new ConnectionDB();
        try {
            connection.getConnection();
            String procedureCall = "call SP_FREEGENERALMANAGER_GETBYTLEAGUEID(?)";
            CallableStatement managerQuery = connection.con.prepareCall(procedureCall);
            managerQuery.setInt(1, league.getLeagueId());
            ResultSet managerResult = managerQuery.executeQuery();
            while (managerResult.next()) {
                IGeneralManager manager = new GeneralManager();
                manager.setGeneralManagerId(managerResult.getInt("generalManagerId"));
                manager.setGeneralManagerName(managerResult.getString("generalManagerName"));
                league.setManager(manager);
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            connection.closeConnection();
            System.exit(1);
        } finally {
            try {
                connection.closeConnection();
            } catch (Exception ignored) {
            }
        }

    }

    @Override
    public void loadCoachesFromDB(ILeague league) {
        ConnectionDB connection = new ConnectionDB();
        try {
            connection.getConnection();
            String procedureCall = "call SP_FREEHEADCOACH_GETBYLEAGUEID(?)";
            CallableStatement coachQuery = connection.con.prepareCall(procedureCall);
            coachQuery.setInt(1, league.getLeagueId());
            ResultSet coachResult = coachQuery.executeQuery();
            while (coachResult.next()) {
                IHeadCoach coach = new HeadCoach();
                coach.setHeadCoachId(coachResult.getInt("headCoachId"));
                coach.setHeadCoachName(coachResult.getString("headCoachName"));
                coach.setHeadCoachSkating(coachResult.getFloat("headCoachSkating"));
                coach.setHeadCoachShooting(coachResult.getFloat("headCoachShooting"));
                coach.setHeadCoachChecking(coachResult.getFloat("headCoachChecking"));
                coach.setHeadCoachSaving(coachResult.getFloat("headCoachSaving"));
                league.setCoach(coach);
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            connection.closeConnection();
            System.exit(1);
        } finally {
            try {
                connection.closeConnection();
            } catch (Exception ignored) {
            }
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
            while (divisionsList.next()) {
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
            try {
                connection.closeConnection();
            } catch (Exception ignored) {
            }
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
            while (teamsList.next()) {
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
            try {
                connection.closeConnection();
            } catch (Exception ignored) {
            }
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
            while (playersList.next()) {
                IPlayer player = new Player();
                player.setPlayerId(playersList.getInt("playerId"));
                player.setPlayerName(playersList.getString("playerName"));
                player.setPlayerPosition(playersList.getString("playerPosition"));
                player.setPlayerCaptain(playersList.getBoolean("isCaptain"));
                player.setPlayerAgeYear(playersList.getInt("playerAgeYear"));
                player.setPlayerAgeDays(playersList.getInt("playerAgeDays"));
                player.setPlayerSkating(playersList.getInt("playerSkating"));
                player.setPlayerShooting(playersList.getInt("playerShooting"));
                player.setPlayerChecking(playersList.getInt("playerChecking"));
                player.setPlayerSaving(playersList.getInt("playerSaving"));
                player.setPlayerWasInjured(playersList.getBoolean("wasInjured"));
                player.setPlayerIsInjured(playersList.getBoolean("isInjured"));
                player.setRecoveryDate(playersList.getDate("recoveryDate"));
                team.addPlayer(player);
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            connection.closeConnection();
            System.exit(1);
        } finally {
            try {
                connection.closeConnection();
            } catch (Exception ignored) {
            }
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
            while (generalManagerResult.next()) {
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
            try {
                connection.closeConnection();
            } catch (Exception ignored) {
            }
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
            while (headCoachResult.next()) {
                IHeadCoach headCoach = new HeadCoach();
                headCoach.setHeadCoachId(headCoachResult.getInt("headCoachId"));
                headCoach.setHeadCoachName(headCoachResult.getString("headCoachName"));
                headCoach.setHeadCoachSkating(headCoachResult.getFloat("headCoachSkating"));
                headCoach.setHeadCoachShooting(headCoachResult.getFloat("headCoachShooting"));
                headCoach.setHeadCoachChecking(headCoachResult.getFloat("headCoachChecking"));
                headCoach.setHeadCoachSaving(headCoachResult.getFloat("headCoachSaving"));
                team.setHeadCoach(headCoach);
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            connection.closeConnection();
            System.exit(1);
        } finally {
            try {
                connection.closeConnection();
            } catch (Exception ignored) {
            }
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
            System.out.println(e.getMessage());
            System.out.println(e);
            connection.closeConnection();
            System.exit(1);
        } finally {
            try {
                connection.closeConnection();
            } catch (Exception ignored) {
            }
        }
    }

    @Override
    public int getLeagueIdFromTeamName(String teamName) {
        return 0; // need to update
    }

    @Override
    public void loadGame(IGame game) {

    }

    private class SaveToDB {
        ConnectionDB connection;

        SaveToDB(ConnectionDB connection) {
            this.connection = connection;
        }

        private void saveLeagues(IGame game) throws SQLException {
            ArrayList<ILeague> leagues = game.getLeagues();
            for (ILeague league : leagues) {
                String procedureCall = "call SP_LEAGUE_INSERT(?, ?, ?)";
                CallableStatement leagueQuery = this.connection.con.prepareCall(procedureCall);
                leagueQuery.setInt(1, league.getLeagueId());
                leagueQuery.setString(2, league.getLeagueName());
                leagueQuery.setDate(3, league.getCurrentDate());
                ResultSet leagueResult = leagueQuery.executeQuery();
                if (leagueResult.next()) {
                    league.setLeagueId(leagueResult.getInt("leagueId"));
                    league.setLeagueName(leagueResult.getString("leagueName"));
                    league.setCurrentDate(leagueResult.getDate("currentDate"));
                    saveGameplayConfig(league);
                    saveConferences(league);
                    saveFreeAgents(league);
                    saveFreeManagers(league);
                    saveFreeCoaches(league);
                }
            }
        }

        private void saveGameplayConfig(ILeague league) throws SQLException {
            IGameplayConfig gameplayConfig = league.getGamePlayConfig();
            String procedureCall = "call SP_GAMEPLAYCONFIG_INSERT(?, ?)";
            CallableStatement configQuery = this.connection.con.prepareCall(procedureCall);
            configQuery.setInt(1, gameplayConfig.getGameConfigId());
            configQuery.setInt(2, league.getLeagueId());
            ResultSet configResult = configQuery.executeQuery();
            if (configResult.next()) {
                gameplayConfig.setGameConfigId(configResult.getInt("gameplayConfigId"));
                saveAging(gameplayConfig);
                saveGameResolver(gameplayConfig);
                saveInjury(gameplayConfig);
                saveTrading(gameplayConfig);
                saveTraining(gameplayConfig);
            }
        }

        private void saveAging(IGameplayConfig gameplayConfig) throws SQLException {
            IAgingConfig aging = gameplayConfig.getAging();
            String procedureCall = "call SP_AGING_INSERT(?, ?, ?, ?)";
            CallableStatement agingQuery = this.connection.con.prepareCall(procedureCall);
            agingQuery.setInt(1, aging.getAgingId());
            agingQuery.setInt(2, aging.getAverageRetirementAge());
            agingQuery.setInt(3, aging.getMaximumAge());
            agingQuery.setInt(4, gameplayConfig.getGameConfigId());
            ResultSet agingResult = agingQuery.executeQuery();
            if (agingResult.next()) {
                aging.setAgingId(agingResult.getInt("agingId"));
                aging.setAverageRetirementAge(agingResult.getInt("averageRetirementAge"));
                aging.setAgingId(agingResult.getInt("maximumAge"));
            }
        }

        private void saveGameResolver(IGameplayConfig gameplayConfig) throws SQLException {
            IGameResolverConfig gameResolver = gameplayConfig.getGameResolver();
            String procedureCall = "call SP_GAMERESOLVER_INSERT(?, ?, ?)";
            CallableStatement resolverQuery = this.connection.con.prepareCall(procedureCall);
            resolverQuery.setInt(1, gameResolver.getGameResolverId());
            resolverQuery.setFloat(2, gameResolver.getRandomWinChance());
            resolverQuery.setInt(3, gameplayConfig.getGameConfigId());
            ResultSet resolverResult = resolverQuery.executeQuery();
            if (resolverResult.next()) {
                gameResolver.setGameResolverId(resolverResult.getInt("gameResolverId"));
                gameResolver.setRandomWinChance(resolverResult.getFloat("randomWinChance"));
            }
        }

        private void saveInjury(IGameplayConfig gameplayConfig) throws SQLException {
            IInjuryConfig injury = gameplayConfig.getInjury();
            String procedureCall = "call SP_INJURY_INSERT(?, ?, ?, ?, ?)";
            CallableStatement injuryQuery = this.connection.con.prepareCall(procedureCall);
            injuryQuery.setInt(1, injury.getInjuryId());
            injuryQuery.setFloat(2, injury.getRandomInjuryChance());
            injuryQuery.setInt(3, injury.getInjuryDaysLow());
            injuryQuery.setInt(4, injury.getInjuryDaysHigh());
            injuryQuery.setInt(5, gameplayConfig.getGameConfigId());
            ResultSet injuryResult = injuryQuery.executeQuery();
            if (injuryResult.next()) {
                injury.setInjuryId(injuryResult.getInt("injuryId"));
                injury.setRandomInjuryChance(injuryResult.getFloat("randomInjuryChance"));
                injury.setInjuryDaysLow(injuryResult.getInt("injuryDaysLow"));
                injury.setInjuryDaysHigh(injuryResult.getInt("injuryDaysHigh"));
            }
        }

        private void saveTrading(IGameplayConfig gameplayConfig) throws SQLException {
            ITradingConfig tradingConfig = gameplayConfig.getTrading();
            String procedureCall = "call SP_TRADING_INSERT(?, ?, ?, ?, ?, ?)";
            CallableStatement tradingQuery = this.connection.con.prepareCall(procedureCall);
            tradingQuery.setInt(1, tradingConfig.getTradingId());
            tradingQuery.setFloat(2, tradingConfig.getRandomTradeOfferChance());
            tradingQuery.setInt(3, tradingConfig.getLossPoint());
            tradingQuery.setInt(4, tradingConfig.getMaxPlayersPerTrade());
            tradingQuery.setFloat(5, tradingConfig.getRandomAcceptanceChance());
            tradingQuery.setInt(6, gameplayConfig.getGameConfigId());
            ResultSet tradingResult = tradingQuery.executeQuery();
            if (tradingResult.next()) {
                tradingConfig.setTradingId(tradingResult.getInt("tradingId"));
                tradingConfig.setRandomTradeOfferChance(tradingResult.getFloat("randomTradeOfferChance"));
                tradingConfig.setLossPoint(tradingResult.getInt("lossPoint"));
                tradingConfig.setMaxPlayersPerTrade(tradingResult.getInt("maxPlayersPerTrade"));
                tradingConfig.setRandomAcceptanceChance(tradingResult.getFloat("randomAcceptanceChance"));
            }
        }

        private void saveTraining(IGameplayConfig gameplayConfig) throws SQLException {
            ITrainingConfig training = gameplayConfig.getTraining();
            String procedureCall = "call SP_TRAINING_INSERT(?, ?, ?, ?)";
            CallableStatement trainingQuery = this.connection.con.prepareCall(procedureCall);
            trainingQuery.setInt(1, training.getTrainingId());
            trainingQuery.setInt(2, training.getDaysUntilStatIncreaseCheck());
            trainingQuery.setInt(3, training.getNoOfDaysTrained());
            trainingQuery.setInt(4, gameplayConfig.getGameConfigId());
            ResultSet trainingResult = trainingQuery.executeQuery();
            if (trainingResult.next()) {
                training.setTrainingId(trainingResult.getInt("trainingId"));
                training.setDaysUntilStatIncreaseCheck(trainingResult.getInt("daysUntilStatIncreaseCheck"));
                training.setNoOfDaysTrained(trainingResult.getInt("noOfDaysTrained"));
            }
        }

        private void saveConferences(ILeague league) throws SQLException {
            ArrayList<IConference> conferences = league.getConferences();
            for (IConference conference : conferences) {
                String procedureCall = "call SP_CONFERENCE_INSERT(?, ?, ?)";
                CallableStatement conferenceQuery = this.connection.con.prepareCall(procedureCall);
                conferenceQuery.setInt(1, conference.getConferenceId());
                conferenceQuery.setString(2, conference.getConferenceName());
                conferenceQuery.setInt(3, league.getLeagueId());
                ResultSet ConferenceResult = conferenceQuery.executeQuery();
                if (ConferenceResult.next()) {
                    conference.setConferenceId(ConferenceResult.getInt("conferenceId"));
                    conference.setConferenceName(ConferenceResult.getString("conferenceName"));
                    saveDivisions(conference, league.getLeagueId());
                }
            }
        }

        private void saveDivisions(IConference conference, int leagueId) throws SQLException {
            ArrayList<IDivision> divisions = conference.getDivisions();
            for (IDivision division : divisions) {
                String procedureCall = "call SP_DIVISION_INSERT(?, ?, ?)";
                CallableStatement divisionQuery = this.connection.con.prepareCall(procedureCall);
                divisionQuery.setInt(1, division.getDivisionId());
                divisionQuery.setString(2, division.getDivisionName());
                divisionQuery.setInt(3, conference.getConferenceId());
                ResultSet divisionResult = divisionQuery.executeQuery();
                if (divisionResult.next()) {
                    division.setDivisionId(divisionResult.getInt("divisionId"));
                    division.setDivisionName(divisionResult.getString("divisionName"));
                    saveTeams(division, leagueId);
                }
            }
        }

        private void saveTeams(IDivision division, int leagueId) throws SQLException {
            ArrayList<ITeam> teams = division.getTeams();
            for (ITeam team : teams) {
                String procedureCall = "call SP_TEAM_INSERT(?, ?, ?, ?, ?)";
                CallableStatement teamQuery = this.connection.con.prepareCall(procedureCall);
                teamQuery.setInt(1, team.getTeamId());
                teamQuery.setString(2, team.getTeamName());
                teamQuery.setString(3, team.getTeamCreatedBy());
                teamQuery.setInt(4, division.getDivisionId());
                teamQuery.setInt(5, leagueId);
                ResultSet teamResult = teamQuery.executeQuery();
                if (teamResult.next()) {
                    team.setTeamId(teamResult.getInt("teamId"));
                    team.setTeamName(teamResult.getString("teamName"));
                    team.setTeamCreatedBy(teamResult.getString("createdBy"));
                    savePlayers(team, leagueId);
                    saveGeneralManager(team, leagueId);
                    saveHeadCoach(team, leagueId);
                }
            }
        }

        private void savePlayers(ITeam team, int leagueId) throws SQLException {
            ArrayList<IPlayer> players = team.getPlayers();
            for (IPlayer player : players) {
                String procedureCall = "call SP_PLAYER_INSERT(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                CallableStatement playerQuery = this.connection.con.prepareCall(procedureCall);
                playerQuery.setInt(1, player.getPlayerId());
                playerQuery.setString(2, player.getPlayerName());
                playerQuery.setString(3, player.getPlayerPosition());
                playerQuery.setBoolean(4, player.isPlayerCaptain());
                playerQuery.setInt(5, player.getPlayerAgeYear());
                playerQuery.setInt(6, player.getPlayerAgeDays());
                playerQuery.setInt(7, player.getPlayerSkating());
                playerQuery.setInt(8, player.getPlayerShooting());
                playerQuery.setInt(9, player.getPlayerChecking());
                playerQuery.setInt(10, player.getPlayerSaving());
                playerQuery.setBoolean(11, player.wasPlayerInjured());
                playerQuery.setBoolean(12, player.isPlayerInjured());
                playerQuery.setDate(13, player.getRecoveryDate());
                playerQuery.setBoolean(14, false);
                playerQuery.setInt(15, team.getTeamId());
                playerQuery.setInt(16, leagueId);
                ResultSet playerResult = playerQuery.executeQuery();
                if (playerResult.next()) {
                    player.setPlayerId(playerResult.getInt("playerId"));
                    player.setPlayerName(playerResult.getString("playerName"));
                    player.setPlayerPosition(playerResult.getString("playerPosition"));
                    player.setPlayerCaptain(playerResult.getBoolean("isCaptain"));
                    player.setPlayerAgeYear(playerResult.getInt("playerAgeYear"));
                    player.setPlayerAgeDays(playerResult.getInt("playerAgeDays"));
                    player.setPlayerSkating(playerResult.getInt("playerSkating"));
                    player.setPlayerSkating(playerResult.getInt("playerShooting"));
                    player.setPlayerSkating(playerResult.getInt("playerChecking"));
                    player.setPlayerSkating(playerResult.getInt("playerSaving"));
                    player.setPlayerWasInjured(playerResult.getBoolean("wasInjured"));
                    player.setPlayerIsInjured(playerResult.getBoolean("isInjured"));
                    player.setRecoveryDate(playerResult.getDate("recoveryDate"));
//                    player.setPlayerRetired(playerResult.getBoolean("isRetired"));
                }
            }
        }

        private void saveGeneralManager(ITeam team, int leagueId) throws SQLException {
            IGeneralManager generalManager = team.getGeneralManager();
            String procedureCall = "call SP_GENERALMANAGER_INSERT(?, ?, ?, ?)";
            CallableStatement generalManagerQuery = this.connection.con.prepareCall(procedureCall);
            generalManagerQuery.setInt(1, generalManager.getGeneralManagerId());
            generalManagerQuery.setString(2, generalManager.getGeneralManagerName());
            generalManagerQuery.setInt(3, team.getTeamId());
            generalManagerQuery.setInt(4, leagueId);
            ResultSet generalManagerResult = generalManagerQuery.executeQuery();
            if (generalManagerResult.next()) {
                generalManager.setGeneralManagerId(generalManagerResult.getInt("generalManagerId"));
                generalManager.setGeneralManagerName(generalManagerResult.getString("generalManagerName"));
            }
        }

        private void saveHeadCoach(ITeam team, int leagueId) throws SQLException {
            IHeadCoach headCoach = team.getHeadCoach();
            String procedureCall = "call SP_HEADCOACH_INSERT(?, ?, ?, ?, ?, ?, ?, ?)";
            CallableStatement headCoachQuery = this.connection.con.prepareCall(procedureCall);
            headCoachQuery.setInt(1, headCoach.getHeadCoachId());
            headCoachQuery.setString(2, headCoach.getHeadCoachName());
            headCoachQuery.setFloat(3, headCoach.getHeadCoachSkating());
            headCoachQuery.setFloat(4, headCoach.getHeadCoachShooting());
            headCoachQuery.setFloat(5, headCoach.getHeadCoachChecking());
            headCoachQuery.setFloat(6, headCoach.getHeadCoachSaving());
            headCoachQuery.setInt(7, team.getTeamId());
            headCoachQuery.setInt(8, leagueId);
            ResultSet headCoachResult = headCoachQuery.executeQuery();
            if (headCoachResult.next()) {
                headCoach.setHeadCoachId(headCoachResult.getInt("headCoachId"));
                headCoach.setHeadCoachName(headCoachResult.getString("headCoachName"));
                headCoach.setHeadCoachSkating(headCoachResult.getFloat("headCoachSkating"));
                headCoach.setHeadCoachSkating(headCoachResult.getFloat("headCoachShooting"));
                headCoach.setHeadCoachSkating(headCoachResult.getFloat("headCoachChecking"));
                headCoach.setHeadCoachSkating(headCoachResult.getFloat("headCoachSaving"));
            }
        }

        private void saveFreeManagers(ILeague league) throws SQLException {
            ArrayList<IGeneralManager> managers = league.getManagers();
            for (IGeneralManager manager : managers) {
                String procedureCall = "call SP_FREEGENERALMANAGER_INSERT(?, ?, ?)";
                CallableStatement managerQuery = this.connection.con.prepareCall(procedureCall);
                managerQuery.setInt(1, manager.getGeneralManagerId());
                managerQuery.setString(2, manager.getGeneralManagerName());
                managerQuery.setInt(3, league.getLeagueId());
                ResultSet managerResult = managerQuery.executeQuery();
                if (managerResult.next()) {
                    manager.setGeneralManagerId(managerResult.getInt("generalManagerId"));
                    manager.setGeneralManagerName(managerResult.getString("generalManagerName"));
                }
            }
        }

        private void saveFreeCoaches(ILeague league) throws SQLException {
            ArrayList<IHeadCoach> coaches = league.getCoaches();
            for (IHeadCoach coach : coaches) {
                String procedureCall = "call SP_FREEHEADCOACH_INSERT(?, ?, ?, ?, ?, ?, ?)";
                CallableStatement coachQuery = this.connection.con.prepareCall(procedureCall);
                coachQuery.setInt(1, coach.getHeadCoachId());
                coachQuery.setString(2, coach.getHeadCoachName());
                coachQuery.setFloat(3, coach.getHeadCoachSkating());
                coachQuery.setFloat(4, coach.getHeadCoachShooting());
                coachQuery.setFloat(5, coach.getHeadCoachChecking());
                coachQuery.setFloat(6, coach.getHeadCoachSaving());
                coachQuery.setInt(7, league.getLeagueId());
                ResultSet coachResult = coachQuery.executeQuery();
                if (coachResult.next()) {
                    coach.setHeadCoachId(coachResult.getInt("headCoachId"));
                    coach.setHeadCoachName(coachResult.getString("headCoachName"));
                    coach.setHeadCoachSkating(coachResult.getFloat("headCoachSkating"));
                    coach.setHeadCoachSkating(coachResult.getFloat("headCoachShooting"));
                    coach.setHeadCoachSkating(coachResult.getFloat("headCoachChecking"));
                    coach.setHeadCoachSkating(coachResult.getFloat("headCoachSaving"));
                }
            }
        }

        private void saveFreeAgents(ILeague league) throws SQLException {
            ArrayList<IPlayer> freeAgents = league.getFreeAgents();
            for (IPlayer freeAgent : freeAgents) {
                String procedureCall = "call SP_FREEAGENT_INSERT(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                CallableStatement freeAgentQuery = this.connection.con.prepareCall(procedureCall);
                freeAgentQuery.setInt(1, freeAgent.getPlayerId());
                freeAgentQuery.setString(2, freeAgent.getPlayerName());
                freeAgentQuery.setString(3, freeAgent.getPlayerPosition());
                freeAgentQuery.setInt(4, freeAgent.getPlayerAgeYear());
                freeAgentQuery.setInt(5, freeAgent.getPlayerAgeDays());
                freeAgentQuery.setInt(6, freeAgent.getPlayerSkating());
                freeAgentQuery.setInt(7, freeAgent.getPlayerShooting());
                freeAgentQuery.setInt(8, freeAgent.getPlayerChecking());
                freeAgentQuery.setInt(9, freeAgent.getPlayerSaving());
                freeAgentQuery.setBoolean(10, freeAgent.wasPlayerInjured());
                freeAgentQuery.setBoolean(11, freeAgent.isPlayerInjured());
                freeAgentQuery.setDate(12, freeAgent.getRecoveryDate());
                freeAgentQuery.setInt(13, league.getLeagueId());
                ResultSet freeAgentResult = freeAgentQuery.executeQuery();
                if (freeAgentResult.next()) {
                    freeAgent.setPlayerId(freeAgentResult.getInt("playerId"));
                    freeAgent.setPlayerName(freeAgentResult.getString("playerName"));
                    freeAgent.setPlayerPosition(freeAgentResult.getString("playerPosition"));
                    freeAgent.setPlayerAgeYear(freeAgentResult.getInt("playerAgeYear"));
                    freeAgent.setPlayerAgeDays(freeAgentResult.getInt("playerAgeDays"));
                    freeAgent.setPlayerSkating(freeAgentResult.getInt("playerSkating"));
                    freeAgent.setPlayerSkating(freeAgentResult.getInt("playerShooting"));
                    freeAgent.setPlayerSkating(freeAgentResult.getInt("playerChecking"));
                    freeAgent.setPlayerSkating(freeAgentResult.getInt("playerSaving"));
                    freeAgent.setPlayerWasInjured(freeAgentResult.getBoolean("wasInjured"));
                    freeAgent.setPlayerIsInjured(freeAgentResult.getBoolean("isInjured"));
                    freeAgent.setRecoveryDate(freeAgentResult.getDate("recoveryDate"));
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
            if (leaguesList.next()) {
                ILeague league = new League();
                league.setLeagueId(leaguesList.getInt("leagueId"));
                league.setLeagueName(leaguesList.getString("leagueName"));
                game.getLeagues().get(0).setLeagueId(leaguesList.getInt("leagueId"));
                // addLeague(league);
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            connection.closeConnection();
            System.exit(1);
        } finally {
            try {
                connection.closeConnection();
            } catch (Exception ignored) {
            }
        }

    }
}
