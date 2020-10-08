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
		String java ="JAVA_HOME";
	    String driver= "DRIVER_DEV";
	    String username="USERNAME_DEV";
	    String password="PASSWORD_DEV";
	    String url="URL_DEV";
		System.out.println(System.getenv(java));
		System.out.println(System.getenv(driver));
		System.out.println(System.getenv(username));
		System.out.println(System.getenv(password));
		System.out.println(System.getenv(url));
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
		String url1 = propsMap.get("url");
		Connection con = null;
		try {
			con = DriverManager.getConnection(url1, properties);
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
