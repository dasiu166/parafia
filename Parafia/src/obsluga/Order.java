package obsluga;
import java.util.*;

public class Order {
	/*Zawiera tresc zamowienia (przystosowane dozlozenia jak narazie!)*/
	
	private Parishioner sender; //ten co nadal zamowienie
	private Adress adresSender; //adres nadawcy
	private Course courseSender; //przebieg zycia religijnego
	private LinkedList<Event> eventList; // lista zdarzen dla zamowienia
	
	private Parishioner executor;//ten co ma odprawic zamowienie
	
	public String[] zapytanie(){
		String tresc[] = new String[5]; 
		
		return tresc; 
	}
	
}
