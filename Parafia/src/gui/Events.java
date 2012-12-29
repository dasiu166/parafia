package gui;

import java.io.IOException;
import java.net.UnknownHostException;

import klient.Client;
import obsluga.Parishioner;
import obsluga.Priest;
import obsluga.User;
import pomoce.Pomoc;
import stale.KindQuery;
import stale.KindRestriction;

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
		//if(!k.getIsConnected()){ JOptionPane.showMessageDialog(null, "B³¹d po³¹czenia z serverem", "Error connect", JOptionPane.ERROR_MESSAGE); }
	}
	
	/**
	 * @return <b>Events</b> - SINGLETON<br />
	 * 			Zwraca instancje siebie lub tworzy naw¹ je¿eli nie istnieje
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
	 * @return <b>NewsList</b> - liste Aktualnoœci
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public NewsList getNewsList() throws ClassNotFoundException, IOException{
		newsList.generateNewsList(5); //pobranie przyk³adowej listy aktualnoœci
		//newsList.generateNewsList(5);
		return newsList;
	}
	
	
	/**
	 * @param login :String
	 * @param haslo :String
	 * @return <b>true</b> - je¿eli logowanie siê powiod³o<br />
	 * 			<b>false</b> - je¿eli logowanie siê nie powiod³o<br /><br />
	 * 		uzupe³nia tekrze odpowiednie obiekty parafianina i ksiêdza w zale¿noœci od uprawniej urzytkownika
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
			p.setRestriction(KindRestriction.GUEST_R);
			return false;
		}			
		
		System.out.println("Prawa dostepu: "+u.getRestriction()+" Ranaga: "+u.getRange());
		
		try {
			if(!k.reciveObject()) return false;
		} catch (ClassNotFoundException e){e.printStackTrace();}catch(IOException e){e.printStackTrace();}
		
		if(u.getRestriction() == KindRestriction.LOGED_R){
			p = (Parishioner)k.getPackage();
			System.out.println("Parishioner Zalogowano jako: "+p.getName()+" "+p.getSurName()+"\n"+" Pesel: "+p.getPesel());
		}else if(u.getRestriction() == KindRestriction.WORKS_R || u.getRestriction() == KindRestriction.GOD_R){
			priest = (Priest)k.getPackage();
			System.out.println("Priest Zalogowano jako: "+priest.getName()+" "+priest.getSurName()+"\n"+" Adres: "+priest.getAdress().getCity()+" Pesel: "+priest.getPesel());
		}else{
			System.out.println("B£¹d logowania");
			return false;
		}
		
		logged = true;
		return true;
	}

	/**
	 * @return <b>true</b> - je¿eli urzytkownik zosta³ wylogowany<br />
	 * 			<b>false</b> - je¿eli wylogowanie siê nie powiod³o<br /><br />
	 * 		ustawia takrze obiekty klasy do stanu pocz¹tkowego w³aœciwego dla urzytkownika bez uprawnieñ
	 */
	public boolean wyloguj(){
		p.setKindQuery(KindQuery.TRY_LOGOUT);
		try {
			if(!k.sendObject(p)) return false;
		} catch (IOException e) {e.printStackTrace();}
		try {
			if(!k.reciveObject()) return false;
		} catch (ClassNotFoundException e) {e.printStackTrace();} catch (IOException e) {e.printStackTrace();}
		
		p = (Parishioner)k.getPackage();
		p = new Parishioner();
		logged = false;
		return true;
	}
	
	public void pobierzDane() throws IOException, ClassNotFoundException{
		p.setKindQuery(KindQuery.SEL_DBASE);
		p.setQuery("Select * from parishioner where pesel="+p.getPesel());
		k.sendObject(p);
		k.reciveObject();
		p=(Parishioner)k.getPackage();
		
		System.out.println("ZAMIESZKALY: "+p.getAdress().getCity()+"    "+p.getAdress().getPostcode());
		System.out.println("URODZONY : "+p.getCourse().getBirthDay().toLocaleString());
		
	}
	
	
	
	/**
	 * @return <b>Parishioner</b> - zwraca aktualnego uzytkownika
	 */
	public Parishioner getParishioner(){
		return p;
	}
	
	/**
	 * @return <b>Priest</b> - zwraca aktualnego ksiêdza
	 */
	public Priest getPriest(){
		return priest;
	}
	
	/**
	 * @return 	<b>true</b> - je¿eli u¿ytkownik jest zalogowany<br />
	 * 			<b>false</b> -  je¿eli u¿ytkownik jest zalogowany
	 */
	public boolean getLogged(){
		return logged;
	}
	
	/**
	 * @return 	zwraca poziom uprawnieñ aktualnego u¿ytkownika<br />
	 * 			KindRestriction.GUEST_R <br \>
	 * 			KindRestriction.LOGED_R <br \>
	 * 			KindRestriction.WORKS_R <br \>
	 * 			KindRestriction.GOD_R <br \>
	 */
	public int getRestriction(){
		return u.getRestriction();
	}
	
}
