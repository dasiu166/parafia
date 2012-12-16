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
					" -> Klient sie polaczyl : Watek:"+this.getName());
			//LOG_END
			
			while (true) {// glowna petla watka
				
			 //wiadomosc = przychodzace.readObject();
			 this.reciveObject();
			 wiadomosc = this.getPackage();
			 Thread.sleep(2000);
			
			
			 System.out.println("Sprawdzam parafianina");
			 if (wiadomosc instanceof Parishioner) {
				 
				 System.out.println("Przyszla wiadomosc 1");
				 if (((Parishioner) wiadomosc).getKindQuery()==0){
					 
					//LOG
						Pomoc.writeToFile(Serwer.LOGDIRECTORY, "threadSerwer.log."+
								d.toLocaleString().substring(0, 10), d.toLocaleString()+
								" -> Klient sie proboje zalogowac : Watek:"+this.getName());
					//LOG_END
					 
					 if (((Parishioner) wiadomosc).getPesel().equals("100")){
						 
						 if (((Parishioner) wiadomosc).getPass().equals("haslo")){
							 
							 ((Parishioner) wiadomosc).setRestriction(1);
							 clientRestriction=1;
							 
							 ((Parishioner) wiadomosc).setName("BOLEK");
							 ((Parishioner) wiadomosc).setSurName("DEKIEL");
							 ((Parishioner) wiadomosc).setAdress(new Adress());
							 
							 Thread.sleep(2000);
							 //wychodzace.writeObject(wiadomosc);
							 this.sendObject(wiadomosc);
							//LOG
								Pomoc.writeToFile(Serwer.LOGDIRECTORY, "threadSerwer.log."+
										d.toLocaleString().substring(0, 10), d.toLocaleString()+
										" -> Klient sie zalogowal (wyslano) : Watek:"+this.getName());
								//LOG_END
						 }
					 }
				 }// koniec logowania
				 
				 if (((Parishioner) wiadomosc).getKindQuery()==-1){
					 
					 ((Parishioner) wiadomosc).setRestriction(0);
					 ((Parishioner) wiadomosc).setData("Wylogowano CIE");
					 this.sendObject(wiadomosc);
					 
					//LOG
						Pomoc.writeToFile(Serwer.LOGDIRECTORY, "threadSerwer.log."+
								d.toLocaleString().substring(0, 10), d.toLocaleString()+
								" -> Klient sie wylogowal (wyslano) : Watek:"+this.getName());
					//LOG_END
				 
				 }//koniec wylogowywania
			 
				 //koniec parafianina
			 } else
			 
			 if (wiadomosc instanceof Order){
				 System.out.println("Przyszla wiadomosc 2");
				 
				 System.out.println("Zamowienie zlozyl: "+((Order) wiadomosc).getSenderPesel()+
					" "+((Order) wiadomosc).getEvent()+"\n"+
						 "Odprawia "+((Order)wiadomosc).getExecutroPesel()+
						 " Kiedy: "+((Order)wiadomosc).getBeginDate());
				 
				 ((Order) wiadomosc).setData("ZAMOWIENIE ZLOZONE");
				 Thread.sleep(2000);
				 //String p = new String("Zamowienie zlozone");
				 //wychodzace.writeObject(wiadomosc);
				 this.sendObject(wiadomosc);
				 
				//LOG
					Pomoc.writeToFile(Serwer.LOGDIRECTORY, "threadSerwer.log."+
							d.toLocaleString().substring(0, 10), d.toLocaleString()+
							" -> Klient zlozyl zamowienie (wyslano) : Watek:"+this.getName());
				//LOG_END
				
				 //koniec skladania zamowienia
			} else System.out.println("Nie rozpoznano");
			 
			
			 
			 //Thread.sleep(5000);
			 
			 
			 
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
