package leagueModel;

import java.util.ArrayList;

public interface ITeam {
    String getTeamName();
    void setTeamName(String teamName);

    IGeneralManager getGeneralManager();
    void setGeneralManager(IGeneralManager generalManager);

    IHeadCoach getHeadCoach();
    void setHeadCoach(IHeadCoach headCoach);

    ArrayList<IPlayer> getPlayers();
    void setPlayers(ArrayList<IPlayer> players);
}
