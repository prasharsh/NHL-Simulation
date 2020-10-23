package com.dal.dhl.LeagueSimulation;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import com.dal.dhl.stateMachine.DHLStateMachine;

import g4dhl.Game;
import g4dhl.GameSchedule;
import g4dhl.IConference;
import g4dhl.IDivision;
import g4dhl.IGameSchedule;
import g4dhl.ILeague;
import g4dhl.ITeam;

public class GameScheduler {

	HashMap<ITeam, HashSet<Date>> teamScheduledMatches = new HashMap<ITeam, HashSet<Date>>();

	public ArrayList<IGameSchedule> scheduleRegularSeason(Game game, DHLStateMachine stateMachine) {
		ArrayList<IGameSchedule> gameScheduleList = new ArrayList<>();
		ArrayList<ITeam> totalTeamList = new ArrayList<ITeam>();
		int gameScheduleCounter=1;
		int gamePerTeam = 82;
		TimeConcept timeConcept = new TimeConcept();
		ILeague league = game.getLeagues().get(0);
		Date currDate = league.getCurrentDate();
		String str="2021-04-01";
		Date regularSeasonEndDate = Date.valueOf(str);
		for (IConference conference : league.getConferences()) {
			int currentConferenceID = conference.getConferenceId();
			for (IDivision division : conference.getDivisions()) {

				for (ITeam team : division.getTeams()) {
					totalTeamList.add(team);
				}
				int currentDivisionId = division.getDivisionId();
				for (ITeam team : division.getTeams()) {
					Date regularSeasonScheduleDate = currDate;
					int teamDivisionMatchesCounter =0;
					int teamOtherDivisionMatchesCounter =0;
					int teamOtherConferenceMatchesCounter =0;
					while(teamDivisionMatchesCounter<(gamePerTeam/3)) {
						for (ITeam opponentTeam : division.getTeams()) {
							if(opponentTeam.getTeamId()!= team.getTeamId()) {
								IGameSchedule gameSchedule = new GameSchedule();
								gameSchedule.setLeagueId(league.getLeagueId());
								gameSchedule.setSeason(1);
								gameSchedule.setGameType("Regular");
								gameSchedule.setTeamA(team);
								gameSchedule.setTeamB(opponentTeam);
								regularSeasonScheduleDate = timeConcept.getNextDate(regularSeasonScheduleDate);
								regularSeasonScheduleDate = timeConcept.getNextDate(regularSeasonScheduleDate);
								gameSchedule.setMatchDate(regularSeasonScheduleDate);
								teamDivisionMatchesCounter++;
								gameSchedule.setGameScheduleId(gameScheduleCounter);
								gameScheduleCounter++;
								gameScheduleList.add(gameSchedule);
								addTeamDatesToDateExclusionList(team, opponentTeam, gameSchedule.getMatchDate());
								if(teamDivisionMatchesCounter==(gamePerTeam/3)) {
									break;
								}
							}
						}
					}
					// iterating till the team gets to play 1/3 of the matches with team from the other divisions than theirs
					boolean isDivisionMatchLimitReached = false;
					while(teamOtherDivisionMatchesCounter<(gamePerTeam/3)) {
						if(isDivisionMatchLimitReached) {
							break;
						}
						for (IDivision otherDivision : conference.getDivisions()) {
							if(isDivisionMatchLimitReached) {
								break;
							}
							if(otherDivision.getDivisionId()!= currentDivisionId ) {
								for (ITeam opponentTeam : otherDivision.getTeams()) {
									IGameSchedule gameSchedule = new GameSchedule();
									gameSchedule.setLeagueId(league.getLeagueId());
									gameSchedule.setSeason(1);
									gameSchedule.setGameType("Regular");
									gameSchedule.setTeamA(team);
									gameSchedule.setTeamB(opponentTeam);
									regularSeasonScheduleDate = timeConcept.getNextDate(regularSeasonScheduleDate);
									gameSchedule.setMatchDate(regularSeasonScheduleDate);
									regularSeasonScheduleDate = timeConcept.getNextDate(regularSeasonScheduleDate);
									teamOtherDivisionMatchesCounter++;
									gameSchedule.setGameScheduleId(gameScheduleCounter);
									gameScheduleCounter++;
									gameScheduleList.add(gameSchedule);
									addTeamDatesToDateExclusionList(team, opponentTeam, gameSchedule.getMatchDate());
									if(teamOtherDivisionMatchesCounter==(gamePerTeam/3)) {
										isDivisionMatchLimitReached = true;
										break;
									}	
								}

							}
						}
					}
					boolean isConferenceLevelMatchLimitReached = false;
					while(teamOtherConferenceMatchesCounter<(gamePerTeam/3)+1) {
						if(isConferenceLevelMatchLimitReached) {
							break;
						}
						for (IConference otherConference : league.getConferences()) {
							if(isConferenceLevelMatchLimitReached) {
								break;
							}
							if(currentConferenceID!= otherConference.getConferenceId()) {
								for (IDivision otherConferenceDivision : otherConference.getDivisions()) {
									if(teamOtherConferenceMatchesCounter<(gamePerTeam/3)) {
										for (ITeam opponentTeam : otherConferenceDivision.getTeams()) {
											IGameSchedule gameSchedule = new GameSchedule();
											gameSchedule.setLeagueId(league.getLeagueId());
											gameSchedule.setSeason(1);
											gameSchedule.setGameType("Regular");
											gameSchedule.setTeamA(team);
											gameSchedule.setTeamB(opponentTeam);
											gameSchedule.setMatchDate(getGameDate(regularSeasonScheduleDate, team, opponentTeam, regularSeasonEndDate, currDate));
											regularSeasonScheduleDate = timeConcept.getNextDate(regularSeasonScheduleDate);
											teamOtherConferenceMatchesCounter++;
											gameSchedule.setGameScheduleId(gameScheduleCounter);
											gameScheduleCounter++;
											gameScheduleList.add(gameSchedule);
											addTeamDatesToDateExclusionList(team, opponentTeam, gameSchedule.getMatchDate());
											if(teamOtherConferenceMatchesCounter==(gamePerTeam/3+1)) {
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
		return gameScheduleList;
	}

	private Date getGameDate(Date regularSeasonScheduleDate, ITeam team, ITeam opponentTeam, Date regularSeasonEndDate, Date currDate) {
		TimeConcept timeConcept = new TimeConcept();
		regularSeasonScheduleDate = timeConcept.getNextDate(regularSeasonScheduleDate);
		if(teamScheduledMatches.get(team)!=null && teamScheduledMatches.get(opponentTeam)!=null) {
			boolean isDateNotUnique = true;
			while(isDateNotUnique) {
				if(teamScheduledMatches.get(team).contains(regularSeasonScheduleDate) || teamScheduledMatches.get(opponentTeam).contains(regularSeasonScheduleDate)) {
					Date possibleDate = timeConcept.getNextDate(regularSeasonScheduleDate);
					if(possibleDate.compareTo(regularSeasonEndDate)==0) {
						regularSeasonScheduleDate = currDate;
					}
					else 
						regularSeasonScheduleDate = possibleDate;

				}
				else {
					isDateNotUnique = false;
				}
			}

		}
		else if(teamScheduledMatches.get(team)!=null ) {
			boolean isDateNotUnique = true;
			while(isDateNotUnique) {
				if(teamScheduledMatches.get(team).contains(regularSeasonScheduleDate) ) {
					Date possibleDate = timeConcept.getNextDate(regularSeasonScheduleDate);
					if(possibleDate.compareTo(regularSeasonEndDate)==0) {
						regularSeasonScheduleDate = currDate;
					}
					else 
						regularSeasonScheduleDate = possibleDate;
				}
				else {
					isDateNotUnique = false;
				}
			}

		}
		else if(teamScheduledMatches.get(team)!=null) {
			boolean isDateNotUnique = true;
			while(isDateNotUnique) {
				if( teamScheduledMatches.get(opponentTeam).contains(regularSeasonScheduleDate)) {
					Date possibleDate = timeConcept.getNextDate(regularSeasonScheduleDate);
					if(possibleDate.compareTo(regularSeasonEndDate)==0) {
						regularSeasonScheduleDate = currDate;
					}
					else 
						regularSeasonScheduleDate = possibleDate;
				}
				else {
					isDateNotUnique = false;
				}
			}

		}
		return regularSeasonScheduleDate;
	}

	private void addTeamDatesToDateExclusionList(ITeam team, ITeam opponentTeam, Date matchDate) {
		if(teamScheduledMatches.get(team) != null) {
			teamScheduledMatches.get(team).add(matchDate);
		}
		else {
			HashSet<Date> dates = new HashSet<>();
			dates.add(matchDate);
			teamScheduledMatches.put(team, dates);
		}

		if(teamScheduledMatches.get(opponentTeam) != null) {
			teamScheduledMatches.get(opponentTeam).add(matchDate);
		}
		else {
			HashSet<Date> dates = new HashSet<>();
			dates.add(matchDate);
			teamScheduledMatches.put(opponentTeam, dates);
		}

	}

}