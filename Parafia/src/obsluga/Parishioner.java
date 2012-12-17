package obsluga;


public class Parishioner extends Person{
	/*Zawiera dane o parafianinie*/
	
	//int kindOfQuery; //rodzaj zapytania jaki wysylamy
	
	
	private Course course; //pole z przebiegiem
	
	public Parishioner(){
		/*probny konstruktor*/
		restriction = 0;
		//pesel = "90122014155";
	}
	
	/*SETTERY*/
	
	public boolean setQuery(String val){
		query = val;
		return true;
	}
	
	public void setCourse(Course c){
		course = c;
	}
	
	/*GETTERY*/
	public Course getCourse(){
		return course;
	}
	
	
	
	
	
	
	public static void main(String[] args){
		/*DLA TESTOW*/
		Parishioner p = new Parishioner();
		
		//System.out.println(a.adressAdd());
	}
	
}
