package serwer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.*;
import pomoce.Pomoc;

import pomoce.ReadFromFile;


public class Serwer {
	protected static int PORT = 2000; //zawiera port serwera
	protected static String LOGDIRECTORY = ""; //zawiera folder glowny plikow log

	public static void main (String args[]) throws IOException{
		Date d = new Date();
		d.getTime();
		System.out.print(Pomoc.loadFromFile("serwer.ini","PORT"));
		PORT = Integer.parseInt(Pomoc.loadFromFile("serwer.ini","PORT")); //zczytanie portu z ini
		LOGDIRECTORY = Pomoc.loadFromFile("serwer.ini", "LOGDIRECTORY");
		
		Pomoc.writeToFile(LOGDIRECTORY, "mainSerwer.log."+
				d.toLocaleString().substring(0, 10), d.toLocaleString()+
				" -> Serwer uruchomiony na porcie : "+PORT);
		
		
		try{
		ServerSocket serwerSocket = new ServerSocket(PORT); //zmienna gniazda
		
		
		try {
			while (true) {
				Socket socket = serwerSocket.accept(); // oczekiwanie na nadejscie polaczenia
				
				try {
				
				Pomoc.writeToFile(LOGDIRECTORY, "mainSerwer.log."+
						d.toLocaleString().substring(0, 10), d.toLocaleString()+
						" -> Nowy watek dla klienta");
					
				new SerwerThread(socket);// utworzenie watka serwera
				
			} catch (IOException e) {
				//LOG
				Pomoc.writeToFile(LOGDIRECTORY, "mainSerwer.log."+
						d.toLocaleString().substring(0, 10), d.toLocaleString()+
						" -> Blad watku klienta");
				//LOG_END
				
					socket.close(); // zamkniecie polaczenia w przypadku wystapienia bledu
				}
			}
		} finally {
			
			//LOG
			Pomoc.writeToFile(LOGDIRECTORY, "mainSerwer.log."+
					d.toLocaleString().substring(0, 10), d.toLocaleString()+
					" -> Zamkniecie serwera");
			serwerSocket.close();
			//LOG_END
			
		}//ss
	
	} catch(Exception e){ //zbiera blad podczas bindowania gniazda
		
		//LOG
		Pomoc.writeToFile(LOGDIRECTORY, "mainSerwer.log."+
				d.toLocaleString().substring(0, 10), d.toLocaleString()+
				" -> BLAD (zajety port) -- Zamkniecie tego serwera serwera");
		//LOG_END
		
		System.out.println("Blad serwera");
	
	}
	}		
}

