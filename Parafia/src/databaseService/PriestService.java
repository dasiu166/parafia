package databaseService;

import obsluga.Adress;
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
			 String tmp1[] = dbReturn.getFirst();
			 
			 String idA = dbReturn.getFirst()[2]; //id adresu

			 
			 pr.setPesel(tmp1[0]);
			 pr.setName(tmp1[3]);
			 pr.setSurName(tmp1[4]);
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
			
		}
		
		if (((Priest) wiadomosc).getKindQuery()==KindQuery.UPD_DBASE){
			
		}
		
		if (((Priest) wiadomosc).getKindQuery()==KindQuery.DEL_DBASE){
			
		}
		
	}//koniec else
	}//koniec doservice
}