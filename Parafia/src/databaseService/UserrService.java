package databaseService;

import database.DBManager;
import obsluga.*;
import serwer.*;
import stale.KindQuery;
import stale.KindRange;
import stale.KindRestriction;

import java.util.*;
import java.io.*;

public class UserrService extends ServicePart  {
	
	public boolean validate(Object o){
		if (o instanceof User) return true; else return false;
	}
	
	public void doService(Object wiadomosc, SerwerThreadService s ) throws IOException{
		if (!validate(wiadomosc)) return; //wyjdz jezeli to nie twoja usluga
		else {
			
			if (((User) wiadomosc).getKindQuery()==KindQuery.TRY_LOGIN){
 				//##BLOK PROBY ZALOGOWANIA

				//LOG-
				s.saveLog("Proba logowania");
				//LOG_END-
				s.setMyLogin(((User) wiadomosc).getLogin());
				
				if(!Serwer.ckeckLoginList(((User) wiadomosc).getLogin())){
					
					((User) wiadomosc).setQuery("ERR");
					((User) wiadomosc).setData("Jestes juz zalogowany");
					 s.sendObject(wiadomosc);
					 //LOG------------------------------
					 s.saveLog("Blad logowania");
					 return;
				}
				Serwer.showLoginList();
 
				
				
				DBManager db = DBManager.getInstance();
				db.setAutoCommit(true);//wlaczenie potwierdzen
 
							//### KONFUGURACJA USERA ###
				//System.out.println("\n"+((User) wiadomosc).getQuery());
				dbReturn = db.execSelectQuery(((User) wiadomosc).getQuery());
				String tmp[] = dbReturn.getFirst();
 
				if (!tmp[0].equals("ERR") ){ //jezeli znaleziono usera

					int idu = Integer.parseInt(tmp[0]);
	
					((User) wiadomosc).setId(idu);
					((User) wiadomosc).setRestriction(Integer.parseInt(tmp[3]));
					((User) wiadomosc).setRange(Integer.parseInt(tmp[4]));
   
					((User) wiadomosc).setQuery("OK+");

					s.sendObject(wiadomosc); //odpowiedz klas¹ user

					if(((User) wiadomosc).getRange()==KindRange.LOGG_RANG){
						//###KONFUGURACJA I WYSLANIE PODST. DANYCH PARAFIANINA
						Parishioner p = new Parishioner();
						dbReturn = db.execSelectQuery("SELECT * FROM parishioner where id_userr="+idu);
						String tmp1[] = dbReturn.getFirst();
						p.setPesel(tmp1[0]);
						p.setName(tmp1[4]);
						p.setSurName(tmp1[5]);
						p.setRestriction(Integer.parseInt(tmp[3]));
						p.setQuery("OK+");
						s.sendObject(p); //odpowiedz klas¹ parishioner
   //System.out.println("---------------------------------Wyslano parafianina");
					} else {
						Priest p = new Priest();
						dbReturn = db.execSelectQuery("SELECT * FROM priest where id_userr="+idu);
						String tmp1[] = dbReturn.getFirst();
						p.setPesel(tmp1[0]);
						p.setName(tmp1[3]);
						p.setSurName(tmp1[4]);
						p.setRestriction(Integer.parseInt(tmp[3]));
						p.setQuery("OK+");
						s.sendObject(p); //odpowiedz klas¹ ksiadz
	//System.out.println("--------------------------------Wyslano Ksiedza");
					}
					//LOG--
					s.saveLog("Zalogowano");
					return;
					//LOG_END--
					//koniec znalezienia usera 
				} else {
					//##BLAD LOGOWANIA
					((User) wiadomosc).setData("Nie ma takiego uzytkownika");
					 ((User) wiadomosc).setQuery("ERR");
					 s.sendObject(wiadomosc);
					 //LOG------------------------------
					 Serwer.removeLoginFromList(((User) wiadomosc).getLogin());

					 s.saveLog("Blad logowania");
					 return;
					 //LOG_END--------------------------
				}
			}//koniec bloku logowania
			
			if (((User) wiadomosc).getKindQuery() == KindQuery.TRY_LOGOUT) {
				// ## BLOK PROBY WYLOGOWANIA

				//((User) wiadomosc).clean();
				if(Serwer.removeLoginFromList(((User) wiadomosc).getLogin())){
				((User) wiadomosc).setRange(KindRange.GUEST_RANG);
				((User) wiadomosc).setRestriction(KindRestriction.GUEST_R);
				((User) wiadomosc).setData("Wylogowano CIE");
				((User) wiadomosc).setQuery("OK+");
				} else {
					((User) wiadomosc).setQuery("ERR");
					((User) wiadomosc).setData("Nie mozna Cie wylogowac - sproboj ponownie za chwile");
				}
				
				s.sendObject(wiadomosc);

				// LOG-------------------------------------------------------------
				s.saveLog("Wylogowano");
				// LOG_END----------------------------------------------------------
				Serwer.showLoginList();
			}
			
			 if (((User) wiadomosc).getKindQuery()==KindQuery.ADD_DBASE){
				 	//##BLOK DODANIA UZYTKOWNIKA DO BAZY
				 DBManager db = DBManager.getInstance();
				 
				 /*W RAZIE PROLEMOW NALEZY WLACZYC (true)*/
				 db.setAutoCommit(false);//wylaczenie potwierdzen
				 
				 /*UTWORZENIE SAVE BAZY (jakby co wykomentowac)*/
				 db.makeSavepoint();//
				 
				 
				 //sprawdzenie czu user juz istnieje
				 dbReturn = db.execSelectQuery("Select count(*) FROM Userr " +
				 		"Where login='"+((User)wiadomosc).getLogin()+"'");
				 
				 System.out.println("!!!!  Select count(*) FROM Userr " +
				 		"Where login='"+((User)wiadomosc).getLogin()+"'");
				 
				 String tmp3[] = dbReturn.getFirst();
				 
				 if (Integer.parseInt(tmp3[0])==0){
				 
				 //proba dodanie jezeli nie istnieje
				 dbReturnInt = db.execUpdateQuery(((User) wiadomosc).getQuery());
				 
				 if (dbReturnInt!=0) {
					 ((User) wiadomosc).setQuery("OK+");
					 
					 //zwrot id_usera dodanego usera
					 dbReturn = db.execSelectQuery("SELECT id_userr FROM Userr WHERE login='"+
							 						((User)wiadomosc).getLogin()+"'");
					
					 System.out.println("@@@@@   SELECT id_userr FROM Userr WHERE login='"+
		 						((User)wiadomosc).getLogin()+"'");
					 
					 String tmp[] = dbReturn.getFirst();
					 
					 if(!tmp[0].equals("ERR")){
						 ((User) wiadomosc).setId(Integer.parseInt(tmp[0]));
					 } else {
						 ((User) wiadomosc).setQuery("ERR");
						 ((User) wiadomosc).setData("Blad dodawania");
						 }
				 
				 
				 } else ((User) wiadomosc).setQuery("ERR");
				 
				 } else {
					 ((User) wiadomosc).setQuery("ERR");
					 ((User) wiadomosc).setData("Uzytkownik juz istnieje");
					 System.out.println("Taki uzytkownik juz jest");
				 } 
				 
				 if (!s.sendObject(wiadomosc)) {
					 System.out.println("UZYCIE KOPII======="+db.useSavePoint());}
				 
				 s.saveLog("Dodano uzytkownika do bazy");
				 
			 }//##KONIEC DODAWANIA UZYTKOWNIKA DO BAZY
		 
			
			 
			 if (((User) wiadomosc).getKindQuery()==KindQuery.DEL_DBASE){
					//##BLOK USUWANIA UZYTKOWNIKA Z BAZY
				
			}//##KONIEC BLOKU USUWANIA UZYTKOWNIKA
				
			
			 
			 
			 if (((User) wiadomosc).getKindQuery()==KindQuery.UPD_DBASE){
					//##BLOK UAKTUALNIENIA UZYTKOWNIA
				
				 DBManager db = DBManager.getInstance();
				 System.out.println("&&&&&& "+((User) wiadomosc).getQuery());
				 
				 dbReturn = db.execSelectQuery("Select * from Userr where login='"+
						 ((User) wiadomosc).getData()+"'");
				 
				 if(!dbReturn.getFirst().equals("ERR")){
					 ((User) wiadomosc).setQuery("ERR");
					 ((User) wiadomosc).setData("Login juz zajety");
					 s.sendObject(wiadomosc);
					 s.saveLog("Login zajety");
					 return;
				 }
				 
				 
				 dbReturnInt = db.execUpdateQuery(((User) wiadomosc).getQuery());
				 if (dbReturnInt!=0) ((User) wiadomosc).setQuery("OK+"); else
					 	((User) wiadomosc).setQuery("ERR");
				 
				 s.sendObject(wiadomosc);
				 s.saveLog("Zmiana w uzytkowniku w bazie");
			
			}//##KONIEC BLOKU UAKTUALNINIA UZYTKOWNIKA
		
	}//koniec else

}
}
