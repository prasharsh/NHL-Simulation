package g4dhl;

import java.util.ArrayList;

public interface ITeam {

	int getTeamId();

	String getTeamName();

	String getTeamCreatedBy();

	int getLossPointCount();

	double getTeamStrength();

	boolean setTeamId(int teamId);

	boolean setTeamName(String teamName);

	boolean setTeamCreatedBy(String teamCreatedBy);

	boolean setLossPointCount(int lossPointCount);

	IGeneralManager getGeneralManager();

	boolean setGeneralManager(IGeneralManager generalManager);

	IHeadCoach getHeadCoach();

	boolean setHeadCoach(IHeadCoach headCoach);

	ArrayList<IPlayer> getPlayers();

	int getPlayersCount();

	int getGoaliesCount();

	int getSkatersCount();

	ArrayList<IInjuredPlayer> getInjuredPlayers();

	boolean addPlayer(IPlayer player);

	boolean addInjuredPlayer(IInjuredPlayer injuredPlayer);

	IPlayer removePlayer(IPlayer player);

	boolean removeInjuredPlayer(IInjuredPlayer injuredPlayer);

}
