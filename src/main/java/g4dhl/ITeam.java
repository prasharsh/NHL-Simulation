package g4dhl;

import java.util.ArrayList;

public interface ITeam {

    int getTeamId();
    String getTeamName();

    void setTeam(int id);
    void setTeamName(String teamName);

    IGeneralManager getGeneralManager();
    void setGeneralManager(IGeneralManager generalManager);

    IHeadCoach getHeadCoach();
    void setHeadCoach(IHeadCoach headCoach);

    ArrayList<IPlayer> getPlayers();
    IPlayer getPlayer(int index);

    void setPlayer(IPlayer player);
    void setPlayers(ArrayList<IPlayer> players);
}
