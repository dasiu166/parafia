package obsluga;

public class Adress {
	/*Zawiera dane adresowe parafianina*/
	
	Integer id;
	String city;
	String street;
	String house_numb;
	
	public Adress(){
		id=1;
		city = "miasto_test";
		street = "ulica_test";
		house_numb = "numer_domu_test";
	}
	
	
	
	public int getId(){
		return id;
	}
	
	public String getCity(){
		return city;
	}
	
	public String getStreet(){
		return street;
	}
	
	public String getHouse(){
		return house_numb;
	}

}
