package com.datamodel.leaguedatamodel;
import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.inputoutputmodel.IPropertyLoader;
import com.inputoutputmodel.PropertyLoader;
import com.statemachine.IStateMachine;

public class GameSchedule implements IGameSchedule {

	private int gameScheduleId;
	private int leagueId;
	private int season;
	private ITeam teamA;
	private ITeam teamB;
	private Date matchDate;
	private int winningTeam;
	private int lossingTeam;
	private String gameType;
	private String status;
	private HashMap<ITeam, HashSet<Date>> teamScheduledMatches;
	private ArrayList<IGameSchedule> gameScheduleList;
	private ArrayList<ITeam> totalTeamList;
	private ArrayList<ITeamStanding> teamStandingList;
	int gameScheduleCounter;
	int gamePerTeam;
    private int goalsPerGame = 0;
    private int penaltiesPerGame = 0;
    private int shots = 0;
    private int saves = 0;
	private String PLAYOFF_START_DATE = "playoffStartDate";
	private String PLAYOFF_END_DATE = "playoffEndDate";
	private String GAME_TYPE_PLAYOFF = "PlayOffs";
	private String REGULAR_SEASON_END_DATE = "seasonEndDate";
	private String GAME_TYPE_REGULAR = "Regular";
	private String GAME_SCHEDULED = "scheduled";
	ITimeConcept timeConcept;

	@Override
	public int getGameScheduleId() {
		return gameScheduleId;
	}

	@Override
	public void setGameScheduleId(int gameScheduleId) {
		this.gameScheduleId = gameScheduleId;
	}

	public GameSchedule() {
		super();
	}

	@Override
	public int getLeagueId() {
		return leagueId;
	}

	@Override
	public void setLeagueId(int leagueId) {
		this.leagueId = leagueId;
	}

	@Override
	public int getSeason() {
		return season;
	}

	@Override
	public void setSeason(int season) {
		this.season = season;
	}

	@Override
	public ITeam getTeamA() {
		return teamA;
	}

	@Override
	public void setTeamA(ITeam teamA) {
		this.teamA = teamA;
	}

	@Override
	public ITeam getTeamB() {
		return teamB;
	}

	@Override
	public void setTeamB(ITeam teamB) {
		this.teamB = teamB;
	}

	@Override
	public Date getMatchDate() {
		return matchDate;
	}

	@Override
	public void setMatchDate(Date matchDate) {
		this.matchDate = matchDate;
	}

	@Override
	public int getWinningTeam() {
		return winningTeam;
	}

	@Override
	public void setWinningTeam(int winningTeam) {
		this.winningTeam = winningTeam;
	}

	@Override
	public int getLossingTeam() {
		return lossingTeam;
	}

	@Override
	public void setLossingTeam(int lossingTeam) {
		this.lossingTeam = lossingTeam;
	}

	@Override
	public String getGameType() {
		return gameType;
	}

	@Override
	public void setGameType(String gameType) {
		this.gameType = gameType;
	}
	

	@Override
    public int getGoalsPerGame() {
		return goalsPerGame;
	}

	@Override
	public void setGoalsPerGame(int goalsPerGame) {
		this.goalsPerGame = goalsPerGame;
	}

	@Override
	public int getPenaltiesPerGame() {
		return penaltiesPerGame;
	}

	@Override
	public void setPenaltiesPerGame(int penaltiesPerGame) {
		this.penaltiesPerGame = penaltiesPerGame;
	}

	@Override
	public int getShots() {
		return shots;
	}

	@Override
	public void setShots(int shots) {
		this.shots = shots;
	}

	@Override
	public int getSaves() {
		return saves;
	}

	@Override
	public void setSaves(int saves) {
		this.saves = saves;
	}

