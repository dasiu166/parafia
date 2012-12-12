package obsluga;
import java.util.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Course {
	/*Zawiera przebieg sakramentow itp w karierze parafianina;)
	 * i np jak data bierzmowania bedzie pusta to nie bedzie mogl zamowic
	 * slubu itp...*/

	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); //parser formatu daty
	
	Date birthday; //data urodzenia
	
	public Course(){
		/* mozna tez zwyklymi set ustawiac poszeczgolne pola
		 * to juz Mariusz do twojej inwencji, jak ci tam wygodniej bedzie
		 * to z formatki wyprowadzic*/
		try{
		birthday = format.parse("1970-01-01");
		} catch(ParseException e){
			System.out.println("Blad konwersji daty");
		}
	}
	
	public Date getBirthDay(){
		return birthday;
	}
	
	public String courseSelect(){
		String zap="";
		
		/*zap = "SELECT * FROM COURSE WHERE  birthday = "+
				Integer.toString(birthday.getYear())+"-"+
				Integer.toString(birthday.getMonth())+"-"+
				Integer.toString(birthday.getDay());*/
		
		zap = "SELECT * FROM COURSE WHERE  birthday = "+ 
				birthday.toLocaleString().substring(0, 10);
				/*od 0 do 10 bo to odpowiada ilosci znakow dla daty*/
		
		return zap;
	}
	
public static void main(String[] args){
		/*DLA TESTOW*/
		Course c = new Course();
		System.out.println(c.courseSelect());
	}
}
