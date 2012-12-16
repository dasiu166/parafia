package gui;

import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import obsluga.Parishioner;
import pomoce.Pomoc;
import klient.Client;

public class Events {
	Client k = new Client();
	Parishioner p = new Parishioner();
	String adr;
	int portt;
	
	Events(){
		try {
			adr = Pomoc.loadFromFile("client.ini", "SERWERADRES");
		} catch(IOException e){e.printStackTrace();} //pobranie adresu
		try {
			portt = Integer.parseInt(Pomoc.loadFromFile("client.ini", "PORT"));
		}catch(NumberFormatException e){e.printStackTrace();}catch(IOException e){e.printStackTrace();} //pobranie portu
		
		try {
			k.setIsConnected(k.connect(adr,portt));
		} catch (UnknownHostException e) {e.printStackTrace();} catch (IOException e) {e.printStackTrace();}
		if (k.getIsConnected()==false) System.out.println("Klient - niepolaczony");
			else System.out.println("Klient - polaczony");
	}
	
	
	public boolean zaloguj(String login, String haslo){
		JOptionPane.showMessageDialog(null, "adres = "+adr+"\nport = "+portt);
		p.setPesel(login);
		p.setPass(haslo);
		p.setKindQuery(0); //przyjmijmy ze zero oznacza probe logowania
		try {
			if(!k.sendObject(p)) return false;
		} catch (IOException e){e.printStackTrace();} //wysyla sie bo wszsytkie obiekty dziedzicza po Object
		try {
			if(!k.reciveObject()) return false;
		} catch (ClassNotFoundException e){e.printStackTrace();}catch(IOException e){e.printStackTrace();}/*tu uwaga panowie bo sie blokuje az nie otrzyma jakiejs przesylki*/
		
		p = (Parishioner)k.getPackage();
		return true;
	}
	
	public boolean wyloguj(){
		p.setKindQuery(-1);
		try {
			if(!k.sendObject(p)) return false;
		} catch (IOException e) {e.printStackTrace();}
		try {
			if(!k.reciveObject()) return false;
		} catch (ClassNotFoundException e) {e.printStackTrace();} catch (IOException e) {e.printStackTrace();}
		
		p = (Parishioner)k.getPackage();
		return true;
	}
	
	public Parishioner getParishioner(){
		return p;
	}
}
