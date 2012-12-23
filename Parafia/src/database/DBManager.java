package database;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData; //opis kolumn
import java.sql.SQLException;
import java.util.*;
import obsluga.User;

public class DBManager {
	/*Klasa odpowiedzialna za polaczenie z baza*/
	
	private static DBManager instance; //instacja managera bazy
	private java.sql.Connection conn = null; //zmienna 
	private String pass="";
	private String user="";
	
	private DBManager(){
		
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
	
	public void connectToDB(){
		/*laczy z baza danych*/
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
	
	public int execUpdateQuery(String q){
		int ret=0; //wynik wykonania zapytania
		
		
		try{
		
		ret = conn.createStatement().executeUpdate(q);
		conn.createStatement().executeUpdate("commit");
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
		ResultSet data; //wynik z bazy
		ResultSetMetaData desc;//opis wyniku
		LinkedList<String[]> dataList = new LinkedList<String[]>();//lista wierszy
		
		try{
		data = conn.createStatement().executeQuery(q); //wykonanie zapytania w bazie
		desc = data.getMetaData(); //zwrocenie opisu kolumn
		
		System.out.println("Laduje dane (Liczba kolumn =)"+desc.getColumnCount());
		while(data.next()){
		 String[] fieldArray = new String[desc.getColumnCount()]; //utworzenie tablicy
			
		 	for(int j=1;j<=desc.getColumnCount();j++){
				fieldArray[j-1] = data.getString(j); //dodanie pol do tablicy
				//System.out.println(fieldArray[j-1]);
			}
			dataList.add(fieldArray);//dodanie tablicy z polami do listy
		 }
		
		}catch(SQLException e){
			data = null;
			String[] fArray = new String[1];//zwrot bledy gdy brak danych
			fArray[0]="ERR";
			dataList.add(fArray);
			e.printStackTrace();
			e.getMessage();
		}
		
		/*try {
			conn.close();
		} catch(SQLException e){
			
		}*/
		
		return dataList;//zwrot listy
	}
		
	public static void main(String[] args){
		DBManager db = DBManager.getInstance();
		
		db.setInfoToConnect("parafia", "abc");
		db.connectToDB();
		db.execUpdateQuery("INSERT INTO userr VALUES (7,'henia','osa',0,11)");
		
		
		/*przyklad przejzenia wyniku z bazy*/
		/*
		LinkedList<String[]> tmpList = new LinkedList<String[]>();
		tmpList= db.execSelectQuery("SELECT * FROM parishioner where id_userr=1");
		//tmpList= db.execSelectQuery("SELECT * FROM userr;");
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
