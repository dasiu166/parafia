package pomoce;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;
import java.lang.*;

public class ReadFromFile {
	
	public static String loadFromFile(String filePath, String pattern) throws IOException {
		  FileReader fileReader = new FileReader(filePath);
		  BufferedReader bufferedReader = new BufferedReader(fileReader);
		  char znaki[];
		  String linia;
		  int i = 0; //iterator tablicy znakow
		  String buf="";
		  
		  try {
		    String textLine = bufferedReader.readLine();
		    
		    do {
		      znaki=textLine.toCharArray();
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

}
