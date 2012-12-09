import java.io.*;

public class Logowanie implements Serializable {
	String name;
	String pass;
	boolean acces = false;
	
	Logowanie(){
		name = "dafault_user";
		pass = "";
	}
	
	void setName(String n){
		name=n;
	}
	void setPass(String p){
		pass=p;
	}
	void setAcces(boolean a){
		acces = a;
	}
	
	void show(){
		System.out.println("Uzytkownik:  Imie = "+name+"  Haslo = "+pass);
	}
}
