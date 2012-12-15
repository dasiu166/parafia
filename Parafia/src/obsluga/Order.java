package obsluga;
import java.util.*;
import java.io.*;

public class Order extends DataBaseElement implements Serializable{
	/*Zawiera tresc zamowienia (przystosowane dozlozenia jak narazie!)*/
	
	private Parishioner sender; //ten co nadal zamowienie
	private String senderPesel; //pesel tego co nadal zamowienie
	
	private Parishioner executor;//ten co ma odprawic zamowienie
	private String executorPesel;//pesel tego co maodprawic zamowienie
	
	/*UP - narazie nie wiem ktore bedzie obowiazujace, zapewnie niedlugo
	 * bedzie tak ze w lekkim zamowieniu beda pesele i daty
	 * a w ciezkim cale informacje o nadawcy/odbiorcy/tresci itp*/
	
	private String event;//zawiera rodzaj zamowienia (msza/wizyta itp)
	private String describe;//opis wydarzenie
	private Date beginD;//poczatek wydarzenia
	private Date endD;//koniec wydarzenia
	
	public void setSenderPesel(String p){
		senderPesel = p;
	}
	public void setExecutorPesel(String p){
		executorPesel = p;
	}
	public void setEvent(String e){
		event = e;
	}
	public void setDescribe(String d){
		describe = d;
	}
	public void setBeginDate(Date d){
		beginD = d;
	}
	public void setEndDate(Date d){
		endD = d;
	}

	public String getSenderPesel(){
		return senderPesel;
	}
	public String getExecutroPesel(){
		return executorPesel;
	}
	public String getEvent(){
		return event;
	}
	public String getDescribe(){
		return describe;
	}
	public Date getBeginDate(){
		return beginD;
	}
	public Date getEndDate(){
		return endD;
	}
	
	public boolean setQuery(String val){
		query = val;
		return true;
	}
	
}
