package g4dhl;

import java.sql.Date;

public interface IInjuredPlayer {
	int getInjuredId();

	int getTeamId();

	int getPlayerId();

	Date getRecoveryDate();

	boolean setInjuredId(int injuredId);

	boolean setTeamId(int teamId);

	boolean setPlayerId(int playerId);

	boolean setRecoveryDate(Date recoveryDate);
}