	@Override
	public HashMap<String, Double> getScheduledGamesAverageStats(List<IGameSchedule> gameSchedules){
		
		HashMap<String, Double> averageStats = new HashMap<>();
		
		double gameCount = 0;
		double totalGoalsPerGame = 0;
		double totalPenaltiesPerGame = 0;
		double totalShots = 0;
		double totalSaves = 0;
		double avgGoalsPerGame = 0;
		double avgPenaltiesPerGame = 0;
		double avgShots = 0;
		double avgSaves = 0;
		
		for (IGameSchedule game : gameSchedules) {
			gameCount += 1;
			totalGoalsPerGame += game.getGoalsPerGame();
			totalPenaltiesPerGame += game.getPenaltiesPerGame();
			totalSaves += game.getSaves();
			totalShots += game.getShots();
		}
		
		avgGoalsPerGame = totalGoalsPerGame/gameCount;
		avgPenaltiesPerGame = totalPenaltiesPerGame/gameCount;
		avgShots = totalShots/gameCount;
		avgSaves = totalSaves/ gameCount;
		
		averageStats.put("Goals", avgGoalsPerGame);
		averageStats.put("Penalties", avgPenaltiesPerGame);
		averageStats.put("Shots", avgShots);
		averageStats.put("Saves", avgSaves);

		return averageStats;
	}
	

