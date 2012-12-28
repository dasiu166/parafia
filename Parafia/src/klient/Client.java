package klient;
import serwer.Serwer;
import obsluga.*;
import pomoce.Pomoc;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
//import java.net.InetAddress;
//import java.net.Socket;
import java.net.*;
import javax.swing.JOptionPane;
import java.util.*;
import java.io.*;
import pomoce.Pomoc;
import stale.*;

public class Client implements KindQuery, KindRange, KindRestriction {
	//probaStart
	private InetAddress addr;
	private Socket socket = null;
	private boolean isConnected = false; //informuje czy klient jest polaczony
	private Object przesylka = null; //sluzy do przyjmowania przyslanych obiektow
	private LinkedList<Event> eventKindList = null; //przechowuje dostepne rodzaje zdarzen
	
	public void setEventKindList(LinkedList<Event> val){
		eventKindList=val;
	}
	
	public LinkedList<Event> getEventKindList(){
		return eventKindList;
	}
	
	public boolean connect(String adres, int port) throws UnknownHostException, IOException
	{	/*laczy sie na podstawie podanego portu na localhoscie (pozniej do zmiany ofcoz)
	 		pobiera informacje o hoscie i binduje socket;
	 		zwraca true jezeli sie polaczyl;
	 		zbieranie wyjatkow dziala;*/
		
		try{
		addr = InetAddress.getByName(adres);
		//addr = InetAddress. 
		System.out.println(addr.getHostAddress());
		
		socket = new Socket(addr,port);
		} catch (UnknownHostException e){
			System.out.print("Nierozpoznany adres serwera");
			return false;
		} catch (IOException e){
			System.out.println("Blad inicjalizacji socketa");
			return false;
		}
		if (socket == null) return false; 
			else return true;
	}
	
	public Object getPackage(){ //zwraca nasza przesylke
		return przesylka;
	}
	
