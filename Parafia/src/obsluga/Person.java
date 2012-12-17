package obsluga;

public abstract class Person extends DataBaseElement {
	
	private String pesel; //id parafinina (numer pesel)
	private String pass; //pole z haslem
	private Adress adress; //pole z adresem parafianina
	private String name; //imie
	private String surname; //nazwisko
	int restriction; //liczba oznaczajaca prawa dostepu
	
	
	public boolean setQuery(String val){
		/*nadpisywana dla parafianina i dla ksiedza*/
		return true;
	}
	
	public void setPesel(String p){
		pesel = p;
	}
	public void setPass(String p){
		pass = p;
	}
	public void setAdress(Adress a){
		/*Ustawienie adresu*/
		adress = a;
	}
	public void setName(String n){
		name = n;
	}
	public void setSurName(String sn){
		surname = sn;
	}
	public void setRestriction(int r){
		/*ustawia prawa dostepu*/
		restriction = r;
	}
	
	public String getPesel(){
		return pesel;
	}
	public String getPass(){
		return pass;
	}
	public Adress getAdress(){
		return adress;
	}
	public String getName(){
		return name;
	}
	public String getSurName(){
		return surname;
	}
	public int getRestriction(){
		return restriction;
	}
	
}
