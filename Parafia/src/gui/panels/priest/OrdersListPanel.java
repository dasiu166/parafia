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

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;

import net.miginfocom.swing.MigLayout;
import obsluga.Order;
import obsluga.Parishioner;
import java.awt.Color;

public class OrdersListPanel extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LoginDialog dialogLogowania;
	private JFrame owner;
	private JPanel panel;
	private int orderNumber = 0;
	private long time = System.currentTimeMillis();

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
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, "cell 0 0,grow");
		
		JLabel lblPodwujneKliknieciOtwiera = new JLabel("Podwujne kliknieci otwiera okno z zamowieniem");
		panel_1.add(lblPodwujneKliknieciOtwiera);
		
		Order order = new Order();
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
		addOrder(order);
		
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
			((MigLayout)panel.getLayout()).setRowConstraints( rowConstraints+constraint );
		rowConstraints = (String)((MigLayout)panel.getLayout()).getRowConstraints();
		JOptionPane.showMessageDialog(null, rowConstraints);
	}
	

	public void addOrder(final Order order){
		orderNumber++;
		addRowConstraint("[40.00px:40.00px]");
		
		String from = order.getSender().getName()+" "+order.getSender().getSurName();
		String type = order.getEvent();
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
						orderDialog = new OrderDialog(null, order);
					orderDialog.setVisible(true);
					//orderDialog.setFocus();
	        		
	        		if(orderDialog.isAccepted()){
	        			JOptionPane.showMessageDialog(null, "ACCEPT Zmiany zosta³y zaakceptowane");
	        			lblStatus.setText("Status: "+order.getStatus());
	        			orderPanel.setBackground(new Color(152, 251, 152));
	        		}else if(orderDialog.isAborted()){
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

	public void listOrders(){
		Events events = Events.getInstance();
		LinkedList<Order> orderList = null;
		try {
			orderList = events.pobierzZamowieniaParafianina();
			JOptionPane.showMessageDialog(null, "TEST");
			JOptionPane.showMessageDialog(null, orderList.getFirst().getStatus());
		} catch (IOException e) {	e.printStackTrace();	} catch (ClassNotFoundException e) {	e.printStackTrace();	}
		Iterator<Order> iterator = orderList.iterator();

		while(iterator.hasNext()){ 
			Order tmp =iterator.next();
			//System.out.println(tmp.getDescribe()+"    "+tmp.getBeginDate().toLocaleString());
			addOrder(tmp);
		}
		//System.out.println(orderList.size());
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
