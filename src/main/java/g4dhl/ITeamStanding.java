package g4dhl;

public interface ITeamStanding {

	public void setTeamId(int teamId);
	public ITeam getTeam();
	public void setTeam(ITeam team);
	public String getDivisionName();
	public void setDivisionName(String divisionName);
	public String getConferenceName() ;
	public void setConferenceName(String conferenceName);
	public int getGamesPlayed();
	public void setGamesPlayed(int gamesPlayed);
	public int getGamesWon();
	public void setGamesWon(int gamesWon);
	public int getGamesLost();
	public void setGamesLost(int gamesLost);
	public int getTotalPoints();
	public void setTotalPoints(int totalPoints);
	
}
