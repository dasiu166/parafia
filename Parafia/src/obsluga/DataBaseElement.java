package obsluga;
import java.io.*;

public abstract class DataBaseElement implements Serializable {
	/*klasa bazowa dla elementow mapowanych na tabeli w bazie
	 * zawiera rodzaj zapytania oraz metode zwracajaca konkretne zapytanie*/
	
	int kindOfQuery; //rodzaj zapytania
	String query; //tresc zapytania
	String data; //inne informacje w razie co
	
	public String retQuery(){
		/*zwraca zapytanie*/
		return query;
	}
	
	abstract boolean setQuery(String val); /*ustawia zapytanie*/
	
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
	
	
}
