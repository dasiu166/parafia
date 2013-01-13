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

import pomoce.Pomoc;

public class OrderService extends ServicePart {

	public boolean validate(Object o) {
		if (o instanceof Order)
			return true;
		else
			return false;
	}

	public void doService(Object wiadomosc, SerwerThreadService s) throws IOException {

		if (!validate(wiadomosc)) return; //wyjdz jezeli to nie twoja usluga
		else{
		
		
		if (((Order) wiadomosc).getKindQuery() == KindQuery.ADD_DBASE) { // dodanie
																			// do
																			// bazy
			// ##BLOK DODAWANIA ZAMOWIENIA DO BAZY

			DBManager db = DBManager.getInstance();
			db.setAutoCommit(true);
			dbReturnInt = db.execUpdateQuery(((Order) wiadomosc).getQuery());

			if (dbReturnInt != 0) {
				((Order) wiadomosc).setData("ZAMOWIENIE ZLOZONE");
				((Order) wiadomosc).setQuery("OK+");
				s.sendObject(wiadomosc);
				// LOG----------------------------------------------------------
				s.saveLog("Zlozenie zamowienia");
				// LOG_END------------------------------------------------------
			} else {
				((Order) wiadomosc).setData("ZAMOWIENIE NIEZLOZONE");
				((Order) wiadomosc).setQuery("ERR");
				s.sendObject(wiadomosc);
				// LOG----------------------------------------------------------
				s.saveLog("Blad zlozenia zamowienia");
				// LOG_END------------------------------------------------------
			}
		}// ##KONIEC BLOKU DODAWANIA ZAMOWIENIA DO BAZY

		if (((Order) wiadomosc).getKindQuery() == KindQuery.SEL_DBASE) {
			// ##BLOK POBRANIA ZAMOWIEN Z BAZY

			LinkedList<Order> orderList = new LinkedList<Order>();
			DBManager db = DBManager.getInstance();

			dbReturn = db.execSelectQuery(((Order) wiadomosc).getQuery());
			Iterator<String[]> it = dbReturn.iterator();
			while (it.hasNext()) {
				Order o = new Order();
				String[] tmp = it.next();
				if (!tmp[0].equals("ERR")) {
					o.setQuery("OK+");
					o.setId(Integer.parseInt(tmp[0]));
					o.setEvent(tmp[1]);
					o.setExecutorPesel(tmp[2]);
					o.setSenderPesel(tmp[3]);
					o.setDescribe(tmp[4]);
					o.setStatus(tmp[5]);
					o.setBeginDate(Pomoc.podajDate(tmp[6].substring(0, 16)));
					// System.out.println("WYNIK::: "+tmp[6].substring(0, 16));
					o.setEndDate(Pomoc.podajDate(tmp[7].substring(0, 16)));
					
					
					
					//BLOK UZUPELNIANIA DANYCH OSOBY zamawiajacej
					dbReturn = db.execSelectQuery("Select * from parishioner where " +
							"pesel="+tmp[3]);
					System.out.println("%%%Select * from parishioner where " +
							"pesel="+tmp[3]);
					
					String tmp3[] = dbReturn.getFirst();
					Parishioner par = new Parishioner();
					par.setName(tmp3[4]);
					par.setSurName(tmp3[5]);
					o.setSender(par);
					
					
					
					//BLOK UZUPELNIANIA DANYCH OSOBY WYKONUJACEJ
					dbReturn = db.execSelectQuery("Select * from priest where " +
							"pesel="+tmp[2]);
					System.out.println("&&&Select * from priest where " +
							"pesel="+tmp[2]);
					String tmp2[] = dbReturn.getFirst();
					Priest p = new Priest();
					p.setName(tmp2[3]);
					p.setSurName(tmp2[4]);
					o.setExecutor(p);

					orderList.add(o); // dodanie obiektu do listy
				} else {
					o.setData("Brak zamowien");
					o.setQuery("ERR");
					orderList.add(o);
					break;
				}
			}
			s.sendObject(orderList);

			// LOG-----------------------------------------------------------
			s.saveLog("Pobranie zamowien");
			// LOG_END-------------------------------------------------------

		}// ##KONIEC BLOKU POIERANIA ZAMOWIEN

		if (((Order) wiadomosc).getKindQuery() == KindQuery.DEL_DBASE) {

			DBManager db = DBManager.getInstance();
			dbReturnInt = db.execUpdateQuery(((Order) wiadomosc).getQuery());

			if (dbReturnInt != 0) {
				((Order) wiadomosc).setData("ZAMOWIENIE USUNIETE");
				((Order) wiadomosc).setQuery("OK+");
				s.sendObject(wiadomosc);
				// LOG----------------------------------------------------------
				s.saveLog("Zamowienie usuniete");
				// LOG_END------------------------------------------------------
			} else {
				((Order) wiadomosc).setData("BLAD USUWANIA ZAMOWIENIA");
				((Order) wiadomosc).setQuery("ERR");
				s.sendObject(wiadomosc);
				// LOG----------------------------------------------------------
				s.saveLog("Blad usuwania zamowienia");
				// LOG_END------------------------------------------------------
			}

		}
		
		if (((Order) wiadomosc).getKindQuery() == KindQuery.UPD_DBASE) {
			
			DBManager db = DBManager.getInstance();
			dbReturnInt = db.execUpdateQuery(((Order) wiadomosc).getQuery());
			System.out.println(((Order) wiadomosc).getQuery());
			
			if (dbReturnInt != 0) {
				((Order) wiadomosc).setData("ZAMOWIENIE UAKTUALNIONE");
				((Order) wiadomosc).setQuery("OK+");
				//((Order) wiadomosc).setStatus(KindQuery.ACK);
				s.sendObject(wiadomosc);
				// LOG----------------------------------------------------------
				s.saveLog("Zamowienie uaktualnione");
				// LOG_END------------------------------------------------------
			} else {
				((Order) wiadomosc).setData("BLAD UAKTUALNIENIA ZAMOWIENIA");
				((Order) wiadomosc).setQuery("ERR");
				s.sendObject(wiadomosc);
				// LOG----------------------------------------------------------
				s.saveLog("Blad uaktualnienia zamowienia");
				// LOG_END------------------------------------------------------
			}
			
		}
	}//koniec else
	}//koniec doservice
}
