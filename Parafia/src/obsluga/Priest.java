package obsluga;
import java.util.*;

public class Priest extends Person {

		Date secularityD; //data swiecen
		Date arrivalD;//data przybycia do parafii
		String position; //nazwa stanowiska //wikary, proboszcz,kapelan itp
		
		public boolean setQuery(String val){
			query = val;
			return true;
		}
		
		public void clean(){
			this.setName(null);
			this.setSurName(null);
			this.setAdress(null);
			this.setPesel(null);
			this.setPass(null);
			secularityD=null;
			arrivalD=null;
			position=null;
		}
		public void setSecularityDate(Date d){
			secularityD =d;
		}
		public void setArrivalDate(Date d){
			arrivalD =d;
		}
		public void setPossition(String val){
			position = val;
		}
		
		public Date getSecularityDate(){
			return secularityD;
		}
		public Date getArrivalDate(){
			return arrivalD;
		}
		public String getPosition(){
			return position;
		}
}
