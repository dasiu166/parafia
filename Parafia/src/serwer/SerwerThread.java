package serwer;
import obsluga.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.io.*;
import java.util.*;
import pomoce.Pomoc;

import pomoce.Pomoc;

public class SerwerThread extends Thread implements Serializable{
	private Socket socket;
	private ObjectInputStream przychodzace;
	private ObjectOutputStream wychodzace;
	Object wiadomosc; //zmienna zabezpieczajaca (na niej wykonywane sa informacje)
	//private LinkedList<Object> kolejka;
	
	int clientRestriction=0; //przetrzymuje uprawnienia, moze a nie musi sie przydac
	Date d = new Date(); //data dla logow
	private Object przesylka = null; //sluzy do przyjmowania przyslanych obiektow
	
	public SerwerThread(Socket s) throws IOException {
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
					 
					 if (((User) wiadomosc).getLogin().equals("Login")&& 
							 ((User) wiadomosc).getPassword().equals("Pass") ){ //tymczasowo
							 
							 ((User) wiadomosc).setRestriction(1);
							 clientRestriction=1;
							 
							 ((User) wiadomosc).setQuery("OK+");
							 this.sendObject(wiadomosc); //odpowiedz klas¹ user
							 
							 Parishioner p = new Parishioner();
							 p.setName("BOLEK");
							 p.setSurName("DEKIEL");
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
						 
					 } else {//kiedy haslo/login jest zly
						 ((User) wiadomosc).setQuery("ERR");
						 this.sendObject(wiadomosc);
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
