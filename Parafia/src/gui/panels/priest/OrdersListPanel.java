package gui.panels.priest;


import gui.Events;
import gui.panels.LoginDialog;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import stale.*;
import pomoce.*;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import net.miginfocom.swing.MigLayout;
import obsluga.Order;
import obsluga.Parishioner;
import java.awt.Color;

public class OrdersListPanel extends JPanel implements ActionListener {
	/**
	 * 
	 */
	
	//Grzesiek - moje zmiany
	GroupLayout gl_orderPanel;
	JPanel panel_1 = new JPanel();
	//
	
	
	private static final long serialVersionUID = 1L;
	private LoginDialog dialogLogowania;
	private JFrame owner;
	private JPanel panel;
	private int orderNumber = 0;
	private long time = System.currentTimeMillis();
	private LinkedList<obsluga.Event> eventList;
	Events events = Events.getInstance();


	/**
	 * Create the panel.
	 */
	public OrdersListPanel(JFrame owner) {
		this.owner = owner;

		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setAutoscrolls(true);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
		);
		
		panel = new JPanel();
		panel.setFont(new Font("Tekton Pro", Font.BOLD, 14));
		scrollPane.setViewportView(panel);
		panel.setLayout(new MigLayout("", "[572.00px:572.00px,grow]", "[24.00]"));
	    
		
		panel.add(panel_1, "cell 0 0,grow");
		
		JLabel lblPodwujneKliknieciOtwiera = new JLabel("Podwujne kliknieci otwiera okno z zamowieniem");
		panel_1.add(lblPodwujneKliknieciOtwiera);
		
		/*Order order = new Order();
		Parishioner sender = new Parishioner();
		sender.setName("Anna");
		sender.setSurName("Wojnar");
		order.setSender(sender);
		order.setEvent("Œlub");
		order.setStatus("Do rozpatrzenia");
		order.setBeginDate(new Date());
		order.setDescribe("wiadomosc od usera");
		addOrder(order);
		order = new Order();
		sender = new Parishioner();
		sender.setName("Adam");
		sender.setSurName("Konar");
		order.setSender(sender);
		order.setEvent("Wypominki");
		order.setStatus("Do rozpatrzenia");
		order.setBeginDate(new Date());
		order.setDescribe("wiadomosc od usera w sprawie wypominek");
		addOrder(order);*/
		
		setLayout(groupLayout);
		
		//JOptionPane.showMessageDialog(null, ((MigLayout)panel.getLayout()).getRowConstraints());
		
