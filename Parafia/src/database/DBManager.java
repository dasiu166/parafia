package database;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData; //opis kolumn
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.*;

import obsluga.Adress;
import obsluga.User;
import obsluga.Priest;
import pomoce.Pomoc;

public class DBManager {
	/*Klasa odpowiedzialna za polaczenie z baza*/
	
	private static DBManager instance; //instacja managera bazy
	private java.sql.Connection conn = null; //zmienna 
	private String pass="";
	private String user="";
	private String port="";
	private String adres="";
	private String sid="";
	Savepoint s;
	
	
	
	private DBManager(){
		
	}
	
	public void setAutoCommit(boolean v){
		try{
			
		conn.setAutoCommit(v);
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("Blad autocommita");
		}
	}
	
	public void makeSavepoint(){
		try{
			s = conn.setSavepoint("SAVE");
		}catch (SQLException e){
			
		}
	}
	
	public void closeResultSet(){
		
	}
	
	
	
	public boolean useSavePoint(){
		try {
			conn.rollback(s);
			return true;
		} catch (SQLException e){
			return false;
		}
	}
	
	public void doCommit(){
		try{
		
		conn.commit();
		}catch(SQLException e){
			try{
				conn.rollback();
			}catch (SQLException e2){}
			
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!BRAK ZMIAN");		
		}
	}
	
	public static DBManager getInstance(){
		if (instance == null) instance = new DBManager();
		return instance;
	}
	
	public void setInfoToConnect(String u, String p){
		/*!!Konieczne wywolanie przed polaczeniem bo trza dane podac*/
		user = u;
		pass = p;
	}
	
	public void setInfoToConnect(String u, String p, String s){
		/*!!Konieczne wywolanie przed polaczeniem bo trza dane podac*/
		user = u;
		pass = p;
		sid  = s;
	}
	
	public boolean connectToDB(){
		/*laczy z baza danych*/
		System.out.print("Sprawdzanie sterownika:\n");
        try {
        	port=Pomoc.loadFromFile("serwer.ini", "DBPORT");
        	adres=Pomoc.loadFromFile("serwer.ini", "DBADRES");
        	sid=Pomoc.loadFromFile("serwer.ini", "DBSID");
        	
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
		} catch (Exception e) {
			System.out.println("Blad przy ladowaniu sterownika bazy!");
			System.exit(1);
		}
		System.out.print(" sterownik OK");
		
		// LACZENIE Z BAZA
		System.out.print("\nLaczenie z baza danych:\n");
		String baza = "jdbc:oracle:thin:@"+adres+":"+port+":"+sid;
		
        try {
			conn=DriverManager.getConnection(baza, user, pass);
			//rownoznacze z zapisem:
			//conn=DriverManager.getConnection("jdbc:mysql://olimp.if.pw.edu.pl/pojava?user=pojava&password=Java");
		} catch (SQLException e) {
			System.out.println("Blad przy ladowaniu sterownika bazy!");
			return false;
		}
		System.out.print(" polaczenie OK\n");
		//this.setAutoCommit(false);
		return true;
		
	}
	
	public int execUpdateQuery(String q){
		int ret=0; //wynik wykonania zapytania
		
		
		try{
		
		ret = conn.createStatement().executeUpdate(q);
		
		//conn.createStatement().executeUpdate("commit");
		}catch(SQLException e){
			ret=0;
			e.printStackTrace();
			e.getMessage();
			
		}
		
		return ret;
	}
	
