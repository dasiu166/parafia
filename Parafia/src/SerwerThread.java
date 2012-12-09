import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.io.*;
import java.util.*;

public class SerwerThread extends Thread implements Serializable{
	private Socket socket;
	private ObjectInputStream przychodzace;
	private ObjectOutputStream wychodzace;
	Object wiadomosc;
	private LinkedList<Object> kolejka;
	
	public SerwerThread(Socket s) throws IOException {
		socket = s;
		// utworzenie strumieni
		przychodzace = new ObjectInputStream(socket.getInputStream());
		wychodzace = new ObjectOutputStream(socket.getOutputStream());
		// wystartowanie watka
		start();
	}
	
	public void run() {
		try {
			System.out.println("Klient sie podlaczyl");
			//a
			while (true) {// glowna petla watka
				
			 wiadomosc = przychodzace.readObject();
			 
			
			 if (wiadomosc instanceof Logowanie) {
				 ((Logowanie) wiadomosc).show();
				 ((Logowanie) wiadomosc).setAcces(true);
			 }
			 
			 //((Logowanie)wiadomosc).show();
			 
			 Thread.sleep(5000);
			 
			 wychodzace.writeObject(wiadomosc);
			 
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
