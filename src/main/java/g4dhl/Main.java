package g4dhl;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Main {

	/**
	 * @param args
	 * @throws IOException
	 * @throws ParseException
	 */
	public static void main(String[] args) throws IOException, ParseException {

		JSONParser parser = new JSONParser();
		Scanner scanInput = new Scanner(System.in);
		System.out.println("Enter path to JSON file: ");
//		"C:\\Users\\deepd\\OneDrive\\Desktop\\league.json" // path to file
		String filePath = scanInput.nextLine();
		scanInput.close();
		try {
			Reader reader = new FileReader(filePath);
			Object jsonObj = parser.parse(reader);
			JSONObject jsonObject = (JSONObject) jsonObj;
			JSONArray conferencesArray = (JSONArray) jsonObject.get("conferences");
			ArrayList<IConference> conferences = new ArrayList<>();
			for (int i = 0; i < conferencesArray.size(); i++) {
				JSONObject conferenceArray = (JSONObject) conferencesArray.get(i);
				JSONArray divisionsArray = (JSONArray) conferenceArray.get("divisions");
				ArrayList<IDivision> divisions = new ArrayList<>();
				for (int j = 0; j < divisionsArray.size(); j++) {
					JSONObject divisionArray = (JSONObject) divisionsArray.get(j);
					JSONArray teamsArray = (JSONArray) divisionArray.get("teams");
					ArrayList<ITeam> teams = new ArrayList<>();
					for (int k = 0; k < teamsArray.size(); k++) {
						JSONObject teamArray = (JSONObject) teamsArray.get(k);
						IGeneralManager generalManager = new GeneralManager();
						generalManager.setGeneralManagerName((String) teamArray.get("generalManager"));
						IHeadCoach headCoach = new HeadCoach();
						headCoach.setHeadCoachName((String) teamArray.get("headCoach"));
						JSONArray playersArray = (JSONArray) teamArray.get("players");
						ArrayList<IPlayer> players = new ArrayList<>();
						for (int x = 0; x < playersArray.size(); x++) {
							JSONObject playerArray = (JSONObject) playersArray.get(x);
							Player player = new Player();
							player.setPlayerName((String) playerArray.get("playerName"));
							player.setPlayerPosition((String) playerArray.get("position"));
							player.setPlayerCaptain((Boolean) playerArray.get("captain"));
							players.add(player);
						}
						Team team = new Team();
						team.setPlayers(players);
						team.setHeadCoach(headCoach);
						team.setGeneralManager(generalManager);
						team.setTeamName((String) teamArray.get("teamName"));
						teams.add(team);
						System.out.println("TeamName: " + team.getTeamName());
						System.out.println("GeneralManagerName: " + generalManager.getGeneralManagerName());
						System.out.println("HeadCoachName: " + headCoach.getHeadCoachName());
						System.out.println("Players:");
						for (int a = 0; a < players.size(); a++) {
							System.out.println("PlayerName: " + players.get(a).getPlayerName());
							System.out.println("Position: " + players.get(a).getPlayerPosition());
							System.out.println("Captain: " + players.get(a).isPlayerCaptain());
						}
					}
					Division division = new Division();
					division.setDivisionName((String) divisionArray.get("divisionName"));
					division.setTeams(teams);
					divisions.add(division);
					System.out.println("DivisionName: " + division.getDivisionName());
				}
				Conference conference = new Conference();
				conference.setConferenceName((String) conferenceArray.get("conferenceName"));
				conference.setDivisions(divisions);
				conferences.add(conference);
				System.out.println("ConferenceName: " + conference.getConferenceName());
			}

			JSONArray freeAgentsArray = (JSONArray) jsonObject.get("freeAgents");
			ArrayList<IFreeAgent> freeAgents = new ArrayList<>();
			for (int i = 0; i < freeAgentsArray.size(); i++) {
				JSONObject freeAgentArray = (JSONObject) freeAgentsArray.get(i);
				FreeAgent freeAgent = new FreeAgent();
				freeAgent.setFreeAgentName((String) freeAgentArray.get("playerName"));
				freeAgent.setFreeAgentPosition((String) freeAgentArray.get("position"));
				freeAgent.setFreeAgentCaptain((Boolean) freeAgentArray.get("captain"));
				freeAgents.add(freeAgent);
			}
			System.out.println("FreeAgents:");
			for (int i = 0; i < freeAgents.size(); i++) {
				System.out.println("PlayerName: " + freeAgents.get(i).getFreeAgentName());
				System.out.println("Position: " + freeAgents.get(i).getFreeAgentPosition());
				System.out.println("Captain: " + freeAgents.get(i).isFreeAgentCaptain());
			}
			ArrayList<ILeague> leagues = new ArrayList<>();
			League league = new League();
			league.setLeagueName((String) jsonObject.get("leagueName"));
			league.setConferences(conferences);
			league.setFreeAgents(freeAgents);
			leagues.add(league);
			System.out.println("LeagueName: " + league.getLeagueName());

			Game game = new Game();
			game.setLeagues(leagues);
		} catch (Exception e) {
			System.out.println("Enter valid path of json file");
		}
	}
}