	public LinkedList<String[]> execSelectQuery(String q){
		/*Metoda zwraca wynik zapytania SELECT w postaci listy tablic stringow
		 * tablica stringow jest wierszem z bazy
		 * jak nie ma nic to zwraca ERR*/
		
		//connectToDB();
		ResultSet data=null; //wynik z bazy
		ResultSetMetaData desc;//opis wyniku
		Statement stmt=null;
		LinkedList<String[]> dataList = new LinkedList<String[]>();//lista wierszy
		int rows=0;
		try{
	    stmt = conn.createStatement();
	    data=stmt.executeQuery(q);
		
	    //data = conn.createStatement().executeQuery(q); //wykonanie zapytania w bazie
		desc = data.getMetaData(); //zwrocenie opisu kolumn
		
		//if(data==null) System.out.println("CATCH SQL");
		
		System.out.println("Laduje dane (Liczba kolumn =)"+desc.getColumnCount());
		
		
		while(data.next()){
		 rows+=1;
		 String[] fieldArray = new String[desc.getColumnCount()]; //utworzenie tablicy
			
		 	for(int j=1;j<=desc.getColumnCount();j++){
				fieldArray[j-1] = data.getString(j); //dodanie pol do tablicy
				
			}
			dataList.add(fieldArray);//dodanie tablicy z polami do listy
		 }
		
		data.close();
		stmt.close();
		
		} 
		
		catch(SQLException e){
			
			e.printStackTrace();
			e.getMessage();
			try{
			data.close();
			stmt.close();
			}catch (SQLException eee){
				
			}
			//return dataList;
		}
		
		if (rows==0){
			//data = null;
			System.out.println("CATCH SQL");
			String[] fArray = new String[1];//zwrot bledu gdy brak danych
			fArray[0]="ERR";
			dataList.add(fArray);
			try{
				data.close();
				stmt.close();
				}catch (SQLException eee){
					return dataList;
				}
			return dataList;
		} else
		System.out.println("Ilosc wierszy "+rows);
		return dataList;//zwrot listy
	}

		
	public static void main(String[] args){
		DBManager db = DBManager.getInstance();
		LinkedList<String[]> dbReturn; //sluzy doodpioru wynikow select z bazy

		db.setInfoToConnect("parafia", "abc");
		db.connectToDB();
		//db.execUpdateQuery("INSERT INTO userr VALUES (7,'henia','osa',0,11)");
		
		 dbReturn=db.execSelectQuery("Select * from Adress where id_adress=5");
		 String tmp1[] = dbReturn.getFirst();
		 Adress a = new Adress();
		 Priest pr = new Priest();
		 a.setId(Integer.parseInt("5"));
		 a.setCity(dbReturn.getFirst()[1]);
		 a.setStreet(dbReturn.getFirst()[2]);
		 a.setHouseNumb(dbReturn.getFirst()[3]);
		 a.setPostcode(dbReturn.getFirst()[4]);
		 pr.setAdress(a); //dodanie adresu
		 
		 pr.setArrivalDate(Pomoc.podajDate(tmp1[5].substring(0, 10)));
		 pr.setSecularityDate(Pomoc.podajDate(tmp1[6].substring(0, 10)));
		 
		 pr.setQuery("OK+");
		 
		 System.out.println(pr.getArrivalDate().toLocaleString());
		
		
		
		
		
		
		
		
		/*przyklad przejzenia wyniku z bazy
		
		LinkedList<String[]> tmpList = new LinkedList<String[]>();
		//tmpList= db.execSelectQuery("SELECT * FROM parishioner where id_userr=1");
		//tmpList= db.execSelectQuery("SELECT * FROM userr WHERE login ='ania' AND password = 'an11'");
		tmpList= db.execSelectQuery("SELECT * FROM actuals");
		Iterator<String[]> iteratorData = tmpList.iterator();
		while(iteratorData.hasNext()){
			String[] tmp = iteratorData.next();
			for(int i=0;i<tmp.length;i++){
				System.out.print(tmp[i]+"--");
			}
			System.out.print("\n");
		}
		
		*/
		
		
		/*przyklad rozebrania wynikow
		Iterator<String[]> iteratorData2 = tmpList.iterator();
		String[] tmp = iteratorData2.next();
		User u = new User();
		u.setRange(Integer.parseInt(tmp[4]));
		System.out.println("USER RANGA "+u.getRange()+" (WARTOSC Z BAZY)"+tmp[4]);*/
		
	}

}
