package gui;


import java.io.IOException;
import java.net.UnknownHostException;
import javax.swing.JOptionPane;
import klient.Client;
import obsluga.*;
import pomoce.Pomoc;
import stale.*;

import java.util.*;

public class Events {
	private static volatile Events INSTANCE;
	private Client k = new Client();
	private Parishioner p = new Parishioner();
	private Priest priest;
	private User u = new User();
	private String adr;
	private int portt;
	private boolean logged = false;
	private NewsList newsList = new NewsList();
	
	/**
	 * CONSTRUCTOR - prywatny do SINGLETON
	 */
	
	private Events(){
		try {
			adr = Pomoc.loadFromFile("client.ini", "SERWERADRES");
		} catch(IOException e){e.printStackTrace();} //pobranie adresu
		try {
			portt = Integer.parseInt(Pomoc.loadFromFile("client.ini", "PORT"));
		}catch(NumberFormatException e){e.printStackTrace();}catch(IOException e){e.printStackTrace();} //pobranie portu
		
		try {
			k.setIsConnected(k.connect(adr,portt));
			
		} catch (UnknownHostException e) {e.printStackTrace();} catch (IOException e) {e.printStackTrace();}
		if (k.getIsConnected()==false) System.out.println("Klient - niepolaczony");
			else System.out.println("Klient - polaczony");
		//if(!k.getIsConnected()){ JOptionPane.showMessageDialog(null, "B��d po��czenia z serverem", "Error connect", JOptionPane.ERROR_MESSAGE); }
	}
	
	public User getUser(){
		return u;
	}
	/**
	 * @return <b>Events</b> - SINGLETON<br />
	 * 			Zwraca instancje siebie lub tworzy naw� je�eli nie istnieje
	 */
	public static Events getInstance(){
		if(INSTANCE == null)
			synchronized(Events.class) {
				if (INSTANCE == null)
		             INSTANCE = new Events();
			}
		return INSTANCE;
	}
	
	/**
	 * @return <b>NewsList</b> - liste Aktualno�ci
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public NewsList getNewsList() throws ClassNotFoundException, IOException{
		newsList.generateNewsList(5); //pobranie przyk�adowej listy aktualno�ci
		//newsList.generateNewsList(5);
		return newsList;
	}
	
	
	/**
	 * @param login :String
	 * @param haslo :String
	 * @return <b>true</b> - je�eli logowanie si� powiod�o<br />
	 * 			<b>false</b> - je�eli logowanie si� nie powiod�o<br /><br />
	 * 		uzupe�nia tekrze odpowiednie obiekty parafianina i ksi�dza w zale�no�ci od uprawniej urzytkownika
	 */
	public boolean zaloguj(String login, String haslo){
		u.setKindQuery(KindQuery.TRY_LOGIN); //zapytanie = logowanie
		u.setLogin(login);
		u.setPassword(haslo);
		
		u.setQuery("SELECT * FROM userr WHERE login = '"+u.getLogin()+"' AND password = '"+u.getPassword()+"'");
		
		System.out.println("Zapytanie: \n"+u.getQuery());
		
		try {
			if(!k.sendObject(u)) return false;
		} catch (IOException e){e.printStackTrace();} //wysyla sie bo wszsytkie obiekty dziedzicza po Object
		try {
			if(!k.reciveObject()) return false;
		} catch (ClassNotFoundException e){e.printStackTrace();}catch(IOException e){e.printStackTrace();}
		
		u = (User)k.getPackage();
		
		if(u.getQuery().equals("ERR")){
			/*Gdy login/haslo bledne*/
			System.out.println("Nie mozna sie zalogowac (zly login/haslo)");
			u.setRestriction(KindRestriction.GUEST_R);
			//p.setRestriction(KindRestriction.GUEST_R);
			return false;
		}			
		
		System.out.println("Prawa dostepu: "+u.getRestriction()+" Ranaga: "+u.getRange());
		
		try {
			if(!k.reciveObject()) return false;
		} catch (ClassNotFoundException e){e.printStackTrace();}catch(IOException e){e.printStackTrace();}
		
		System.out.println("%%%%%%%%%%%%%%%  "+u.getRestriction());
		
		if(u.getRestriction() == KindRestriction.LOGED_R){
			p = (Parishioner)k.getPackage();
			System.out.println("Parishioner Zalogowano jako: "+p.getName()+" "+p.getSurName()+"\n"+" Pesel: "+p.getPesel());
		}else if(u.getRestriction() == KindRestriction.WORKS_R || u.getRestriction() == KindRestriction.GOD_R){
			priest = (Priest)k.getPackage();
			System.out.println("Priest Zalogowano jako: "+priest.getName()+" "+priest.getSurName()+"\n"+" Adres: "+" Pesel: "+priest.getPesel());
		}else{
			System.out.println("B��d logowania");
			return false;
		}
		
		logged = true;
		
		return true;
	}

