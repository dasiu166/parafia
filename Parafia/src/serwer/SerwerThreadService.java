package serwer;
import obsluga.*;
import database.DBManager;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.io.*;
import java.util.*;
import pomoce.Pomoc;

import pomoce.Pomoc;
import databaseService.*;
import stale.KindQuery;
import stale.KindRange;
import stale.KindRestriction;

public class SerwerThreadService extends Thread implements KindQuery , KindRange, KindRestriction{
	/*Watek Grzeska */
	private Socket socket;
	private ObjectInputStream przychodzace;
	private ObjectOutputStream wychodzace;
	private Object wiadomosc; //zmienna zabezpieczajaca (na niej wykonywane sa informacje)
	//private LinkedList<Object> kolejka;
	
	int clientRestriction=0; //przetrzymuje uprawnienia, moze a nie musi sie przydac
	
	Date d = new Date(); //data dla logow
	private Object przesylka = null; //sluzy do przyjmowania przyslanych obiektow
	
	private LinkedList<String[]> dbReturn; //sluzy doodpioru wynikow select z bazy
	private LinkedList<String[]> dbReturn1;
	private int dbReturnInt=0;//odbior wynikow w postaci int
	private LinkedList<ServicePart> serviceList = new LinkedList<ServicePart>();
	
	public SerwerThreadService(Socket s) throws IOException {
		socket = s;
		serviceList.add(new UserrService());
		serviceList.add(new OrderService());
		serviceList.add(new AdressService());
		serviceList.add(new CourseService());
		serviceList.add(new ParishionerService());
		serviceList.add(new PriestService());
		serviceList.add(new ActualsService());
		serviceList.add(new EventService());
		serviceList.add(new AnonymService());
		d.getTime();//pobranie daty i czasu
		start();
	}
	
	public void saveLog(String text){
		Pomoc.writeToFile(Serwer.LOGDIRECTORY, "threadSerwer.log."+
				d.toLocaleString().substring(0, 10), d.toLocaleString()+
				": Watek:"+this.getName()+" -> "+ text);
	}
	
	private void setNullPackage(){
		przesylka=null;
		wiadomosc=null;
	}
	
	private Object getPackage(){ //zwraca nasza przesylke
		return przesylka;
	}
	
	public boolean sendObject(Object object) throws IOException{
		/*wysyla obiekt wykorzystujac socket*/
		try{
		ObjectOutputStream wychodzacy = new ObjectOutputStream(socket.getOutputStream());
		wychodzacy.writeObject(object);
		
		} catch (IOException e){
			System.out.println("Blad wysylania objektu");
			return false;
		}
		System.out.println("Objekt wyslany");
		return true;
	}
	
	public boolean reciveObject() throws IOException, ClassNotFoundException
	{	/*odbiera przesylke i zwraca jako zwykly obiekt
	 	trzeba sobie samemu zrzutowac pozniej;
	 	JEST TO FUNKCJA BLOKUJACA, dopoki nie otrzyma zwrotu to reszta stoi*/
		//przesylka=null;
		try{
		ObjectInputStream przychodzacy = new ObjectInputStream(socket.getInputStream());
			
				try {
					przesylka = przychodzacy.readObject();
				} catch (ClassNotFoundException e){
				System.out.println("Nie rozpoznano przesylki");
				return false;
				} //koniec try dla rzutowania przesylki
		
		} catch (IOException e){
			System.out.println("Blad odbioru wiadomosci");
			e.setStackTrace(null);
			return false;
		}//koniec try dla odbierania przesylki
	  return true;
	}
	
	public void run() {
		try {
			System.out.println("Klient sie podlaczyl");
			
			//LOG
			this.saveLog("Klient sie podlaczyl");
			//LOG_END
			
			while (true) {// glowna petla watka
				//this.setNullPackage();
			 //wiadomosc = przychodzace.readObject();
			 this.reciveObject();
			 
			 wiadomosc = this.getPackage();
			 System.out.println("Sprawdzam parafianina");
			 
			 Iterator<ServicePart> itSL = serviceList.iterator();
			 
			 while(itSL.hasNext()){
				 itSL.next().doService(wiadomosc,this);
			 }
			
			}//#######KONIEC TRY
		} catch (Exception e) {
			
			DBManager db = DBManager.getInstance();
			System.out.println("Klient sie --odlaczyl "+db.useSavePoint());
			this.saveLog("Klient sie rozlaczyl");
		} finally {
			
			try {
				
				socket.close(); // zamkniecie polaczenia
			
			} catch (IOException e) {
				e.printStackTrace();
			}
		}//koniec finally
	}//koniec run

}//koniec klasy


