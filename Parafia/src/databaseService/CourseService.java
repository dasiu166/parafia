package databaseService;

import obsluga.Adress;
import obsluga.Course;
import obsluga.Order;
import obsluga.User;
import database.DBManager;
import obsluga.*;
import serwer.*;
import stale.KindQuery;
import stale.KindRange;
import java.util.*;
import java.io.*;

public class CourseService extends ServicePart {

	public boolean validate(Object o) {
		if (o instanceof Course)
			return true;
		else
			return false;
	}

	public void doService(Object wiadomosc, SerwerThreadService s)
			throws IOException {
		if (!validate(wiadomosc))
			return; // wyjdz jezeli to nie twoja usluga
		else {

			if (((Course) wiadomosc).getKindQuery() == KindQuery.ADD_DBASE) {
				DBManager db = DBManager.getInstance();

				dbReturnInt = db.execUpdateQuery(((Course) wiadomosc)
						.getQuery());
				// System.out.println("^^^^^^^^^ "+((Adress)
				// wiadomosc).getQuery());

				if (dbReturnInt != 0) {
					((Course) wiadomosc).setQuery("OK+");
					dbReturn = db
							.execSelectQuery("Select seq_course.currval from dual");
					String tmp[] = dbReturn.getFirst();

					if (!tmp[0].equals("ERR")) {
						((Course) wiadomosc).setId(Integer.parseInt(tmp[0]));
					} else
						((Course) wiadomosc).setQuery("ERR");
				} else
					((Course) wiadomosc).setQuery("ERR");

				if (!s.sendObject(wiadomosc)) {
					System.out.println("UZYCIE KOPII======="
							+ db.useSavePoint());
					return;
				}
			}

			if (((Course) wiadomosc).getKindQuery() == KindQuery.UPD_DBASE) {
				
				DBManager db = DBManager.getInstance();
				dbReturnInt = db.execUpdateQuery(((Course) wiadomosc).getQuery());
				System.out.println(((Course) wiadomosc).getQuery());
				
				if (dbReturnInt != 0) {
					((Course) wiadomosc).setData("PRZEBIEG UAKTUALNIONE");
					((Course) wiadomosc).setQuery("OK+");
					//((Order) wiadomosc).setStatus(KindQuery.ACK);
					s.sendObject(wiadomosc);
					// LOG----------------------------------------------------------
					s.saveLog("Przebieg uaktualnione");
					// LOG_END------------------------------------------------------
				} else {
					((Course) wiadomosc).setData("BLAD UAKTUALNIENIA PRZEBIEGU");
					((Course) wiadomosc).setQuery("ERR");
					s.sendObject(wiadomosc);
					// LOG----------------------------------------------------------
					s.saveLog("Blad uaktualnienia przebiegu");
					// LOG_END------------------------------------------------------
				}

			}

			if (((Course) wiadomosc).getKindQuery() == KindQuery.DEL_DBASE) {

			}
		}// koniec else
	}// koniec doservice

}
