package obsluga;
import java.util.*;

public class Actuals extends DataBaseElement{

	private int id;
	private String priestPesel;
	private String describe;
	private Date addDate;
	
	private String name; //moje zmiany(VeLoOx)
	private String surName;//moje zmiany(VeLoOx)
	
	public boolean setQuery(String val){
		query = val;
		return true;
	}
	
	public void setId(int val){
		id=val;
	}
	public void setPriestPesel(String val){
		priestPesel=val;
	}
	public void setDescribe(String val){
		describe=val;
	}
	public void setAddDate(Date d){
		addDate=d;
	}
	
	public int getId(){
		return id;
	}
	public String getPriestPesel(){
		return priestPesel;
	}
	public String getDescribe(){
		return describe;
	}
	public Date getAddDate(){
		return addDate;
	}
	
	//moje zmiany(VeLoOx)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}
}
