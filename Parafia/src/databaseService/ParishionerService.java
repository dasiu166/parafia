package databaseService;

import obsluga.Adress;
import obsluga.Course;
import obsluga.Parishioner;
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

public class ParishionerService extends ServicePart {

	public boolean validate(Object o) {
		if (o instanceof Parishioner)
			return true;
		else
			return false;
	}

	public void doService(Object wiadomosc, SerwerThreadService s)
			throws IOException {

		if (!validate(wiadomosc)) return; //wyjdz jezeli to nie twoja usluga
		else {
		
		if (((Parishioner) wiadomosc).getKindQuery() == KindQuery.TRY_LOGOUT) {
			// ## BLOK PROBY WYLOGOWANIA

			((Parishioner) wiadomosc).clean();
			((Parishioner) wiadomosc).setRestriction(KindRestriction.GUEST_R);
			((Parishioner) wiadomosc).setData("Wylogowano CIE");
			((Parishioner) wiadomosc).setQuery("OK+");
			s.sendObject(wiadomosc);

			// LOG-------------------------------------------------------------
			s.saveLog("Wylogowano");
			// LOG_END----------------------------------------------------------

		}// ##KONIEC BLOKU WYLOGOWANIA

		if (((Parishioner) wiadomosc).getKindQuery() == KindQuery.SEL_DBASE) {
			// ##BLOK PROBY POBRANIA PELNYCH DANYCH
			DBManager db = DBManager.getInstance();
			dbReturn = db.execSelectQuery(((Parishioner) wiadomosc).getQuery());
			String idA = dbReturn.getFirst()[3]; // id adresu
			String idC = dbReturn.getFirst()[1]; // id przebiegu

			// tworze adres
			dbReturn = db
					.execSelectQuery("Select * from Adress where id_adress="
							+ idA);
			Adress a = new Adress();
			a.setId(Integer.parseInt(idA));
			a.setCity(dbReturn.getFirst()[1]);
			a.setStreet(dbReturn.getFirst()[2]);
			a.setHouseNumb(dbReturn.getFirst()[3]);
			a.setPostcode(dbReturn.getFirst()[4]);
			((Parishioner) wiadomosc).setAdress(a); // dodanie adresu

			// tworze przebieg
			dbReturn = db
					.execSelectQuery("Select * from Course where id_course="
							+ idC);
			Course c = new Course();
			String[] cs = dbReturn.getFirst();
			c.setId(Integer.parseInt(cs[0]));
			if (cs[1] != null)
				c.setBirthday(Pomoc.podajDate(cs[1].substring(0, 10)));
			if (cs[2] != null)
				c.setBaptism(Pomoc.podajDate(cs[2].substring(0, 10)));
			if (cs[3] != null)
				c.setCommunion(Pomoc.podajDate(cs[3].substring(0, 10)));
			if (cs[4] != null)
				c.setConfirmation(Pomoc.podajDate(cs[4].substring(0, 10)));
			if (cs[5] != null)
				c.setMarriage(Pomoc.podajDate(cs[5].substring(0, 10)));
			if (cs[6] != null)
				c.setDeath(Pomoc.podajDate(cs[6].substring(0, 10)));
			((Parishioner) wiadomosc).setCourse(c);// dodanie przebiegu

			s.sendObject(wiadomosc);// wyslanie obiektu

		}// ##KONIEC BLOKU POBIERANIA PELNYCH DANYCH

		if (((Parishioner) wiadomosc).getKindQuery() == KindQuery.ADD_DBASE) {
			// BLOK DODAWANIA DANYCH PARAFIANINA
			DBManager db = DBManager.getInstance();

			dbReturnInt = db.execUpdateQuery(((Parishioner) wiadomosc)
					.getQuery());

			if (dbReturnInt == 0) {
				((Parishioner) wiadomosc).setQuery("ERR");
				((Parishioner) wiadomosc).setData("Taki pesel juz jest");
				System.out.println("UZYCIE KOPII=======" + db.useSavePoint());
			
			} else {
				((Parishioner) wiadomosc).setQuery("OK+");
			}

			if (!s.sendObject(wiadomosc)) {
				// uzycie kopii przy bledzie dodawania (cofnie sie az do usera)
				System.out.println("UZYCIE KOPII=======" + db.useSavePoint());
			} else
				db.doCommit(); // potwierdzenie zapisu

		}// ##KONIEC BLOKU DODAWANIA DANYCH PARAFIANINA

		
		if (((Parishioner) wiadomosc).getKindQuery() == KindQuery.DEL_DBASE) {
			// ##BLOK USUWANIA DANYCH UZYTKOWNIKA(parafianin)

		}// ##KONIEC BLOKU USUWANIA DANYCH UZYTKOWNIKA(parafianin)

		
		if (((Parishioner) wiadomosc).getKindQuery() == KindQuery.UPD_DBASE) {
			// ##BLOK UAKTUALNIENIA DANYCH

		}

	}//koniec else
	}//koniec doservice
}