	public boolean getIsConnected(){
		return isConnected;
	}
	public void setIsConnected(boolean val){
		isConnected = val;
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
					//try{ //w razie gdyby potrzebne bylo spanie
					//Thread.sleep(0);
					//}catch(InterruptedException e){}
				} catch (ClassNotFoundException e){
				System.out.println("Nie rozpoznano przesylki");
				return false;
				} //koniec try dla rzutowania przesylki
		
		} catch (IOException e){
			System.out.println("Blad odbioru wiadomosci");
			e.getStackTrace();
			return false;
		}//koniec try dla odbierania przesylki
		
		
		
		return true;
	}
	
	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException{
		Client k = new Client();
		
		/*Ponizej jest symulacja logowania - zlozenia zamowienia - wylogowania
		 * dziala jak zloto jak narazie, trza dolaczyc baze i formatke i bedzie mam
		 * nadzieje, Panowie musimy przyspieszyc to troche bo inaczej...*/
		
		String adr = Pomoc.loadFromFile("client.ini", "SERWERADRES"); //pobranie adresu
		int portt = Integer.parseInt(Pomoc.loadFromFile("client.ini", "PORT")); //pobranie portu
		
		k.isConnected = k.connect(adr,portt);
		if (k.isConnected==false) System.out.println("Klient - niepolaczony");
			else System.out.println("Klient - polaczony");
		
		/*!!Poczatek logowania!!-------------------------------------------------*/
		Parishioner p = new Parishioner();
		User u = new User();
		u.setKindQuery(KindQuery.TRY_LOGIN); //zapytanie = logowanie
		u.setLogin("ania");
		u.setPassword("an11");
		
		u.setQuery("SELECT * FROM userr WHERE login = '"+u.getLogin()+"' AND password = '"+
		 u.getPassword()+"'");
		
		
		k.sendObject(u); //wysyla sie bo wszsytkie obiekty dziedzicza po Object
		k.reciveObject();/*tu uwaga panowie bo sie blokuje az nie otrzyma jakiejs przesylki*/
		
		u = (User)k.getPackage();
		
		if(u.getQuery().equals("ERR")){
			/*Gdy login/haslo bledne*/
			System.out.println("Nie mozna sie zalogowac (zly login/haslo)");
			u.setRestriction(KindRestriction.GUEST_R);
			p.setRestriction(KindRestriction.GUEST_R);
			return;
		}
		
		System.out.println("Prawa dostepu: "+u.getRestriction()+" Ranaga: "+u.getRange());
		
		k.reciveObject();
		
		p = (Parishioner)k.getPackage();
		
		System.out.println("Zalogowano jako: "+p.getName()+" "+p.getSurName()+"\n" +
				" Pesel: "+p.getPesel());
		
		
		//Poczatek zlozenia zamowienia
		Order o = new Order();
		o.setKindQuery(1); //dodanie do bazy
		o.setSenderPesel(p.getPesel());
		o.setExecutorPesel("69010566803");
		o.setBeginDate(Pomoc.podajDate("2012-12-15"));
		o.setEndDate(Pomoc.podajDate("2012-12-16"));
		o.setEvent("3");//3 to msza wg Pawla bazy
		o.setDescribe("jakis tam opis");
		o.setStatus(KindRange.NEW);
		String q;
		q="INSERT INTO Orderr VALUES (seq_orderr.nextval,3,'"+o.getExecutroPesel()+"','"+
		o.getSenderPesel()+"','"+o.getDescribe()+"','"+o.getStatus()+"',"+
		"to_date('"+o.getBeginDate().toLocaleString().substring(0, 10)+
		"','yyyy-MM-dd'),"+"to_date('"+o.getEndDate().toLocaleString().substring(0, 10)+
		"','yyyy-MM-dd'))";
		o.setQuery(q);
		
		
		
		k.sendObject(o);
		k.reciveObject();
		o = (Order)k.getPackage();
		System.out.println("KLIENT:  (otrzymana odpowiedz)"+o.getData());
		System.out.println(q);
		
		
		/*Poczatek pobrania listy zamowien---------------------------------*/
		//Order o = new Order();
		o.setKindQuery(KindQuery.SEL_DBASE);
		/*przykladowe zapytanie(POBIERA WSZYSTKIE ZAMOWIENIA ZLOZONE PRZEZ PARAFIANINA)*/
		o.setQuery("Select * from orderr where zamawiajacy_pesel="+p.getPesel());
		k.sendObject(o);
		k.reciveObject();
		
		LinkedList<Order> orderList = new LinkedList<Order>();
		
		orderList = (LinkedList<Order>)k.getPackage();
		Iterator<Order> iterator = orderList.iterator();
		
		while(iterator.hasNext()){
			Order tmp =iterator.next();
			System.out.println(tmp.getDescribe()+"    "+tmp.getBeginDate().toLocaleString());
		}
		System.out.println(orderList.size());
		
		/*Pobranie szczegolowych danych na temat parafianina--------------*/
		p.setKindQuery(KindQuery.SEL_DBASE);
		p.setQuery("Select * from parishioner where pesel="+p.getPesel());
		k.sendObject(p);
		k.reciveObject();
		p=(Parishioner)k.getPackage();
		
		System.out.println("ZAMIESZKALY: "+p.getAdress().getCity()+"    "+p.getAdress().getDistrict());
		System.out.println("URODZONY : "+p.getCourse().getBirthDay().toLocaleString());
		
		/*Pobranie dostepnych rodzajow zdarzen z bazy---------------------*/
		  Event e = new Event();
		  e.setKindQuery(KindQuery.SEL_DBASE);
		  e.setQuery("Select * from event");
		  k.sendObject(e);
		  k.reciveObject();
		  k.setEventKindList((LinkedList<Event>)k.getPackage());
		  Iterator<Event> it = k.getEventKindList().iterator();
		  while(it.hasNext()){
			  Event ee = it.next();
			  System.out.println(ee.getName());
		  }
		
		  
		/*Dodanie uzytkownika (userr) do bazy
		  User ne = new User();
		  ne.setPassword("TEST_ADD");
		  ne.setLogin("TEST_LIGIN");
		  ne.setRestriction(KindRestriction.LOGED_R);
		  ne.setRange(KindRange.LOGG_RANG);
		  
		  ne.setKindQuery(KindQuery.ADD_DBASE);
		  ne.setQuery("Insert into userr values (8,'"+ ne.getLogin() +"','"+
				 ne.getPassword()+"',"+ne.getRestriction()+","+
				  ne.getRange()+")");
		  
		  k.sendObject(ne);
		  k.reciveObject();
		  ne=(User)k.getPackage();
		  System.out.println("Wynik dodania uzytkownika: "+ne.getQuery());
		
		/*Poczatek wylogowania--------------------------------------------*/
		
		p.setKindQuery(-1);
		k.sendObject(p);
		k.reciveObject();
		
	    p = (Parishioner)k.getPackage();
		
		System.out.println("KLIENT:  (otrzymana odpowiedz)"+p.getData());
		
	}
	
	
	//probaEnd
	
	
	
}
