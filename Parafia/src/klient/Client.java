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

public class Client {
	//probaStart
	private InetAddress addr;
	private Socket socket = null;
	private boolean isConnected = false; //informuje czy klient jest polaczony
	private Object przesylka = null; //sluzy do przyjmowania przyslanych obiektow
	
	public boolean connect(String adres, int port) throws UnknownHostException, IOException
	{	/*laczy sie na podstawie podanego portu na localhoscie (pozniej do zmiany ofcoz)
	 		pobiera informacje o hoscie i binduje socket;
	 		zwraca true jezeli sie polaczyl;
	 		zbieranie wyjatkow dziala;*/
		
		try{
		addr = InetAddress.getByName(adres);
		
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
			e.setStackTrace(null);
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
		
		/*!!Poczatek logowania!!*/
		Parishioner p = new Parishioner();
		User u = new User();
		u.setKindQuery(0); //zapytanie = logowanie
		u.setLogin("ania");
		u.setPassword("an11");
		
		//p.setPesel("100");
		//p.setPass("haslo");
		//p.setKindQuery(0); 
		k.sendObject(u); //wysyla sie bo wszsytkie obiekty dziedzicza po Object
		k.reciveObject();/*tu uwaga panowie bo sie blokuje az nie otrzyma jakiejs przesylki*/
		
		u = (User)k.getPackage();
		if(u.getQuery().equals("ERR")){
			System.out.println("Nie mozna sie zalogowac (zly login/haslo)");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		
		System.out.println("Prawa dostepu: "+u.getRestriction()+" Ranaga: "+u.getRange());
		
		k.reciveObject();
		p = (Parishioner)k.getPackage();
		
		System.out.println("Zalogowano jako: "+p.getName()+" "+p.getSurName()+"\n" +
				" Adres: "+p.getAdress().getCity()+" Pesel: "+p.getPesel());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*Poczatek zlozenia zamowienia*/
		Order o = new Order();
		o.setKindQuery(1); //dodanie do bazy
		o.setSenderPesel(p.getPesel());
		o.setExecutorPesel("69010566803");
		o.setBeginDate(Pomoc.podajDate("2012-12-15"));
		o.setEndDate(Pomoc.podajDate("2012-12-16"));
		o.setEvent("3");//3 to msza wg Pawla bazy
		o.setDescribe("jakis tam opis");
		
		k.sendObject(o);
		k.reciveObject();
		o = (Order)k.getPackage();
		System.out.println("KLIENT:  (otrzymana odpowiedz)"+o.getData());
		
		
		/*Poczatek pobrania listy zamowien*/
	/*	o.setKindQuery(4);
		k.sendObject(o);
		k.reciveObject();
		
		LinkedList<Order> orderList = new LinkedList<Order>();
		
		orderList = (LinkedList<Order>)k.getPackage();
		Iterator<Order> iterator = orderList.iterator();
		
		while(iterator.hasNext()){
			System.out.println(iterator.next().getExecutroPesel());
		}
		System.out.println(orderList.size());
		
		*/
		/*Poczatek wylogowania*/
		p.setKindQuery(-1);
		k.sendObject(p);
		k.reciveObject();
		
	    p = (Parishioner)k.getPackage();
		
		System.out.println("KLIENT:  (otrzymana odpowiedz)"+p.getData());
		
	}
	
	
	//probaEnd
	
	
	
}