	@Override
	public String toString() {
		return "GameSchedule [gameScheduleId=" + gameScheduleId + ", leagueId=" + leagueId + ", season=" + season
				+ ", teamA=" + teamA + ", teamB=" + teamB + ", matchDate=" + matchDate + ", winningTeam=" + winningTeam
				+ ", lossingTeam=" + lossingTeam + ", gameType=" + gameType + "]";
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public ArrayList<IGameSchedule> schedulePlayoff(IGame game, IStateMachine stateMachine) {
		IPropertyLoader propertyLoader = new PropertyLoader();
		teamScheduledMatches = new HashMap<>();
		gameScheduleList = new ArrayList<>();
		ILeague league = game.getLeagues().get(0);
		String[] date = league.getSimulationStartDate().toString().split("-");
		int year = Integer.parseInt(date[0]);
		Date playOffStartDate = Date.valueOf("" + (year + 1) + propertyLoader.getPropertyValue(PLAYOFF_START_DATE));
		Date playOffEndDate = Date.valueOf("" + (year + 1) + propertyLoader.getPropertyValue(PLAYOFF_END_DATE));
		LocalDate roundOneMatchDate = playOffStartDate.toLocalDate().plusDays(6)
				.with(TemporalAdjusters.nextOrSame(DayOfWeek.WEDNESDAY));
		playOffStartDate = Date.valueOf(roundOneMatchDate);
		HashMap<Integer, ITeam> playoffTeamList = new HashMap<>();
		league.getTeamStandings().sort((standing1, standing2) -> {
			double points1 = standing1.getTotalPoints();
			double points2 = standing2.getTotalPoints();
			if (points1 > points2) {
				return -1;
			} else {
				return 0;
			}
		});
		//league.setTeamStandings(new ArrayList<ITeamStanding>(league.getTeamStandings().subList(0, 10)));
		for (ITeamStanding iTeamStanding : league.getTeamStandings().subList(0, 10)) {
			playoffTeamList.put(iTeamStanding.getTotalPoints(), iTeamStanding.getTeam());
		}
		ArrayList<ITeam> teamPlayoffs = new ArrayList<>();
		Map<Integer, ITeam> sortedTeamStanding = new TreeMap<>(Collections.reverseOrder());
		sortedTeamStanding.putAll(playoffTeamList);
		for (Entry<Integer, ITeam> entry : sortedTeamStanding.entrySet()) {
			teamPlayoffs.add(entry.getValue());
		}
		for (ITeam team : teamPlayoffs) {
			for (ITeam opponentTeam : teamPlayoffs) {
				addMatchSchedule(league, team, opponentTeam, playOffStartDate, playOffEndDate, league.getCurrentDate(),
						GAME_TYPE_PLAYOFF);
			}
		}
		game.getLeagues().get(0).setGameSchedules(gameScheduleList);
		return gameScheduleList;
	}

	@Override
	public ArrayList<IGameSchedule> scheduleRegularSeason(IGame game, IStateMachine stateMachine) {
		IPropertyLoader propertyLoader = new PropertyLoader();
		teamScheduledMatches = new HashMap<>();
		gameScheduleList = new ArrayList<>();
		totalTeamList = new ArrayList<>();
		teamStandingList = new ArrayList<>();
		gameScheduleCounter = 1;
		gamePerTeam = 82;
		timeConcept = new TimeConcept();
		ILeague league = game.getLeagues().get(0);
		Date currDate = league.getCurrentDate();
		String[] date = league.getSimulationStartDate().toString().split("-");
		int year = Integer.parseInt(date[0]);
		Date regularSeasonEndDate = Date
				.valueOf("" + (year + 1) + propertyLoader.getPropertyValue(REGULAR_SEASON_END_DATE));
		for (IConference conference : league.getConferences()) {
			IConference currentConference = conference;
			for (IDivision division : conference.getDivisions()) {
				for (ITeam team : division.getTeams()) {
					LeagueDataModelAbstractFactory dataModelFactory = LeagueDataModelFactory.getNewInstance();
					ITeamStanding teamStanding = dataModelFactory.createTeamStanding();
					totalTeamList.add(team);
					teamStanding.setConferenceName(conference.getConferenceName());
					teamStanding.setDivisionName(division.getDivisionName());
					teamStanding.setTeam(team);
					teamStandingList.add(teamStanding);
				}
				IDivision currentDivision = division;
				for (ITeam team : division.getTeams()) {
					Date regularSeasonScheduleDate = currDate;
					int teamDivisionMatchesCounter = 0;
					int teamOtherDivisionMatchesCounter = 0;
					int teamOtherConferenceMatchesCounter = 0;
					while (teamDivisionMatchesCounter < (gamePerTeam / 3)) {
						for (ITeam opponentTeam : division.getTeams()) {
							if (isDifferentObject(opponentTeam, team)) {
								addMatchSchedule(league, team, opponentTeam, regularSeasonScheduleDate,
										regularSeasonEndDate, currDate, GAME_TYPE_REGULAR);
								teamDivisionMatchesCounter++;
								if (teamDivisionMatchesCounter == (gamePerTeam / 3)) {
									break;
								}
							}
						}
					}
					boolean isDivisionMatchLimitReached = false;
					while (teamOtherDivisionMatchesCounter < (gamePerTeam / 3)) {
						if (isDivisionMatchLimitReached) {
							break;
						}
						for (IDivision otherDivision : conference.getDivisions()) {
							if (isDivisionMatchLimitReached) {
								break;
							}
							if (isDifferentObject(otherDivision, currentDivision)) {
								for (ITeam opponentTeam : otherDivision.getTeams()) {
									addMatchSchedule(league, team, opponentTeam, regularSeasonScheduleDate,
											regularSeasonEndDate, currDate, GAME_TYPE_REGULAR);
									teamOtherDivisionMatchesCounter++;
									if (teamOtherDivisionMatchesCounter == (gamePerTeam / 3)) {
										isDivisionMatchLimitReached = true;
										break;
									}
								}

							}
						}
					}
					boolean isConferenceLevelMatchLimitReached = false;
					while (teamOtherConferenceMatchesCounter < (gamePerTeam / 3) + 1) {
						if (isConferenceLevelMatchLimitReached) {
							break;
						}
						for (IConference otherConference : league.getConferences()) {
							if (isConferenceLevelMatchLimitReached) {
								break;
							}
							if (currentConference.equals(otherConference)) {
								for (IDivision otherConferenceDivision : otherConference.getDivisions()) {
									if (teamOtherConferenceMatchesCounter <= (gamePerTeam / 3)) {
										for (ITeam opponentTeam : otherConferenceDivision.getTeams()) {
											addMatchSchedule(league, team, opponentTeam, regularSeasonScheduleDate,
													regularSeasonEndDate, currDate, GAME_TYPE_REGULAR);
											teamOtherConferenceMatchesCounter++;
											if (teamOtherConferenceMatchesCounter == (gamePerTeam / 3 + 1)) {
												isConferenceLevelMatchLimitReached = true;
												break;
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		stateMachine.setTeamList(totalTeamList);
		game.getLeagues().get(0).setTeamStandings(teamStandingList);
		game.getLeagues().get(0).setGameSchedules(gameScheduleList);
		return gameScheduleList;
	}

	private void addMatchSchedule(ILeague league, ITeam team, ITeam opponentTeam, Date startDate, Date endDate,
			Date currDate, String gameType) {
		LeagueDataModelAbstractFactory dataModelFactory = LeagueDataModelFactory.getNewInstance();
		IGameSchedule gameSchedule = dataModelFactory.createGameSchedule();
		gameSchedule.setLeagueId(league.getLeagueId());
		gameSchedule.setSeason(league.getSeason());
		gameSchedule.setGameType(gameType);
		gameSchedule.setStatus(GAME_SCHEDULED);
		gameSchedule.setTeamA(team);
		gameSchedule.setTeamB(opponentTeam);
		gameSchedule.setMatchDate(getGameDate(startDate, team, opponentTeam, endDate, currDate));
		gameSchedule.setGameScheduleId(gameScheduleCounter);
		gameScheduleCounter++;
		gameScheduleList.add(gameSchedule);
		addTeamDatesToDateExclusionList(team, opponentTeam, gameSchedule.getMatchDate());
	}

	private Date getGameDate(Date regularSeasonScheduleDate, ITeam team, ITeam opponentTeam, Date regularSeasonEndDate,
			Date currDate) {
		TimeConcept timeConcept = new TimeConcept();
		regularSeasonScheduleDate = timeConcept.getNextDate(regularSeasonScheduleDate);
		if (isNotNull(teamScheduledMatches)) {
			if (isNotNull(teamScheduledMatches.get(team)) && isNotNull(teamScheduledMatches.get(opponentTeam))) {
				boolean isDateNotUnique = true;
				while (isDateNotUnique) {
					if (teamScheduledMatches.get(team).contains(regularSeasonScheduleDate)
							|| teamScheduledMatches.get(opponentTeam).contains(regularSeasonScheduleDate)) {
						Date possibleDate = timeConcept.getNextDate(regularSeasonScheduleDate);
						if (possibleDate.compareTo(regularSeasonEndDate) == 0) {
							regularSeasonScheduleDate = currDate;
							isDateNotUnique = false;
						} else
							regularSeasonScheduleDate = possibleDate;
					} else {
						isDateNotUnique = false;
					}
				}
			} else if (isNotNull(teamScheduledMatches.get(team))) {
				boolean isDateNotUnique = true;
				while (isDateNotUnique) {
					if (teamScheduledMatches.get(team).contains(regularSeasonScheduleDate)) {
						Date possibleDate = timeConcept.getNextDate(regularSeasonScheduleDate);
						if (possibleDate.compareTo(regularSeasonEndDate) == 0) {
							regularSeasonScheduleDate = currDate;
						} else {
							regularSeasonScheduleDate = possibleDate;
						}
					} else {
						isDateNotUnique = false;
					}
				}
			} else if (isNotNull(teamScheduledMatches.get(team))) {
				boolean isDateNotUnique = true;
				while (isDateNotUnique) {
					if (teamScheduledMatches.get(opponentTeam).contains(regularSeasonScheduleDate)) {
						Date possibleDate = timeConcept.getNextDate(regularSeasonScheduleDate);
						if (possibleDate.compareTo(regularSeasonEndDate) == 0) {
							regularSeasonScheduleDate = currDate;
						} else {
							regularSeasonScheduleDate = possibleDate;
						}
					} else {
						isDateNotUnique = false;
					}
				}
			}
		}
		return regularSeasonScheduleDate;
	}

	private boolean isDifferentObject(Object object1, Object object2) {
		if (object1.equals(object2)) {
			return false;
		} else {
			return true;
		}
	}

	private boolean isNotNull(Object object) {
		if (object == null) {
			return false;
		} else {
			return true;
		}
	}

	private void addTeamDatesToDateExclusionList(ITeam team, ITeam opponentTeam, Date matchDate) {
		if (teamScheduledMatches.get(team) == null) {
			HashSet<Date> dates = new HashSet<>();
			dates.add(matchDate);
			teamScheduledMatches.put(team, dates);
		} else {
			teamScheduledMatches.get(team).add(matchDate);
		}
		if (teamScheduledMatches.get(opponentTeam) == null) {
			HashSet<Date> dates = new HashSet<>();
			dates.add(matchDate);
			teamScheduledMatches.put(opponentTeam, dates);
		} else {
			teamScheduledMatches.get(opponentTeam).add(matchDate);
		}
	}
}
