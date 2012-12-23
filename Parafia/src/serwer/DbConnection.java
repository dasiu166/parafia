package serwer;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbConnection {

	static java.sql.Connection conn = null;
	public static void dbConn(){
		System.out.print("Sprawdzanie sterownika:\n");
        try {
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
		} catch (Exception e) {
			System.out.println("Blad przy ladowaniu sterownika bazy!");
			System.exit(1);
		}
		System.out.print(" sterownik OK");
		
		// LACZENIE Z BAZA
		System.out.print("\nLaczenie z baza danych:\n");
		String baza = "jdbc:oracle:thin:@localhost:1521";
		
		String user = "parafia";
		String pass = "abc";
		
		
        try {
			conn=DriverManager.getConnection(baza, user, pass);
			//rownoznacze z zapisem:
			//conn=DriverManager.getConnection("jdbc:mysql://olimp.if.pw.edu.pl/pojava?user=pojava&password=Java");
		} catch (SQLException e) {
			System.out.println("Blad przy ladowaniu sterownika bazy!");
			System.exit(1);
		}
		System.out.print(" polaczenie OK\n");
	}
	
	

}
