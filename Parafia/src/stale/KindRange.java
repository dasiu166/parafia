package stale;

public interface KindRange {
	/*Rodzaj rangi uzytkownika (Pole range w Userr) 
	 * pierwsza cyfra odpowiada prawom tej z praw dostepu (KindRestriction)
	 * druga to wlasciwe oznaczenie
	 * trzecia to wariacja (np worker(np sekretarka) nie jest ksiedzem ale ma prawa
	 * takie jak ksiadz)*/

	int GUEST_RANG = 11;
	int LOGG_RANG = 22;
	int PRIEST_RANG = 33;
	int WORKER_RANG = 331;
	int GOD_RANG = 44;
	int ADMIN_RANG = 441;
	
	/*Ta czesc zawiera komendy odpowiedzi po zapytaniach do bazy*/
	String ACK = "ACK"; //zamowienie zaakceptowane
	String DEN = "DENIED"; //zamowienie odrzucone
	String NEW = "NEW";//zamowienie czeka na rozpatrzenie
}
