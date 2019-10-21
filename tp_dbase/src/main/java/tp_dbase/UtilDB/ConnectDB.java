package tp_dbase.UtilDB;

import java.sql.*;

public class ConnectDB {
	String url = "jdbc:postgresql://localhost:5432/BDD_TP";
	Connection connect;
	
	// Constructors
	
	public ConnectDB() {
		try {
			connect = DriverManager.getConnection(url, "postgres", "KilleroPG0");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ConnectDB(String urlParam){
		url = urlParam;
		try {
			connect = DriverManager.getConnection(url, "postgres", "KilleroPG0");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Methods
	
	public static Connection getConnect () {
		return new ConnectDB().connect;
	}

	public static Connection getConnect (String url) {
		return new ConnectDB(url).connect;
	}

}
