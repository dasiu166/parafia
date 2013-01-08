package databaseService;


import obsluga.Actuals;
import obsluga.Adress;
import obsluga.User;
import database.DBManager;
import obsluga.*;
import serwer.*;
import stale.KindQuery;
import stale.KindRange;
import java.util.*;
import java.io.*;

import pomoce.Pomoc;

public class ActualsService extends ServicePart {
	
	public boolean validate(Object o){
		if (o instanceof Actuals) return true; else return false;
	}
	
	public void doService(Object wiadomosc, SerwerThreadService s)throws IOException{
		if (!validate(wiadomosc)) return; //wyjdz jezeli to nie twoja usluga
		else {
		if(((Actuals) wiadomosc).getKindQuery()==KindQuery.SEL_DBASE){
			
			LinkedList<Actuals> actualList = new LinkedList<Actuals>();
			DBManager db = DBManager.getInstance();
			
			dbReturn = db.execSelectQuery(((Actuals) wiadomosc).getQuery());
			Iterator<String[]> it = dbReturn.iterator();
			
			while(it.hasNext()){
				Actuals a = new Actuals();
				String[] tmp = it.next();
				if(!tmp[0].equals("ERR")){
				a.setId(Integer.parseInt(tmp[0]));
				a.setPriestPesel(tmp[1]);
				a.setSubject(tmp[2]);
				a.setDescribe(tmp[3]);
				a.setAddDate(Pomoc.podajDate(tmp[4]));
				//moje zmiany(VeLoOx)
				//Priest pr = new Priest();
				   dbReturn1 = db.execSelectQuery("SELECT * FROM priest where pesel="+tmp[1]);
				   String tmp2[] = dbReturn1.getFirst();
				   a.setName(tmp2[3]);
				   a.setSurName(tmp2[4]);
				//----------------------
				a.setQuery("OK+");
				actualList.add(a); //dodanie obiektu do listy
				} else {
					a.setQuery("ERR");
					actualList.add(a);
					break;
				}
			}
			s.sendObject(actualList);
			s.saveLog("Pobranie listy aktualnosci");
		}
		
		
		if(((Actuals) wiadomosc).getKindQuery()==KindQuery.DEL_DBASE){
			
		}
		
		
		if(((Actuals) wiadomosc).getKindQuery()==KindQuery.ADD_DBASE){
			
		}

		
		if(((Actuals) wiadomosc).getKindQuery()==KindQuery.UPD_DBASE){

		}
	
		}//koniec else
	}//koniec doservice

}
