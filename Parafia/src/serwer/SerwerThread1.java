package serwer;
import obsluga.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.io.*;
import java.util.*;

import pomoce.Pomoc;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import pomoce.Pomoc;


/*TESTY PIOTRKA*/
public class SerwerThread1 extends Thread implements Serializable{
	private Socket socket;
	private ObjectInputStream przychodzace;
	private ObjectOutputStream wychodzace;
	Object wiadomosc; //zmienna zabezpieczajaca (na niej wykonywane sa informacje)
	//private LinkedList<Object> kolejka;
	
	int clientRestriction=0; //przetrzymuje uprawnienia, moze a nie musi sie przydac
	Date d = new Date(); //data dla logow
	private Object przesylka = null; //sluzy do przyjmowania przyslanych obiektow
	
	public SerwerThread1(Socket s) throws IOException {
		socket = s;
		d.getTime();//pobranie daty i czasu
		// utworzenie strumieni
		//przychodzace = new ObjectInputStream(socket.getInputStream());
		//wychodzace = new ObjectOutputStream(socket.getOutputStream());
		// wystartowanie watka
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
			
			DbConnection.dbConn();
//=============================================================================
		int zalogowany=0;	
			
			
			
			
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
			 Thread.sleep(2000);
			
			
			 System.out.println("Sprawdzam parafianina");
			 
			 
			 if (wiadomosc instanceof User) {
				 
				 System.out.println("Przyszla wiadomosc 1");
				 if (((User) wiadomosc).getKindQuery()==0){
					 
					//LOG
					 Pomoc.writeToFile(Serwer.LOGDIRECTORY, "threadSerwer.log."+
								d.toLocaleString().substring(0, 10), d.toLocaleString()+
								": Watek:"+this.getName()+" "+
								" -> Klient proboje sie zalogowac");
					//LOG_END
					 
					 
					 Statement s = null;
						System.out.println("patrze w baze\n");
						try {
							s = DbConnection.conn.createStatement();   // tworzenie obiektu Statement przesylajacego zapytania do bazy conn
						    ResultSet dane,u;				
						    u=s.executeQuery("Select * from Userr");  // wykonanie kwerendy i przeslanie wynikow do obiektu ResultSet  
						   
						    
						    
						    System.out.println("przyslane: "+((User) wiadomosc).getLogin()+" "+((User) wiadomosc).getPassword());
						    while (u.next()) {
			
				                String userr = u.getString("LOGIN"); 
				                String passs = u.getString("PASSWORD");
				                String userID=u.getString("ID_USERR");
				                
				                System.out.println("pobrane z bazy: "+userr+" "+passs);
					 
				                Thread.sleep(2000);
					 
					 if (((User) wiadomosc).getLogin().equals(userr)&& 
							 ((User) wiadomosc).getPassword().equals(passs) ){ //tymczasowo
							 
						 System.out.println("dane sie zgadzaja\n ZALOGOWANO");
						 
							 ((User) wiadomosc).setRestriction(1);
							 clientRestriction=1;
							 
							 ((User) wiadomosc).setQuery("OK+");
							 this.sendObject(wiadomosc); //odpowiedz klas¹ user
							 
							 
							 System.out.println("pobieram dane\n");
							 dane=s.executeQuery("Select * from Parishioner where id_userr="+userID);
				             dane.next();
							 String name = dane.getString("NAME");
							 String surname = dane.getString("SURNAME");
							 String pesel = dane.getString("PESEL");
							 
							 //System.out.println("pobrane dane "+name+""+surname);
							 
							 Parishioner p = new Parishioner();
							 p.setName(name);
							 p.setSurName(surname);
							 p.setPesel(pesel);
							 p.setAdress(new Adress());
							 p.setRestriction(clientRestriction);
							 p.setQuery("OK+");
							 
							 Thread.sleep(2000);
							 this.sendObject(p); //odpowiedz klas¹ parishioner
							
							 //LOG
							 Pomoc.writeToFile(Serwer.LOGDIRECTORY, "threadSerwer.log."+
										d.toLocaleString().substring(0, 10), d.toLocaleString()+
										": Watek:"+this.getName()+" "+
										" -> Klient sie zalogowal");
							 //LOG_END
						 
						 zalogowany=1;
					 } 
					
				 }
						    if(zalogowany==0) {//kiedy haslo/login jest zly
						    	System.out.println("doszlo do konca i wysyla err");
								 ((User) wiadomosc).setQuery("ERR");
								 this.sendObject(wiadomosc);
							 }
				 }
						catch (SQLException e) {
						System.out.println("Blad odczytu z bazy! " +e.toString());
						System.exit(3);
					}	
					 
				 }// koniec logowania
			 } else //konice usera
				
				if (wiadomosc instanceof Parishioner){
				 
				 if (((Parishioner) wiadomosc).getKindQuery()==-1){
					 
					 ((Parishioner) wiadomosc).setRestriction(0);
					 ((Parishioner) wiadomosc).setData("Wylogowano CIE");
					 ((Parishioner) wiadomosc).setQuery("OK+");
					 this.sendObject(wiadomosc);
					 
					//LOG
					 Pomoc.writeToFile(Serwer.LOGDIRECTORY, "threadSerwer.log."+
								d.toLocaleString().substring(0, 10), d.toLocaleString()+
								": Watek:"+this.getName()+" "+
								" -> Klient sie wylogowal");
					//LOG_END
				 
				 }//koniec wylogowywania
			}else
			 
			 if (wiadomosc instanceof Order){
				 
/*---------------*/if(((Order) wiadomosc).getKindQuery()==1){ //dodanie do bazy
				 System.out.println("Przyszla wiadomosc 2"); //tymczasem
				 System.out.println("Zamowienie zlozyl: "+((Order) wiadomosc).getSenderPesel()+
					" "+((Order) wiadomosc).getEvent()+"\n"+
						 "Odprawia "+((Order)wiadomosc).getExecutroPesel()+
						 " Kiedy: "+((Order)wiadomosc).getBeginDate());
				 
				
				 
				 String sPesel=((Order) wiadomosc).getSenderPesel();
				 String ePesel=((Order) wiadomosc).getExecutroPesel();
				 String event=((Order) wiadomosc).getEvent();
				 String describe=((Order) wiadomosc).getDescribe();
				 Date bDate=((Order) wiadomosc).getBeginDate();
				 Date eDate=((Order) wiadomosc).getEndDate();
				 
				 Statement dod = null;
				 dod = DbConnection.conn.createStatement();
				 //String dodaj=("insert into orderr values (6,"+event+","+ePesel+","+sPesel+","+describe+"," +
				 	//"to_date('2003/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),to_date('2003/05/04 21:02:44', 'yyyy/mm/dd hh24:mi:ss'))");
	             
String dodaj="INSERT INTO orderr (id_orderr,id_event,odprawiajacy_pesel,zamawiajacy_pesel,describe) VALUES"+ 
"(4,"+event+","+ePesel+","+sPesel+",'"+describe+"')";//,to_date('2003/05/03 21:02:44', 'yyyy/mm/dd)";
				
				 //int nr=6;
				 //String dodaj="Insert into test1 (id) values"+"("+nr+")";
				 System.out.println("zapisuje dane\n");
				 try {
						int insertedRows = dod.executeUpdate(dodaj);
						if (insertedRows!=0) System.out.println("dodano");
						else
							System.out.println("nie dodano");
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
				 
				 ((Order) wiadomosc).setData("ZAMOWIENIE ZLOZONE");
				 ((Order) wiadomosc).setQuery("OK+");
				 Thread.sleep(2000);
				 this.sendObject(wiadomosc);
				 
				//LOG
					Pomoc.writeToFile(Serwer.LOGDIRECTORY, "threadSerwer.log."+
							d.toLocaleString().substring(0, 10), d.toLocaleString()+
							": Watek:"+this.getName()+" "+
							" -> Klient zlozyl zamowienie (wyslano)");
				//LOG_END
				}//koniec skladania zamowienia
				 
/*--------------*/if(((Order) wiadomosc).getKindQuery()==4){ //select z bazy
					
					LinkedList<Order> orderList = new LinkedList<Order>();
					
					for (int i=0;i<50;i++) orderList.add(new Order());
					orderList.getFirst().setQuery("OK+");
					this.sendObject(orderList);
					
					Pomoc.writeToFile(Serwer.LOGDIRECTORY, "threadSerwer.log."+
							d.toLocaleString().substring(0, 10), d.toLocaleString()+
							": Watek:"+this.getName()+" "+
							" -> Klient pobral liste zamowien");
					
				}//koniec wysylania listy zamowien (docelowo po odczycie z bazy)
			
			 } else System.out.println("Nie rozpoznano");
			 
			}
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
