package com.datamodeltest.leaguedatamodeltest;
import java.util.ArrayList;
import java.util.List;

import com.datamodel.gameplayconfig.GamePlayConfigAbstractFactory;
import com.datamodel.gameplayconfig.GamePlayConfigFactory;
import com.datamodel.leaguedatamodel.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TeamTest {

	private LeagueDataModelAbstractFactory leagueDataModelAbstractFactory = LeagueDataModelAbstractFactory.instance();
	private ITeam team;

	@BeforeClass
	public static void createTeam(){
		LeagueDataModelAbstractFactory.setFactory(new LeagueDataModelFactoryTest());
	}

	@Before
	public void loadTeam(){
		ITeam team = leagueDataModelAbstractFactory.createTeam();
		team.setTeamName("Boston");
		this.team = team;
	}

	private List<IPlayer> createTwoPlayers(){
		List<IPlayer> players = new ArrayList<>();
		IPlayer player1 = new Player();
		player1.setPlayerName("Rob");
		IPlayer player2 = new Player();
		player2.setPlayerName("Hawkey");
		players.add(player1);
		players.add(player2);
		return players;
	}

	@Test
	public void getTeamNameTest() {
		Assert.assertEquals("Boston", team.getTeamName());
	}

	@Test
	public void getTeamCreatedByTest() {
		team.setTeamCreatedBy("user");
		Assert.assertEquals("user", team.getTeamCreatedBy());
	}

	@Test
	public void getPlayersWhenNoPlayersTest() {
		Assert.assertEquals(new ArrayList<IPlayer>(), team.getPlayers());
	}

	@Test
	public void addNullPlayerTest() {
		Assert.assertFalse("Player cannot be null", team.addPlayer(null));
	}

	@Test
	public void addPlayerWithNullPlayerNameTest() {
		IPlayer player = new Player();
		player.setPlayerName(null);
		Assert.assertFalse("Player with null player name cannot not be inserted", team.addPlayer(player));
	}

	@Test
	public void addPlayerWithEmptyPlayerNameTest() {
		IPlayer player = new Player();
		player.setPlayerName("");
		Assert.assertFalse("Player with empty player name cannot not be inserted", team.addPlayer(player));
	}

	@Test
	public void setTeamNameTest() {
		Assert.assertEquals("Boston", team.getTeamName());
	}

	@Test
	public void addSinglePlayerTest() {
		IPlayer player = new Player();
		player.setPlayerName("Rob");
		team.addPlayer(player);
		Assert.assertEquals(player, team.getPlayers().get(0));
	}

	@Test
	public void addMultiplePlayersTest() {
		List<IPlayer> players = createTwoPlayers();
		team.addPlayer(players.get(0));
		team.addPlayer(players.get(1));
		Assert.assertEquals(players, team.getPlayers());
	}

	@Test
	public void removePlayer() {
		IPlayer player = new Player();
		player.setPlayerName("Rob");
		team.addPlayer(player);
		Assert.assertEquals(player, team.removePlayer(player));
	}

	@Test
	public void setGeneralManagerTest() {
		IGeneralManager generalManager = new GeneralManager();
		team.setGeneralManager(generalManager);
		Assert.assertEquals(generalManager, team.getGeneralManager());
	}

	@Test
	public void setHeadCoachTest() {
		Team team = new Team();
		IHeadCoach headCoach = new HeadCoach();
		team.setHeadCoach(headCoach);
		Assert.assertEquals(headCoach, team.getHeadCoach());
	}

	@Test
	public void getTeamStrengthTest() {
		List<IPlayer> players = createTwoPlayers();
		IPlayer player1 = players.get(0);
		player1.setPlayerPosition("defense");
		player1.setPlayerShooting(10);
		player1.setPlayerSkating(16);
		player1.setPlayerChecking(14);
		player1.setPlayerSaving(18);
		IPlayer player2 = players.get(1);
		player2.setPlayerPosition("forward");
		player2.setPlayerShooting(10);
		player2.setPlayerSkating(16);
		player2.setPlayerChecking(14);
		player2.setPlayerSaving(15);
		team.addPlayer(player1);
		team.addPlayer(player2);
		double teamStrength = player1.getPlayerStrength() + player2.getPlayerStrength();
		Assert.assertEquals(teamStrength, team.getTeamStrength(), 0.0);
	}

	@Test
	public void getPlayerTest(){
		IPlayer player = new Player();
		player.setPlayerName("Player1");
		team.addPlayer(player);
		Assert.assertEquals(player, team.getPlayer(0));
	}

	@Test
	public void getPlayersTest() {
		List<IPlayer> players = createTwoPlayers();
		team.addPlayer(players.get(0));
		team.addPlayer(players.get(1));
		Assert.assertEquals(players, team.getPlayers());
	}

	@Test
	public void proposeTradeTest(){
		ITrading trading = new TradingMock();
		team.proposeTrade(trading);
		Assert.assertEquals(team, trading.getOfferingTeam());
	}

	@Test
	public void getTradingGainTest(){
		Assert.assertEquals(0.0, team.getTradingGain(0 ,0 , 0, 0), 0.0);
	}

	@Test
	public void getTeamGainByStatTest(){
		Assert.assertEquals(-2.0, team.getTeamGainByStat(-2, 1, 0), 0.0);
		Assert.assertEquals(2.0, team.getTeamGainByStat(2, 1, 2), 0.0);
	}

	@Test(expected = Exception.class)
	public void getFreeAgentsHiredAfterTradeTestForExpectedException() throws Exception{
		LeagueMock leagueMock = new LeagueMock();
		ILeague league = leagueMock.league;
		team = league.getAllTeams().get(0);
		List<IPlayer> players = new ArrayList<>();
		players.add(team.getPlayer(0));
		players.add(team.getPlayer(1));
		List<IPlayer> freeAgents = new ArrayList<>(league.getFreeAgents());
		for (IPlayer freeAgent: freeAgents){
			league.removeFreeAgent(freeAgent);
		}
		List<IPlayer> hiredFreeAgents = team.getFreeAgentsHiredAfterTrade(players, league);
		Assert.assertEquals(players.size(), hiredFreeAgents.size());
	}

	@Test
	public void getFreeAgentsHiredAfterTradeTestWithNoException() throws Exception{
		GamePlayConfigAbstractFactory.setFactory(new GamePlayConfigFactory());
		LeagueDataModelAbstractFactory.setFactory(new LeagueDataModelFactoryTest());
		new LeagueMock();
		ILeague league = LeagueDataModelAbstractFactory.instance().createLeague();
		team = league.getAllTeams().get(0);
		List<IPlayer> players = new ArrayList<>();
		players.add(team.getPlayer(0));
		players.add(team.getPlayer(1));
		List<IPlayer> hiredFreeAgents = team.getFreeAgentsHiredAfterTrade(players, league);
		Assert.assertEquals(players.size(), hiredFreeAgents.size());
	}

	@Test
	public void completeRosterTest(){
		GamePlayConfigAbstractFactory.setFactory(new GamePlayConfigFactory());
		LeagueDataModelAbstractFactory.setFactory(new LeagueDataModelFactoryTest());
		new LeagueMock();
		ILeague league = LeagueDataModelAbstractFactory.instance().createLeague();
		team = league.getAllTeams().get(0);
		int noOfPlayers = team.getPlayers().size();
		List<IPlayer> players = createTwoPlayers();
		IPlayer player1 = players.get(0);
		player1.setPlayerPosition("defense");
		player1.setPlayerShooting(10);
		player1.setPlayerSkating(16);
		player1.setPlayerChecking(14);
		player1.setPlayerSaving(18);
		IPlayer player2 = players.get(1);
		player2.setPlayerPosition("goalie");
		player2.setPlayerShooting(10);
		player2.setPlayerSkating(16);
		player2.setPlayerChecking(14);
		player2.setPlayerSaving(15);
		team.addPlayer(player1);
		team.addPlayer(player2);
		team.removePlayer(team.getPlayers().get(0));
		team.completeRoster(league);
		Assert.assertEquals(noOfPlayers, team.getPlayers().size());
	}

	@Test
	public void hireStrongestPlayersFromFreeAgentListTest(){
		GamePlayConfigAbstractFactory.setFactory(new GamePlayConfigFactory());
		LeagueDataModelAbstractFactory.setFactory(new LeagueDataModelFactoryTest());
		new LeagueMock();
		ILeague league = LeagueDataModelAbstractFactory.instance().createLeague();
		team = league.getAllTeams().get(0);
		List<IPlayer> players = team.getPlayers();
		int noOfPlayers = players.size();
		List<IPlayer> freeAgents = league.getFreeAgents();
		int noOfFreeAgents = freeAgents.size();
		team.hireStrongestPlayersFromFreeAgentList(league, "forward", 2);
		Assert.assertEquals(noOfPlayers+2, players.size());
		Assert.assertEquals(noOfFreeAgents-2, freeAgents.size());
	}

	@Test
	public void dropWeakestPlayersToFreeAgentListTest(){
		GamePlayConfigAbstractFactory.setFactory(new GamePlayConfigFactory());
		LeagueDataModelAbstractFactory.setFactory(new LeagueDataModelFactoryTest());
		new LeagueMock();
		ILeague league = LeagueDataModelAbstractFactory.instance().createLeague();
		team = league.getAllTeams().get(0);
		List<IPlayer> players = team.getPlayers();
		int noOfPlayers = players.size();
		List<IPlayer> freeAgents = league.getFreeAgents();
		int noOfFreeAgents = freeAgents.size();
		team.dropWeakestPlayersToFreeAgentList(league, "forward", 2);
		Assert.assertEquals(noOfPlayers-2, players.size());
		Assert.assertEquals(noOfFreeAgents+2, freeAgents.size());
	}

	@Test
	public void getActiveWeakestPlayersTest(){
		List<IPlayer> players = createTwoPlayers();
		IPlayer player1 = players.get(0);
		player1.setPlayerPosition("forward");
		player1.setPlayerShooting(12);
		player1.setPlayerSkating(18);
		player1.setPlayerChecking(15);
		player1.setPlayerSaving(16);
		IPlayer player2 = players.get(1);
		player2.setPlayerPosition("forward");
		player2.setPlayerShooting(10);
		player2.setPlayerSkating(16);
		player2.setPlayerChecking(14);
		player2.setPlayerSaving(15);
		team.addPlayer(players.get(0));
		team.addPlayer(players.get(1));
		List<IPlayer> weakestPlayers = team.getActiveWeakestPlayers("forward");
		Assert.assertEquals(players.get(1), weakestPlayers.get(0));
		Assert.assertEquals(players.get(0), weakestPlayers.get(1));
	}

	@Test
	public void getActivePlayersCountWithPositionTest(){
		List<IPlayer> players = createTwoPlayers();
		IPlayer player1 = players.get(0);
		player1.setPlayerPosition("forward");
		IPlayer player2 = players.get(1);
		player2.setPlayerPosition("defense");
		Assert.assertEquals(1, team.getActivePlayersCountWithPosition(players ,"forward"));
	}

	@Test
	public void getStrongestPlayersByStrengthTest(){
		List<IPlayer> players = createTwoPlayers();
		IPlayer player1 = players.get(0);
		player1.setPlayerPosition("forward");
		player1.setPlayerShooting(10);
		player1.setPlayerSkating(16);
		player1.setPlayerChecking(14);
		player1.setPlayerSaving(15);
		IPlayer player2 = players.get(1);
		player2.setPlayerPosition("forward");
		player2.setPlayerShooting(12);
		player2.setPlayerSkating(18);
		player2.setPlayerChecking(15);
		player2.setPlayerSaving(16);
		List<IPlayer> strongestPlayers = team.getStrongestPlayersByStrength(players);
		Assert.assertEquals(players.get(1), strongestPlayers.get(0));
		Assert.assertEquals(players.get(0), strongestPlayers.get(1));
	}

	@Test
	public void getActivePlayersWithPositionTest(){
		List<IPlayer> players = createTwoPlayers();
		IPlayer player1 = players.get(0);
		player1.setPlayerPosition("forward");
		IPlayer player2 = players.get(1);
		player2.setPlayerPosition("forward");
		Assert.assertEquals(players, team.getActivePlayersWithPosition(players ,"forward"));
	}
}
