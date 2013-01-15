package pomoce;
import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import obsluga.Priest;

public class Pomoc {
	/*Zawiera rozne statyczne metody ulatwiajace zycie
	 * np podawanie daty jako strong*/
	//static String formatString = "yyyy-MM-dd";
	static String formatString = "yyyy-MM-dd HH:mm";
	
	public static Date podajDate(String val){
		if(val.length()==10) val=val+" 00:00";
		Date d = new Date();
		SimpleDateFormat format = new SimpleDateFormat(formatString); //parser formatu daty
		
		try{
			d = format.parse(val);
			} catch(ParseException e){
				System.out.println("Blad konwersji daty");
			}
		
		return d;
	}
	
	public static void writeToFile(String directory, String fileName, String content){
		if (new File(directory).mkdirs()) System.out.println("folder utworzny");
		
		File plik = new File(directory+fileName+".txt");
		if(plik.isFile()) System.out.print(""); else
			try{
			if(plik.createNewFile()); //System.out.println("Plik utworzony");
			} catch (IOException e){
				//System.out.println("Blad tworznie pliku");
			}
		
		try{
		  FileWriter plikZapis = new FileWriter(directory+fileName+".txt",true);
		  BufferedWriter out = new BufferedWriter(plikZapis);
		  
		  out.write(content+"\r\n");
		  out.close();
		} catch (IOException e){
			System.out.println("Blad zapisu do pliku");
		}
		   
	}
	
	public static String loadFromFile(String filePath, String pattern) throws IOException {
		  FileReader fileReader = new FileReader(filePath);
		  BufferedReader bufferedReader = new BufferedReader(fileReader);
		 // char znaki[];
		  String linia;
		  int i = 0; //iterator tablicy znakow
		  String buf="";
		  
		  try {
		    String textLine = bufferedReader.readLine();
		    
		    do {
		      //znaki=textLine.toCharArray();
		      linia=textLine.toString();
		      if(linia.startsWith(pattern)){
		    	  i=pattern.length()+1;
		    	  while (linia.charAt(i)!=';'){
		    		 buf = buf+ linia.charAt(i);
		    		 i++;
		    	  }
		      }
		      
		      
		      textLine = bufferedReader.readLine();
		    } while (textLine != null);
		  } finally {
		    bufferedReader.close();
		  }
		  return buf;
		}
	
		
	public static String validateEventName(LinkedList<obsluga.Event> list, String type){
		
		Iterator<obsluga.Event> it =list.iterator();
		while(it.hasNext()){
			obsluga.Event ee = it.next();
			if (ee.getId()==Integer.parseInt(type))
					return ee.getName();
		}
		
		return "Inne";
		
	}

public static String validatePriestName(LinkedList<Priest> lp, int index){
	
	Iterator<Priest> it = lp.iterator();
	
	Priest pr = lp.get(index-1);
	
	return pr.getPesel();
}

public static long substractDates(Date d1, Date d2){
	
	return Math.round((d1.getTime() - d2.getTime())/86400000.0);
	
}
	
	public static void main(String[] args){
		//Pomoc.writeToFile("log\\","log2","jakie zycie taki rap");
		//System.out.println(Pomoc.podajDate("2012-12-20 12:00").toLocaleString());
		//System.out.println(Double.parseDouble("90.122014155"));
		
		System.out.println(Pomoc.substractDates(Pomoc.podajDate("2013-01-15"),
				Pomoc.podajDate("2013-01-15")));
		
	}
	
	

}
