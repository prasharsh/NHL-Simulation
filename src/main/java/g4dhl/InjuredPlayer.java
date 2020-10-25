package g4dhl;

import java.sql.Date;

public class InjuredPlayer implements IInjuredPlayer {

	private int injuredId;
	private int teamId;
	private int playerId;
	private Date recoveryDate;

	@Override
	public int getInjuredId() {
		return injuredId;
	}

	@Override
	public int getTeamId() {
		return teamId;
	}

	@Override
	public int getPlayerId() {
		return playerId;
	}

	@Override
	public Date getRecoveryDate() {
		return recoveryDate;
	}

	@Override
	public boolean setInjuredId(int injuredId) {
		this.injuredId = injuredId;
		return true;
	}

	@Override
	public boolean setTeamId(int teamId) {
		this.teamId = teamId;
		return true;
	}

	@Override
	public boolean setPlayerId(int playerId) {
		this.playerId = playerId;
		return true;
	}

	@Override
	public boolean setRecoveryDate(Date recoveryDate) {
		this.recoveryDate = recoveryDate;
		return true;
	}

}
