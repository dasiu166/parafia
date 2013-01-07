package databaseService;

import java.io.IOException;
import java.util.LinkedList;

import obsluga.*;
import serwer.*;

public abstract class ServicePart {
	
	protected LinkedList<String[]> dbReturn; //sluzy doodpioru wynikow select z bazy
	protected LinkedList<String[]> dbReturn1; //up (tylko pomocnicza)
	protected int dbReturnInt=0;//odbior wynikow w postaci int
	
	public boolean validate(Object o){
		return false;
	}
	
	abstract public void doService(Object wiadomosc, SerwerThreadService s) throws IOException;
		
		//System.out.println("ServicePart");
		
	//}

}
