package g4dhl;

import java.util.ArrayList;

public interface ILeague {

	int getLeagueId();

	String getLeagueName();

	boolean setLeagueId(int leagueId);

	boolean setLeagueName(String leagueName);

	ArrayList<IConference> getConferences();

	boolean addConference(IConference conference);

	ArrayList<IFreeAgent> getFreeAgents();

	boolean addFreeAgent(IFreeAgent freeAgent);

	ArrayList<IGeneralManager> getManagers();

	boolean setManager(IGeneralManager manager);

	ArrayList<IHeadCoach> getCoaches();

	boolean setCoach(IHeadCoach coach);

//    IGameplayConfig getGamePlayConfig();
//    boolean setGamePlayConfig(IGameplayConfig gameplayConfig);
}
