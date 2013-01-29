package pdf;

import java.util.*;
import java.awt.Color;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.GroupLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import obsluga.Course;
import obsluga.Order;
import obsluga.Person;
import obsluga.Priest;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.Font;

import com.itextpdf.text.*;
import pomoce.*;
import obsluga.Actuals;
import gui.Events;
import gui.CardLayoutExp;
import java.util.Iterator;
import java.util.LinkedList;
import obsluga.Parishioner;
import gui.panels.LoginDialog;
import gui.panels.priest.OrderDialog;

public class PdfCreator {

	GroupLayout gl_orderPanel;
	JPanel panel_1 = new JPanel();

	private LinkedList<obsluga.Event> eventList2;

	public void setMyEventList(LinkedList<obsluga.Event> val) {
		eventList2 = val;
	}
	
	public void createPersonDataPdf(LinkedList<Person> pctl, String path)
			throws DocumentException, IOException{
		
		Document document = new Document();

		if (!path.substring(path.length() - 4).equals(".pdf"))
			path = path + ".pdf";
		PdfWriter.getInstance(document, new FileOutputStream(path));

		document.open();
		Iterator<Person> iterator = pctl.iterator();
		
		Paragraph paragraph2 = new Paragraph(" DANE OSOBOWE\n\n",
				new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD));

		paragraph2.setAlignment(Element.ALIGN_CENTER);
		
		document.add(paragraph2); 
		PdfPTable table = new PdfPTable(1); // Code 1
		
		
		
		PdfPCell odstep = new PdfPCell(new Paragraph("  "));
		//odstep.setBackgroundColor(BaseColor.LIGHT_GRAY);
		odstep.setBorderWidth(1f);
		
        table.setSpacingAfter(10); 
        table.setSpacingBefore(10);
        Font myFont = new Font(Font.FontFamily.HELVETICA, 15, Font.NORMAL);
        Font myFont2 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
        
        while(iterator.hasNext()){
        	Person s = iterator.next();
        	PdfPCell cell = new PdfPCell();
        	PdfPCell cell2 = new PdfPCell();
        	String text = "";
			DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
			
			text = "DANE -- "+s.getName()+" "+s.getSurName()+"        PESEL  "+s.getPesel()+"\n" +
					"\n ADRES -- " +
					s.getAdress().getCity()+"   ul. "+s.getAdress().getStreet()+"  "+
					s.getAdress().getHouse()+"\n\n";
			
			if(s instanceof Parishioner){
				Course c = ((Parishioner) s).getCourse();
				                           text=text+" Data urodzenia:   ---    "+c.getBirthDay().toLocaleString().substring(0, 10);
				if(c.getBaptism()!=null) text=text+"\n Chrzest:          ---    "+c.getBaptism().toLocaleString().substring(0,10);
			  if(c.getCommunion()!=null) text=text+"\n Komunia œw:       ---    "+c.getCommunion().toLocaleString().substring(0,10);
		   if(c.getConfirmation()!=null) text=text+"\n Bierzmowanie:     ---    "+c.getConfirmation().toLocaleString().substring(0,10);
		       if(c.getMarriage()!=null) text=text+"\n Slub:             ---    "+c.getMarriage().toLocaleString().substring(0,10);
				  if(c.getDeath()!=null) text=text+"\n Smierc:           ---    "+c.getDeath().toLocaleString().substring(0,10);

			
			
			}
			
			if(s instanceof Priest){
				if(((Priest) s).getArrivalDate()!=null) text=text+"\n   Przybycie:   ---    "+((Priest) s).getArrivalDate().toLocaleString().substring(0,10);
			 if(((Priest) s).getSecularityDate()!=null) text=text+"\n   Swiecenia:   ---    "+((Priest) s).getSecularityDate().toLocaleString().substring(0,10);

			}
			text=text+"\n_";
            cell.setPhrase(new Phrase(text,myFont2));

			 table.addCell(cell);
			 table.addCell(odstep);
			
        	
        	
        	
        }
        document.add(table);

        document.close();
        
		
		
	}

	public  boolean createActualsPdf( LinkedList<Actuals> actL, String path)

            throws DocumentException, IOException   {
			Document document = new Document();
			
			if(path==null) return false;
			if(actL.getFirst().getQuery().equals("ERR")){
				JOptionPane.showMessageDialog(null, "Lista aktualnoœci pusta");
				return false;
			}
			
			if(path.equals("null")){
				JOptionPane.showMessageDialog(null, "Anulowano");
				return false;
			}
			
			if (!path.substring(path.length() - 4).equals(".pdf"))
				path = path + ".pdf";
			PdfWriter.getInstance(document, new FileOutputStream(path));

			document.open();
			Iterator<Actuals> iterator = actL.iterator();
			Actuals act = actL.getFirst();

			Paragraph paragraph2 = new Paragraph(" AKTUALNOSCI   PARAFIALNE\n\n",
					new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD));

			paragraph2.setAlignment(Element.ALIGN_CENTER);
			
			document.add(paragraph2); 
			PdfPTable table = new PdfPTable(1); // Code 1
			
			
			PdfPCell odstep = new PdfPCell(new Paragraph("  "));
			//odstep.setBackgroundColor(BaseColor.LIGHT_GRAY);
			odstep.setBorderWidth(0f);
			
            table.setSpacingAfter(10); 
            table.setSpacingBefore(10);
            Font myFont = new Font(Font.FontFamily.HELVETICA, 15, Font.NORMAL);
            Font myFont2 = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL);
            while(iterator.hasNext()){ 
            	PdfPCell cell = new PdfPCell();
            	PdfPCell cell2 = new PdfPCell();
    			
              Actuals tmp = iterator.next();
              
              PdfPTable tableS = new PdfPTable(1);
              String text="";
              
              text = tmp.getSubject();
              cell2.setPhrase(new Phrase(text,myFont));
              cell2.setBorderWidth(0f);
              tableS.addCell(cell2);
              table.addCell(tableS);
              
              
              text = tmp.getName()+" "+tmp.getSurName()+"   Data dodania: "+tmp.getAddDate().toLocaleString().substring(0,10);
              text = text+"\n\n"+tmp.getDescribe();
              cell.setPhrase(new Phrase(text,myFont2));

              table.addCell(cell);

              //table.addCell(""+formatter.format(dataTime)+"\n"+tmp.getName()+" "+tmp.getSurName());
              //table.addCell(""+tmp.getDescribe());

             table.addCell(odstep);
}