		//listOrders();
	}
	
	/**
	 * @param constraint - ograniczenie "[[40px:]40px[,grow]]"
	 * @return void - dodaje domyœlne rozmiary w pionie dla nowego Zamówienia
	 */
	private synchronized void addRowConstraint(String constraint){
		
		
		String rowConstraints = (String)((MigLayout)panel.getLayout()).getRowConstraints();
		//JOptionPane.showMessageDialog(null, rowConstraints);
		if(rowConstraints.equals("[]"))
			((MigLayout)panel.getLayout()).setRowConstraints( constraint );
		else
			((MigLayout)panel.getLayout()).setRowConstraints( rowConstraints);
		//rowConstraints = (String)((MigLayout)panel.getLayout()).getRowConstraints();
		//JOptionPane.showMessageDialog(null, rowConstraints);
	}
	
	
	

	public void addOrder(final Order order){
		
		orderNumber++;
		addRowConstraint("[40.00px:40.00px]");
		
		String from = order.getSender().getName()+" "+order.getSender().getSurName();
		//String from = "empty";
		String type = Pomoc.validateEventName(eventList, order.getEvent());
		String status = order.getStatus();
		Date dataTime = order.getBeginDate();
		
		final JPanel orderPanel = new JPanel();
		orderPanel.setBackground(new Color(230, 230, 250));
		orderPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.add(orderPanel, "cell 0 "+orderNumber+",grow");
		
		JLabel lblFrom = new JLabel("Od: "+from);
		JLabel lblType = new JLabel("Typ: "+type);		
		final JLabel lblStatus = new JLabel("Status: "+status);
		DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		JLabel lblData = new JLabel("Data: "+formatter.format(dataTime));
		
		orderPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				long currTime = System.currentTimeMillis();
				
				if(currTime-time < 250){
					//System.out.println("doubleClick in: "+(currTime-time));
					OrderDialog orderDialog = null;
					
					if(orderDialog==null)
						//System.out.println("!!!Rozmiar eventList"+eventList.size());
						orderDialog = new OrderDialog(null, order, eventList);//dodane
					orderDialog.setVisible(true);
					//orderDialog.setEventList(eventList);
					//orderDialog.setFocus();
	        		
	        		if(orderDialog.isAccepted()){
	        			//DODANE
	        			try{
	        			Order o = events.akceptujZamowienie(order);
	        			order.setStatus(o.getStatus());
	        			} catch (IOException ee){
	        			
	        			} catch (ClassNotFoundException ee){
	        				
	        			}
	        			//END_DODANE
	        			JOptionPane.showMessageDialog(null, "ACCEPT Zmiany zosta³y zaakceptowane");
	        			lblStatus.setText("Status: "+order.getStatus());
	        			orderPanel.setBackground(new Color(152, 251, 152));
	        		}else if(orderDialog.isAborted()){
	        			//DODANE
	        			try{
		        			Order o = events.odrzucZamowienie(order);
		        			order.setStatus(o.getStatus());
		        			} catch (IOException ee){
		        			
		        			} catch (ClassNotFoundException ee){
		        				
		        			}
	        			//END_DODANE
	        			JOptionPane.showMessageDialog(null, "ABORT Zmiany zosta³y zaakceptowane");
	        			lblStatus.setText("Status: "+order.getStatus());
	        			orderPanel.setBackground(new Color(240, 230, 140));
	        		}else{
	        			JOptionPane.showMessageDialog(null, "Porzucono zmiany");
	        		}
				}
				time = currTime;
			}
		});
		
		
		
		GroupLayout gl_orderPanel = new GroupLayout(orderPanel);
		
		gl_orderPanel.setHorizontalGroup(
			gl_orderPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_orderPanel.createSequentialGroup()
					.addGap(6)
					.addComponent(lblFrom, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblType, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblStatus, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(lblData, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
					.addGap(6))
		);
		
		gl_orderPanel.setVerticalGroup(
			gl_orderPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_orderPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_orderPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFrom)
						.addComponent(lblType)
						.addComponent(lblStatus)
						.addComponent(lblData))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		orderPanel.setLayout(gl_orderPanel);
	}

	public boolean listOrders(){
		Events events = Events.getInstance();
		LinkedList<Order> orderList = null;
		try {
			orderList = events.pobierzZamowieniaKsiedza(KindRange.PRIEST_RANG);
			//JOptionPane.showMessageDialog(null, "TEST");
			//JOptionPane.showMessageDialog(null, orderList.getFirst().getStatus());
		} catch (IOException e) {	e.printStackTrace();	} catch (ClassNotFoundException e) {	e.printStackTrace();	}
		Iterator<Order> iterator = orderList.iterator();

		while(iterator.hasNext()){ 
			Order tmp =iterator.next();
			//System.out.println(tmp.getDescribe()+"    "+tmp.getBeginDate().toLocaleString());
			
			if(tmp.getQuery().equals("ERR")) {
				JOptionPane.showMessageDialog(null, "Brak zamowien");
				return false;
			}
			addOrder(tmp);
		}
		System.out.println("Wielkosc listy zamowien "+orderList.size());
		return true;
	}
	
	public void loadListOrder(){
		//Grzesiek - moje zmiany
		panel.removeAll();
		panel_1.removeAll();
		JLabel lblPodwujneKliknieciOtwiera = new JLabel("Podwujne kliknieci otwiera okno z zamowieniem");
		panel_1.add(lblPodwujneKliknieciOtwiera);
		panel.add(panel_1);
		
		
		orderNumber=0;
		if(!listOrders()){
			panel.removeAll();
			panel_1.removeAll();
			JLabel lblBrakZam = new JLabel("Brak zamowien");
			panel_1.add(lblBrakZam);
			panel.add(panel_1);
			
		}
		//end
	}
	
	public void setEventList(LinkedList<obsluga.Event> e){
		eventList = e;
	}
	public LinkedList<obsluga.Event> getEventList(){
		return eventList;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
