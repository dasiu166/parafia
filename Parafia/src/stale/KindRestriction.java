package stale;

public interface KindRestriction {
	/*Okresla prawa dostepu (Pole restriction w User)*/
	
	int GUEST_R = 1; //niezalogowany
	int LOGED_R = 2; //zalogowany
	int WORKS_R = 3; //pracownik
	int GOD_R = 4;// admin (proboszcz)
}
