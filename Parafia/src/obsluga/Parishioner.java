package obsluga;


public class Parishioner extends DataBaseElement{
	/*Zawiera dane o parafianinie*/
	
	//int kindOfQuery; //rodzaj zapytania jaki wysylamy
	
	private String pesel; //id parafinina (numer pesel)
	private String pass; //pole z haslem
	private Adress adress; //pole z adresem parafianina
	private Course course; //pole z przebiegiem
	private String name; //imie
	private String surname; //nazwisko
	
	int restriction; //liczba oznaczajaca prawa dostepu
	
	public Parishioner(){
		/*probny konstruktor*/
		restriction = 0;
		pesel = "90122014155";
	}
	
	/*SETTERY*/
	
	public boolean setQuery(String val){
		query = val;
		return true;
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
	public void setPesel(String p){
		pesel = p;
	}
	public void setPass(String p){
		pass = p;
	}
	
	/*GETTERY*/
	
	public int getRestriction(){
		return restriction;
	}
	public Adress getAdress(){
		return adress;
	}
	public Course getCourse(){
		return course;
	}
	public String getName(){
		return name;
	}
	public String getSurName(){
		return surname;
	}
	public String getPesel(){
		return pesel;
	}
	public String getPass(){
		return pass;
	}
	
	
	
	
	public static void main(String[] args){
		/*DLA TESTOW*/
		Parishioner p = new Parishioner();
		//System.out.println(a.adressAdd());
	}
	
}
