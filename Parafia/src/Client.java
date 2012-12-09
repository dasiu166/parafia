import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
//import java.net.InetAddress;
//import java.net.Socket;
import java.net.*;
import javax.swing.JOptionPane;
import java.util.*;
import java.io.*;

public class Client {
	//probaStart
	private InetAddress addr;
	private Socket socket = null;
	private boolean isConnected = false; //informuje czy klient jest polaczony
	private Object przesylka = null; //sluzy do przyjmowania przyslanych obiektow
	
	boolean connect(int port) throws UnknownHostException, IOException
	{	/*laczy sie na podstawie podanego portu na localhoscie (pozniej do zmiany ofcoz)
	 		pobiera informacje o hoscie i binduje socket;
	 		zwraca true jezeli sie polaczyl;
	 		zbieranie wyjatkow dziala;*/
		
		try{
		addr = InetAddress.getByName("localhost");
		
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
	
	Object getPackage(){ //zwraca nasza przesylke
		return przesylka;
	}
	
	boolean sendObject(Object object) throws IOException{
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
	
	boolean reciveObject() throws IOException, ClassNotFoundException
	{	/*odbiera przesylke i zwraca jako zwykly obiekt
	 	trzeba sobie samemu zrzutowac pozniej;
	 	JEST TO FUNKCJA BLOKUJACA, dopoki nie otrzyma zwrotu to reszta stoi*/
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
			return false;
		}//koniec try dla odbierania przesylki
		
		
		
		return true;
	}
	
	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException{
		Client k = new Client();
		Logowanie l;
		
		l = new Logowanie();
		l.setName("ALa");
		l.setPass("111");
		
		k.isConnected = k.connect(Serwer.PORT);
		if (k.isConnected==false) System.out.println("Klient - niepolaczony");
			else System.out.println("Klient - polaczony");
		
		k.sendObject(l); //wysyla sie bo wszsytkie obiekty dziedzicza po Object
		k.sendObject(l);
		
		k.reciveObject();/*tu uwaga panowie bo sie blokuje az nie otrzyma jakiejs przesylki*/
		
		if (k.getPackage() instanceof Logowanie){//ponizej przyklad rzutowania obiektu na konkretna klase
			if (((Logowanie)k.getPackage()).acces) System.out.println("Jestem zalogowany");
			else  System.out.println("Nie zalogowano mnie");
		}
	}
	
	
	//probaEnd
	
	
	/*
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Logowanie l;
		
		InetAddress addr = InetAddress.getByName("localhost"); // pobranie adresu sieciowego
		
		Socket socket = new Socket(addr, Serwer.PORT); // otwarcie polaczenia
		try {
			// utworzenie strumieni
			//FileInputStream plikPrzychodzacy = new FileInputStream("in.ser");
			//FileOutputStream plikWychodzacy = new FileOutputStream("out.ser");
			
			
			
			
			ObjectOutputStream wychodzace = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream przychodzace = new ObjectInputStream(socket.getInputStream());
			
			l = new Logowanie();
			l.setName("ALa");
			l.setPass("111");
			
			
			wychodzace.writeObject((Object)l); //rzutowanie w gore na klase bazow object
		} finally {
			// zamkniecie polaczenia
			socket.close();
		}
	}
	*/
}
