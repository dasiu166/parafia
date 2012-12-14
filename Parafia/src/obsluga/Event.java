package obsluga;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Event extends DataBaseElement {
	/*KLASA JAK NARZIE WYLACZONA Z UZYTKU!!!!!!!!!*/
	
	/*Klasa zwierajaca dane zdarzenie, co do opcji jakie moga tu wystapic to 
	 * beda one pobierane z bazy przez klienta zaraz po jego zalogowaniu
	 * (automatycznie), a tylko proboszcz bedzie mial mozliwosc dodania/usuniecia danego
	 * zdarzenia (w sensie opcji do wyboru) 
	 * (tylko ze to bedzie zwykly tekst i liczba mu odpowiadajaca)*/
	
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd kk:mm"); //parser formatu datu
	
	private int id; //id zdarzenia w bazie
	private String name; //nazwa
	private Date begin;
	
	public Event(){
		id=1;
		name = "event_test";
		
		try{
			begin = format.parse("1970-02-01 12:12");
		}catch(ParseException e){
			System.out.println("Blad konwersji daty i godziny");
		}
	}
	
	public Date getBegin(){
		return begin;
	}
	
	public boolean setQuery(String val){
		query = val;
		return true;
	}

	
	public static void main(String[] args){
		/*DLA TESTOW*/
		Event e = new Event();
	
	}
}
