package gui;

import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.text.*;

/**
 * Prosty zegar w Swingu
 * @author kodatnik.blogspot.com
 */

// nowa klasa Zegar zbudowana w oparciu o klas� JLabel
class Zegar extends JLabel implements Runnable {
 // w�tek
 private Thread watek;
 // liczba milisekund pauzy (1000 ms czyli 1 sekunda)
 private int pauza = 1000;

 // konstruktor klasy
 public Zegar() {
  // wyr�wnamy napisy do �rodka
  super("", SwingConstants.CENTER);
  // wybieramy font do wy�wietlenia zagara (podajemy nazw�, styl oraz rozmiar)
  setFont (new Font ("Consolas", Font.BOLD, 28));
  this.setSize(100, 50);
  setForeground(Color.BLUE);
  // ustawiamy prze�roczysto��
  setOpaque(true);
 }

 // metoda start tworzy i uruchamia w�tek zegara
 public void start() {
  // je�li nie ma dzia�aj�cego w�tka, utw�rz i uruchom nowy
  if (watek == null) {
   watek = new Thread(this);
   watek.start();
  }
 }

 // metoda wywo�ana po starcie w�tku
 public void run() {
  // dop�ki zmienna watek wskazuje na bie��cy w�tek
  while ( watek == Thread.currentThread()) {
   // nowy obiekt klasy Date
   Date time = new Date();
   // formatowanie
   DateFormat df = DateFormat.getTimeInstance(DateFormat.MEDIUM);
   // ustawiamy tekst
   setText(df.format(time));
   try {
    // wstrzymujemy dzia�anie w�tku na 1 sekund�
    watek.sleep(pauza);
   } catch (InterruptedException e) {}
  }
 }

 // metoda zatrzymuj�ca zegar (w�tek)
 public void stop() {
  // ustawiamy referencj� watek na null
  watek = null;
 }
}
