package g4dhl;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Properties;

public class HelloWorld {

	public static void main(String[] args)  {
		HashMap<String, String> propsMap = null;
		PropertyLoader envProps = new PropertyLoader();
		try {
			propsMap = envProps.getPropValues();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.setProperty("Jdbc.drivers", propsMap.get("driver"));
		Properties properties = new Properties();
		properties.put("user", propsMap.get("username"));
		properties.put("password", propsMap.get("password"));
		String url = propsMap.get("url");
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, properties);
			System.out.println("Connection established: "+ con);
			con.close();
			System.out.println("connection closed");
		}catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		}
		finally {
			if(con !=null) {
				try {
				con.close();
				System.out.println("connection closed");
				}
				catch (SQLException e) {
					e.getStackTrace();
				}
			}
		}
	
	}

}
