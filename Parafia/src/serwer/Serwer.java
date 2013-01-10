package serwer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.*;

import database.DBManager;
import pomoce.Pomoc;

import pomoce.ReadFromFile;


public class Serwer {
	protected static int PORT = 2000; //zawiera port serwera
	protected static String LOGDIRECTORY = ""; //zawiera folder glowny plikow log
	private static Date d = new Date();
	protected static LinkedList<String> loginNameList = new LinkedList<String>();
	
	private static void saveLog(String text){
		Pomoc.writeToFile(Serwer.LOGDIRECTORY, "mainSerwer.log."+
				d.toLocaleString().substring(0, 10), d.toLocaleString()+
				": SERWER -> "+ text);
	}
	
	protected static void addLoginToList(String val){
		loginNameList.add(val);
	}
	
	public synchronized static boolean ckeckLoginList(String val){
		Iterator<String> it = loginNameList.iterator();
		while(it.hasNext()){
			if(it.next().equals(val)) return false;
		}
		loginNameList.add(val);
		return true;
	}
	
	public synchronized static boolean removeLoginFromList(String val){
		Iterator<String> it = loginNameList.iterator();
		int index=0;
		while(it.hasNext()){
			
			if(it.next().equals(val)){
				loginNameList.remove(index);
				return true;
			}
			index++;
		}
		return false;
	}
	
	public static void showLoginList(){
		Iterator<String> it = loginNameList.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
	}
	
	public static void main (String args[]) throws IOException{
		
		d.getTime();
		System.out.print(Pomoc.loadFromFile("serwer.ini","PORT"));
		PORT = Integer.parseInt(Pomoc.loadFromFile("serwer.ini","PORT")); //zczytanie portu z ini
		LOGDIRECTORY = Pomoc.loadFromFile("serwer.ini", "LOGDIRECTORY");
		
		//LOG
		Serwer.saveLog("Serwer uruchomiony - Port: "+PORT);
		//LOG_END
		
		DBManager db = DBManager.getInstance();
		db.setInfoToConnect(Pomoc.loadFromFile("serwer.ini","DBLOGIN"),
				Pomoc.loadFromFile("serwer.ini","DBPASSW"));
		
		if (db.connectToDB()) Serwer.saveLog("Polaczono z baza danych"); else 
			Serwer.saveLog("Blad polaczenie z baza danych");
		
		
		try{
		ServerSocket serwerSocket = new ServerSocket(PORT); //zmienna gniazda
		
		
		try {
			while (true) {
				Socket socket = serwerSocket.accept(); // oczekiwanie na nadejscie polaczenia
				
				try {
				
				//LOG
				Serwer.saveLog("Nowy watek");
				//LOG_END
				
				//new SerwerThread(socket);// utworzenie watka serwera
				new SerwerThreadService(socket);// utworzenie watka serwera
				
			} catch (IOException e) {
				
				//LOG
				Serwer.saveLog("Blad watku");
				//LOG_END
				
					socket.close(); // zamkniecie polaczenia w przypadku wystapienia bledu
				}
			}
		} finally {
			serwerSocket.close();
			//LOG
			Serwer.saveLog("Zamkniecie serwera");
			//LOG_END
		}
	
	} catch(Exception e){ //zbiera blad podczas bindowania gniazda
		//LOG
		Serwer.saveLog("Port zajety");
		//LOG_END
		System.out.println("Blad serwera");
	
	}
	}		
}

