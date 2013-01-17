package obsluga;
import java.util.*;
import java.io.*;

public class Order extends DataBaseElement implements Serializable{
	/*Zawiera tresc zamowienia (przystosowane dozlozenia jak narazie!)*/
	
	private Parishioner sender; //ten co nadal zamowienie
	private String senderPesel; //pesel tego co nadal zamowienie
	
	private Priest senderP;//ksiadz jako zamawiajacy
	
	private Priest executor;//ten co ma odprawic zamowienie
	private String executorPesel;//pesel tego co ma odprawic zamowienie
	
	/*UP - narazie nie wiem ktore bedzie obowiazujace, zapewnie niedlugo
	 * bedzie tak ze w lekkim zamowieniu beda pesele i daty
	 * a w ciezkim cale informacje o nadawcy/odbiorcy/tresci itp*/
	
	private String status;/*oznacza probe_zarezerewowania terminu lub
	ze termin zarezerwowany, nie bedzie tu odrzucenia zamowienia bo wtedy
	odrazu pojdzie zapytania kasujace taki rekord  z bazy*/
	
	
	private int id;
	private String event;//zawiera rodzaj zamowienia (msza/wizyta itp)
	private String describe;//opis wydarzenie
	private Date beginD;//poczatek wydarzenia
	private Date endD;//koniec wydarzenia
	
	public void setId(int val){
		id = val;
	}
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
	public void setStatus(String val){
		status = val;
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
	public String getStatus(){
		return status;
	}
	public int getId(){
		return id;
	}
	
	public boolean setQuery(String val){
		query = val;
		return true;
	}
	
	public Person getSender(){
		if (sender!=null)return sender; else return senderP;
	}
	
	public Person getExecutor(){
		return executor;
	}
	
	public void setSender(Parishioner sender){
		this.sender = sender;
	}
	
	public void setSender(Priest sender){
		this.senderP = sender;
	}
	
	public void setExecutor(Priest executor){
		this.executor = executor;
	}
	
}
