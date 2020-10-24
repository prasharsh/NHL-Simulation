package com.dal.dhl.states;

import com.dal.dhl.stateMachine.DHLStateMachine;

public class InjuryCheck implements IStateTransistion {
	DHLStateMachine stateMachine;

	public InjuryCheck(DHLStateMachine stateMachine) {

		this.stateMachine = stateMachine;
	}

	@Override
	public void entry() {
		// check both teams players for injury
//		Game game = stateMachine.getGame();
//		Date currentDate = game.getLeagues().get(0).getCurrentDate();
//		IInjury injuryChance = new Injury();
//		float randomInjuryChance = injuryChance.getRandomInjuryChance();
//
//		ITeam team = game.getLeagues().get(0).getConferences().get(0).getDivisions().get(0).getTeams().get(0);
//
//		ArrayList<IPlayer> players = team.getPlayers();
//		for (IPlayer player : players) {
//			if (player.checkPlayerInjury(randomInjuryChance)) {
//				Date recoveryDate = injuryChance.getRecoveryDate(currentDate);
//				IInjuredPlayer injuredPlayer = new InjuredPlayer();
//				injuredPlayer.setPlayerId(player.getPlayerId());
//				injuredPlayer.setTeamId(team.getTeamId());
//				injuredPlayer.setRecoveryDate(recoveryDate);
//				team.addInjuredPlayer(injuredPlayer);
//			}
//		}
//		ArrayList<IInjuredPlayer> injuredPlayers = team.getInjuredPlayers();
//		for (IInjuredPlayer injuredPlayer : injuredPlayers) {
//			if (currentDate.compareTo(injuredPlayer.getRecoveryDate()) == 0) {
//				team.removeInjuredPlayer(injuredPlayer);
//			}
//		}
		stateMachine.setCurrState(stateMachine.getAdvanceTime());
		stateMachine.getCurrState().doTask();
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doTask() {
		// TODO Auto-generated method stub

	}

}
