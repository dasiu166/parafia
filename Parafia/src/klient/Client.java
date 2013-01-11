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
	
	public void setNullPackage(){
		przesylka=null;
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
		//Parishioner p = new Parishioner();
		
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
			//p.setRestriction(KindRestriction.GUEST_R);
			return;
		}
		
		System.out.println("Prawa dostepu: "+u.getRestriction()+" Ranaga: "+u.getRange());
		
		k.reciveObject();
		
		Parishioner p = new Parishioner();
		p=(Parishioner)k.getPackage();
		
		/*fragment rozrozniajacy rangi, trzeba go zaimplementowac w jakis sposob 
		 * do formatek
		 * 
		 * if(u.getRange()<KindRange.PRIEST_RANG)
		{	Parishioner p = new Parishioner(); 
			p = (Parishioner)k.getPackage();
			} else{
				Priest p = new Priest();
				p = (Priest)k.getPackage();
			}*/
		
		System.out.println("Zalogowano jako: "+p.getName()+" "+p.getSurName()+"\n" +
				" Pesel: "+p.getPesel());
		
		//## USUWANIE AKTUALNOSCI##
		
		Actuals act = new Actuals();
		act.setId(1);
		act.setKindQuery(KindQuery.DEL_DBASE);
		act.setQuery("DELETE FROM actuals where id_actuals="+act.getId());
		k.sendObject(act);
		k.reciveObject();
		act=(Actuals)k.getPackage();
		System.out.println("Wynik usuniecia aktualnosci="+act.getQuery());
		
		/*DODANIE KSIEDZA
		User newU = new User();
		newU.setLogin("xxxx");
		newU.setPassword("P");
		newU.setRestriction(KindRestriction.WORKS_R);
		newU.setRange(KindRange.PRIEST_RANG);
		newU.setKindQuery(KindQuery.ADD_DBASE);
		newU.setQuery("INSERT INTO Userr VALUES (" +
				"seq_userr.nextval,'"+
				newU.getLogin()+"','"+
				newU.getPassword()+"',"+
				newU.getRestriction()+","+
				newU.getRange()
				+")");
		System.out.println("^^^^^^^^^^^^"+newU.getQuery());
		k.sendObject(newU);
		k.reciveObject();
		newU = (User)k.getPackage();
		System.out.println("Wynik dodania uzytkownika  "+newU.getQuery());
		
		Adress newA = new Adress();
		newA.setCity("Kielce");
		newA.setHouseNumb("12A");
		newA.setPostcode("14-111");
		newA.setStreet("Wieczorna");
		newA.setKindQuery(KindQuery.ADD_DBASE);
		newA.setQuery("INSERT INTO Adress VALUES (" +
				"seq_adress.nextval,'" +
				newA.getCity()+"','"+
				newA.getStreet()+"','"+
				newA.getHouse()+"','"+
				newA.getPostcode()+
				"')");
		k.sendObject(newA);
		k.reciveObject();
		newA=(Adress)k.getPackage();
		System.out.println("Wynik dodania adresu  "+newA.getQuery()+" "+newA.getId());
		
		try{
			System.out.println("!!!!!!!!!!!!Oczekuje");
			Thread.sleep(10000);
			}catch(InterruptedException e){
				
			}
			
			if (newU.getRestriction()>KindRestriction.LOGED_R){
				Priest newP = new Priest();
				newP.setPesel("900122");
				newP.setName("AdamKSIADZ");
				newP.setSurName("Milk");
				newP.setPossition("Ksiadz");
				newP.setSecularityDate(Pomoc.podajDate("1990-12-20"));
				newP.setArrivalDate(Pomoc.podajDate("2002-08-10"));
				newP.setKindQuery(KindQuery.ADD_DBASE);
				newP.setQuery("INSERT INTO Priest VALUES (" +
						newP.getPesel()+","+
						newU.getId()+","+
						newA.getId()+",'"+
						newP.getName()+"','"+
						newP.getSurName()+"','"+
						newP.getPosition()+"',"+
						"to_date('"+newP.getArrivalDate().toLocaleString().substring(0, 10)+"','yyyy-MM-dd'),"+
						"to_date('"+newP.getSecularityDate().toLocaleString().substring(0, 10)+"','yyyy-MM-dd')"+
						")");
				System.out.println(newP.getQuery());
				
				k.sendObject(newP);
				k.setNullPackage();
				k.reciveObject();
				
				newP = (Priest)k.getPackage();
				System.out.println("Wynik dodania parafianina"+newP.getQuery());
		
			}
		
		*/
		
		/*DODANIE PARAFIANINA (ze sprawdzeniem
		 * czy taki juz jest, i ze zwrotem jego id po dodaniu
		 * (wszystkim zajmuje sie serwer))
		
		User newU = new User();
		newU.setLogin("Xx");
		newU.setPassword("P");
		newU.setRestriction(KindRestriction.LOGED_R);
		newU.setRange(KindRange.LOGG_RANG);
		newU.setKindQuery(KindQuery.ADD_DBASE);
		newU.setQuery("INSERT INTO Userr VALUES (" +
				"seq_userr.nextval,'"+
				newU.getLogin()+"','"+
				newU.getPassword()+"',"+
				newU.getRestriction()+","+
				newU.getRange()
				+")");
		System.out.println("^^^^^^^^^^^^"+newU.getQuery());
		k.sendObject(newU);
		k.reciveObject();
		newU = (User)k.getPackage();
		System.out.println("Wynik dodania uzytkownika  "+newU.getQuery());
		
		Adress newA = new Adress();
		newA.setCity("Kielce");
		newA.setHouseNumb("12A");
		newA.setPostcode("14-111");
		newA.setStreet("Wieczorna");
		newA.setKindQuery(KindQuery.ADD_DBASE);
		newA.setQuery("INSERT INTO Adress VALUES (" +
				"seq_adress.nextval,'" +
				newA.getCity()+"','"+
				newA.getStreet()+"','"+
				newA.getHouse()+"','"+
				newA.getPostcode()+
				"')");
		k.sendObject(newA);
		k.reciveObject();
		newA=(Adress)k.getPackage();
		System.out.println("Wynik dodania adresu  "+newA.getQuery()+" "+newA.getId());
		
		Course newC = new Course();
		newC.setBirthday(Pomoc.podajDate("1990-12-20"));
		newC.setBaptism(Pomoc.podajDate("1991-01-11"));
		newC.setKindQuery(KindQuery.ADD_DBASE);
		newC.setQuery("INSERT INTO Course VALUES (" +
				"seq_course.nextval," +
				"to_date('"+newC.getBirthDay().toLocaleString().substring(0, 10)+"','yyyy-MM-dd')"+
				",to_date('"+newC.getBaptism().toLocaleString().substring(0, 10)+"','yyyy-MM-dd'),"+
				"null,null,null,null)");
		
		System.out.println(newC.getQuery());
		
		k.sendObject(newC);
		k.setNullPackage();
		k.reciveObject();
		newC = (Course)k.getPackage();
		System.out.println("Wynik dodania przebiegu  "+newC.getQuery()+" "+newC.getId());

		try{
		System.out.println("!!!!!!!!!!!!Oczekuje");
		Thread.sleep(10000);
		}catch(InterruptedException e){
			
		}
		
		if (newU.getRestriction()==KindRestriction.LOGED_R){
			Parishioner newP = new Parishioner();
			newP.setPesel("90012");
			newP.setName("Adam");
			newP.setSurName("Milk");
			newP.setKindQuery(KindQuery.ADD_DBASE);
			newP.setQuery("INSERT INTO Parishioner VALUES (" +
					newP.getPesel()+","+
					newC.getId()+","+
					newU.getId()+","+
					newA.getId()+",'"+
					newP.getName()+"','"+
					newP.getSurName()+"'"+
					")");
			System.out.println(newP.getQuery());
			
			k.sendObject(newP);
			k.setNullPackage();
			k.reciveObject();
			
			newP = (Parishioner)k.getPackage();
			System.out.println("Wynik dodania parafianina"+newP.getQuery());
		}*/
		
		
		
		/*UPDATE UZYTKOWNIKA------------------------
		u.setKindQuery(KindQuery.UPD_DBASE);
		String newPass="ania";
		u.setQuery("UPDATE Userr SET password='"+newPass+"' WHERE id_userr="+u.getId());
		System.out.println(u.getQuery());
		k.setNullPackage();
		k.sendObject(u);
		k.reciveObject();
		u=(User)k.getPackage();
		if(u.getQuery().equals("OK+")) System.out.println("Update hasla ok"); else
			System.out.println("Blad updatu hasla");
		*/
		
		
		/*Poczatek zlozenia zamowienia
		Order o = new Order();
		o.setKindQuery(1); //dodanie do bazy
		o.setSenderPesel(p.getPesel());
		o.setExecutorPesel("69010566803");
		o.setBeginDate(Pomoc.podajDate("2020-06-30 12:00"));
		o.setEndDate(Pomoc.podajDate("2012-12-16"));
		o.setEvent("3");//3 to msza wg Pawla bazy
		o.setDescribe("jakis tam opis");
		o.setStatus(KindRange.NEW);
		String q;
		
		q="INSERT INTO Orderr VALUES (seq_orderr.nextval,3,'"+o.getExecutroPesel()+"','"+
		o.getSenderPesel()+"','"+o.getDescribe()+"','"+o.getStatus()+"',"+
		"to_date('"+o.getBeginDate().toLocaleString().substring(0, 16)+
		"','yyyy-MM-dd HH24:MI'),"+"to_date('"+o.getEndDate().toLocaleString().substring(0, 10)+
		"','yyyy-MM-dd'))";
		o.setQuery(q);
		
		
		
		k.sendObject(o);
		k.reciveObject();
		o = (Order)k.getPackage();
		System.out.println("KLIENT:  (otrzymana odpowiedz)"+o.getData());
		System.out.println(q);
		*/
		
		/*Poczatek pobrania listy zamowien---------------------------------
		Order o = new Order();
		o.setKindQuery(KindQuery.SEL_DBASE);
		//przykladowe zapytanie(POBIERA WSZYSTKIE ZAMOWIENIA ZLOZONE PRZEZ PARAFIANINA)
		o.setQuery("Select id_orderr,id_event," +
				"odprawiajacy_pesel,zamawiajacy_pesel," +
				"describe,status ,"+
				"to_char(beginD,'yyyy-MM-dd HH24:MI')," +
				"to_char(endD, 'yyyy-MM-dd HH24:MI') " +
				"from orderr where zamawiajacy_pesel="+p.getPesel());
		System.out.println(o.getQuery());
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
		*/
		
		
		/*Pobranie szczegolowych danych na temat parafianina--------------
		p.setKindQuery(KindQuery.SEL_DBASE);
		p.setQuery("Select * from parishioner where pesel="+p.getPesel());
		k.sendObject(p);
		k.reciveObject();
		p=(Parishioner)k.getPackage();
		
		System.out.println("ZAMIESZKALY: "+p.getAdress().getCity()+"    "+p.getAdress().getPostcode());
		System.out.println("URODZONY : "+p.getCourse().getBirthDay().toLocaleString());
		*/
		
		/*Pobranie dostepnych rodzajow zdarzen z bazy---------------------
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
		  */
		
		  /*Pobranie listy aktualnosci
		  Actuals act = new Actuals();
		  act.setKindQuery(KindQuery.SEL_DBASE);
		  act.setQuery("Select * from actuals");
		  k.sendObject(act);
		  
		  k.reciveObject();
		  LinkedList<Actuals> actL = new LinkedList<Actuals>();
		  actL=(LinkedList<Actuals>)k.getPackage();
		  Iterator<Actuals> itA = actL.iterator();
		  while(itA.hasNext()){
			  Actuals ae = itA.next();
			  System.out.println(ae.getDescribe());
		  }
		  */
		  
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
		*/
		
		/*Poczatek wylogowania--------------------------------------------*/
		
		
		/*Usuwanie zamowien sterowane poprzez odpwiednie zapytanie
				Order o = new Order();
				o.setKindQuery(KindQuery.SEL_DBASE);
				//*przykladowe zapytanie(POBIERA WSZYSTKIE ZAMOWIENIA ZLOZONE PRZEZ PARAFIANINA)
				o.setQuery("Select id_orderr,id_event," +
						"odprawiajacy_pesel,zamawiajacy_pesel," +
						"describe,status ,"+
						"to_char(beginD,'yyyy-MM-dd HH24:MI')," +
						"to_char(endD, 'yyyy-MM-dd HH24:MI') " +
						"from orderr where zamawiajacy_pesel="+p.getPesel()+
						" AND status='"+KindQuery.TODEL+"'");
				
				System.out.println(o.getQuery());
				k.sendObject(o);
				
				k.reciveObject();
				
				LinkedList<Order> orderList = new LinkedList<Order>();
				
				orderList = (LinkedList<Order>)k.getPackage();
				Iterator<Order> iterator = orderList.iterator();
				
				while(iterator.hasNext()){
					Order tmp =iterator.next();
					System.out.println(tmp.getDescribe()+"    "+tmp.getStatus());
				}
				System.out.println(orderList.size());
				
				Iterator<Order> iterator2 = orderList.iterator();
				
				while(iterator2.hasNext()){
					Order tmp =iterator2.next();
					tmp.setKindQuery(KindQuery.DEL_DBASE);
					tmp.setQuery("DELETE FROM Orderr where id_orderr="+tmp.getId());
					k.sendObject(tmp);
					k.reciveObject();
					Order check = (Order)k.getPackage();
					System.out.println("Wynik usuwania "+check.getQuery());
				}*/
				
		
		
		
		
		//----------------------------------------------------------
		/*
		//###################DODANIE AKTUALNOSCI#####################
				Actuals akt = new Actuals();
				akt.setKindQuery(KindQuery.ADD_DBASE);
				akt.setSubject("jakis temat");
				akt.setDescribe("jakis opis");
				akt.setPriestPesel("59040665847");
				akt.setAddDate(Pomoc.podajDate("1990-12-20"));
				
				akt.setQuery("INSERT INTO Actuals VALUES (" + 
				"seq_actuals.nextval,"+
				akt.getPriestPesel()+",'"+
				akt.getSubject()+"','"+
				akt.getDescribe()+"',"+
				"to_date('"+akt.getAddDate().toLocaleString().substring(0, 16)+"','yyyy-MM-dd HH24:MI'))");
			
						/*q="INSERT INTO Orderr VALUES (seq_orderr.nextval,3,'"+o.getExecutroPesel()+"','"+
								o.getSenderPesel()+"','"+o.getDescribe()+"','"+o.getStatus()+"',"+
								"to_date('"+o.getBeginDate().toLocaleString().substring(0, 16)+
								"','yyyy-MM-dd HH24:MI'),"+"to_date('"+o.getEndDate().toLocaleString().substring(0, 10)+
								"','yyyy-MM-dd'))";
				
				//System.out.println("^^^^^^^^^^^^" + akt.getQuery());
				//k.sendObject(akt);
				//if(!k.sendObject(akt)){
					//this.connectionError();
				//}
		*/
		
		p.setKindQuery(-1);
		k.sendObject(p);
		k.reciveObject();
		
	    p = (Parishioner)k.getPackage();
		
		System.out.println("KLIENT:  (otrzymana odpowiedz)"+p.getData());
		
	}
	
	
	//probaEnd
	
	
		
}