document.add(table);

document.close();

return true;

}

	public boolean createOrderPdf(LinkedList<Order> orderList, String path)
			throws DocumentException, IOException {
		Iterator<Order> iterator = orderList.iterator();
		Document document = new Document();
		Order o = orderList.getFirst();
		
		if(path.equals("null")){
			JOptionPane.showMessageDialog(null, "Anulowano");
			return false;
		}
		
		if (!path.substring(path.length() - 4).equals(".pdf"))
			path = path + ".pdf";
		PdfWriter.getInstance(document, new FileOutputStream(path));
		document.open();
		document.add(new Paragraph("Lista zamowien\nks."
				+ o.getExecutor().getName() + " "
				+ o.getExecutor().getSurName() + "\n\n\n", new Font(
				Font.FontFamily.COURIER, 18, Font.BOLD)));
		Font myFont = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL);

		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(95.0f);
	
		int nr = 0;
		while (iterator.hasNext()) {
			Order tmp = iterator.next();
			nr++;
			String text = "";
			String event = Pomoc.validateEventName(eventList2, tmp.getEvent());
			Date dataTime = tmp.getBeginDate();
			DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
			DateFormat formatter2 = new SimpleDateFormat("HH:mm");

			text = "POZYCJA " + nr + "                                                 "
					+ formatter.format(dataTime) + " -- godz: "
					+ formatter2.format(dataTime) + "\n\n";
		    text = text + "Zamawiajacy: "
					+ tmp.getSender().getName() + " "
					+ tmp.getSender().getSurName() + "  --- ";
			text = text + "Rodzaj: " + event + " --- Status: "
					+ tmp.getStatus() + "\n\n";
			text = text + "OPIS: \n";
			text = text + tmp.getDescribe();

			PdfPCell cell = new PdfPCell();
			cell.setPhrase(new Phrase(text,myFont));
			
			
			cell.setPadding(15);
			cell.setBorderWidth(1);
			
			cell.setBorderColorBottom(BaseColor.BLACK);

			table.addCell(cell);

			PdfPCell cellLow = new PdfPCell();
			cellLow.setPhrase(new Phrase("\n"));
			cellLow.setBackgroundColor(BaseColor.GRAY);
			table.addCell(cellLow);

			

		}
		document.add(table);
		document.setPageCount(0);

		document.close();
		return true;
	}
	
	public boolean createOwnOrderPdf(LinkedList<Order> orderList, String path)
			throws DocumentException, IOException {
		Iterator<Order> iterator = orderList.iterator();
		Document document = new Document();
		Order o = orderList.getFirst();
		
		if(path.equals("null")){
			JOptionPane.showMessageDialog(null, "Anulowano");
			return false;
		}
		
		if (!path.substring(path.length() - 4).equals(".pdf"))
			path = path + ".pdf";
		
		PdfWriter.getInstance(document, new FileOutputStream(path));
		document.open();
		document.add(new Paragraph("Lista zamowien\n"
				+ o.getSender().getName() + " "
				+ o.getSender().getSurName() + "\n\n\n", new Font(
				Font.FontFamily.COURIER, 18, Font.BOLD)));
		Font myFont = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL);

		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(95.0f);
	
		int nr = 0;
		while (iterator.hasNext()) {
			Order tmp = iterator.next();
			nr++;
			String text = "";
			String event = Pomoc.validateEventName(eventList2, tmp.getEvent());
			Date dataTime = tmp.getBeginDate();
			DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
			DateFormat formatter2 = new SimpleDateFormat("HH:mm");

			text = "POZYCJA " + nr + "                                                 "
					+ formatter.format(dataTime) + " -- godz: "
					+ formatter2.format(dataTime) + "\n\n";
		    text = text + "Odprawiaj¹cy: "
					+ tmp.getExecutor().getName() + " "
					+ tmp.getExecutor().getSurName() + "  --- ";
			text = text + "Rodzaj: " + event + " --- Status: "
					+ tmp.getStatus() + "\n\n";
			text = text + "OPIS: \n";
			text = text + tmp.getDescribe();

			PdfPCell cell = new PdfPCell();
			cell.setPhrase(new Phrase(text,myFont));
			
			
			cell.setPadding(15);
			cell.setBorderWidth(1);
			
			cell.setBorderColorBottom(BaseColor.BLACK);

			table.addCell(cell);

			PdfPCell cellLow = new PdfPCell();
			cellLow.setPhrase(new Phrase("\n"));
			cellLow.setBackgroundColor(BaseColor.GRAY);
			table.addCell(cellLow);

			

		}
		document.add(table);
		document.setPageCount(0);

		document.close();
	return true;
	}
	

}
