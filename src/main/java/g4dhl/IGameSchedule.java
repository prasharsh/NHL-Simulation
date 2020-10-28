package g4dhl;

import java.sql.Date;

public interface IGameSchedule {

	public int getGameScheduleId();
	public void setGameScheduleId(int gameScheduleId);
	public int getLeagueId();
	public void setLeagueId(int leagueId);
	public int getSeason() ;
	public void setSeason(int season) ;
	public ITeam getTeamA() ;
	public void setTeamA(ITeam teamA) ;
	public ITeam getTeamB();
	public void setTeamB(ITeam teamB) ;
	public Date getMatchDate() ;
	public void setMatchDate(Date matchDate); 
	public int getWinningTeam();
	public void setWinningTeam(int winningTeam);
	public int getLossingTeam();
	public void setLossingTeam(int lossingTeam);
	public String getGameType();
	public void setGameType(String gameType) ;
	public String getStatus();
	public void setStatus(String status);
}
