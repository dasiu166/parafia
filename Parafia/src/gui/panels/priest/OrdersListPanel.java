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
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

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
	private JPanel panel_OrdersList;
	private int orderNumber = 0;
	private long time = System.currentTimeMillis();
	private LinkedList<obsluga.Event> eventList;
	private Events events = Events.getInstance();


	/**
	 * Create the panel.
	 */
	public OrdersListPanel(JFrame owner) {
		setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), null));
		this.owner = owner;

		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setAutoscrolls(true);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		JPanel panel_Headline = new JPanel();
		
		JLabel lblInfo_ = new JLabel("Podw\u00F3jne klikniecie otwiera okno z zamowieniem");
		lblInfo_.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_panel_Headline = new GroupLayout(panel_Headline);
		gl_panel_Headline.setHorizontalGroup(
			gl_panel_Headline.createParallelGroup(Alignment.TRAILING)
				.addComponent(lblInfo_, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE)
		);
		gl_panel_Headline.setVerticalGroup(
			gl_panel_Headline.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Headline.createSequentialGroup()
					.addComponent(lblInfo_, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(36, Short.MAX_VALUE))
		);
		panel_Headline.setLayout(gl_panel_Headline);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(7)
					.addComponent(panel_Headline, GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE)
					.addGap(23))
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 607, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel_Headline, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE))
		);
		
		panel_OrdersList = new JPanel();
		panel_OrdersList.setBorder(null);
		panel_OrdersList.setFont(new Font("Tekton Pro", Font.BOLD, 14));
		scrollPane.setViewportView(panel_OrdersList);
		panel_OrdersList.setLayout(new MigLayout("", "[572.00px:572.00px,grow]", "[]"));
		
		JPanel panel_Header_ = new JPanel();
		scrollPane.setColumnHeaderView(panel_Header_);
		
		JPanel panel_From_ = new JPanel();
		panel_From_.setBorder(new MatteBorder(2, 2, 2, 0, (Color) Color.DARK_GRAY));
		panel_From_.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblFrom_ = new JLabel("Od");
		panel_From_.add(lblFrom_);
		
		JPanel panel_Type_ = new JPanel();
		panel_Type_.setBorder(new MatteBorder(2, 2, 2, 0, (Color) Color.DARK_GRAY));
		panel_Type_.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblType_ = new JLabel("Typ");
		panel_Type_.add(lblType_);
		
		JPanel panel_Status_ = new JPanel();
		panel_Status_.setBorder(new MatteBorder(2, 2, 2, 0, (Color) Color.DARK_GRAY));
		panel_Status_.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lbl_Status_ = new JLabel("Status");
		panel_Status_.add(lbl_Status_);
		
		JPanel panel_Dtat_ = new JPanel();
		panel_Dtat_.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(64, 64, 64)));
		panel_Dtat_.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblData__ = new JLabel("Data");
		panel_Dtat_.add(lblData__);
		GroupLayout gl_panel_Header_ = new GroupLayout(panel_Header_);
		gl_panel_Header_.setHorizontalGroup(
			gl_panel_Header_.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Header_.createSequentialGroup()
					.addGap(7)
					.addComponent(panel_From_, GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
					.addGap(1)
					.addComponent(panel_Type_, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
					.addGap(1)
					.addComponent(panel_Status_, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
					.addGap(1)
					.addComponent(panel_Dtat_, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
					.addGap(8))
		);
		gl_panel_Header_.setVerticalGroup(
			gl_panel_Header_.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_From_, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
				.addComponent(panel_Type_, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
				.addComponent(panel_Status_, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
				.addComponent(panel_Dtat_, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
		);
		panel_Header_.setLayout(gl_panel_Header_);
		
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
		String rowConstraints = (String)((MigLayout)panel_OrdersList.getLayout()).getRowConstraints();
		//JOptionPane.showMessageDialog(null, rowConstraints);
		if(rowConstraints.equals("[]"))
			((MigLayout)panel_OrdersList.getLayout()).setRowConstraints( constraint );
		else
			((MigLayout)panel_OrdersList.getLayout()).setRowConstraints( rowConstraints+constraint );
		//rowConstraints = (String)((MigLayout)panel.getLayout()).getRowConstraints();
		//JOptionPane.showMessageDialog(null, rowConstraints);
	}
	

	public void addOrder(final Order order){		
		addRowConstraint("[19.00px]");
		
		Color color = new Color(128, 128, 128);
		if(order.getStatus().equals(KindQuery.ACK)){
			color = new Color(152, 251, 152);
			//color = new Color(204, 255, 153);
		}else if(order.getStatus().equals(KindQuery.DEN)){
			color = new Color(240, 230, 140);
		}
		
		final JPanel orderPanel = new JPanel();
		orderPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_OrdersList.add(orderPanel, "cell 0 "+orderNumber+",grow");		
		orderNumber++;
		
		final JPanel panel_From = new JPanel();
		panel_From.setBackground(color);
		panel_From.setBorder(new MatteBorder(0, 0, 0, 0, new Color(128, 128, 128)));		
		final JPanel panel_Type = new JPanel();
		panel_Type.setBackground(color);
		panel_Type.setBorder(new MatteBorder(0, 1, 0, 0, new Color(128, 128, 128)));		
		final JPanel panel_Status = new JPanel();
		panel_Status.setBackground(color);
		panel_Status.setBorder(new MatteBorder(0, 1, 0, 0, new Color(128, 128, 128)));		
		final JPanel panel_Data = new JPanel();
		panel_Data.setBackground(color);
		panel_Data.setBorder(new MatteBorder(0, 1, 0, 0, new Color(128, 128, 128)));
		
		//################## WYSWIETLANE POLA ######################
		String from = order.getSender().getName()+" "+order.getSender().getSurName();
		//String from = "<empty>";
		String type = Pomoc.validateEventName(eventList, order.getEvent());
		//String type = "Typ";
		String status = order.getStatus();
		Date dataTime = order.getBeginDate();
		DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		String data = formatter.format(dataTime);
		
		JLabel lblFrom = new JLabel(from);
		panel_From.add(lblFrom);		
		
		JLabel lblType = new JLabel(type);
		panel_Type.add(lblType);
		
		final JLabel lblStatus = new JLabel(status);
		panel_Status.add(lblStatus);
		
		JLabel lblData = new JLabel(data);
		panel_Data.add(lblData);
		
		//################## ON CLICK ######################
		orderPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				long currTime = System.currentTimeMillis();
				
				if(currTime-time < 500){
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
	        			JOptionPane.showMessageDialog(null, events.getLastErrData());
	        			lblStatus.setText(order.getStatus());
	        			//orderPanel.setBackground(new Color(152, 251, 152));
	        			Color color = new Color(204, 255, 153);
	        			panel_From.setBackground(color);
	        			panel_Type.setBackground(color);
	        			panel_Status.setBackground(color);
	        			panel_Data.setBackground(color);
	        		}else 
	        			
	        			if(orderDialog.isAborted()){
	        			//DODANE
	        			try{
		        			Order o = events.odrzucZamowienie(order);
		        			order.setStatus(o.getStatus());
		        			JOptionPane.showMessageDialog(null, events.getLastErrData());
		        			} catch (IOException ee){
		        			
		        			} catch (ClassNotFoundException ee){
		        				
		        			}
	        			//END_DODANE
	        			//JOptionPane.showMessageDialog(null, "ABORT Zmiany zosta³y odrzucone");
	        			lblStatus.setText(order.getStatus());
	        			//orderPanel.setBackground(new Color(240, 230, 140));
	        			Color color = new Color(240, 230, 140);
	        			panel_From.setBackground(color);
	        			panel_Type.setBackground(color);
	        			panel_Status.setBackground(color);
	        			panel_Data.setBackground(color);
	        		} else if (orderDialog.isDeleted()){
	        			try{
	        			
	        			
	        			
	        			Order o = events.usunZamowienie(order);
	        			JOptionPane.showMessageDialog(null, events.getLastErrData());
	        			loadListOrder();
	        			} catch(IOException ee){
	        				
	        			} catch (ClassNotFoundException ee){
	        				
	        			}
	        		}
	        			
	        			else{
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
					.addComponent(panel_From, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
					.addGap(1)
					.addComponent(panel_Type, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
					.addGap(1)
					.addComponent(panel_Status, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
					.addGap(1)
					.addComponent(panel_Data, GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE))
		);
		gl_orderPanel.setVerticalGroup(
			gl_orderPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_From, GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
				.addComponent(panel_Type, GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
				.addComponent(panel_Status, GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
				.addComponent(panel_Data, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
		);
		orderPanel.setLayout(gl_orderPanel);
		
	}//end addOrder()

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
		panel_OrdersList.removeAll();
		panel_1.removeAll();

		//panel.add(panel_1);
		
		
		orderNumber=0;
		if(!listOrders()){
			panel_OrdersList.removeAll();
			panel_1.removeAll();
			JLabel lblBrakZam = new JLabel("Brak zamowien");
			panel_1.add(lblBrakZam);
			panel_OrdersList.add(panel_1);
			
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
