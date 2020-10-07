package g4dhl;

import g4db.IGameDB;
import g4db.GameDB;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

public class HelloWorld {

	public static void main(String[] args)  {
//		HashMap<String, String> propsMap = null;
//		PropertyLoader envProps = new PropertyLoader();
//		try {
//			propsMap = envProps.getPropValues();
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
//		System.setProperty("Jdbc.drivers", propsMap.get("driver"));
//		Properties properties = new Properties();
//		properties.put("user", propsMap.get("username"));
//		properties.put("password", propsMap.get("password"));
//		String url = propsMap.get("url");
//		Connection con = null;
//		try {
//			con = DriverManager.getConnection(url, properties);
//			String x = "call SP_LEAGUE_LIST()";
//			CallableStatement stmt = con.prepareCall(x);
////			stmt.setString(1, "League1");
//			ResultSet rs = stmt.executeQuery();
//			while(rs.next()) {
//				System.out.println(rs.getInt(1) + " " + rs.getString(2));
//			}
//			con.close();
//			System.out.println("connection closed");
//		}catch (SQLException e) {
//			System.out.println(e.getLocalizedMessage());
//		}
//		finally {
//			if(con !=null) {
//				try {
//				con.close();
//				System.out.println("connection closed");
//				}
//				catch (SQLException e) {
//					e.getStackTrace();
//				}
//			}
//		}
//

		Game game = new Game();
		GameDB gamedb = new GameDB();
		game.loadLeagues(gamedb);
		System.out.println(game.getLeagues().get(0).getLeagueName());
	}

}
