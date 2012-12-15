package pomoce;
import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Pomoc {
	/*Zawiera rozne statyczne metody ulatwiajace zycie
	 * np podawanie daty jako strong*/
	static String formatString = "yyyy-MM-dd";
	
	public static Date podajDate(String val){
		Date d = new Date();
		SimpleDateFormat format = new SimpleDateFormat(formatString); //parser formatu daty
		
		try{
			d = format.parse(val);
			} catch(ParseException e){
				System.out.println("Blad konwersji daty");
			}
		
		return d;
	}

}
