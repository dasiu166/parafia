package obsluga;
import java.io.*;

public abstract class DataBaseElement implements Serializable {
	/*klasa bazowa dla elementow mapowanych na tabeli w bazie
	 * zawiera rodzaj zapytania oraz metode zwracajaca konkretne zapytanie
	 * !!!!! UWAGA UWAGA !!!!!
	 * podczas zwrotu serwer bedzie zamienial pole query na 
	 * OK+ - jak zapytanie sie powiedzie
	 * ERR - jak zapytanie sie nie powiedzie
	 * (zapewne znacie to skads;))
	 * dzieki temu odsylam caly czas ten sam obiekt i nie musze tworzyc klasy dla odpowiedzi
	 * serwera i dodawac niepotrzebnych warunkow do kodu klienta*/
	
	
	int kindOfQuery; //rodzaj zapytania
	String query; //tresc zapytania
	String data; //inne informacje w razie co
	
	public String retQuery(){
		/*zwraca zapytanie*/
		return query;
	}
	
	final public boolean setQuery(String val){ /*ustawia zapytanie*/
		
		if (!this.checkQuery(val)&& (!val.contains("OK+")) && (!val.contains("ERR"))) return false;
		query=val;
		return true;
	}
	
	protected boolean checkQuery(String val){
		if (val.length()<1) return false; else
			return true;
		
	}
	
	public void setKindQuery(int val){
		/*ustawia rodzaj zapytania*/
		kindOfQuery = val;
	}
	public void setData(String val){
		data = val;
	}
	
	public int getKindQuery(){
		return kindOfQuery;
	}
	public String getData(){
		return data;
	}
	public String getQuery(){
		return query;
	}
	
	
}
