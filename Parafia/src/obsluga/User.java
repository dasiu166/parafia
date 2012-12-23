package obsluga;

public class User extends DataBaseElement {
	/*klasa uzywana dla logowania i okreslenia praw dostepu/rangi
	 * pozniej juz uzywana bedzie konkretna klasa ksiedza/parafianina
	 * zeby nie komplikowac warunkow na serwerze (tak do wylogowani tez uzyjemy klasy 
	 * ksiadz parafianin, dlatego zostawilem tam pole restriction)*/

	private int id;
	private String login;
	private String password;
	private int restriction; //prawa dostepu
	private int range; /*wprowadzilem to tutaj, bo 
	a) bedzie potrzebne przy logowaniu jak rozbije ksiezy i parafian na osobne klasy
	b) jak sie proboszczowi uwidzi to se sekretarke zatrudni ktora nie bedzie ksiedzem
	ale bedzie miala takie same prawa jak ksiadz
	*/
	
	
	public boolean setQuery(String val){
		query = val;
		return true;
	}
	
	public void setId(int val){
		id=val;
	}
	public void setLogin(String val){
		login = val;
	}
	public void setPassword(String val){
		password = val;
	}
	public void setRestriction(int val){
		restriction = val;
	}
	public void setRange(int r){
		range = r;
	}
	
	public int getId(){
		return id;
	}
	public String getLogin(){
		return login;
	}
	public String getPassword(){
		return password;
	}
	public int getRestriction(){
		return restriction;
	}
	public int getRange(){
		return range;
	}
}
