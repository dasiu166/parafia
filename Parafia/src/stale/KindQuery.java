package stale;

public interface KindQuery {
	/*Zawiera rodzaje zapytan do bazy (pole kindOfquery w DataBaseElement)
	 * potrzebne do rozpozniania (w polaczeniu z typem obiektu) co ma zwrocic
	 * serwer i na co ma czekac klient*/
	
	/*Specjalne*/
	int TRY_LOGIN = 0; //logowanie
	int TRY_LOGOUT = -1; //wylogowanie
	
	/*Zwykle*/
	int ADD_DBASE = 1; //dodanie
	int UPD_DBASE = 2; //update 
	int DEL_DBASE = 3; //usuniecie 
	int SEL_DBASE = 4; //select

	
	/*Ta czesc zawiera komendy odpowiedzi po zapytaniach do bazy*/
	String ACK = "Zaakceptowane"; //zamowienie zaakceptowane
	String DEN = "Odrzucone"; //zamowienie odrzucone(moze byc usuniete z bazy)
	String NEW = "Nowy";//zamowienie czeka na rozpatrzenie
	String TODEL = "Do usuniecia";//do usuniecia z bazy
	
	/*Mozliwe komunikaty*/
	String ServErr = "SerwerError";
}
