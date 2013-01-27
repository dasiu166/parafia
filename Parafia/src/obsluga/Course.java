package obsluga;

import java.util.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Course extends DataBaseElement {
	/*
	 * Zawiera przebieg sakramentow itp w karierze parafianina;) i np jak data
	 * bierzmowania bedzie pusta to nie bedzie mogl zamowic slubu itp...
	 */

	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); // parser
																	// formatu
																	// daty

	private int id;
	private Date birthday; // data urodzenia
	private Date baptism; // data chrztu
	private Date communion; // data komunii swietej
	private Date confirmation; // data bierzmowania
	private Date marriage; // data slubu
	private Date death; // data smierci

	public Course() {
		/*
		 * mozna tez zwyklymi set ustawiac poszeczgolne pola to juz Mariusz do
		 * twojej inwencji, jak ci tam wygodniej bedzie to z formatki
		 * wyprowadzic
		 */
		/*
		 * try{ birthday = format.parse("1970-01-01"); } catch(ParseException
		 * e){ System.out.println("Blad konwersji daty"); }
		 */
	}

	/*public boolean setQuery(String val) { 

		if (!this.checkQuery(val) && (!val.contains("OK+"))
				&& (!val.contains("ERR")))
			return false;
		query = val;
		return true;
	}*/

	protected boolean checkQuery(String val) {
		if ((val.length() < 6)
				|| ((!val.contains("Course")) && (!val.contains("course")) && (!val
						.contains("COURSE"))))
			return false;
		else
			return true;

	}

	public int getId() {
		return id;
	}

	public Date getBirthDay() {
		return birthday;
	}

	public Date getBaptism() {
		return baptism;
	}

	public Date getCommunion() {
		return communion;
	}

	public Date getConfirmation() {
		return confirmation;
	}

	public Date getMarriage() {
		return marriage;
	}

	public Date getDeath() {
		return death;
	}

	public void setId(int val) {
		id = val;
	}

	public boolean setBirthday(Date d) {
		birthday = d;
		return true;
	}

	public boolean setBaptism(Date d) {
		baptism = d;
		return true;
	}

	public boolean setCommunion(Date d) {
		communion = d;
		return true;
	}

	public boolean setConfirmation(Date d) {
		confirmation = d;
		return true;
	}

	public boolean setMarriage(Date d) {
		marriage = d;
		return true;
	}

	public boolean setDeath(Date d) {
		death = d;
		return true;
	}

	public static void main(String[] args) {
		/* DLA TESTOW */
		Course c = new Course();

	}
}
