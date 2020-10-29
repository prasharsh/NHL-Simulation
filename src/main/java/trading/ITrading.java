package trading;

import g4dhl.ILeague;
import g4dhl.ITeam;
import java.util.ArrayList;

public interface ITrading {
    void startTrading(g4dhl.ITrading trading , ILeague league, ArrayList<ITeam> teams);
}
