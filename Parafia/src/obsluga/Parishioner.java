package obsluga;
import java.math.BigInteger;

public class Parishioner {
	/*Zawiera dane o parafianinie*/
	
	String pesel; //id parafinina (numer pesel)
	Adress adress; //pole z adresem parafianina
	Course course; //pole z przebiegiem
	
	String name; //imie
	String surname; //nazwisko
	
	int restriction; //liczba oznaczajaca prawa dostepu
	
	public Parishioner(){
		/*probny konstruktor*/
		restriction = 0;
		pesel = "90122014155";
	}
	
	public void setRestriction(int r){
		/*ustawia prawa dostepu*/
		restriction = r;
	}
	
	public void setAdress(Adress a){
		/*Ustawienie adresu*/
		adress = a;
	}
	
	public void setCourse(Course c){
		course = c;
	}
	
	public void setName(String n){
		name = n;
	}
	
	public void setSurName(String sn){
		surname = sn;
	}
	
	public String selectParishioner(){
		
		return "s";
	}
	
	
	
	
	
	public static void main(String[] args){
		/*DLA TESTOW*/
		Parishioner p = new Parishioner();
		//System.out.println(a.adressAdd());
	}
	
}
