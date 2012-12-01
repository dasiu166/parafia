import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import javax.swing.JOptionPane;

public class Client {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		InetAddress addr = InetAddress.getByName("localhost"); // pobranie adresu sieciowego
		
		Socket socket = new Socket(addr, Serwer.PORT); // otwarcie polaczenia
		try {
			// utworzenie strumieni
			ObjectOutputStream wychodzace = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream przychodzace = new ObjectInputStream(socket.getInputStream());
			
			wychodzace.writeObject((Object)"Czesc"); //rzutowanie w gore na klase bazow object
		} finally {
			// zamkniecie polaczenia
			socket.close();
		}
	}
}
