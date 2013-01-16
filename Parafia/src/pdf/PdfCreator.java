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
import javax.swing.JPanel;
import obsluga.Order;

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
	
	public void setMyEventList(LinkedList<obsluga.Event> val){
		eventList2=val;
	}
    
    public  void createOrderPdf( LinkedList<Order> orderList, String path)
    		throws DocumentException, IOException   {
    			Iterator<Order> iterator = orderList.iterator();
    			Document document = new Document();
       	        Order o = orderList.getFirst();
       	        if(!path.substring(path.length()-4).equals(".pdf")) path=path+".pdf";
    	        PdfWriter.getInstance(document, new FileOutputStream(path));
    	        document.open();
    	        document.add(new Paragraph("Lista zamowien\nks."+o.getExecutor().getName()+" "+o.getExecutor().getSurName()+"\n\n\n",new
    	        		 Font(Font.FontFamily.COURIER, 18, Font.BOLD)));
    	        Font myFont = new Font( Font.FontFamily.TIMES_ROMAN, 12,Font.BOLD );
    	    	
    	        PdfPTable table = new PdfPTable(1);
    	    	table.setWidthPercentage(95.0f);
    	    	
    	    	
    	    	
    	    /*
    			//table.addCell("Parafianin");
    	    	table.setHorizontalAlignment(Element.ALIGN_CENTER);
    	    	table.addCell(new Phrase ("Parafianin",myFont));
    			//table.setBackgroundColor(BaseColor.LIGHT_GRAY);
    			table.addCell(new Phrase ("Typ",myFont));
    			table.addCell(new Phrase ("Opis",myFont));
    			//table.addCell(new Phrase ("Status",myFont));
    			cell.setPhrase(new Phrase ("Status",myFont));
    			cell.setColspan(1);
    			table.addCell(cell);
    			table.addCell(new Phrase ("Data",myFont));
    			table.addCell(new Phrase ("Godzina",myFont));
    	    */
    			
    		    //eventList2 = jpOrdersList.getEventList();
    			int nr=0;
    			while(iterator.hasNext()){ 
    				Order tmp = iterator.next();
    				nr++;
    				String text="";
   		            String event = Pomoc.validateEventName(eventList2, tmp.getEvent());
   		            Date dataTime = tmp.getBeginDate();
  					DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
  					DateFormat formatter2 = new SimpleDateFormat("HH:mm");
   		            
   		            
   		            text = "POZYCJA "+nr+"                       DATA: "+formatter.format(dataTime)+"      GODZ: "+formatter2.format(dataTime)+"\n\n";
  	    /*Linia 2*/ text = text+"Zamawiajacy: "+tmp.getSender().getName()+" "+tmp.getSender().getSurName()+"\n\n";
    				text = text+"Rodzaj: "+event+"         Status: "+tmp.getStatus()+"\n\n";
    				text = text+"OPIS: \n";
    				text = text+tmp.getDescribe();
    				
    				PdfPCell cell = new PdfPCell();
    				cell.setPhrase(new Phrase(text));
    				//cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
    				cell.setPadding(15);
    				//cell.setBorder(10);
    				cell.setBorderWidth(2);
    				//cell.setBorderColorTop(BaseColor.WHITE);
    				//cell.setBorderColorRight(BaseColor.WHITE);
    				//cell.setBorderColorLeft(BaseColor.BLACK);
    				cell.setBorderColorBottom(BaseColor.BLACK);
    				
    				table.addCell(cell);
    				
    				PdfPCell cellLow = new PdfPCell();
    				cellLow.setPhrase(new Phrase("\n"));
    				cellLow.setBackgroundColor(BaseColor.GRAY);
    				table.addCell(cellLow);
    				
    				
    				
    				//PdfPCell cell5 = new PdfPCell (new Paragraph (tmp.getSenderPesel()));
    				//table.addCell(" "+tmp.getSender().getName()+"\n "+tmp.getSender().getSurName());
    				//ponizsza linijka nie dziala, tu sie sapie o to ze chce byc statyczna, a jak zrobie ja
    				//statyczna to sie wszysko sypie
   		            //String event = Pomoc.validateEventName(eventList2, tmp.getEvent());
    				//table.addCell(" "+tmp.getEvent());
    				//table.addCell(" "+event);
    				//table.addCell(" "+tmp.getDescribe());
    				//table.addCell(" "+tmp.getStatus());
    				
    				
    				//DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm");
    				

    				//table.addCell(" "+formatter.format(dataTime));
    				//table.addCell(" "+formatter2.format(dataTime));

    				//table.addCell(dataTime.getHours()+1+" : "+dataTime.getMinutes()+1);
    				//document.add(new Paragraph (" "+tmp.getSender().getName()+" "+tmp.getSender().getSurName()+"\n"));
    				//table.addCell(cell5);

    			}
    			document.add(table);
    			document.setPageCount(0);
    			
    	        document.close();
    	    }
    /*
    public String validateEventName2(LinkedList<obsluga.Event> list, String type){
    	
    	Iterator<obsluga.Event> it =list.iterator();
    	while(it.hasNext()){
    		obsluga.Event ee = it.next();
    		if (ee.getId()==Integer.parseInt(type))
    				return ee.getName();
    	}
    	
    	return "Inne";
    	
    }
*/
    
}


