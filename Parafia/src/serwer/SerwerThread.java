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
import stale.KindQuery;
import stale.KindRange;
import stale.KindRestriction;

public class SerwerThread extends Thread implements KindQuery , KindRange, KindRestriction{
	/*Watek Grzeska */
	private Socket socket;
	private ObjectInputStream przychodzace;
	private ObjectOutputStream wychodzace;
	Object wiadomosc; //zmienna zabezpieczajaca (na niej wykonywane sa informacje)
	//private LinkedList<Object> kolejka;
	
	int clientRestriction=0; //przetrzymuje uprawnienia, moze a nie musi sie przydac
	
	Date d = new Date(); //data dla logow
	private Object przesylka = null; //sluzy do przyjmowania przyslanych obiektow
	
	private LinkedList<String[]> dbReturn; //sluzy doodpioru wynikow select z bazy
	private int dbReturnInt=0;//odbior wynikow w postaci int
	
	public SerwerThread(Socket s) throws IOException {
		socket = s;
		d.getTime();//pobranie daty i czasu
		start();
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
			Pomoc.writeToFile(Serwer.LOGDIRECTORY, "threadSerwer.log."+
					d.toLocaleString().substring(0, 10), d.toLocaleString()+
					": Watek:"+this.getName()+" "+
					" -> Klient sie podlaczyl");
			//LOG_END
			
			while (true) {// glowna petla watka
				
			 //wiadomosc = przychodzace.readObject();
			 this.reciveObject();
			 
			 wiadomosc = this.getPackage();
			 System.out.println("Sprawdzam parafianina");
			 
			 
			 if (wiadomosc instanceof User) {
				 
				 System.out.println("Przyszla wiadomomosc typu user");
				 if (((User) wiadomosc).getKindQuery()==KindQuery.TRY_LOGIN){
					 				//##BLOK PROBY ZALOGOWANIA
					
					 //LOG-------------------------------------------------------
					 Pomoc.writeToFile(Serwer.LOGDIRECTORY, "threadSerwer.log."+
								d.toLocaleString().substring(0, 10), d.toLocaleString()+
								": Watek:"+this.getName()+" "+
								" -> Klient proboje sie zalogowac");
					//LOG_END---------------------------------------------------
					 
					 DBManager db = DBManager.getInstance();
					 
					 //### KONFUGURACJA USERA ###
					 dbReturn = db.execSelectQuery(((User) wiadomosc).getQuery());
					 String tmp[] = dbReturn.getFirst();
					 if (!tmp[0].equals("ERR") ){ //jezeli znaleziono usera
				
				     int idu = Integer.parseInt(tmp[0]);
							 
				     ((User) wiadomosc).setRestriction(Integer.parseInt(tmp[3]));
				     ((User) wiadomosc).setRange(Integer.parseInt(tmp[4]));
				     clientRestriction=Integer.parseInt(tmp[3]);
				     ((User) wiadomosc).setQuery("OK+");
					
				     this.sendObject(wiadomosc); //odpowiedz klas¹ user
					
				     //###KONFUGURACJA I WYSLANIE PODST. DANYCH PARAFIANINA
				     Parishioner p = new Parishioner();
					 dbReturn = db.execSelectQuery("SELECT * FROM parishioner where id_userr="+idu);
					 String tmp1[] = dbReturn.getFirst();
					 p.setPesel(tmp1[0]);
					 p.setName(tmp1[4]);
					 p.setSurName(tmp1[5]);
					 //p.setAdress(new Adress());
					 p.setRestriction(clientRestriction);
					 p.setQuery("OK+");
							 
					 this.sendObject(p); //odpowiedz klas¹ parishioner
							
					//LOG-----------------------------------------------------
					Pomoc.writeToFile(Serwer.LOGDIRECTORY, "threadSerwer.log."+
						d.toLocaleString().substring(0, 10), d.toLocaleString()+
						": Watek:"+this.getName()+" "+
						" -> Klient sie zalogowal");
					//LOG_END-------------------------------------------------
						 
					 } else {//##BLAD LOGOWANIA
						 ((User) wiadomosc).setQuery("ERR");
						 this.sendObject(wiadomosc);
					 }
				 
				 }//##KONIEC PROBY ZALOGOWANIA
				 
				 if (((User) wiadomosc).getKindQuery()==KindQuery.ADD_DBASE){
					 	//##BLOK DODANIA UZYTKOWNIKA DO BAZY
					 DBManager db = DBManager.getInstance();
					 dbReturnInt = db.execUpdateQuery(((User) wiadomosc).getQuery());
					 if (dbReturnInt!=0) ((User) wiadomosc).setQuery("OK+"); else
						 	((User) wiadomosc).setQuery("ERR");
					 
					 this.sendObject(wiadomosc);
					 
				 }//##KONIEC DODAWANIA UZYTKOWNIKA DO BAZY
			 
			 } else //###KONIEC OBSLUGI KLASY USER
				
				if (wiadomosc instanceof Parishioner){
				 
				 if (((Parishioner) wiadomosc).getKindQuery()==KindQuery.TRY_LOGOUT){
					 			//## BLOK PROBY WYLOGOWANIA
					 
					 ((Parishioner) wiadomosc).clean();
					 ((Parishioner) wiadomosc).setRestriction(KindRestriction.GUEST_R);
					 ((Parishioner) wiadomosc).setData("Wylogowano CIE");
					 ((Parishioner) wiadomosc).setQuery("OK+");
					 this.sendObject(wiadomosc);
					 
					//LOG-------------------------------------------------------------
					 Pomoc.writeToFile(Serwer.LOGDIRECTORY, "threadSerwer.log."+
								d.toLocaleString().substring(0, 10), d.toLocaleString()+
								": Watek:"+this.getName()+" "+
								" -> Klient sie wylogowal");
					//LOG_END----------------------------------------------------------
				 
				 }//##KONIEC BLOKU WYLOGOWANIA
				 
				 if (((Parishioner) wiadomosc).getKindQuery()==KindQuery.SEL_DBASE){
					 			//##BLOK PROBY POBRANIA PELNYCH DANYCH
					 DBManager db = DBManager.getInstance();
					 dbReturn = db.execSelectQuery(((Parishioner) wiadomosc).getQuery());
					 String idA = dbReturn.getFirst()[3]; //id adresu
					 String idC = dbReturn.getFirst()[1]; //id przebiegu
					 
					 //tworze adres
					 dbReturn = db.execSelectQuery("Select * from Adress where id_adress="+idA);
					 Adress a = new Adress();
					 a.setId(Integer.parseInt(idA));
					 a.setCity(dbReturn.getFirst()[1]);
					 a.setStreet(dbReturn.getFirst()[2]);
					 a.setHouseNumb(dbReturn.getFirst()[3]);
					 a.setDistrict(dbReturn.getFirst()[4]);
					 ((Parishioner) wiadomosc).setAdress(a); //dodanie adresu
					 
					 //tworze przebieg
					 dbReturn = db.execSelectQuery("Select * from Course where id_course="+idC);
					 Course c = new Course();
					 String[] cs = dbReturn.getFirst();
					 c.setId(Integer.parseInt(cs[0]));
					 if(cs[1]!=null) c.setBirthday(Pomoc.podajDate(cs[1].substring(0, 10)));
					 if(cs[2]!=null) c.setBaptism(Pomoc.podajDate(cs[2].substring(0, 10)));
					 if(cs[3]!=null) c.setCommunion(Pomoc.podajDate(cs[3].substring(0, 10)));
					 if(cs[4]!=null) c.setConfirmation(Pomoc.podajDate(cs[4].substring(0, 10)));
					 if(cs[5]!=null) c.setMarriage(Pomoc.podajDate(cs[5].substring(0, 10)));
					 if(cs[6]!=null) c.setDeath(Pomoc.podajDate(cs[6].substring(0, 10)));
					 ((Parishioner) wiadomosc).setCourse(c);//dodanie przebiegu
					 
					 this.sendObject(wiadomosc);//wyslanie obiektu
					 
					 
				 }//##KONIEC BLOKU POBIERANIA PELNYCH DANYCH
				 
				 if (((Parishioner) wiadomosc).getKindQuery()==KindQuery.ADD_DBASE){
					 			//BLOK DODAWANIA DANYCH PARAFIANINA
				 
				 }//##KONIEC BLOKU DODAWANIA DANYCH PARAFIANINA
				 
			}else //KONIEC OBSLUGI KLASY PARISHIONER
			 
			 if (wiadomosc instanceof Order){
				 
				 if(((Order) wiadomosc).getKindQuery()==KindQuery.ADD_DBASE){ //dodanie do bazy
					 		//##BLOK DODAWANIA ZAMOWIENIA DO BAZY
					 
				 System.out.println("Przyszla wiadomosc 2"); //tymczasem
				 System.out.println("Zamowienie zlozyl: "+((Order) wiadomosc).getSenderPesel()+
				  " "+((Order) wiadomosc).getEvent()+"\n"+
				  "Odprawia "+((Order)wiadomosc).getExecutroPesel()+
				  " Kiedy: "+((Order)wiadomosc).getBeginDate());
				 
				 ((Order) wiadomosc).setData("ZAMOWIENIE ZLOZONE");
				 ((Order) wiadomosc).setQuery("OK+");
				 
				 this.sendObject(wiadomosc);
				 
				//LOG----------------------------------------------------------
					Pomoc.writeToFile(Serwer.LOGDIRECTORY, "threadSerwer.log."+
							d.toLocaleString().substring(0, 10), d.toLocaleString()+
							": Watek:"+this.getName()+" "+
							" -> Klient zlozyl zamowienie (wyslano)");
				//LOG_END------------------------------------------------------
				}//##KONIEC BLOKU DODAWANIA ZAMOWIENIA DO BAZY
				 
				if(((Order) wiadomosc).getKindQuery()==KindQuery.SEL_DBASE){
							//##BLOK POBRANIA ZAMOWIEN Z BAZY
					
					LinkedList<Order> orderList = new LinkedList<Order>();
					DBManager db = DBManager.getInstance();
					
					dbReturn = db.execSelectQuery(((Order) wiadomosc).getQuery());
					Iterator<String[]> it = dbReturn.iterator();
					while(it.hasNext()){
						Order o = new Order();
						String[] tmp = it.next();
						o.setId(Integer.parseInt(tmp[0]));
						o.setEvent(tmp[1]);
						o.setExecutorPesel(tmp[2]);
						o.setSenderPesel(tmp[3]);
						o.setDescribe(tmp[4]);
						o.setStatus(tmp[5]);
						o.setBeginDate(Pomoc.podajDate(tmp[6].substring(0, 10)));
						o.setEndDate(Pomoc.podajDate(tmp[7].substring(0, 10)));
						
						orderList.add(o); //dodanie obiektu do listy
					}
					this.sendObject(orderList);
					
					//LOG-----------------------------------------------------------
					Pomoc.writeToFile(Serwer.LOGDIRECTORY, "threadSerwer.log."+
							d.toLocaleString().substring(0, 10), d.toLocaleString()+
							": Watek:"+this.getName()+" "+
							" -> Klient pobral liste zamowien");
					//LOG_END-------------------------------------------------------
					
				}//##KONIEC BLOKU POIERANIA ZAMOWIEN
			
			 }else//##KONIEC OBSLUGI KLASY ORDER
			 
			 if(wiadomosc instanceof Event){
				 if(((Event) wiadomosc).getKindQuery()==KindQuery.SEL_DBASE){
					 		//##BLOK POBRANIA ZDARZEN
					 DBManager db = DBManager.getInstance();
						
					dbReturn = db.execSelectQuery(((Event) wiadomosc).getQuery());
					LinkedList<Event> eventList = new LinkedList<Event>();
					Iterator<String[]> it = dbReturn.iterator();
					
					while(it.hasNext()){
						String[] tmp = it.next();
						Event e = new Event();
						e.setId(Integer.parseInt(tmp[0]));
						e.setName(tmp[1]);
						e.setDescribe(tmp[2]);
						e.setQuery("OK+");
						eventList.add(e);
					}
					this.sendObject(eventList);
					 
				 }//##KONIEC BLOKU POBIERANIA ZDARZEN
				 
			 }//##KONIEC OBSLUGI KLASY EVENT
			 else {
				 		//##GDY WATEK NIE ROZPOZNA KLASY/POLECENIA
				 System.out.println("Nie rozpoznano");
			 }
			
			}//#######KONIEC TRY
		} catch (Exception e) {
			System.out.println("Klient sie --odlaczyl");
		} finally {
			
			try {
				
				socket.close(); // zamkniecie polaczenia
			
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}

