package g4dhl;

import java.sql.Date;

public class GameSchedule {

	private int gameScheduleId;
	private int leagueId;
	private int season;
	private int teamA;
	private int teamB;
	private Date matchDate;
	private int winningTeam;
	private int lossingTeam;
	private String gameType;
	
	
	public int getGameScheduleId() {
		return gameScheduleId;
	}
	public void setGameScheduleId(int gameScheduleId) {
		this.gameScheduleId = gameScheduleId;
	}
	public GameSchedule() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getLeagueId() {
		return leagueId;
	}
	public void setLeagueId(int leagueId) {
		this.leagueId = leagueId;
	}
	public int getSeason() {
		return season;
	}
	public void setSeason(int season) {
		this.season = season;
	}
	public int getTeamA() {
		return teamA;
	}
	public void setTeamA(int teamA) {
		this.teamA = teamA;
	}
	public int getTeamB() {
		return teamB;
	}
	public void setTeamB(int teamB) {
		this.teamB = teamB;
	}
	public Date getMatchDate() {
		return matchDate;
	}
	public void setMatchDate(Date matchDate) {
		this.matchDate = matchDate;
	}
	public int getWinningTeam() {
		return winningTeam;
	}
	public void setWinningTeam(int winningTeam) {
		this.winningTeam = winningTeam;
	}
	public int getLossingTeam() {
		return lossingTeam;
	}
	public void setLossingTeam(int lossingTeam) {
		this.lossingTeam = lossingTeam;
	}
	public String getGameType() {
		return gameType;
	}
	public void setGameType(String gameType) {
		this.gameType = gameType;
	}
	@Override
	public String toString() {
		return "GameSchedule [gameScheduleId=" + gameScheduleId + ", leagueId=" + leagueId + ", season=" + season
				+ ", teamA=" + teamA + ", teamB=" + teamB + ", matchDate=" + matchDate + ", winningTeam=" + winningTeam
				+ ", lossingTeam=" + lossingTeam + ", gameType=" + gameType + "]";
	}


}
