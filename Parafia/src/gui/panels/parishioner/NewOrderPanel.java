package gui.panels.parishioner;

import gui.Events;
import gui.calendar.JDateChooser;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JTextPane;
import java.util.*;
import obsluga.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class NewOrderPanel extends JPanel implements ActionListener {
	private JComboBox comboOrderType;
	private JTextPane textMessage;
	private JDateChooser dateStart;
	private JComboBox comboHour;
	private JButton btnSend;
	private JButton btnReset;
	private Events events = Events.getInstance();
	/**
	 * Create the panel.
	 */
	public NewOrderPanel() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JLabel lblOrderType_ = new JLabel("Typ Zamowienia:");
		lblOrderType_.setHorizontalAlignment(SwingConstants.RIGHT);
		
		comboOrderType = new JComboBox();
		/*Grzesiek*/
		
		JLabel lblDateStart_ = new JLabel("Data:");
		lblDateStart_.setHorizontalAlignment(SwingConstants.RIGHT);
		
		dateStart = new JDateChooser(true);
		
		btnSend = new JButton("Wy\u015Blij");
		btnSend.addActionListener(this);
		
		btnReset = new JButton("Reset");
		btnReset.addActionListener(this);
		
		JLabel lblMessage_ = new JLabel("Wiadomo\u015B\u0107:");
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JLabel lblHour_ = new JLabel("Godzina");
		
		comboHour = new JComboBox();
		comboHour.addItem("<Wybierz>");
		comboHour.addItem("08:00");
		comboHour.addItem("10:00");
		comboHour.addItem("12:00");
		comboHour.addItem("14:00");
		comboHour.addItem("16:00");
	
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(190)
							.addComponent(btnReset, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
							.addGap(3)
							.addComponent(btnSend, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(38)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(panel, GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
								.addComponent(lblMessage_, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))))
					.addGap(40))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(65)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblOrderType_, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(comboOrderType, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(70)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblDateStart_, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblHour_))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(comboHour, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
								.addComponent(dateStart, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(71, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(51)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblOrderType_, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboOrderType, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(13)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(dateStart, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDateStart_, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(67)
							.addComponent(lblMessage_, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblHour_)
								.addComponent(comboHour, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnReset)
						.addComponent(btnSend))
					.addGap(29))
		);
		
		textMessage = new JTextPane();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(textMessage, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(textMessage, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
		);
		panel.setLayout(gl_panel);
		setLayout(groupLayout);

	}
	
	public void setOrderList(LinkedList<Event> le){
		
		Iterator<Event> it = le.iterator();
		String kindOrder[] = new String[le.size()+1];
		kindOrder[0] = "<wybierz Typ>";
		int index=1;
		  while(it.hasNext()){
			  Event ee = it.next();
			  kindOrder[index] = ee.getId()+"  "+ee.getName();
			  index++;
		  }
		
		comboOrderType.setModel(new DefaultComboBoxModel(kindOrder));
	}
	
	public int getSelectedOrderItemIndex(){
		return comboOrderType.getSelectedIndex();
	}
	
	public String getSelectedOrderItem(){
		return (String)comboOrderType.getSelectedItem();
	}
	
	public Date getDateStart(){
		return (!dateStart.isEmpty())?dateStart.getDate():null;
	}
	
	public int getSelectedHourIndex(){
		return comboHour.getSelectedIndex();
	}
	
	public String getSelectedHour(){
		return (String)comboHour.getSelectedItem();
	}
	
	public String getMessage(){
		return textMessage.getText();
	}
	
	public void reset(){
		comboOrderType.setSelectedIndex(0);
		dateStart.setEmpty();
		comboHour.setSelectedIndex(0);
		textMessage.setText("");
	}
	
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object z = arg0.getSource();
		
		if(z==btnSend){
			//events.newOrder(getSelectedOrderItemIndex(),getDateStart(),getSelectedHour(),getMessage());
		}else if(z==btnReset){
			reset();
		}
		
	}
}
