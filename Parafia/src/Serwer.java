import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.Random;

public class Serwer {
	static final int PORT = 2000;	

	public static void main (String args[]) throws IOException{
		System.out.print(ReadFromFile.loadFromFile("serwer.ini","PORT"));
		try{
		ServerSocket serwerSocket = new ServerSocket(PORT); //zmienna gniazda
		
		
		try {
			while (true) {
				Socket socket = serwerSocket.accept(); // oczekiwanie na nadejscie polaczenia
				
				try {
				new SerwerThread(socket);// utworzenie watka serwera
				
			} catch (IOException e) {
					
					socket.close(); // zamkniecie polaczenia w przypadku wystapienia bledu
				}
			}
		} finally {
			serwerSocket.close();
		}//ss
	
	} catch(Exception e){ //zbiera blad podczas bindowania gniazda
		System.out.println("Blad serwera");
	
	}
	}		
}

