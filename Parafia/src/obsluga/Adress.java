package obsluga;

public class Adress {
	/*Zawiera dane adresowe parafianina*/
	
	private Integer id;
	private String city;
	private String street;
	private String house_numb;
	
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
	
	public String adressAdd(){
		String zap="";
		
		zap = "INSERT INTO ADRESS VALUES "+city+" "+street+" "+
				house_numb;
		
		return zap;
	}
	
	public static void main(String[] args){
		/*DLA TESTOW*/
		Adress a = new Adress();
		System.out.println(a.adressAdd());
	}

}