	/**
	 * @return <b>true</b> - je�eli urzytkownik zosta� wylogowany<br />
	 * 			<b>false</b> - je�eli wylogowanie si� nie powiod�o<br /><br />
	 * 		ustawia takrze obiekty klasy do stanu pocz�tkowego w�a�ciwego dla urzytkownika bez uprawnie�
	 */

	public boolean wyloguj(){
		int restriction = getRestriction();
		if(restriction==KindRestriction.LOGED_R){
		p.setKindQuery(KindQuery.TRY_LOGOUT);
		try {
			if(!k.sendObject(p)) return false;
		} catch (IOException e) {e.printStackTrace();}
		try {
			if(!k.reciveObject()) return false;
		} catch (ClassNotFoundException e) {e.printStackTrace();} catch (IOException e) {e.printStackTrace();}
		
		p = (Parishioner)k.getPackage();
		p = new Parishioner();
		u.setRestriction(KindRestriction.GUEST_R);
		} else {
			priest.setKindQuery(KindQuery.TRY_LOGOUT);
			try {
				if(!k.sendObject(priest)) return false;
			} catch (IOException e) {e.printStackTrace();}
			try {
				if(!k.reciveObject()) return false;
			} catch (ClassNotFoundException e) {e.printStackTrace();} catch (IOException e) {e.printStackTrace();}
			
			priest = (Priest)k.getPackage();
			priest = new Priest();
			u.setRestriction(KindRestriction.GUEST_R);

		}
		logged = false;
		return true;
	}

	public void pobierzDane() throws IOException, ClassNotFoundException{
		int restriction = getRestriction();
		if (restriction==KindRestriction.LOGED_R){
			p.setKindQuery(KindQuery.SEL_DBASE);
			p.setQuery("Select * from parishioner where pesel="+p.getPesel());
			k.sendObject(p);
			k.reciveObject();
			p=(Parishioner)k.getPackage();
		
		//System.out.println("ZAMIESZKALY: "+p.getAdress().getCity()+"    "+p.getAdress().getPostcode());
		//System.out.println("URODZONY : "+p.getCourse().getBirthDay().toLocaleString());
		}
		
		if (restriction>=KindRestriction.WORKS_R){
			priest.setKindQuery(KindQuery.SEL_DBASE);
			priest.setQuery("Select * from priest where pesel="+priest.getPesel());
			JOptionPane.showMessageDialog(null, "Select * from priest where pesel=\""+priest.getPesel()+"\"");
			k.sendObject(priest);
			k.reciveObject();
			priest=(Priest)k.getPackage();
		}
	}
	
