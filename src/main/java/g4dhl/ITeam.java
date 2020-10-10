package g4dhl;

import java.util.ArrayList;

public interface ITeam {

    int getTeamId();
    String getTeamName();

    void setTeamId(int teamId);
    void setTeamName(String teamName);

    IGeneralManager getGeneralManager();
    void setGeneralManager(IGeneralManager generalManager);

    IHeadCoach getHeadCoach();
    void setHeadCoach(IHeadCoach headCoach);

    ArrayList<IPlayer> getPlayers();
    IPlayer getPlayer(int index);

    void addPlayer(IPlayer player);
    void setPlayers(ArrayList<IPlayer> players);
}
