package databaseService;

import obsluga.Adress;
import obsluga.Parishioner;
import obsluga.Priest;
import obsluga.User;
import database.DBManager;
import obsluga.*;
import serwer.*;
import stale.KindQuery;
import stale.KindRange;
import stale.KindRestriction;

import java.util.*;
import java.io.*;

import pomoce.Pomoc;

public class PriestService extends ServicePart {
	public boolean validate(Object o){
		if (o instanceof Priest) return true; else return false;
	}
	
	public void doService(Object wiadomosc, SerwerThreadService s) throws IOException{
		if (!validate(wiadomosc)) return; //wyjdz jezeli to nie twoja usluga
		else{
		
		if (((Priest) wiadomosc).getKindQuery()==KindQuery.TRY_LOGOUT){
 			//## BLOK PROBY WYLOGOWANIA
 
			((Priest) wiadomosc).clean();
			((Priest) wiadomosc).setRestriction(KindRestriction.GUEST_R);
			((Priest) wiadomosc).setData("Wylogowano CIE");
			((Priest) wiadomosc).setQuery("OK+");
			s.sendObject(wiadomosc);
 
			//LOG-------------------------------------------------------------
			s.saveLog("Wylogowano");
			//LOG_END----------------------------------------------------------

		}//##KONIEC BLOKU WYLOGOWANIA
		
		if (((Priest) wiadomosc).getKindQuery()==KindQuery.SEL_DBASE){
			//## BLOK SELECTU DANYCH KSIEDZA
			//MOJE PROBY MAM NADZIEJE ZE OWOCNE
			 Priest pr = new Priest();
			 DBManager db = DBManager.getInstance();
			 dbReturn = db.execSelectQuery(((Priest) wiadomosc).getQuery());
			 
			 if (((Priest) wiadomosc).getQuery().contains("where")==false){
				 
				 System.out.println("!!!!!!!!!! Wysylam liste ksiezy");
				 LinkedList<Priest> lpr = new LinkedList<Priest>();
				 Iterator<String[]> it = dbReturn.iterator();
				 while(it.hasNext()){
					 String tmp[] = it.next();
					 if(!tmp[0].equals("ERR")){
						 Priest tmppr = new Priest();
						 tmppr.setPesel(tmp[0]);
						 tmppr.setName(tmp[3]);
						 tmppr.setSurName(tmp[4]);
						 lpr.add(tmppr);
					 } else {
						 Priest tmppr = new Priest();
						 tmppr.setQuery("ERR");
						 tmppr.setData("Brak ksiezy");
						 lpr.add(tmppr);
						 s.sendObject(lpr);
						 return;
					 }
				 }
				s.sendObject(lpr);
				return;
			 } 
			 
			 String tmp1[] = dbReturn.getFirst();
			 
			 String idA = dbReturn.getFirst()[2]; //id adresu

			 
			 pr.setPesel(tmp1[0]);
			 pr.setName(tmp1[3]);
			 pr.setSurName(tmp1[4]);
			 pr.setPossition(tmp1[5]);
			 pr.setArrivalDate(Pomoc.podajDate(tmp1[6].substring(0, 10)));
			 pr.setSecularityDate(Pomoc.podajDate(tmp1[7].substring(0, 10)));

			 
			//tworze adres
			 dbReturn = db.execSelectQuery("Select * from Adress where id_adress="+idA);
			 Adress a = new Adress();
			 a.setId(Integer.parseInt(idA));
			 a.setCity(dbReturn.getFirst()[1]);
			 a.setStreet(dbReturn.getFirst()[2]);
			 a.setHouseNumb(dbReturn.getFirst()[3]);
			 a.setPostcode(dbReturn.getFirst()[4]);
			 pr.setAdress(a); //dodanie adresu
			 
			 
			 
			 pr.setQuery("OK+");
				 s.sendObject(pr); 
		}//## KONIEC SELECTU DANYCH KSIEDZA
		
		if (((Priest) wiadomosc).getKindQuery()==KindQuery.ADD_DBASE){
			
			DBManager db = DBManager.getInstance();

			dbReturnInt = db.execUpdateQuery(((Priest) wiadomosc)
					.getQuery());

			if (dbReturnInt == 0) {
				((Priest) wiadomosc).setQuery("ERR");
				((Priest) wiadomosc).setData("Taki pesel juz jest");
				System.out.println("UZYCIE KOPII=======" + db.useSavePoint());
			
			} else {
				((Priest) wiadomosc).setQuery("OK+");
			}

			if (!s.sendObject(wiadomosc)) {
				db.doCommit();
				// uzycie kopii przy bledzie dodawania (cofnie sie az do usera)
				//System.out.println("UZYCIE KOPII=======" + db.useSavePoint());
			} else
				db.doCommit(); // potwierdzenie zapisu
			
		}
		
		if (((Priest) wiadomosc).getKindQuery()==KindQuery.UPD_DBASE){
			
		}
		
		if (((Priest) wiadomosc).getKindQuery()==KindQuery.DEL_DBASE){
			
		}
		
	}//koniec else
	}//koniec doservice
}
