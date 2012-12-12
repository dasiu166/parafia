package obsluga;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Event {
	/*Klasa zwierajaca dane zdarzenie, co do opcji jakie moga tu wystapic to 
	 * beda one pobierane z bazy przez klienta zaraz po jego zalogowaniu
	 * (automatycznie), a tylko proboszcz bedzie mial mozliwosc dodania/usuniecia danego
	 * zdarzenia (w sensie opcji do wyboru) 
	 * (tylko ze to bedzie zwykly tekst i liczba mu odpowiadajaca)*/
	
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd kk:mm"); //parser formatu datu
	
	int id;
	String name;
	Date begin;
	
	public Event(){
		id=1;
		name = "event_test";
		
		try{
			begin = format.parse("1970-02-01 12:12");
		}catch(ParseException e){
			System.out.println("Blad konwersji daty i godziny");
		}
	}
	
	public String eventSelect(){
		String zap="";
		
		/*zap = "SELECT * FROM COURSE WHERE  birthday = "+
				Integer.toString(birthday.getYear())+"-"+
				Integer.toString(birthday.getMonth())+"-"+
				Integer.toString(birthday.getDay());*/
		
		zap = "SELECT * FROM COURSE WHERE  birthday = "+ 
				begin.toLocaleString().substring(0, 16);
				/*od 0 do 10 bo to odpowiada ilosci znakow dla daty*/
		
		return zap;
	}
	
	public Date getBegin(){
		return begin;
	}

	
	public static void main(String[] args){
		/*DLA TESTOW*/
		Event e = new Event();
		System.out.println(e.eventSelect());
	}
}
