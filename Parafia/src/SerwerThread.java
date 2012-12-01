import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

public class SerwerThread extends Thread{
	private Socket socket;
	private ObjectInputStream przychodzace;
	private ObjectOutputStream wychodzace;
	Object wiadomosc;
	
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
			
			while (true) {// glowna petla watka
				
			 wiadomosc = przychodzace.readObject();
			 System.out.println((String)wiadomosc.toString()); //rzutowanie w dol na konkretna klase
			
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
