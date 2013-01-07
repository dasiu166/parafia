package databaseService;

import obsluga.Adress;
import obsluga.Event;
import obsluga.User;
import database.DBManager;
import obsluga.*;
import serwer.*;
import stale.KindQuery;
import stale.KindRange;
import java.util.*;
import java.io.*;

public class EventService extends ServicePart {

	public boolean validate(Object o) {
		if (o instanceof Event)
			return true;
		else
			return false;
	}

	public void doService(Object wiadomosc, SerwerThreadService s) throws IOException {
		if (!validate(wiadomosc)) return; //wyjdz jezeli to nie twoja usluga
		else{
		
		if (((Event) wiadomosc).getKindQuery() == KindQuery.SEL_DBASE) {
			// ##BLOK POBRANIA ZDARZEN
			DBManager db = DBManager.getInstance();

			dbReturn = db.execSelectQuery(((Event) wiadomosc).getQuery());
			LinkedList<Event> eventList = new LinkedList<Event>();
			Iterator<String[]> it = dbReturn.iterator();

			while (it.hasNext()) {
				String[] tmp = it.next();
				Event e = new Event();
				if (!tmp[0].equals("ERR")) {
					e.setId(Integer.parseInt(tmp[0]));
					e.setName(tmp[1]);
					e.setDescribe(tmp[2]);
					e.setQuery("OK+");
					eventList.add(e);
				} else {
					e.setQuery("ERR");
					eventList.add(e);
					break;
				}
			}
			s.sendObject(eventList);

		}// ##KONIEC BLOKU POBIERANIA ZDARZEN

		if (((Event) wiadomosc).getKindQuery() == KindQuery.DEL_DBASE) {

		}

		if (((Event) wiadomosc).getKindQuery() == KindQuery.ADD_DBASE) {

		}

		if (((Event) wiadomosc).getKindQuery() == KindQuery.UPD_DBASE) {

		}

	}//koniec else
	}//koniec doservice
}
