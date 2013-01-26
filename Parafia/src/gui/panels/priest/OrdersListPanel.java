package gui.panels.priest;


import gui.Events;
import gui.Obserwator;
import gui.Obserwowany;
import gui.calendar.JDateChooser;
import gui.panels.LoginDialog;
import gui.panels.parishioner.OrderDialogParishioner;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;

import net.miginfocom.swing.MigLayout;
import obsluga.Event;
import obsluga.Order;
import obsluga.Parishioner;
import obsluga.Priest;
import pdf.PdfCreator;
import pomoce.Pomoc;
import stale.KindQuery;
import stale.KindRange;
import stale.KindRestriction;
import gui.calendar.JDateChooser;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

import com.itextpdf.text.DocumentException;

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
	
	private JButton btnApply;
	private ObserwatorStanuSortowania obserwatorStanuSortowania = new ObserwatorStanuSortowania(this);
	private LinkedList<Order> orderList;
	private int lastKindOrderQuery=0;//przechowuje jak byla tworzona lista z zamowineiami


	//POLA
	private JDateChooser dateFrom;
	private JDateChooser dateTo;
	private JComboBox comboStatus;
	private JComboBox comboPriest;
	private JComboBox comboType;
	private JLabel lblFrom_;
	private JLabel lblPriest_;
	private JButton btnPdf;
	private JButton btnReset;
	
	/**
	 * Create the panel.
	 */
	public OrdersListPanel(JFrame owner) {
		setBorder(new MatteBorder(2, 2, 1, 1, (Color) new Color(0, 0, 0)));
		this.owner = owner;

		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setAutoscrolls(true);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		JPanel panel_Headline = new JPanel();
		panel_Headline.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		comboStatus = new JComboBox();
		comboStatus.setModel(new DefaultComboBoxModel(new String[] {"Wszystkie","Nowe", "Zaakceptowane", "Odrzucone"}));
		comboStatus.setSelectedIndex(1);
		
		comboPriest = new JComboBox();
		//comboPriest.setModel(new DefaultComboBoxModel(new String[] {"Kazimierz Ksiazecki"}));
		
		dateFrom = new JDateChooser(true);		
		dateTo = new JDateChooser(true);
		
		/*
		 * dateFrom = new JDateChooser(true); 
		 * jest równoznaczne z
		 * dateFrom = new JDateChooser();
		 * dateFrom.setEmpty();
		 */
		
		JLabel lblFromDate_ = new JLabel("Od:");
		lblFromDate_.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblToDate_ = new JLabel("Do:");
		lblToDate_.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblStatusOrder_ = new JLabel("Status:");
		lblStatusOrder_.setHorizontalAlignment(SwingConstants.RIGHT);
		
		lblPriest_ = new JLabel("Ksi\u0105dz:");
		lblPriest_.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 605, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(7)
					.addComponent(panel_Headline, GroupLayout.PREFERRED_SIZE, 588, Short.MAX_VALUE)
					.addGap(10))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel_Headline, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE))
		);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		btnApply = new JButton("Poka\u017C");
		btnApply.setIcon(new ImageIcon(OrdersListPanel.class.getResource("/icons/get.png")));
		btnApply.addActionListener(this);
		
		JLabel lblTyp_ = new JLabel("Typ:");
		
		comboType = new JComboBox();
		
		btnPdf = new JButton("PDF");
		btnPdf.setIcon(new ImageIcon(OrdersListPanel.class.getResource("/icons/pdf24png.png")));
		btnPdf.addActionListener(this);
		
		btnReset = new JButton("Reset");
		btnReset.setIcon(new ImageIcon(OrdersListPanel.class.getResource("/icons/reset.png")));
		btnReset.addActionListener(this);
		//comboType.setModel(new DefaultComboBoxModel(new String[] {"wszystkie", "Msza", "Wypominki", "Pogrzeb"}));
		
		
		GroupLayout gl_panel_Headline = new GroupLayout(panel_Headline);
		gl_panel_Headline.setHorizontalGroup(
			gl_panel_Headline.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Headline.createSequentialGroup()
					.addGap(6)
					.addGroup(gl_panel_Headline.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblFromDate_, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblToDate_, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(gl_panel_Headline.createParallelGroup(Alignment.LEADING)
						.addComponent(dateFrom, GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
						.addComponent(dateTo, GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addGroup(gl_panel_Headline.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblStatusOrder_, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPriest_, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(gl_panel_Headline.createParallelGroup(Alignment.LEADING)
						.addComponent(comboPriest, 0, 100, Short.MAX_VALUE)
						.addComponent(comboStatus, 0, 100, Short.MAX_VALUE))
					.addGap(8)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_panel_Headline.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel_Headline.createSequentialGroup()
							.addGap(6)
							.addComponent(btnApply, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnPdf, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_Headline.createSequentialGroup()
							.addGap(6)
							.addComponent(lblTyp_, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboType, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addGap(4))
		);
		gl_panel_Headline.setVerticalGroup(
			gl_panel_Headline.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Headline.createSequentialGroup()
					.addGap(13)
					.addComponent(lblFromDate_, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addGap(7)
					.addComponent(lblToDate_, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_Headline.createSequentialGroup()
					.addContainerGap()
					.addComponent(dateFrom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(7)
					.addComponent(dateTo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
				.addGroup(gl_panel_Headline.createSequentialGroup()
					.addGap(13)
					.addComponent(lblStatusOrder_, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblPriest_, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_Headline.createSequentialGroup()
					.addContainerGap()
					.addComponent(comboStatus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(7)
					.addComponent(comboPriest, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
				.addGroup(gl_panel_Headline.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_Headline.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTyp_)
						.addComponent(comboType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_Headline.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnApply)
						.addComponent(btnPdf))
					.addGap(9))
		);
		panel_Headline.setLayout(gl_panel_Headline);
		
		//ImageIcon iconA = new ImageIcon("icons/arrow_sort_a.png");
		//ImageIcon iconB = new ImageIcon("icons/arrow_sort_b.png");
		//ImageIcon iconC = new ImageIcon("icons/arrow_sort_c.png");

		
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
			lblFrom_ = new JLabel("Od");
			if(events.getRestriction()==KindRestriction.LOGED_R)
			    lblFrom_.setText("Do");
			panel_From_.add(lblFrom_);			
			final JLabel lblFrom_Icon = new JLabel("");
			panel_From_.add(lblFrom_Icon);
			final StanuSortowania stanuSortowaniaFrom = new StanuSortowania(lblFrom_Icon, "from");
			obserwatorStanuSortowania.dodajObserwatora(stanuSortowaniaFrom);
		// ON CLICK panel_From_ 
			panel_From_.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					obserwatorStanuSortowania.powiadomObserwatorow(stanuSortowaniaFrom);
				}
			});
		
		JPanel panel_Type_ = new JPanel();
		panel_Type_.setBorder(new MatteBorder(2, 2, 2, 0, (Color) Color.DARK_GRAY));
		panel_Type_.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));		
			JLabel lblType_ = new JLabel("Typ");
			panel_Type_.add(lblType_);		
			final JLabel lblType_Icon = new JLabel("");
			panel_Type_.add(lblType_Icon);
			final StanuSortowania stanuSortowaniaType = new StanuSortowania(lblType_Icon, "type");
			obserwatorStanuSortowania.dodajObserwatora(stanuSortowaniaType);
		// ON CLICK panel_Type_ 
			panel_Type_.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					obserwatorStanuSortowania.powiadomObserwatorow(stanuSortowaniaType);
				}
			});
		
		JPanel panel_Status_ = new JPanel();
		panel_Status_.setBorder(new MatteBorder(2, 2, 2, 0, (Color) Color.DARK_GRAY));
		panel_Status_.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));		
			JLabel lblStatus_ = new JLabel("Status");
			panel_Status_.add(lblStatus_);			
			final JLabel lbl_Status_Icon = new JLabel("");
			panel_Status_.add(lbl_Status_Icon);
			final StanuSortowania stanuSortowaniaStatus = new StanuSortowania(lbl_Status_Icon, "status");
			obserwatorStanuSortowania.dodajObserwatora(stanuSortowaniaStatus);
		// ON CLICK panel_Status_ 
			panel_Status_.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					obserwatorStanuSortowania.powiadomObserwatorow(stanuSortowaniaStatus);
				}
			});
		
		JPanel panel_Data_ = new JPanel();
		panel_Data_.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(64, 64, 64)));
		panel_Data_.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));		
			JLabel lblData__ = new JLabel("Data");
			panel_Data_.add(lblData__);
			final JLabel lblData__Icon = new JLabel("");
			panel_Data_.add(lblData__Icon);
			final StanuSortowania stanuSortowaniaDate = new StanuSortowania(lblData__Icon, "date", StanuSortowania.IMG_UP);
			obserwatorStanuSortowania.dodajObserwatora(stanuSortowaniaDate);
			// ON CLICK panel_Data_ 
			panel_Data_.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					obserwatorStanuSortowania.powiadomObserwatorow(stanuSortowaniaDate);
				}
			});
		
		
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
					.addComponent(panel_Data_, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
					.addGap(8))
		);
		gl_panel_Header_.setVerticalGroup(
			gl_panel_Header_.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_From_, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
				.addComponent(panel_Type_, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
				.addComponent(panel_Status_, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
				.addComponent(panel_Data_, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
		);
		panel_Header_.setLayout(gl_panel_Header_);
		
		/*Order order = new Order();
		Parishioner sender = new Parishioner();
		sender.setName("Anna");
		sender.setSurName("Wojnar");
		order.setSender(sender);
		order.setEvent("ślub");
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
	
	protected void paintComponent(Graphics g, ImageIcon icon){
	    g.drawImage(icon.getImage(), 0, 0, null);
	}
	
	/**
	 * @param constraint - ograniczenie "[[40px:]40px[,grow]]"
	 * @return void - dodaje domyślne rozmiary w pionie dla nowego Zamówienia
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
	
	private synchronized void resetRowConstraint(){
			((MigLayout)panel_OrdersList.getLayout()).setRowConstraints("[]");
	}
	
	public void changeVisibility(){
		if(events.getRestriction()>KindRestriction.LOGED_R) dateTo.setVisible(false);

	}
	
	public void setPriestList(LinkedList<Priest> lp){
		Iterator<Priest> it = lp.iterator();
		String strPriest[] = new String[lp.size()];
		
		int index=0;
		while(it.hasNext()){
			Priest prr = it.next();
			strPriest[index] =prr.getName()+" "+prr.getSurName();
			index++;
		}
		
		comboPriest.setModel(new DefaultComboBoxModel(strPriest));
		
	}
	
	public void setOrderTypeList(LinkedList<Event> le){
		
		Iterator<Event> it = le.iterator();
		String kindOrder[] = new String[le.size()+1];
		kindOrder[0] = "Wszystkie";
		int index=1;
		  while(it.hasNext()){
			  Event ee = it.next();
			  kindOrder[index] = ee.getId()+"  "+ee.getName();
			  index++;
		  }
		
		comboType.setModel(new DefaultComboBoxModel(kindOrder));
	}
	
	public void resetFields(){
		comboPriest.setSelectedIndex(0);
		comboType.setSelectedIndex(0);
		comboStatus.setSelectedIndex(0);
		dateFrom.setEmpty();
		dateTo.setEmpty();
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
		String from;
		if(events.getRestriction()==KindRestriction.LOGED_R){
			from = order.getExecutor().getName()+" "+order.getExecutor().getSurName();

		} else {
		    from = order.getSender().getSurName()+" "+order.getSender().getName();
	
		}
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
					OrderDialogParishioner orderDialogPR = null;
					
					if(events.getRestriction()>KindRestriction.LOGED_R){
					
						if(events.getRestriction()==KindRestriction.WORKS_R){
						if(!order.getExecutroPesel().equals(events.getPriest().getPesel())){
						JOptionPane.showMessageDialog(null, "Nie masz uprawnień do modyfikownaia tego zamówienia");
						return;
						}
					}
					
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
	        			//JOptionPane.showMessageDialog(null, "ABORT Zmiany zostały odrzucone");
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
	        			loadListOrder(lastKindOrderQuery);
	        			} catch(IOException ee){
	        				
	        			} catch (ClassNotFoundException ee){
	        				
	        			}
	        		}
	        			
	        			else{
	        			JOptionPane.showMessageDialog(null, "Porzucono zmiany");
	        		}
				} else {
					if(orderDialogPR==null)
						//System.out.println("!!!Rozmiar eventList"+eventList.size());
						orderDialogPR = new OrderDialogParishioner(null, order, eventList);//dodane
					orderDialogPR.setVisible(true);
					
					if(orderDialogPR.isAborted()){
	        			//DODANE
	        			try{
		        			Order o = events.odrzucZamowienie(order);
		        			order.setStatus(o.getStatus());
		        			JOptionPane.showMessageDialog(null, events.getLastErrData());
		        			} catch (IOException ee){
		        			
		        			} catch (ClassNotFoundException ee){
		        				
		        			}
	        			//END_DODANE
	        			//JOptionPane.showMessageDialog(null, "ABORT Zmiany zostały odrzucone");
	        			lblStatus.setText(order.getStatus());
	        			//orderPanel.setBackground(new Color(240, 230, 140));
	        			Color color = new Color(240, 230, 140);
	        			panel_From.setBackground(color);
	        			panel_Type.setBackground(color);
	        			panel_Status.setBackground(color);
	        			panel_Data.setBackground(color);
	        		} else if (orderDialogPR.isDeleted()){
	        			try{
	        			Order o = events.usunZamowienie(order);
	        			JOptionPane.showMessageDialog(null, events.getLastErrData());
	        			loadListOrder(lastKindOrderQuery);
	        			} catch(IOException ee){
	        				
	        			} catch (ClassNotFoundException ee){
	        				
	        			}
	        		}
	        			
	        			else{
	        			JOptionPane.showMessageDialog(null, "Porzucono zmiany");
	        		}
					
					
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

	public boolean listOrders(int role){
		//role=0 - pobieranie zamowien nowych (przy wybraniu orderList z menu)
		//role>0 - pobranie zamowien zgodnie z polami (po nacisnieciu przycisku na formatce)
		Events events = Events.getInstance();
		orderList = null;
		try {
			if(role==0){
				//orderList = events.pobierzZamowieniaKsiedza(KindRange.PRIEST_RANG);
				
				
				if(events.getRestriction()==KindRestriction.LOGED_R){
				orderList = events.pobierzZamowieniaParafianina(); 
				comboPriest.setVisible(false);
				lblPriest_.setVisible(false);
				lblFrom_.setText("Do");
				}else {
					comboPriest.setVisible(true);
					lblPriest_.setVisible(true);
				orderList = events.pobierzZamowieniaKsiedza(KindRestriction.WORKS_R, 
							events.getPriest().getPesel(), 
							KindQuery.NEW, 
							null, 
							null, 
							0); //0=null
				}
			}
			
			if(role>0){ 
				
				if(events.getRestriction()==KindRestriction.LOGED_R){
					comboPriest.setVisible(false);
					lblPriest_.setVisible(false);
					lblFrom_.setText("Do");
					orderList = events.pobierzZamowieniaParafianina(
							this.getStatusSelected(), 
							this.getDateFrom(), 
							this.getDateTo(), 
							this.getTypeSelectedIndex()); 
					
					
					}else {
						
						comboPriest.setVisible(true);
						lblPriest_.setVisible(true);
						lblFrom_.setText("Od");
						
						orderList = events.pobierzZamowieniaKsiedza(KindRestriction.WORKS_R, 
						Pomoc.validatePriestName(events.getClient().getPriestList(),this.getPriestSelectedIndex()+1), 
						this.getStatusSelected(), 
						this.getDateFrom(), 
						this.getDateTo(), 
						this.getTypeSelectedIndex()); //0=null rodzaj eventu
					}
			}
			
			
			
			
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
	
	public void listOrdersSorted(){
		panel_OrdersList.removeAll();
		panel_1.removeAll();
		orderNumber=0;
		resetRowConstraint();
		
		Iterator<Order> iterator = orderList.iterator();

		while(iterator.hasNext()){
			addOrder(iterator.next());
		}
	}
	
	public void loadListOrder(int role){
		//Grzesiek - moje zmiany
		//role=0 - pobieranie zamowien nowych (przy wybraniu orderList z menu)
		//role>0 - pobranie zamowien zgodnie z polami (po nacisnieciu przycisku na formatce)
		panel_OrdersList.removeAll();
		panel_OrdersList.repaint();
		panel_1.removeAll();
		panel_1.repaint();

		//panel.add(panel_1);
		
		lastKindOrderQuery=role;
		orderNumber=0;
		if(!listOrders(role)){
			panel_OrdersList.removeAll();
			panel_1.removeAll();
			JLabel lblBrakZam = new JLabel("Brak zamowien");
			panel_1.add(lblBrakZam);
			panel_OrdersList.add(panel_1);
			
		}
		//end
	}
	
	public LinkedList<Order> getOrderList(){
		return orderList;
	}
	
	public void setOrderList(LinkedList<Order> orderList){
		this.orderList = orderList;
	}
	
	public void setEventList(LinkedList<obsluga.Event> e){
		eventList = e;
	}
	
	public LinkedList<obsluga.Event> getEventList(){
		return eventList;
	}
	
	/**
	 * @return <B>Date</B> - Data początkowa<br />
	 * 			<b>null</b> - data nie wybrana
	 */
	public Date getDateFrom(){
		return (!dateFrom.isEmpty())?dateFrom.getDate():null;
	}
	
	/**
	 * @return <B>Date</B> - Data końcowa<br />
	 * 			<b>null</b> - data nie wybrana
	 */
	public Date getDateTo(){
		return (!dateTo.isEmpty())?dateTo.getDate():null;
	}
	
	/**
	 * @return 	<b>KindQuery.NEW</b> jeżeli = Nowe<br />
	 * 			<b>KindQuery.ACK</b> jeżeli = Zaakceptowane<br />
	 * 			<b>KindQuery.DEN</b> jeżeli = Odrzucone<br />
	 * 			<b>""</b> 			jeżeli = Wszystkie<br />
	 * 			<b>null</b> 		jeżeli = !!Error!!<br />
	 */
	public String getStatusSelected(){
		if(comboStatus.getSelectedIndex()==1)
			return KindQuery.NEW;
		else if(comboStatus.getSelectedIndex()==2)
			return KindQuery.ACK;
		else if(comboStatus.getSelectedIndex()==3)
			return KindQuery.DEN;
		//else if(comboStatus.getSelectedIndex()<=3)
			//return "";
		else
			return null;
	}
	
	public int getStatusSelectedIndex(){
		return (comboStatus.getSelectedIndex()==-1)?1:comboStatus.getSelectedIndex();
	}
	
	public String getStatusSelectedItem(){
		return (String)comboStatus.getSelectedItem();
	}
	
	public int getPriestSelectedIndex(){
		return (comboPriest.getSelectedIndex()==-1)?0:comboPriest.getSelectedIndex();
	}
	
	public String getPriestSelectedItem(){
		return (String)comboPriest.getSelectedItem();
	}
	
	public int getTypeSelectedIndex(){
		return (comboType.getSelectedIndex()==-1)?0:comboType.getSelectedIndex();
	}
	
	public String getTypeSelectedItem(){
		return (String)comboType.getSelectedItem();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object z = e.getSource();
		
		if(z == btnApply){
			Date from = getDateFrom();
			Date to = getDateTo();
			String statusS = getStatusSelectedItem();
			int status = getStatusSelectedIndex();
			String priestS = getPriestSelectedItem();
			int priest = getPriestSelectedIndex();
			String typeS = getTypeSelectedItem();
			int type = getTypeSelectedIndex();
			
			//events.pobierzZamowienia(from, to, status, priest);
			//events.pobierzZamowienia(from, to, statusS, priestS);
			
			//setEventList(events.getClient().getEventKindList());
			loadListOrder(1); //pobiera zgodnie z polami
		}
		
		if(z==btnPdf){
			PdfCreator pdf = new PdfCreator();
			try{
			pdf.setMyEventList(events.getClient().getEventKindList());
			if(events.getRestriction()==KindRestriction.LOGED_R)
				pdf.createOwnOrderPdf(this.getOrderList(), Pomoc.saveFileWindow());
			else
			pdf.createOrderPdf(this.getOrderList(), Pomoc.saveFileWindow());
			}catch(IOException rrr){
				JOptionPane.showMessageDialog(null, "Błąd w miejscu zapisu");
			}catch(DocumentException rrr){
				JOptionPane.showMessageDialog(null, "Błąd kreatora dokumentu");

			}
		}
		
		if(z==btnReset){
			this.resetFields();
		}
	}
}













class ObserwatorStanuSortowania implements Obserwowany
{
    protected List<Obserwator> obserwatorzy = new ArrayList<Obserwator>();
    private Obserwator obserwator;
    private OrdersListPanel ordersListPanel;
 
    public ObserwatorStanuSortowania(OrdersListPanel ordersListPanel){
    	super();
    	this.ordersListPanel = ordersListPanel;
    }
    public void dodajObserwatora( Obserwator obserwator ) {
        obserwatorzy.add( obserwator );
        if(obserwator.getStan() != StanuSortowania.IMG_CLEAR)
        	this.obserwator = obserwator;
    }
 
    public void powiadomObserwatorow(Obserwator obserwator) {
		this.obserwator = obserwator;
        Iterator<Obserwator> it = obserwatorzy.iterator();
        sortOrderList();
        while( it.hasNext() )
        {
        	Obserwator actual = it.next();
        	if(actual != obserwator)
        		actual.uaktualnij(StanuSortowania.IMG_CLEAR);
        	else{
        		if(actual.getStan() == StanuSortowania.IMG_UP)
        			actual.uaktualnij(StanuSortowania.IMG_DOWN);
        		else
        			actual.uaktualnij(StanuSortowania.IMG_UP);
        	}
        }        
    }
    
    private void sortOrderList(){
    	LinkedList<Order> orderList = ordersListPanel.getOrderList();
    	Order[] orderArray = orderList.toArray(new Order[orderList.size()]);
    	//JOptionPane.showMessageDialog(null, orderArray[0].getClass());
    	Order tmp;
    	if(obserwator.getName().equals("date")){
    		if(obserwator.getStan() == StanuSortowania.IMG_UP){
		    	for(int j=0;j<orderArray.length-1;j++)
			    	for(int i=1; i<orderArray.length;i++){
			    		if(orderArray[i-1].getBeginDate().getTime() < orderArray[i].getBeginDate().getTime()){
			    			tmp = orderArray[i-1];
			    			orderArray[i-1] = orderArray[i];
			    			orderArray[i] = tmp;
			    		}
			    	}
    		} else {
		    	for(int j=0;j<orderArray.length-1;j++)
			    	for(int i=1; i<orderArray.length;i++){
			    		if(orderArray[i-1].getBeginDate().getTime() > orderArray[i].getBeginDate().getTime()){
			    			tmp = orderArray[i-1];
			    			orderArray[i-1] = orderArray[i];
			    			orderArray[i] = tmp;
			    		}
			    	}
    		}

    	} else if(obserwator.getName().equals("status")){
    		if(obserwator.getStan() == StanuSortowania.IMG_UP){
		    	for(int j=0;j<orderArray.length-1;j++)
			    	for(int i=1; i<orderArray.length;i++){
			    		if( orderArray[i-1].getStatus().compareToIgnoreCase(orderArray[i].getStatus()) < 0){
			    			tmp = orderArray[i-1];
			    			orderArray[i-1] = orderArray[i];
			    			orderArray[i] = tmp;
			    		}
			    	}
    		} else {
		    	for(int j=0;j<orderArray.length-1;j++)
			    	for(int i=1; i<orderArray.length;i++){
			    		if(orderArray[i-1].getStatus().compareToIgnoreCase(orderArray[i].getStatus()) > 0){
			    			tmp = orderArray[i-1];
			    			orderArray[i-1] = orderArray[i];
			    			orderArray[i] = tmp;
			    		}
			    	}
    		}
    	} else if(obserwator.getName().equals("type")){
    		if(obserwator.getStan() == StanuSortowania.IMG_UP){
		    	for(int j=0;j<orderArray.length-1;j++)
			    	for(int i=1; i<orderArray.length;i++){
			    		if( orderArray[i-1].getEvent().compareToIgnoreCase(orderArray[i].getEvent()) < 0){
			    			tmp = orderArray[i-1];
			    			orderArray[i-1] = orderArray[i];
			    			orderArray[i] = tmp;
			    		}
			    	}
    		} else {
		    	for(int j=0;j<orderArray.length-1;j++)
			    	for(int i=1; i<orderArray.length;i++){
			    		if(orderArray[i-1].getEvent().compareToIgnoreCase(orderArray[i].getEvent()) > 0){
			    			tmp = orderArray[i-1];
			    			orderArray[i-1] = orderArray[i];
			    			orderArray[i] = tmp;
			    		}
			    	}
    		}
    	}else if(obserwator.getName().equals("from")){
    		if(obserwator.getStan() == StanuSortowania.IMG_UP){
		    	for(int j=0;j<orderArray.length-1;j++)
			    	for(int i=1; i<orderArray.length;i++){
			    		if( orderArray[i-1].getSender().getSurName().compareToIgnoreCase(orderArray[i].getSender().getSurName()) < 0){
			    			tmp = orderArray[i-1];
			    			orderArray[i-1] = orderArray[i];
			    			orderArray[i] = tmp;
			    		}
			    	}
    		} else {
		    	for(int j=0;j<orderArray.length-1;j++)
			    	for(int i=1; i<orderArray.length;i++){
			    		if(orderArray[i-1].getSender().getSurName().compareToIgnoreCase(orderArray[i].getSender().getSurName()) > 0){
			    			tmp = orderArray[i-1];
			    			orderArray[i-1] = orderArray[i];
			    			orderArray[i] = tmp;
			    		}
			    	}
    		}
    	}
    	
    	orderList.clear();

    	for(int i=0; i<orderArray.length;i++){
    		orderList.add(orderArray[i]);
    	}
    	ordersListPanel.setOrderList(orderList);
    	ordersListPanel.listOrdersSorted();
    	
    }
 
    public void usunObserwatora( Obserwator obserwator ) {
    	obserwatorzy.remove( obserwator );
    }
    
    public Obserwator getActualObserwator(){
    	return obserwator;
    }
}

class StanuSortowania implements Obserwator
{
    public static final int IMG_CLEAR = 0;
    public static final int IMG_UP = 1;
    public static final int IMG_DOWN = 2;
	private static final ImageIcon ICON_UP = new ImageIcon("icons/sortup.png");
	private static final ImageIcon ICON_DOWN = new ImageIcon("icons/sortdown.png");
	private static final ImageIcon ICON_CLEAR = new ImageIcon("icons/sortclean.png");

    
	private String name;
	private JLabel label;
	private int stan;
 
    /**
     * @param label - JLabel gdzie ma znajdować się ikona
     * @param name - Nadana nazwa dla Stanu Sortowania
     */
    public StanuSortowania(JLabel label, String name) {
        this.name = name;
        this.label = label;
        this.uaktualnij(IMG_CLEAR);
    }
    
    /**
     * @param label - JLabel gdzie ma znajdować się ikona
     * @param name - Nadana nazwa dla Stanu Sortowania
     * @param stan - IMG_CLEAR, IMG_UP lub IMG_DOWN
     */
    public StanuSortowania(JLabel label, String name, int stan) {
    	this(label,name);
    	this.uaktualnij(stan);
    }
 
    public void uaktualnij(int stan) {
    	this.stan = stan;
    	if(stan == IMG_CLEAR)
    		label.setIcon(ICON_CLEAR);
    	else if(stan == IMG_UP)
    		label.setIcon(ICON_UP);
    	else if(stan == IMG_DOWN)
    		label.setIcon(ICON_DOWN);
    	else
    		label.setIcon(ICON_CLEAR);
    }
    
    public String getName(){
    	return name;
    }
        
    public int getStan(){
    	return stan;
    }
}
