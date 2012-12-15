package database;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC {
	
	public static void main(String[] args) throws IOException {
		
		// LADOWANIE STEROWNIKA
		System.out.print("Sprawdzanie sterownika:");
        try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e) {
			System.out.println("Blad przy ladowaniu sterownika bazy!");
			System.exit(1);
		}
		System.out.print(" sterownik OK");
		
		// LACZENIE Z BAZA
		System.out.print("\nLaczenie z baza danych:\n");
		String baza = "jdbc:mysql://localhost/baza";
		// objasnienie opisu bazy:
		// jdbc: - mechanizm laczenia z baza (moze byc inny, np. odbc)
		// mysql: - rodzaj bazy
		// //olimp.if.pw.edu.pl - adres serwera z baza (moze byc tez w formie adresu IP)
		// /pojava - nazwa bazy (poniewaz na serwerze moze byc kilka roznych baz...)
		
		
        
		
		
		String user = "baza";
		String pass = "baza";
		
		java.sql.Connection conn = null;
        try {
			conn=DriverManager.getConnection(baza, user, pass);
			//rownoznacze z zapisem:
			//conn=DriverManager.getConnection("jdbc:mysql://olimp.if.pw.edu.pl/pojava?user=pojava&password=Java");
		} catch (SQLException e) {
			System.out.println("Blad przy ladowaniu sterownika bazy!");
			System.exit(1);
		}
		System.out.print(" polaczenie OK\n");
		
		
		//LOGOWANIE
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
		String login;
		System.out.println("podaj login:\n");
        login = br.readLine();
        //a = Integer.parseInt(wybor);
		
        String haslo;
        System.out.println("podaj haslo:\n");
        haslo = br.readLine();
        //===================================================================
		
		
        // WYKONYWANIE OPERACJI NA BAZIE DANYCH
		System.out.println("Pobieranie danych z bazy:");
		Statement s = null;
		try {
			s = conn.createStatement();   // tworzenie obiektu Statement przesylajacego zapytania do bazy conn
		    ResultSet r;				
		    r=s.executeQuery("Select * from user;");  // wykonanie kwerendy i przeslanie wynikow do obiektu ResultSet  
		    //r.next();			// przejscie do kolejnego rekordu (wiersza) otrzymanych wynikow
		    
		    
		    					
		    //ResultSetMetaData rsmd = r.getMetaData();
            //int numcols = rsmd.getColumnCount();   // pobieranie liczby kolumn
            
            //wyswietlanie nazw kolumn:
            /*for (int i = 1; i <= numcols; i++) { 
             System.out.print(rsmd.getColumnLabel(i)+"  |  "); 
            }
            System.out.print("\n------------------------------------\n");

            //wyswietlanie kolejnych rekordow:
            while (r.next()) {
                for (int i = 1; i <= numcols; i++) {
               	   	 Object obj = r.getObject(i);
                     if (obj != null)System.out.print(obj.toString()+ " | ");
                     else  System.out.print(" ");
                   }
                System.out.println();
            }
            */
            
            while (r.next()) { 
                String userr = r.getString("user"); 
                String passs = r.getString("pass"); 
                
                if((login.equals(userr))&(haslo.equals(passs))) {
                
                System.out.println("User: " + userr); 
                System.out.println("Pass: " + passs);
                System.out.println("zalogowano");
                }
             }
            
		} catch (SQLException e) {
			System.out.println("Blad odczytu z bazy! " +e.toString());
			System.exit(3);
		}
		
		//DODAWANIE REKORDU
		/*String sql = "INSERT INTO user VALUES"+ "('trzy','trzy')";
	    try {
			int insertedRows = s.executeUpdate(sql);
			if (insertedRows!=0) System.out.println("dodano");
			else
				System.out.println("nie dodano");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
	
		// ZAMYKANIE POLACZENIA Z BAZA
		System.out.print("\nZamykanie polaczenia z baza:");
		try {
			s.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("Blad przy zamykaniu polaczenia " +e.toString());
			System.exit(4);
		}
		System.out.print(" zamkniecie OK");
	}

}