	public void pobierzZdarzenia() throws IOException, ClassNotFoundException{
		  Event e = new Event();
		  e.setKindQuery(KindQuery.SEL_DBASE);
		  e.setQuery("Select * from event");
		  
		  k.sendObject(e);
		  k.reciveObject();
		  LinkedList<Event> le = new LinkedList<Event>();
		  le = (LinkedList<Event>)k.getPackage();
		  k.setEventKindList(le);
	}
	
	
	public void zlozZamowienie(String prPesel, String date, String idEvent, String desc)
			throws IOException, ClassNotFoundException{
		int restriction = getRestriction();
		Order o = new Order();
		o.setKindQuery(KindQuery.ADD_DBASE); //dodanie do bazy
		if (restriction==KindRestriction.LOGED_R) o.setSenderPesel(p.getPesel());
		if (restriction>KindRestriction.LOGED_R) o.setSenderPesel(priest.getPesel());

		o.setExecutorPesel(prPesel);
		o.setBeginDate(Pomoc.podajDate(date));
		o.setEndDate(Pomoc.podajDate(date));
		o.setEvent(idEvent);//3 to msza wg Pawla bazy
		o.setDescribe(desc);
		o.setStatus(KindRange.NEW);
		String q;
		
		q="INSERT INTO Orderr VALUES (seq_orderr.nextval,3,'"+o.getExecutroPesel()+"','"+
		o.getSenderPesel()+"','"+o.getDescribe()+"','"+o.getStatus()+"',"+
		"to_date('"+o.getBeginDate().toLocaleString().substring(0, 16)+
		"','yyyy-MM-dd HH24:MI'),"+"to_date('"+o.getEndDate().toLocaleString().substring(0, 10)+
		"','yyyy-MM-dd HH24:MI'))";
		o.setQuery(q);
		
		k.sendObject(o);
		k.reciveObject();
		o = (Order)k.getPackage();
		
		System.out.println("KLIENT:  (otrzymana odpowiedz)"+o.getData());
		System.out.println(q);
	
	}
	
	public void dodajUzytkownika(User newU, Adress newA, Course newC, Parishioner newP)
			throws IOException, ClassNotFoundException{
		/*Jako arametry chyba najlepeij przekazac bedzie gotowe obiekty bo inaczej
		 * to parametrow w ciul*/
		/*User newU = new User();
		newU.setLogin("Iipii");
		newU.setPassword("P");
		newU.setRestriction(KindRestriction.LOGED_R);
		newU.setRange(KindRange.LOGG_RANG);*/
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
		
		/*Adress newA = new Adress();
		newA.setCity("Kielce");
		newA.setHouseNumb("12A");
		newA.setPostcode("14-111");
		newA.setStreet("Wieczorna");*/
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
		
		/*Course newC = new Course();
		newC.setBirthday(Pomoc.podajDate("1990-12-20"));
		newC.setBaptism(Pomoc.podajDate("1991-01-11"));*/
		newC.setKindQuery(KindQuery.ADD_DBASE);
		//UWAGA zastanawiamsie nad sposobem sprwdzenia nullow w datach
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

		
		if (newU.getRestriction()==KindRestriction.LOGED_R){
			/*Parishioner newP = new Parishioner();
			newP.setPesel("900");
			newP.setName("Adam");
			newP.setSurName("Milk");*/
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
		}
	}
	
	public void updateUzytkownik(String login, String pass)
			throws IOException, ClassNotFoundException{
		
		u.setKindQuery(KindQuery.UPD_DBASE);
		String newPass=pass;
		String newLogin=login;
		u.setQuery("UPDATE Userr SET "+"login='"+newLogin+"',password='"+newPass+"' WHERE id_userr="+u.getId());
		System.out.println(u.getQuery());
		
		k.setNullPackage();
		k.sendObject(u);
		k.reciveObject();
		u=(User)k.getPackage();
		
		if(u.getQuery().equals("OK+")) System.out.println("Update hasla ok"); else
			System.out.println("Blad updatu hasla");
	}
	
	
	public Client getClient(){
		return k;
	}
	
	
	
	/**
	 * @return <b>Parishioner</b> - zwraca aktualnego uzytkownika
	 */
	public Parishioner getParishioner(){
		return p;
	}
	
	/**
	 * @return <b>Priest</b> - zwraca aktualnego ksi�dza
	 */
	public Priest getPriest(){
		return priest;
	}
	
	/**
	 * @return 	<b>true</b> - je�eli u�ytkownik jest zalogowany<br />
	 * 			<b>false</b> -  je�eli u�ytkownik jest zalogowany
	 */
	public boolean getLogged(){
		return logged;
	}
	
	/**
	 * @return 	zwraca poziom uprawnie� aktualnego u�ytkownika<br />
	 * 			KindRestriction.GUEST_R <br \>
	 * 			KindRestriction.LOGED_R <br \>
	 * 			KindRestriction.WORKS_R <br \>
	 * 			KindRestriction.GOD_R <br \>
	 */
	public int getRestriction(){
		return u.getRestriction();
	}
	
}
