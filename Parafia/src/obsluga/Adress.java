package obsluga;

public class Adress extends DataBaseElement{
	/*Zawiera dane adresowe parafianina*/
	
	private Integer id; //id adresu z bazy danych
	private String city; //miasto
	private String street; //ulica
	private String district; //osiedle
	private String house_numb; //numer domu/bloku
	
	public Adress(){
		//id=1;
		//city = "miasto_test";
		//street = "ulica_test";
		//district = "district_test";
		//house_numb = "numer_domu_test";
	}
	
	public void setId(int val){
		id=val;
	}
	public void setCity(String s){
		city=s;
	}
	public void setStreet(String s){
		street=s;
	}
	public void setDistrict(String s){
		district=s;
	}
	public void setHouseNumb(String s){
		house_numb=s;
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
	public String getDistrict(){
		return district;
	}
	
	
	public boolean setQuery(String val){
		query = val;
		return true;
	}
	
	public static void main(String[] args){
		/*DLA TESTOW*/
		Adress a = new Adress();
		
	}

}
