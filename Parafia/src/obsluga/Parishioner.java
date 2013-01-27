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
	
	public void clean(){
		/*Zeroje pola przy wylogowaniu*/
		course = null;
		this.setName(null);
		this.setSurName(null);
		this.setAdress(null);
		this.setPesel(null);
		this.setPass(null);
	}
	
	/*SETTERY*/
	
	/*public boolean setQuery(String val) { 

		if (!this.checkQuery(val) && (!val.contains("OK+"))
				&& (!val.contains("ERR")))
			return false;
		query = val;
		return true;
	}*/

	protected boolean checkQuery(String val) {
		if ((val.length() < 11)
				|| ((!val.contains("Parishioner")) && (!val.contains("parishioner"))
						&& (!val.contains("PARISHIONER"))))
			return false;
		else
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
