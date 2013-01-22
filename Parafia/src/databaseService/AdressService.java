package databaseService;

import obsluga.Adress;
import obsluga.Order;
import obsluga.User;
import database.DBManager;
import obsluga.*;
import serwer.*;
import stale.KindQuery;
import stale.KindRange;
import java.util.*;
import java.io.*;

public class AdressService extends ServicePart {
	
	public boolean validate(Object o){
		if (o instanceof Adress) return true; else return false;
	}
	
	public void doService(Object wiadomosc, SerwerThreadService s)throws IOException{
		if (!validate(wiadomosc)) return; //wyjdz jezeli to nie twoja usluga
		else {
		
		if (((Adress) wiadomosc).getKindQuery()==KindQuery.ADD_DBASE){
			DBManager db = DBManager.getInstance();
			
			dbReturnInt = db.execUpdateQuery(((Adress) wiadomosc).getQuery());
			//System.out.println("^^^^^^^^^ "+((Adress) wiadomosc).getQuery());
			
			if(dbReturnInt!=0){
				((Adress) wiadomosc).setQuery("OK+");
				dbReturn = db.execSelectQuery("Select seq_adress.currval from dual");
				
				String tmp[] = dbReturn.getFirst();
				
				if (!tmp[0].equals("ERR")){
					((Adress) wiadomosc).setId(Integer.parseInt(tmp[0]));
				} else ((Adress) wiadomosc).setQuery("ERR");
				
			} else {
				((Adress) wiadomosc).setQuery("ERR");
			}
			
			if (!s.sendObject(wiadomosc)) {
				 System.out.println("UZYCIE KOPII======="+db.useSavePoint());
			     s.saveLog("Rollback bazy");
			     return;
			} else return;
		}
		
		if (((Adress) wiadomosc).getKindQuery()==KindQuery.DEL_DBASE){
			
			
			
		}
		
		if (((Adress) wiadomosc).getKindQuery()==KindQuery.UPD_DBASE){
			DBManager db = DBManager.getInstance();
			dbReturnInt = db.execUpdateQuery(((Adress) wiadomosc).getQuery());
			System.out.println(((Adress) wiadomosc).getQuery());
			
			if (dbReturnInt != 0) {
				((Adress) wiadomosc).setData("Adres UAKTUALNIONE");
				((Adress) wiadomosc).setQuery("OK+");
				//((Order) wiadomosc).setStatus(KindQuery.ACK);
				s.sendObject(wiadomosc);
				// LOG----------------------------------------------------------
				s.saveLog("Zamowienie uaktualnione");
				// LOG_END------------------------------------------------------
			} else {
				((Adress) wiadomosc).setData("BLAD UAKTUALNIENIA Adresu");
				((Adress) wiadomosc).setQuery("ERR");
				s.sendObject(wiadomosc);
				// LOG----------------------------------------------------------
				s.saveLog("Blad uaktualnienia adresu");
				// LOG_END------------------------------------------------------
			}
			
		}
		
	}//koniec else
	
	}//koniec doservice
		
	}


