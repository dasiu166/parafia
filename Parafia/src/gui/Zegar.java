package gui;

import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.text.*;

/**
 * Prosty zegar w Swingu
 * @author kodatnik.blogspot.com
 */

// nowa klasa Zegar zbudowana w oparciu o klasê JLabel
class Zegar extends JLabel implements Runnable {
 // w¹tek
 private Thread watek;
 // liczba milisekund pauzy (1000 ms czyli 1 sekunda)
 private int pauza = 1000;

 // konstruktor klasy
 public Zegar() {
  // wyrównamy napisy do œrodka
  super("", SwingConstants.CENTER);
  // wybieramy font do wyœwietlenia zagara (podajemy nazwê, styl oraz rozmiar)
  setFont (new Font ("Consolas", Font.BOLD, 28));
  this.setSize(100, 50);
  setForeground(Color.BLUE);
  // ustawiamy przeŸroczystoœæ
  setOpaque(true);
 }

 // metoda start tworzy i uruchamia w¹tek zegara
 public void start() {
  // jeœli nie ma dzia³aj¹cego w¹tka, utwórz i uruchom nowy
  if (watek == null) {
   watek = new Thread(this);
   watek.start();
  }
 }

 // metoda wywo³ana po starcie w¹tku
 public void run() {
  // dopóki zmienna watek wskazuje na bie¿¹cy w¹tek
  while ( watek == Thread.currentThread()) {
   // nowy obiekt klasy Date
   Date time = new Date();
   // formatowanie
   DateFormat df = DateFormat.getTimeInstance(DateFormat.MEDIUM);
   // ustawiamy tekst
   setText(df.format(time));
   try {
    // wstrzymujemy dzia³anie w¹tku na 1 sekundê
    watek.sleep(pauza);
   } catch (InterruptedException e) {}
  }
 }

 // metoda zatrzymuj¹ca zegar (w¹tek)
 public void stop() {
  // ustawiamy referencjê watek na null
  watek = null;
 }
}
